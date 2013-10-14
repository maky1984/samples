package com.maky.cards;

public class Pair {
	private Card attacker;
	private Card blocker;
	byte trumpSuit;

	public Pair(Card attacker, byte trumpSuit) {
		this.attacker = attacker;
		this.trumpSuit = trumpSuit;
	}

	private boolean blockRank(byte rank) {
		return rank > attacker.getRank();
	}

	public boolean block(Card blocker) {
		boolean result;
		if (blocker.getSuit() == trumpSuit) {
			if (attacker.getSuit() == trumpSuit) {
				result = blockRank(blocker.getRank());
			} else {
				result = true;
			}
		} else {
			if (attacker.getSuit() == trumpSuit) {
				result = false;
			} else {
				result = blockRank(blocker.getRank());
			}
		}
		if (result) {
			this.blocker = blocker;
		}
		return result;
	}

	public Card getAttack() {
		return attacker;
	}

	public Card getBlock() {
		return blocker;
	}

	public boolean isBlocked() {
		return blocker != null;
	}

	public byte getTrumpSuit() {
		return trumpSuit;
	}

	@Override
	public String toString() {
		return "(ATTACKER: " + attacker + "; BLOCKER: " + blocker + ")";
	}
}
