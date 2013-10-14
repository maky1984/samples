package com.mk.algebra;

public class Vector extends Matrix {

	public Vector(double[] data) {
		super(data);
	}

	public Vector(Matrix data) {
		super(data.getData());
	}

	public Vector(int n) {
		super(n,1);
	}

	public Vector(int n, double defaultValue) {
		super(n,1, defaultValue);
		
	}
	
	public double scalarProduct(Vector vector) {
		Matrix result = transpose().multiply(vector);
		if (result.getN() > 1 || result.getM() > 1) {
			throw new IllegalArgumentException("Cant make scalar product for this. This is not a vector");
		}
		return result.getData()[0][0];
	}
}
