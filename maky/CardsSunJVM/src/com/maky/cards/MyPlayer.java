package com.maky.cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.maky.cards.player.Player;

public class MyPlayer extends Player {

	public MyPlayer(ITable table, int number) {
		super(table, "MyPlayer" + number, number);
	}
	
	public void readyForAdditionalAttack() {
		stopAttack();
	}

	@Override
	public void notifyWin() {
	}

	@Override
	public void notifyLose() {
	}

	@Override
	public int getAttackCardNumberFromHand() {
		synchronized (table) {
			System.out.println(table);
			System.out.println(hand);
			System.out.println("Choose card to attck:");
			int a = readCardNumber();
			System.out.println(a);
			return a;
		}
	}
	
	@Override
	public Pair getBlockedPair() {
		synchronized (table) {
			System.out.println("Pairs:");
			for (int i=0;i<pairs.size();i++) {
				System.out.print("Pair:" + pairs.get(i).getAttack() + " ");
			}
			int pairNumber = readCardNumber();
			System.out.println(hand);
			int cardNumber = readCardNumber();
			Pair pair = pairs.get(pairNumber);
			Card card = hand.dropCard(cardNumber);
			if (pair == null || card == null) {
				return null;
			}
			pair.block(card);
			return pair;
		}
	}
	
	private int readCardNumber() {
		int a = -1;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String str = reader.readLine();
			switch(str.charAt(0)) {
			case '1':
				a = 0;
				break;
			case '2':
				a = 1;
				break;
			case '3':
				a = 2;
				break;
			case '4':
				a = 3;
				break;
			case '5':
				a = 4;
				break;
			case '6':
				a = 5;
				break;
			case 'q':
				System.out.println("QUIT");
				System.exit(0);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}

}
