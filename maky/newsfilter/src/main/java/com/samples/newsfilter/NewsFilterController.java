package com.samples.newsfilter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Path("/")
public class NewsFilterController {

	private static final Logger logger = LoggerFactory.getLogger(NewsFilterController.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/news")
	public Response getNews(@QueryParam(value = "filter") String term) {
		
		// Execution of requirements 2 and 3 from specs
		List<NewsDescriptor> results = getGoogleNews(term);
		
		// Execution requirement 4
		results = filterOutNews(results, term);
		
		// Sort according to text similarity - requirement 5
		results = TextSimilarityUtil.sort(results, term);

		NewsResponse response = new NewsResponse();
		response.setSearchTerm(term);
		response.setResults(results);
		Response generalResponse = new Response();
		generalResponse.setNewsResponse(response);
		return generalResponse;
	}
	
	private List<NewsDescriptor> filterOutNews(List<NewsDescriptor> news, String searchTerm) {
		// filter out items without search term
		Iterator<NewsDescriptor> it = news.iterator();
		while (it.hasNext()) {
			NewsDescriptor descriptor = it.next();
			if (!descriptor.getTitle().contains(searchTerm)) {
				it.remove();
			}
		}
		return news;
	}

	private List<NewsDescriptor> getGoogleNews(String searchTerm) {
		List<NewsDescriptor> news = new ArrayList<NewsDescriptor>();
		HttpURLConnection connection = null;
		InputStream in = null;
		try {
			URL url = new URL("https://news.google.com/news/feeds?hl=en&gl=us&um=1&ie=UTF-8&output=rss&q=" + searchTerm);
			connection = (HttpURLConnection)url.openConnection();
			in = connection.getInputStream();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(in);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					NewsDescriptor descriptor = new NewsDescriptor();
					Node title = element.getElementsByTagName("title").item(0);
					if (title != null) {
						descriptor.setTitle(title.getTextContent());
						Node link = element.getElementsByTagName("link").item(0);
						if (link != null) {
							descriptor.setLink(link.getTextContent());
						}
						Node pubDate = element.getElementsByTagName("pubDate").item(0);
						if (pubDate != null) {
							descriptor.setPubDate(pubDate.getTextContent());
						}
						news.add(descriptor);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error during reading/parsing data from Google News", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("Cant close input stream", e);
				}
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return news;
	}

}
