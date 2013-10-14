package com.maky.smeta.domain;

import java.util.List;

public class DomainModel {

	public static class SmetaObject {

		private String name;
		private double price;
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public void setPrice(double price) {
			this.price = price;
		}
		
		public double getPrice() {
			return price;
		}
	}
	
	public static class Material extends SmetaObject {
		
	}
	
	public static class Work extends SmetaObject {
		
		private List<Material> materials;
		
	}
	
	public static class Smeta extends SmetaObject {
		
		private List<Work> works;
		
		
	}
}
