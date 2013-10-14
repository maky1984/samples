package com.maky.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private int defaultSize;
	protected final List<Card> cards;

	public Hand(int defaultSize) {
		this.cards = new ArrayList<Card>(defaultSize);
		this.defaultSize = defaultSize;
	}

	public void putCard(Card card) {
		Logger.getInstance().log("Hand.putCard " + card);
		cards.add(card);
	}

	public void putCards(Deck deck) {
		for (int i=cards.size(); i<defaultSize;i++) {
			if (deck.hasCard()) {
				putCard(deck.drawCard());
			} else {
				break;
			}
		}
	}
	
	public Card dropCard(int number) {
		return cards.remove(number);
	}
	
	public Card getCard(int number) {
		return cards.get(number);
	}
	
	public boolean hasCard(int number) {
		return number < cards.size();
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Hand: ");
		for (int i=0;i<cards.size();i++) {
			str.append(cards.get(i));
		}
		return str.toString();
	}
}