package com.maky.cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.maky.cards.player.IPlayer;

public class Battle {

	private List<Pair> pairs;
	private List<IPlayer> attackers;
	private byte trumpSuit;
	private int pairLimit;
	private boolean isLost;
	private boolean noAttacker;

	public Battle(byte trumpSuit, int pairLimit) {
		this.pairLimit = pairLimit;
		this.trumpSuit = trumpSuit;
		pairs = new ArrayList<Pair>();
		attackers = new ArrayList<IPlayer>();
	}
	
	private void log(String msg) {
		Logger.getInstance().log("Battle: " + msg);
	}

	public void addAttacker(IPlayer player) {
		attackers.add(player);
	}
	
	public void stopAttack(IPlayer player) {
		boolean result = attackers.remove(player);
		if (!result) {
			Logger.getInstance().log("Battle.stopAttack problem, no attacker found: " + player);
		}
	}

	public Pair attack(Card card) {
		Pair pair = null;
		if (pairs.size() == 0) {
			pair = new Pair(card, trumpSuit);
		} else if (pairs.size() < pairLimit) {
			byte rank = card.getRank();
			boolean found = false;
			for (int i=0;i<pairs.size();i++) {
				if (pairs.get(i).getAttack().getRank() == rank) {
					found = true;
					break;
				}
			}
			if (found) {
				pair = new Pair(card, trumpSuit);
			}
		}
		if (pair == null) {
			log("Cant add this card to attack, card = " + card);
		} else {
			pairs.add(pair);
		}
		return pair;
	}
	
	public boolean block(Pair pair, Card card) {
		if (pairs.contains(pair)) {
			return pair.block(card);
		}
		return false;
	}
	
	public boolean isActive() {
		Iterator<Pair> it = pairs.iterator();
		while (it.hasNext()) {
			if (!it.next().isBlocked()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Card> takeCards() {
		isLost = true;
		List<Card> result = new ArrayList<Card>();
		while (!pairs.isEmpty()) {
			Pair pair = pairs.remove(0);
			result.add(pair.getAttack());
			if (pair.isBlocked()) {
				result.add(pair.getBlock());
			}
		}
		return result;
	}
	
	public boolean isLost() {
		return isLost;
	}

	public void clean(Cleaning cleaning) {
		while (!pairs.isEmpty()) {
			Pair pair = pairs.remove(0);
			cleaning.cleanCard(pair.getAttack());
			if (pair.isBlocked()) {
				cleaning.cleanCard(pair.getBlock());
			}
		}
	}

	public boolean attackStopped() {
		return attackers.isEmpty();
	}
}
