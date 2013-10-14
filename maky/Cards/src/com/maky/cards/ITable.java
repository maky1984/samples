package com.maky.cards;

import java.util.List;

import com.maky.cards.player.IPlayer;

public interface ITable {

	public void addPlayer(IPlayer player);
	
	public void startGame();
	
	public void startBattle(IPlayer player);

	public boolean addToAttack(Card card);

	public void stopAttack(IPlayer player);

	public boolean block(Pair pair, Card card);

	public List<Card> takeBattle();

}
