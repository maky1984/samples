package com.maky.cards;

import java.awt.Frame;

import javax.swing.JFrame;

import com.maky.cards.player.IPlayer;

public class CardsGameSunJVM {
	
	private Frame frame;

	private void init() {
		frame = new JFrame();
		frame.setBounds(0, 0, 200, 200);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		ITable table = new Table(0);
		IPlayer player1 = new MyPlayer(table, 1);
		IPlayer player2 = new MyPlayer(table, 2);
		table.addPlayer(player1);
		table.addPlayer(player2);
		table.startGame();
		CardsGameSunJVM test = new CardsGameSunJVM();
		//test.init();
	}
}
