package aiclass;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Search {

	//
	//		  STATE_1
	//		 /  |    \
	//		2	3	  4
	//    / |  / \   / \
	//	 5  6 7   8 9  10
	//	     /|    \
	//	   11 12   13
	//

	//	    1
	//     / \
	//    2   3
	//   / \ / \
	//  4   5   6
	// / \ / \ / \
	// 7  8   9  10
	// \ / \ / \ /
	//  11  12  13
	//   \ / \ /
	//   14  15
	//     \ /
	//     16
	//
	
	public class Action {}
		
	public class TreeSearchNode {
		State state;
		Runnable action;
		public int cost;
		TreeSearchNode parent;
		
		public TreeSearchNode(State state) {
			this.state = state;
		}
		
		@Override
		public boolean equals(Object obj) {
			return state.equals(((TreeSearchNode)obj).state);
		}
		
		@Override
		public int hashCode() {
			return state.intValue();
		}
	}
	
	public class TreeSearchResult {
		public TreeSearchNode path;
		public int expandCount;
		public HashSet<TreeSearchNode> explored;
	}

	//LinkedList<TreeSearchNode> frontier;
	//HashSet explored;
	
	private static final int BFS_ALGORITHM = 1;
	private static final int DFS_ALGORITHM = 2;
	
	public static int SEARCH_ALGORITHM = BFS_ALGORITHM;
	public static boolean LEFT_TO_RIGHT = true;
	
	private TreeSearchNode removeChoise(LinkedList<TreeSearchNode> frontier, int algorithm) {
		TreeSearchNode result = null, node;
		for(int i=0;i<frontier.size();i++) {
			node = frontier.get(i);
			if (algorithm == BFS_ALGORITHM) {
				if (result == null || node.cost < result.cost) {
					result = node;
				}
			} else if (algorithm == DFS_ALGORITHM) {
				if (result == null || node.cost > result.cost) {
					result = node;
				}
			} else {
				throw new NullPointerException("Wrong algorithm");
			}
		}
		frontier.remove(result);
		return result;
	}
		
	/**
	 * Breadth First Search	
	 */
	public TreeSearchResult graphSearch(State startState, State goal, int algorithm) {
		int count = 0;
		HashSet<TreeSearchNode> explored = new HashSet<TreeSearchNode>();
		LinkedList<TreeSearchNode> frontier = new LinkedList<TreeSearchNode>();
		TreeSearchNode start = new TreeSearchNode(startState);
		frontier.add(start);
		TreeSearchNode resultPath = null;
		boolean found = false;
		do {
			if (frontier.isEmpty()) {
				resultPath = null;
				found = true;
			} else {
				TreeSearchNode path = removeChoise(frontier, algorithm);
				explored.add(path);
				count++;
				System.out.println(" Node checked:" + path.state.intValue() + " count:" + count);
				State s = path.state;
				if (s.equals(goal)) {
					resultPath = path;
					found = true;
				} else {
					List<State> connections = s.connections();
					for (int i=0;i < connections.size();i++) {
						State state;
						if (LEFT_TO_RIGHT) {
							state = connections.get(i);
						} else {
							state = connections.get(connections.size() -1 - i);
						}
						if (state.intValue() < s.intValue()) {
							continue;
						}
						TreeSearchNode node = new TreeSearchNode(state);
						node.cost = path.cost + 1;
						node.parent = path;
						if (!explored.contains(node) && !frontier.contains(node)) {
							frontier.add(node);
						}
					}
				}
			}
			System.out.print("Frontier:");
			for (int i=0;i<frontier.size();i++) {
				System.out.print(frontier.get(i).state.intValue() + "(" + frontier.get(i).cost + ") ");
			}
			System.out.println();
			System.out.print("Explored:");
			TreeSearchNode[] exploredArray = explored.toArray(new TreeSearchNode[explored.size()]); 
			for (int i=0;i<exploredArray.length;i++) {
				System.out.print(exploredArray[i].state.intValue() + "(" + exploredArray[i].cost + ") ");
			}
			System.out.println();
		} while(!found);
		TreeSearchResult result = new TreeSearchResult();
		result.path = resultPath;
		result.expandCount = count;
		result.explored = explored;
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Define tree
//		TreeState state1 = new TreeState(null, 1);
//		TreeState state2 = new TreeState(state1, 2);
//		TreeState state3 = new TreeState(state1, 3);
//		TreeState state4 = new TreeState(state1, 4);
//		TreeState state5 = new TreeState(state2, 5);
//		TreeState state6 = new TreeState(state2, 6);
//		TreeState state7 = new TreeState(state3, 7);
//		TreeState state8 = new TreeState(state3, 8);
//		TreeState state9 = new TreeState(state4, 9);
//		TreeState state10 = new TreeState(state4, 10);
//		TreeState state11 = new TreeState(state7, 11);
//		TreeState state12 = new TreeState(state7, 12);
//		TreeState state13 = new TreeState(state8, 13);
//		state1.connect(state2).connect(state3).connect(state4);
//		state2.connect(state5).connect(state6);
//		state3.connect(state7).connect(state8);
//		state4.connect(state9).connect(state10);
//		state7.connect(state11).connect(state12);
//		state8.connect(state13);
		
		State state1 = new State(1);
		State state2 = new State(2);
		State state3 = new State(3);
		State state4 = new State(4);
		State state5 = new State(5);
		State state6 = new State(6);
		State state7 = new State(7);
		State state8 = new State(8);
		State state9 = new State(9);
		State state10 = new State(10);
		State state11 = new State(11);
		State state12 = new State(12);
		State state13 = new State(13);
		State state14 = new State(14);
		State state15 = new State(15);
		State state16 = new State(16);
		state1.connect(state2).connect(state3);
		state2.connect(state1).connect(state4).connect(state5);
		state3.connect(state1).connect(state5).connect(state6);
		state4.connect(state7).connect(state8).connect(state2);
		state5.connect(state2).connect(state8).connect(state9).connect(state3);
		state6.connect(state3).connect(state9).connect(state10);
		state7.connect(state11).connect(state4);
		state8.connect(state4).connect(state11).connect(state12).connect(state5);
		state9.connect(state5).connect(state12).connect(state13).connect(state6);
		state10.connect(state13).connect(state6);
		state11.connect(state14).connect(state8).connect(state7);
		state12.connect(state14).connect(state15).connect(state9).connect(state8);
		state13.connect(state15).connect(state9).connect(state10);
		state14.connect(state16).connect(state11).connect(state12);
		state15.connect(state12).connect(state16).connect(state13);
		state16.connect(state14).connect(state15);
		
		//Define algorithm parameters
		SEARCH_ALGORITHM = DFS_ALGORITHM;
		LEFT_TO_RIGHT = false;
		
		Search search = new Search();
		TreeSearchResult result = search.graphSearch(state1, state10, SEARCH_ALGORITHM);
		TreeSearchNode path = result.path;
		System.out.print(path.state.intValue());
		while (path.parent != null) {
			path = path.parent;
			System.out.print("->" + path.state.intValue());
		}
		System.out.println(" Expand count:" + result.expandCount);
	}
}