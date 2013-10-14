package com.maky.demo.graph;

public class Atom {
	
	public static final int TRANSPARENT = 0;
	
	int a;
	int r;
	int g;
	int b;
	
	int color;
	
	public Atom(int a, int r, int g, int b) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
		color = r << 11 | g << 5 | b;
	}
	
	public int toRGB565() {
		return color;
	}
}
