package com.mk.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mk.algebra.Matrix;
import com.mk.algebra.Vector;
import com.mk.model.LinearRegressionModel;
import com.mk.model.LogisticRegressionModel;

public class TestModel {
	private static final double[][] TEST_DATA1 = { { 10, 14, 21, 23, 27, 32,
			39, 45, 55, 61, 62, 68 } };
	private static final double[] TEST_Y1 = { 3.8, 4.8, 5.9, 6.1, 6.2, 6.3, 6.6,
			7.4, 8.5, 9.7, 10.5, 12.4 };

	private static final double[][] TEST_DATA2 = { { -0.2, 0, -0.8 } };
	private static final double[] TEST_Y2 = { 0.8, 0.9, 0.4 };
	
	@Test
	public void testLinearModel() {
		Matrix data = new Matrix(TEST_DATA1);
		Vector y = new Vector(TEST_Y1);
		System.out.println("-------------- LINEAR REGRESSION MODEL -----------");
		System.out.println(data);
		System.out.println(y);
		LinearRegressionModel model = new LinearRegressionModel();
		model.setTrainingSet(data, y);
		System.out.println(" Gradient descent counter: " + model.getGradientDescentCounter());
		System.out.println(" Result of values from model: " + model.modelCalculatedTrainingResult());
		System.out.println(" Model calculated coefficients: " + model.getCoefficients());
		assertEquals("test", "test");
	}
	
//	@Test
//	public void testLogisticModel() {
//		Matrix data = new Matrix(TEST_DATA2);
//		Vector y = new Vector(TEST_Y2);
//		System.out.println("-------------- LOGISTIC REGRESSION MODEL -----------");
//		System.out.println(data);
//		System.out.println(y);
//		LogisticRegressionModel model = new LogisticRegressionModel();
//		model.setTrainingSet(data, y);
//		System.out.println(" Gradient descent counter: " + model.getGradientDescentCounter());
//		System.out.println(" Result of values from model: " + model.modelCalculatedTrainingResult());
//		System.out.println(" Model calculated coefficients: " + model.getCoefficients());
//		assertEquals("test", "test");
//	}

}
