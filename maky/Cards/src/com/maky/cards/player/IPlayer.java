package com.maky.cards.player;

import com.maky.cards.Deck;
import com.maky.cards.Pair;

public interface IPlayer {

	public void setNextPlayer(IPlayer player);

	public IPlayer getNextPlayer();

	public void takeCardsFromDeck(Deck deck);

	public void readyForAttack();

	public void readyForAdditionalAttack();

	public void notifyAttack(Pair pair);

	public void notifyWin();

	public void notifyLose();

}
