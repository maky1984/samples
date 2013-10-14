package com.maky.test.datacenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * See description of the test at {@link https://docs.google.com/open?id=0B-wmPp-8m4TCQXRicXpJUXJsc0k}
 * or {@link http://www.lucidchart.com/invitations/accept/50d056ee-df70-4c85-97e7-78050ace2a7f}
 * 
 * @author mkotlyar
 *
 */
public class DataCenterProblem {

	private static final int NO_EDGE = -1;
	
	private int[][] edgeTable;
	private int startNodeIndex;
	private int endNodeIndex;

	private String[] nodes;

	/**
	 * Add node to nodes array.
	 * @param node
	 * @return
	 */
	private int addNode(String node) {
		int index = -1;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == null) {
				nodes[i] = node;
				index = i;
				break;
			} else if (nodes[i].equals(node)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * Returns node index in node array by name
	 * @param node - name of the node
	 * @return
	 */
	private int getNodeIndex(String node) {
		int index = -1;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].equals(node)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * Returns name of the node by the node index 
	 * @param index
	 * @return
	 */
	private String getNodeName(int index) {
		return nodes[index];
	}

	/**
	 * Static function, that creates empty edge table
	 * @param nodeNumber
	 * @return
	 */
	private static int[][] createEdgeTable(int nodeNumber) {
		int[][] table = new int[nodeNumber][nodeNumber];
		for (int i = 0; i < nodeNumber; i++) {
			for (int j = 0; j < nodeNumber; j++) {
				table[i][j] = NO_EDGE;
			}
		}
		return table;
	}

	/**
	 * Add edge to the edge table, and simplify parallels, if there is one
	 * @param index1 - index of start node for the edge 
	 * @param index2 - index of end node for the edge
	 * @param newEdgeValue
	 */
	private void putEdge(int index1, int index2, int newEdgeValue) {
		int oldValue = edgeTable[index1][index2];
		if (oldValue > NO_EDGE) {
			// There is already a parallel edge, simplify it
			edgeTable[index1][index2] = oldValue * newEdgeValue / (oldValue + newEdgeValue);
		} else {
			edgeTable[index1][index2] = newEdgeValue;
			edgeTable[index2][index1] = newEdgeValue;
		}
	}

	/**
	 * Simplify the route from startNodeIndex to the endNodeIndex using edgeTable
	 */
	public void simplifyEdges() {
		boolean needRecalculate = true;
		while (needRecalculate) {
			needRecalculate = false;
			for (int i = 0; i < edgeTable.length; i++) {
				if (i != startNodeIndex && i != endNodeIndex) {
					int firstEdgeIndex = NO_EDGE, secondEdgeIndex = NO_EDGE;
					for (int j = 0; j < edgeTable[i].length; j++) {
						if (i != startNodeIndex && i != endNodeIndex && edgeTable[i][j] != NO_EDGE) {
							if (firstEdgeIndex == NO_EDGE) {
								firstEdgeIndex = j;
							} else if (secondEdgeIndex == NO_EDGE) {
								secondEdgeIndex = j;
							} else {
								break;
							}
						}
					}
					if (secondEdgeIndex != NO_EDGE) {
						int newDelay = edgeTable[i][firstEdgeIndex] + edgeTable[i][secondEdgeIndex];
						edgeTable[i][firstEdgeIndex] = NO_EDGE;
						edgeTable[firstEdgeIndex][i] = NO_EDGE;
						edgeTable[i][secondEdgeIndex] = NO_EDGE;
						edgeTable[secondEdgeIndex][i] = NO_EDGE;
						putEdge(firstEdgeIndex, secondEdgeIndex, newDelay);
						needRecalculate = true;
					}
				}
			}
		}
	}

	/**
	 * Print out the result edges
	 */
	public void dumpOutResult() {
		System.out.println("Output:");
		for (int i = 0; i < edgeTable.length; i++) {
			for (int j = 0; j < edgeTable[i].length; j++) {
				if (edgeTable[i][j] != NO_EDGE) {
					String firstNodeName = getNodeName(i);
					String lastNodeName = getNodeName(j);
					System.out.println(firstNodeName + " " + lastNodeName + " " + edgeTable[i][j]);
					edgeTable[i][j] = NO_EDGE;
					edgeTable[j][i] = NO_EDGE;
				}
			}
		}
	}

	/**
	 * Load data from file, create edge table.
	 * @param filename
	 * @throws IOException
	 */
	public void load(String filename) throws IOException {
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new FileReader(filename));
			String line = bufReader.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			int nodeCounter = Integer.parseInt(tokenizer.nextToken());
			edgeTable = createEdgeTable(nodeCounter);
			nodes = new String[nodeCounter];
			String startNodeName = tokenizer.nextToken();
			String endNodeName = tokenizer.nextToken();
			System.out.println("Input:");
			for (int i = 0; i < nodeCounter; i++) {
				// Read edge information
				line = bufReader.readLine();
				System.out.println(line);
				if (line == null) {
					throw new IOException("Not enough data. Input data inconsistant");
				} else {
					// Parse edge information
					tokenizer = new StringTokenizer(line);
					String firstNodeName = tokenizer.nextToken();
					String secondNodeName = tokenizer.nextToken();
					int newEdgeValue = Integer.parseInt(tokenizer.nextToken());
					// Add new nodes if any
					int index1 = addNode(firstNodeName);
					int index2 = addNode(secondNodeName);
					// Form edges table
					putEdge(index1, index2, newEdgeValue);
				}
			}
			startNodeIndex = getNodeIndex(startNodeName);
			endNodeIndex = getNodeIndex(endNodeName);
		} finally {
			if (bufReader != null) {
				bufReader.close();
			}
		}
	}

	/**
	 * Starting point of the application
	 * @param args
	 */
	public static void main(String[] args) {
		DataCenterProblem center = new DataCenterProblem();
		try {
			center.load("input.dat");
		} catch (IOException e) {
			System.out.println("Error during load file" + e.getMessage());
			System.exit(-1);
		}
		center.simplifyEdges();
		center.dumpOutResult();
	}

}
