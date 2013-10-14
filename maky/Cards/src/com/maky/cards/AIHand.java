package com.maky.cards;

public class AIHand extends Hand {

	public AIHand(int defaultSize) {
		super(defaultSize);
	}
	
	public int findToBlock(Pair pair) {
		int number = -1;
		int blockingParameter = -1, param = -1;
		for (int i=0; i<cards.size();i++) {
			// TODO: find card to block
			if ((param = 0) > blockingParameter) {
				blockingParameter = param;
				number = i;
			}
		}
		return number;
	}

}