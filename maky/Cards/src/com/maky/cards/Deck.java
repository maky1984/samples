package com.maky.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {

	public static Deck createDurackGameDeck() {
		byte firstRank = Card.RANK_6;
		int rankSize = Card.RANK_A - Card.RANK_6 + 1;
		Card[] deckCards = new Card[Card.SUIT_LENGTH * rankSize];
		for (byte suit = Card.SUIT_SPADES; suit < Card.SUIT_LENGTH; suit++) {
			for (byte j = 0; j < rankSize; j++) {
				deckCards[suit * rankSize + j] = Card.getCard(suit, (byte) (firstRank + j), false);
			}
		}
		return new Deck(deckCards);
	}

	private final List<Card> cards;
	private final Random rand;
	private final int SHUFFLE_COUNT = 1000;
	private Card trumpCard;

	private Deck(Card[] cards) {
		this.cards = new ArrayList<Card>();
		this.cards.addAll(Arrays.asList(cards));
		rand = new Random();
	}

	public void shuffle() {
		final int size = cards.size();
		int i, j;
		Card card;
		for (int index = 0; index < SHUFFLE_COUNT; index++) {
			i = rand.nextInt(size);
			j = rand.nextInt(size);
			card = cards.get(i);
			cards.set(i, cards.get(j));
			cards.set(j, card);
		}
	}
	
	public void shuffleAndChooseTrump() {
		shuffle();
		trumpCard = drawCard();
	}

	public boolean hasCard() {
		return !cards.isEmpty();
	}

	public Card drawCard() {
		return cards.remove(cards.size() - 1);
	}
	
	public byte getTrumpSuit() {
		return trumpCard.getSuit();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("DECK:\r\n");
		for (int i = 0; i < cards.size(); i++) {
			builder.append(cards.get(i));
		}
		return builder.toString();
	}
}
