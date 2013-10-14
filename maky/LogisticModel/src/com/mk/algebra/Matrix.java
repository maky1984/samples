package com.mk.algebra;


/**
 * Represents matrix for linear regression and logical regression model
 * plus helper functions for these models
 * 
 * TODO: optimization tip - remove matrix creation for each operation, need refactoring of the model class 
 * 
 * @author mkotlyar
 *
 */
public class Matrix {
	
	public static class NormalizationObject {
		double average;
		double deviation;
		
		public NormalizationObject(double average, double deviation) {
			this.average = average;
			this.deviation = deviation;
		}
	}

	private final double data[][];
	private final int n;
	private final int m;
	
	public Matrix(double[] data) {
		this.n = data.length;
		this.m = 1;
		this.data = new double[n][m];
		for (int i=0;i<n;i++) {
			this.data[i][0] = data[i];
		}
	}
	
	public Matrix(double[][] data) {
		this.data = data;
		n = data.length;
		checkSize(n);
		m = data[0].length;
		checkSize(m);
	}

	public Matrix(int n, int m) {
		checkSize(n);
		checkSize(m);
		this.n = n;
		this.m = m;
		this.data = new double[n][m];
	}
	
	public Matrix(int n, int m, double defaultValue) {
		this(n, m);
		reset(defaultValue);
	}


	private void checkSize(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("One of matrix size is zero");
		}
	}
	
	/**
	 * Returns new matrix result = current_matrix + alpha * matrix 
	 * @param matrix
	 * @param alpha
	 * @return
	 */
	public Matrix linearOperation(Matrix matrix, double alpha) {
		final int ADDITION = 1;
		final int SUBTRACTION = -1;
		final int DEFAULT = 0;
		int operation;
		if (alpha == 1) {
			operation = ADDITION;
		} else if (alpha == -1) {
			operation = SUBTRACTION;
		} else {
			operation = DEFAULT;
		}
		Matrix result = new Matrix(n, m);
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				switch (operation) {
				case SUBTRACTION:
					result.data[i][j] = data[i][j] - matrix.data[i][j];
					break;
				case ADDITION:
					result.data[i][j] = data[i][j] + matrix.data[i][j];
					break;
				default:
					result.data[i][j] = data[i][j] + alpha * matrix.data[i][j];
					break;
				}
			}
		}
		return result;
	}
	
	private static double sigmoid(double x) {
		double h = 1/(1 + Math.exp(-x));
		return h;
	}
	
	public Matrix sigmoidOperation() {
		Matrix result = new Matrix(n,m);
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				result.data[i][j] = sigmoid(data[i][j]);
			}
		}
		return result;
	}
		
	public Matrix add(Matrix matrix) {
		return linearOperation(matrix, 1);
	}
	
	public Matrix subtraction(Matrix matrix) {
		return linearOperation(matrix, -1);
	}
	
	public Matrix multiply(double scalar) {
		Matrix multiply = new Matrix(n,m);
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				multiply.data[i][j] = data[i][j] * scalar;
			}
		}
		return multiply;
	}
	
	public Matrix multiply(Matrix matrix) {
		if (m != matrix.n) {
			throw new IllegalArgumentException("Cant multiply matrix with sizes:" + n +" x " + m + " and " + matrix.n + " x " + matrix.m);
		}
		Matrix result = new Matrix(n, matrix.m);
		for (int i=0;i<n;i++) {
			for(int j=0;j<matrix.m;j++) {
				for(int k=0;k<m;k++) {
					result.data[i][j] += data[i][k] * matrix.data[k][j];
				}
			}
		}
		return result;
	}
	
	public Matrix transpose() {
		Matrix result = new Matrix(m, n);
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				result.data[j][i] = data[i][j]; 
			}
		}
		return result;
	}
	
	public Matrix addRow(double defaultValue) {
		Matrix result = new Matrix(n + 1,m);
		for(int j=0;j<m;j++) {
			result.data[0][j] = defaultValue;
		}
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				result.data[i + 1][j] = data[i][j];
			}
		}
		return result;
	}
	
	public int getN() {
		return n;
	}
	
	public int getM() {
		return m;
	}
	
	public double[][] getData() {
		return data;
	}
	
	public Matrix normalization(NormalizationObject[] normalizationObjects) {
		Matrix normMatrix = new Matrix(getN(), getM());
		for(int i=0;i<data.length;i++) {
			normMatrix.data[i] = normalization(data[i], normalizationObjects[i]);
		}
		return normMatrix;
	}
	
	public NormalizationObject[] calculateNormalizationObjects() {
		NormalizationObject[] result = new NormalizationObject[data.length];
		for(int i=0;i<data.length;i++) {
			result[i] = calculateNormalizationObject(data[i]);
		}
		return result;
	}
	
	private static NormalizationObject calculateNormalizationObject(double[] x) {
		double max = x[0], min = x[0], sum = x[0];
		for(int i=1;i<x.length;i++) {
			if (max < x[i]) {
				max = x[i];
			} else if (min > x[i]) {
				min = x[i];
			}
			sum += x[i];
		}
//		NormalizationObject result = new NormalizationObject(0,1);
		NormalizationObject result = new NormalizationObject(sum / x.length, max - min);
		return result;
	}
	
	private static double[] normalization(double[] x, NormalizationObject normalizationObject) {
		double[] res = new double[x.length];
		for(int i=0;i<x.length;i++) {
			res[i] = (x[i] - normalizationObject.average) / normalizationObject.deviation;
		}
		return res;
	}

	
	public double norm() {
		double result;
		if (n == 1 && m == 1) {
			result = data[0][0];
		} else if (m == 1) {
			result = 0;
			for (int i=0;i<n;i++) {
				result += data[i][0] * data[i][0];
			}
			result = Math.sqrt(result);
		} else {
			// TODO: add descriminant calculation maybe
			result = -1;
		}
		return result;
	}
	
	private void reset(double defaultValue) {
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				data[i][j] = defaultValue;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("Matrix " + n + " x " + m + " dimension:\r\n");
		for (int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				buffer.append(data[i][j] + " ");
			}
			buffer.append("\r\n");
		}
		return buffer.toString();
	}
}
