package test.ks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class KnightSeq {

	private static final char[][] keypad = {
		{'A', 'B', 'C', 'D', 'E'},
		{'F', 'G', 'H', 'I', 'J'},
		{'K', 'L', 'M', 'N', 'O'},
		{' ', '1', '2', '3', ' '}
	};
	
	private static final int[][] knightMove = { 
		{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
	};
	
	private class Key {
		private Set<Key> knightMoves = new HashSet<Key>();
		private char key;
		private boolean isVowel;
		
		public Key(char c) {
			key = c;
			switch(c) {
			case 'A':
			case 'E':
			case 'I':
			case 'O':
				isVowel = true;
				break;
			}
		}
		
		public void fillKnightMoves(int x, int y) {
			int nx,ny;
			for (int k = 0; k < knightMove.length; k++) {
				nx = x + knightMove[k][0];
				ny = y + knightMove[k][1];
				Key key = getKey(nx, ny);
				if (key != null) {
					knightMoves.add(key);
				}
			}
		}

		public void calculateSequence() {
			Sequence seq = new Sequence();
			seq.add(this);
			calculateSequence(seq, knightMoves.iterator());
		}
		
		private void calculateSequence(Sequence seq, Iterator<Key> moves) {
			while (moves.hasNext()) {
				Key key = moves.next();
				if (seq.add(key)) {
					if (seq.size() == sequenceSize) {
						if (OUTPUT_ENABLED) {
							seq.dump(output);
						}
						sequencesCount++;
						seq.remove(seq.size() - 1);
					} else {
						calculateSequence(seq, key.knightMoves.iterator());
					}
				}
			}
			seq.remove(seq.size() - 1);
		}
		
		
		public String toString() {
			return Character.toString(key);
		}
	}
	
	private class Sequence extends ArrayList<Key> {
		private int vowelCount;
		
		public boolean add(Key key) {
			boolean result;
			if (key.isVowel) {
				if (vowelCount == maxVowelCount) {
					result = false;
				} else {
					vowelCount++;
					result = super.add(key);
				}
			} else {
				result = super.add(key);
			}
			return result;
		}
		
		public Key remove(int index) {
			Key key = super.remove(index);
			if (key.isVowel) {
				vowelCount--;
			}
			return key;
		}
		
		public void dump(StringBuffer str) {
			Iterator<Key> it = iterator();
			while(it.hasNext()) {
				str.append(it.next());
			}
			str.append("\r\n");
		}
	}
	
	private int sequenceSize;
	private int maxVowelCount;
	private int sequencesCount;
	private StringBuffer output;
	private Key[][] keyData;
		
	public KnightSeq(char[][] data, int vowelLimit, int sequenceLimit) {
		sequenceSize = sequenceLimit;
		maxVowelCount = vowelLimit;
		output = new StringBuffer();
		keyData = new Key[data.length][];
		for(int i=0;i<data.length;i++) {
			keyData[i] = new Key[data[i].length];
			for (int j=0;j<data[i].length;j++) {
				if (data[i][j] != ' ') {
					keyData[i][j] = new Key(data[i][j]);
				}
			}
		}
	}
		
	public Key getKey(int i, int j) {
		Key result = null;
		if (i >= 0 && i < keyData.length) {
			if (j >= 0 && j < keyData[i].length) {
				result = keyData[i][j];
			}
		}
		return result;
	}
	
	public void fillKnightMoves() {
		for(int i=0;i<keyData.length;i++) {
			for (int j=0;j<keyData[i].length;j++) {
				if (keyData[i][j] != null) {
					keyData[i][j].fillKnightMoves(i, j);
				}
			}
		}
	}
	
	public void calculate() {
		for(int i=0;i<keyData.length;i++) {
			for (int j=0;j<keyData[i].length;j++) {
				if (keyData[i][j] != null) {
					keyData[i][j].calculateSequence();
				}
			}
		}
	}
	
	public void dump() {
		System.out.println(sequencesCount);
		if (OUTPUT_ENABLED) {
			System.out.println(output);
		}
	}
	
	private final static boolean OUTPUT_ENABLED = false;
	
	public static void main(String[] args) {
		KnightSeq sequence = new KnightSeq(keypad, 2, 10);
		sequence.fillKnightMoves();
		sequence.calculate();
		sequence.dump();
	}

}
