package com.maky.cards.test;

import java.io.IOException;

import junit.framework.TestCase;

import com.maky.cards.Deck;
import com.maky.cards.Pair;
import com.maky.cards.Table;
import com.maky.cards.player.Player;

public class CardsGameTest extends TestCase {

	class TestPlayer extends Player {

		public TestPlayer(Table table, String name, long uid) {
			super(table, name, uid);
		}
		
		@Override
		public int getAttackCardNumberFromHand() {
			return 0;
		}
		
//		@Override
//		public void notifyAttack(Pair pair) {
//			int number = hand.findToBlock(pair);
//			number = 0;
//			while (hand.hasCard(number)) {
//				if (block(pair, hand.dropCard(number))) {
//					return;
//				}
//				number++;
//			}
//			takeCardsFromTable();
//		}

		@Override
		public void readyForAdditionalAttack() {
			stopAttack();
		}
		
		@Override
		public void notifyLose() {
			System.out.println("Player:" + this + " lost");
		}
		
		@Override
		public void notifyWin() {
			System.out.println("Player:" + this + " won");
		}
		
		@Override
		public Pair getBlockedPair() {
			return null;
		}
	}

	public void test() {
		System.out.println("TEST START");
		Deck deck = Deck.createDurackGameDeck();
		System.out.println(deck);
		System.out.println("------------------- SHUFFLED: --------------------");
		deck.shuffle();
		System.out.println(deck);
		Table table = new Table(1);
		TestPlayer player1 = new TestPlayer(table, "Player A", 1);
		TestPlayer player2 = new TestPlayer(table, "Player B", 2);
		table.addPlayer(player1);
		table.addPlayer(player2);
		table.startGame();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}
}