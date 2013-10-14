package com.maky.cards.player;

import com.maky.cards.ITable;
import com.maky.cards.Pair;

public class AutoPlayer extends Player {

	public AutoPlayer(ITable table, String name, long uid) {
		super(table, name, uid);
	}

	public void readyForAdditionalAttack() {
		
	}

	@Override
	public void notifyWin() {
	}

	@Override
	public void notifyLose() {
	}

	@Override
	public int getAttackCardNumberFromHand() {
		return 0;
	}

	@Override
	public Pair getBlockedPair() {
		return null;
	}

}
