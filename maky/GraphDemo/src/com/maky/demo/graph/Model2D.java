package com.maky.demo.graph;

import java.util.Random;

public class Model2D {

	private Atom[][] atoms;
	private int width;
	private int height;
	
	public Model2D() {
	}
	
	public static Model2D createTestModel2DV1(boolean useRandom) {
		Random r = new Random();
		Model2D model = new Model2D();
		model.width = 1000;
		model.height = 1000;
		Atom[][] atoms = new Atom[model.width][model.height];
		for (int i=0;i<model.width;i++) {
			for (int j=0;j<model.height;j++) {
				if (useRandom) {
					atoms[i][j] = new Atom(0,r.nextInt(256),r.nextInt(256),r.nextInt(256));
				} else {
					atoms[i][j] = new Atom(0,i % 256,j % 256,i % 256);
				}
			}
		}
		model.atoms = atoms;
		return model;
	}
	
	public static Model2D createTestModelTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int r, int g, int b) {
		Model2D model = new Model2D();
		int minX, minY, maxX, maxY;
		float k1,k2,k3,b1,b2,b3;
		minX = Math.min(x1, Math.min(x2, x3));
		minY = Math.min(y1, Math.min(y2, y3));
		maxX = Math.max(x1, Math.max(x2, x3));
		maxY = Math.max(y1, Math.max(y2, y3));
		model.width = maxX - minX + 1;
		model.height = maxY - minY + 1;
		Atom[][] atoms = new Atom[model.width][model.height];
		if (x2 == x1) {
			k1 = Integer.MAX_VALUE;		
			b1 = 0;
		} else {
			k1 =  (y2 - y1) / (x2 - x1);		
			b1 = y1 - k1 * x1;
		}
		if (x3 == x1) {
			k2 = Integer.MAX_VALUE;
			b2 = 0;
		} else {
			k2 = (y3 - y1) / (x3 - x1);
			b2 = y1 - k2 * x1;
		}
		if (x2 == x3) {
			k3 = Integer.MAX_VALUE;
			b3 = 0;
		} else {
			k3 = (y2 - y3) / (x2 - x3);
			b3 = y3 - k3 * x3;
		}
		System.out.println(" y=" + k1 +"*x+" + b1);
		System.out.println(" y=" + k2 +"*x+" + b2);
		System.out.println(" y=" + k3 +"*x+" + b3);
		for (int x = 0;x < model.width;x++) {
			float yi1 = k1 * x + b1;
			if (yi1 >= 0 && ((int)yi1) < model.height) {
				if (atoms[x][(int)yi1] == null) {
					atoms[x][(int)yi1] = new Atom(0 , r, g, b);
				}
			}
			float yi2 = k2 * x + b2;
			if (yi2 >= 0 && ((int)yi2) < model.height) {
				if (atoms[x][(int)yi2] == null) {
					atoms[x][(int)yi2] = new Atom(0 , r, g, b);
				}
			}
			float yi3 = k3 * x + b3;
			if (yi3 >= 0 && ((int)yi3) < model.height) {
				if (atoms[x][(int)yi3] == null) {
					atoms[x][(int)yi3] = new Atom(0 , r, g, b);
				}
			}
			System.out.println(" x=" + x + " yi1=" + yi1 + " yi2=" + yi2 + " yi3=" + yi3);
		}
		for (int y = 0;y < model.height;y++) {
			float xi1 = k1 == Float.MAX_VALUE ? 0 : (y - b1)/k1;
			if (xi1 >= 0 && ((int)xi1) < model.width) {
				if (atoms[(int)xi1][y] == null) {
					atoms[(int)xi1][y] = new Atom(0 , r, g, b);
				}
			}
			float xi2 = k2 == Float.MAX_VALUE ? 0 : (y - b2)/k2;
			if (xi2 >= 0 && ((int)xi2) < model.width) {
				if (atoms[(int)xi2][y] == null) {
					atoms[(int)xi2][y] = new Atom(0 , r, g, b);
				}
			}
			float xi3 = k3 == Float.MAX_VALUE ? 0 : (y - b3)/k3;
			if (xi3 >= 0 && ((int)xi3) < model.width) {
				if (atoms[(int)xi3][y] == null) {
					atoms[(int)xi3][y] = new Atom(0 , r, g, b);
				}
			}
			System.out.println(" y=" + y + " xi1=" + xi1 + " xi2=" + xi2 + " xi3=" + xi3);
		}
		for (int x=0;x<model.width;x++) {
			boolean firstStartFound = false;
			boolean firstEndFound = false;
			int start = 0;
			for (int y=0; y<model.height;y++) {
				if (atoms[x][y] == null || atoms[x][y].color == Atom.TRANSPARENT) {
					if (firstStartFound && !firstEndFound) {
						firstEndFound = true;
						start = y;
					}
				} else {
					if (firstStartFound) {
						if (firstEndFound) {
							for (int yy = start;yy<y;yy++) {
								atoms[x][yy] = new Atom(0, r, g, b);
							}
						}
					} else {
						firstStartFound = true;
					}
				}
			}
		}
		model.atoms = atoms;
		return model;
	}
	
	/**
	 * Fill data from model to data on screen	
	 * @param data - array from screen
	 * @param offset - start offset in data array
	 * @param screenW - screen width
	 * @param modelW - width of model on screen in pixels
	 * @param modelH - height of model on screen in pixels
	 * @param viewW - width of model shown on screen in pixels
	 * @param viewH - height of model shown on screen in pixels
	 */
	public void fillData(int[] data, int offset, int screenW, int modelW, int modelH, int viewW, int viewH) {
		float dx = (float)width / (float)modelW;
		float dy = (float)height / (float)modelH;
		for (int i=0;i<viewH;i++) {
			for (int j = 0;j<viewH;j++) {
				float x = dx * i;
				float y = dy * j;
				Atom atom = atoms[(int)x][(int)y];
				data[offset + j*screenW + i] = atom == null ? Atom.TRANSPARENT : atom.toRGB565();
			}
		}
	}
	
	public String log() {
		StringBuffer buf = new StringBuffer();
		for (int i=0;i<atoms.length;i++) {
			for (int j=0;j<atoms[i].length;j++) {
				if (atoms[i][j] == null || atoms[i][j].color == Atom.TRANSPARENT) {
					buf.append(" ");
				} else {
					buf.append("#");
				}
			}
			buf.append("\r\n");
		}
		return buf.toString();
	}
	
	
	public interface Test {
		public String getString();
		public void clean();
		public void fill();
	}
	
	public class Test1 implements Test {
		public String str;
		
		public String getString() {
			if (str != null) {
				return str;
			}
			return null;
		}
		
		public void clean() {
			str = null;
		}
		
		public void fill() {
			str = new String("Test1");
		}
		
	}

	public class Test2 implements Test {
		public String str;
		
		public synchronized String getString() {
			if (str != null) {
				return str;
			}
			return null;
		}
		
		public synchronized void clean() {
			str = null;
		}
		
		public synchronized void fill() {
			str = new String("Test1");
		}
		
	}

	public class Test3 implements Test {
		public volatile String str;
		
		public String getString() {
			if (str != null) {
				return str;
			}
			return null;
		}
		
		public void clean() {
			str = null;
		}
		
		public void fill() {
			str = new String("Test1");
		}
		
	}

	public class Task extends Thread {
		private Test test; 
		public Task(Test test) {
			this.test = test;
		}
		
		@Override
		public void run() {
			long time = System.currentTimeMillis();
			for (int i=0;i<10000000;i++) {
				test.fill();
				test.getString();
				test.clean();
			}
			time = System.currentTimeMillis() - time;
			System.out.println("Time = " + time);
		}
	}
	
	
	public static void main(String[] args) {
//		Model2D model = createTestModelTriangle(0, 0, 5, 10, 10, 0, 255, 100, 100);
		Model2D model = new Model2D();
//		System.out.println(model.log());
//		try {
//			model.new Task(model.new Test1()).start();
//			Thread.sleep(5000);
//			model.new Task(model.new Test2()).start();
//			Thread.sleep(5000);
//			model.new Task(model.new Test3()).start();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		model.new Task(model.new Test2()).start();

	}
}
