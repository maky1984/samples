package com.mk.model;

import com.mk.algebra.Matrix;
import com.mk.algebra.Matrix.NormalizationObject;
import com.mk.algebra.Vector;

/**
 * This class implements Linear regression model.
 * Based on description from Coursera {@link https://share.coursera.org/wiki/index.php/ML:Linear_Regression_with_Multiple_Variables }}
 * @author mkotlyar
 *
 */
public class LinearRegressionModel {

	public final double ALPHA = 0.1; // gradient descent scale factor, learning rate
	public final double ACCURACY = 0.001;
	
	private Matrix originalX;
	private Matrix x;
	private Vector y;
	private Vector teta;
	private NormalizationObject[] normalizationObjects;
	private int gradientDescentCounter;
	
	/**
	 * Creates empty linear regression model.
	 * You should set training set for the model using {@link #setTrainingSet(double[][], double[])} function
	 */
	public LinearRegressionModel() {
	}

	/**
	 * {@link #setTrainingSet(double[][], double[])}
	 * @param data
	 * @param y
	 */
	public LinearRegressionModel(double[][] data, double[] y) {
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
//		System.out.println("Y before normalization: " + y);
		this.y = y;
//		this.y = new Vector(y.transpose().normalization(y.transpose().calculateNormalizationObjects()).transpose());
//		System.out.println("Y after normalization: " + this.y);
		gradientDescent();
		
		System.out.println("Gradient descent teta:\r\n " + teta);
		System.out.println("Conjured descent start");
		System.out.println("Conjured descent teta:\r\n " + conjuredDescent());
	}
	
	/**
	 * Calculates h(x) = teta0 + x1*teta1 + x2*teta2 + ... + xN*tetaN
	 * 
	 * @param alpha
	 * @param x
	 * @param teta
	 * @return h(x)
	 */
	public Vector hypotesis(Matrix input) {
		return new Vector(input.normalization(normalizationObjects).addRow(1).transpose().multiply(teta));
	}
	
	public Vector modelCalculatedTrainingResult() {
		return hypotesis(originalX);
	}
	
	/**
	 * Resolution of XQ-Y -> 0 using gradient descent
	 */
	private void gradientDescent() {
		teta = new Vector(x.getM());
		Vector newTeta = teta;
		gradientDescentCounter = 0;
		do {
			gradientDescentCounter++;
			teta = newTeta;
			newTeta = new Vector(teta.linearOperation(x.transpose().multiply(x.multiply(teta).subtraction(y)), -ALPHA/x.getN()));
		} while (teta.subtraction(newTeta).norm() > ACCURACY);
	}
	
	private Vector conjuredDescent() {
//		System.out.println("x = " + x);
//		System.out.println("y = " + y);
		Matrix A = x.transpose().multiply(x).multiply(1.0/x.getN());
//		System.out.println("A = " + A);
		Vector b = new Vector(x.transpose().multiply(y).multiply(1.0/x.getN()));
//		System.out.println("b = " + b);
		Vector currentTeta = new Vector(x.getM());
		System.out.println("currentTeta = " + currentTeta);
		Vector oldTeta = new Vector(currentTeta);
		Vector r = new Vector(b.subtraction(A.multiply(currentTeta)));
//		System.out.println("r0 = " + r);
		Vector s = new Vector(r);
		int counter = 0;
		do {
//			System.out.println("s = " + s);
			double lambda = - s.scalarProduct(r) / s.scalarProduct(new Vector(A.multiply(s)));
//			System.out.println("lambda = " + lambda);
			currentTeta = new Vector(currentTeta.add(s.multiply(-lambda)));
			System.out.println("currentTeta = " + currentTeta);
			r = new Vector(r.add(A.multiply(s).multiply(lambda)));
//			System.out.println("r = " + r);
			double beta = - r.scalarProduct(new Vector(A.multiply(s)))/ s.scalarProduct(new Vector(A.multiply(s)));
			s = new Vector(r.add(s.multiply(beta)));
		} while (/*counter++ < x.getN()*/ oldTeta.subtraction(currentTeta).norm() > ACCURACY);
		return currentTeta;
	}

	public int getGradientDescentCounter() {
		return gradientDescentCounter;
	}
	
	public Vector getCoefficients() {
		return teta;
	}
}
