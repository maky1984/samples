package com.mk.model;

import com.mk.algebra.Matrix;
import com.mk.algebra.Matrix.NormalizationObject;
import com.mk.algebra.Vector;

public class LogisticRegressionModel {

	public final double ALPHA = 0.00005; // gradient descent scale factor, learning
										// rate
	public final double ACCURACY = 0.000000001;

	private Matrix originalX;
	private Matrix x;
	private Vector y;
	private Vector teta;
	private NormalizationObject[] normalizationObjects;
	private int gradientDescentCounter;

	public LogisticRegressionModel() {
	}
	
	public LogisticRegressionModel(double[][] data, double[] y) {
		setTrainingSet(data, y);
	}

	/**
	 * Training set is the set of input values and corresponding result y of
	 * this linear regression model If data dimension is m x n, then m is number
	 * of training examples and n is number of parameters in the model. Then y
	 * should have dimension m according to data array.
	 * 
	 * @param data
	 * @param y
	 */
	public void setTrainingSet(double[][] data, double[] y) {
		setTrainingSet(new Matrix(data), new Vector(y));
	}

	/**
	 * {@link #setTrainingSet(double[][], double[])}
	 * 
	 * @param data
	 * @param y
	 */
	public void setTrainingSet(Matrix data, Vector y) {
		originalX = data;
		System.out.println("X before normalization: " + data);
		normalizationObjects = data.calculateNormalizationObjects();
		Matrix normalizedMatrix = data.normalization(normalizationObjects);
		System.out.println("X after normalization: " + normalizedMatrix);
		this.x = normalizedMatrix.addRow(1).transpose();
		this.y = y;
		calculateTeta();
	}

	private Vector gradientDescent() {
		Vector newTeta = new Vector(teta.linearOperation(x.transpose().multiply(x.multiply(teta).sigmoidOperation().subtraction(y)), -ALPHA/x.getM()));
		return newTeta;
	}
	
	public Vector hypotesis(Matrix input) {
		return new Vector(input.normalization(normalizationObjects).addRow(1).transpose().multiply(teta).sigmoidOperation());
	}
	
	public Vector modelCalculatedTrainingResult() {
		return hypotesis(originalX);
	}

	private void calculateTeta() {
		teta = new Vector(x.getM());
		Vector newTeta = teta;
		gradientDescentCounter = 0;
		do {
			gradientDescentCounter++;
			teta = newTeta;
			newTeta = gradientDescent();
//			System.out.println("New teta: " + newTeta);
		} while (teta.subtraction(newTeta).norm() > ACCURACY);
	}
	
	public int getGradientDescentCounter() {
		return gradientDescentCounter;
	}
	
	public Vector getCoefficients() {
		return teta;
	}

}
