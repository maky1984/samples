package com.maky.cards;

public class Card {

	public static final byte SUIT_UNKNOWN = -1;
	public static final byte SUIT_SPADES = 0; // Пика
	public static final byte SUIT_CLUBS = 1; // Трефы
	public static final byte SUIT_DIAMONDS = 2;
	public static final byte SUIT_HEARTS = 3;
	public static final byte SUIT_LENGTH = 4;
	public static final String SUIT_NAMES[] = {"SPADES", "CLUBS", "DIAMONDS", "HEARTS"};

	public static final byte RANK_UNKNOWN = -1;
	public static final byte RANK_2 = 0;
	public static final byte RANK_3 = 1;
	public static final byte RANK_4 = 2;
	public static final byte RANK_5 = 3;
	public static final byte RANK_6 = 4;
	public static final byte RANK_7 = 5;
	public static final byte RANK_8 = 6;
	public static final byte RANK_9 = 7;
	public static final byte RANK_10 = 8;
	public static final byte RANK_J = 9;
	public static final byte RANK_Q = 10;
	public static final byte RANK_K = 11;
	public static final byte RANK_A = 12;
	public static final byte RANK_LENGTH = 13;
	public static final String RANK_NAMES[] = {"2", "3", "4", "5", "6" ,"7", "8", "9", "10", "J", "Q", "K", "A"};

	public static final byte JOCKER_LENGTH = 2;

	public static final Card[] CARDS;
	private static final int CARDS_PACKAGE_SIZE = Card.SUIT_LENGTH * Card.RANK_LENGTH + Card.JOCKER_LENGTH;
	static {
		CARDS = new Card[CARDS_PACKAGE_SIZE];
		for (byte i = Card.SUIT_SPADES; i < Card.SUIT_LENGTH; i++) {
			for (byte j = Card.RANK_2; j < Card.RANK_LENGTH; j++) {
				CARDS[(i * Card.RANK_LENGTH + j)] = new Card(i, j, false);
			}
		}
		for (int i = 0; i < Card.JOCKER_LENGTH; i++) {
			CARDS[Card.SUIT_LENGTH * Card.RANK_LENGTH + i] = new Card(Card.SUIT_UNKNOWN, Card.RANK_UNKNOWN, true);
		}
	}

	private byte suit;
	private byte rank;
	private boolean isJocker;

	public static Card getCard(byte suit, byte rank, boolean isJocker) {
		if (isJocker) {
			return CARDS[Card.SUIT_LENGTH * Card.RANK_LENGTH + suit];
		}
		return CARDS[(suit * Card.RANK_LENGTH + rank)];
	}

	private Card(byte suit, byte rank, boolean isJocker) {
		this.suit = suit;
		this.rank = rank;
		this.isJocker = isJocker;
	}

	public byte getSuit() {
		return suit;
	}

	public byte getRank() {
		return rank;
	}

	public boolean isJocker() {
		return isJocker;
	}
	
	@Override
	public String toString() {
		StringBuffer result;
		if (isJocker()) {
			result = new StringBuffer("JOCKER, ");
		} else {
			result = new StringBuffer(" ");
		}
		result.append(SUIT_NAMES[suit]);
		result.append(":");
		result.append(RANK_NAMES[rank]);
		return result.toString();
	}
}