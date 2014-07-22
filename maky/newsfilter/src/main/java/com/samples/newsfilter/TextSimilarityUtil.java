package com.samples.newsfilter;

import java.util.ArrayList;
import java.util.List;

public class TextSimilarityUtil {

	/**
	 * See {@link http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/
	 * Levenshtein_distance}
	 * 
	 * @param s0
	 *            - first string to compare
	 * @param s1
	 *            - second string to compare
	 * @return
	 */
	public static int levenshteinDistance(String s0, String s1) {
		int len0 = s0.length() + 1;
		int len1 = s1.length() + 1;

		// the array of distances
		int[] cost = new int[len0];
		int[] newcost = new int[len0];

		// initial cost of skipping prefix in String s0
		for (int i = 0; i < len0; i++)
			cost[i] = i;

		// dynamicaly computing the array of distances

		// transformation cost for each letter in s1
		for (int j = 1; j < len1; j++) {

			// initial cost of skipping prefix in String s1
			newcost[0] = j - 1;

			// transformation cost for each letter in s0
			for (int i = 1; i < len0; i++) {

				// matching current letters in both strings
				int match = (s0.charAt(i - 1) == s1.charAt(j - 1)) ? 0 : 1;

				// computing cost for each transformation
				int cost_replace = cost[i - 1] + match;
				int cost_insert = cost[i] + 1;
				int cost_delete = newcost[i - 1] + 1;

				// keep minimum cost
				newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
			}

			// swap cost/newcost arrays
			int[] swap = cost;
			cost = newcost;
			newcost = swap;
		}

		// the distance is the cost for transforming all letters in both strings
		return cost[len0 - 1];
	}

	/**
	 * Calculates the score for the pair of news
	 * @param o1
	 * @param o2
	 * @return
	 */
	private static double score(NewsDescriptor o1, NewsDescriptor o2) {
		String s1 = o1.getTitle();
		String s2 = o2.getTitle();
		int distance = levenshteinDistance(s1, s2);
		double ratio = ((double) distance) / (Math.max(s1.length(), s2.length()));
		return ratio;
	}

	public static List<NewsDescriptor> sort(List<NewsDescriptor> descriptors, String searchTerm) {
		NewsDescriptor bestOne = null;
		int maxCount = 0;
		for (NewsDescriptor descriptor : descriptors) {
			int count = descriptor.getTitle().split(searchTerm).length - 1;
			if (bestOne == null || count > maxCount) {
				bestOne = descriptor;
			}
		}
		descriptors.remove(bestOne);
		double[] scores = new double[descriptors.size()];
		List<NewsDescriptor> result = new ArrayList<NewsDescriptor>();
		result.add(bestOne);
		for (int i = 0; i < descriptors.size(); i++) {
			scores[i] = score(bestOne, descriptors.get(i));
			result.add(descriptors.get(i));
			for (int j = 0; j < i; j++) {
				if (scores[i] < scores[j]) {
					NewsDescriptor t = result.get(i);
					result.set(i, result.get(j));
					result.set(j, t);
				}
			}
		}
		return result;
	}
}
