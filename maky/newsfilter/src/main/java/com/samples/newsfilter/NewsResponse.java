package com.samples.newsfilter;

import java.util.List;

public class NewsResponse {

	private String searchTerm;

	private List<NewsDescriptor> results;

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public void setResults(List<NewsDescriptor> results) {
		this.results = results;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public List<NewsDescriptor> getResults() {
		return results;
	}

}
