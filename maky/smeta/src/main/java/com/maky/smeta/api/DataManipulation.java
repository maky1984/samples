package com.maky.smeta.api;

import java.util.Map;

public interface DataManipulation {

	public void insertValues(String entityName, Map<String, Object> values);

	public void getValues(String entityName, Map<String, Object> parameters);
	
}
