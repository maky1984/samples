package com.maky.cards.player;

import java.util.ArrayList;
import java.util.List;

import com.maky.cards.AIHand;
import com.maky.cards.Card;
import com.maky.cards.Deck;
import com.maky.cards.ITable;
import com.maky.cards.Logger;
import com.maky.cards.Pair;

public abstract class Player implements IPlayer, Runnable {

	private int HAND_SIZE = 6;
	protected AIHand hand;
	private IPlayer nextPlayer;
	protected ITable table;
	protected List<Pair> pairs;
	private String name;
	private long uid;

	public Player(ITable table, String name, long uid) {
		this.table = table;
		hand = new AIHand(HAND_SIZE);
		this.name = name;
		this.uid = uid;
	}

	@Override
	public boolean equals(Object o) {
		return uid == ((Player) o).uid;
	}

	@Override
	public int hashCode() {
		return (int) (uid ^ (uid >>> 32));
	}

	public void setNextPlayer(IPlayer player) {
		this.nextPlayer = player;
	}

	public IPlayer getNextPlayer() {
		return nextPlayer;
	}

	public void takeCardsFromDeck(Deck deck) {
		hand.putCards(deck);
		if (hand.isEmpty()) {
			nextPlayer = nextPlayer.getNextPlayer();
		}
	}

	public void beginAttack() {
		if (hand.isEmpty()) {
			Logger.getInstance().log("No card for attack for player:" + this);
		} else {
			table.startBattle(this);
		}
	}

	public boolean addToAttack(Card card) {
		return table.addToAttack(card);
	}

	public void stopAttack() {
		table.stopAttack(this);
	}

	public void nothingToAdd() {

	}

	public boolean block(Pair pair, Card card) {
		return table.block(pair, card);
	}

	public void takeCardsFromTable() {
		List<Card> cards = table.takeBattle();
		while (!cards.isEmpty()) {
			hand.putCard(cards.remove(0));
		}
	}

	// -------------------------------------------------
	// -----> To Player
	// Your turn to move
	public void readyForAttack() {
		new Thread() {
			public void run() {
				beginAttack();
				boolean attackFinished = false;
				while (!attackFinished) {
					int cardNumber = getAttackCardNumberFromHand();
					if (cardNumber >= 0) {
						Card card = hand.dropCard(cardNumber);
						if (!addToAttack(card)) {
							hand.putCard(card);
						}
					} else {
						attackFinished = true;
					}
				}
				stopAttack();
			}
		}.start();
	}

	private boolean hasEmptyPair() {
		for (int i=0;i<pairs.size();i++) {
			if (!pairs.get(i).isBlocked()) return true;
		}
		return false;
	}
	
	// You have to block it
	public void notifyAttack(Pair pair) {
		Thread task = new Thread() {
			public void run() {
				while (hasEmptyPair()) {
					Pair pair = getBlockedPair();
					if (pair == null) {
						takeCardsFromTable();
						break;
					}
					block(pair, pair.getBlock());
				}
			}
		};

		if (pairs == null) {
			pairs = new ArrayList<Pair>();
			pairs.add(pair);
			task.start();
		} else {
			pairs.add(pair);
		}
	}

	public void run() {
	}

	// You win
	public abstract void notifyWin();

	// You lose
	public abstract void notifyLose();

	// ---------------------------------------------------
	// -------> To Remote Player
	/**
	 * Returns card number from hand that player choose for attack, or -1 if there is no card to attack
	 */
	public abstract int getAttackCardNumberFromHand();
	
	/**
	 * Returns pair with blocked card in it, or null if there is posibility to block any pair
	 * @return
	 */
	public abstract Pair getBlockedPair();

	@Override
	public String toString() {
		return name + "(" + uid + ")";
	}
}
