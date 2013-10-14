package com.maky.cards;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.maky.cards.player.IPlayer;
import com.maky.cards.player.Player;

public class Table implements ITable {

	private static int BATTLE_SIZE = 6;

	private LinkedList<IPlayer> players;
	private IPlayer currentMovePlayer;
	private IPlayer blockPlayer;

	private Deck deck;
	private Battle battle;
	private Cleaning cleaning;

	private int uid;

	public Table(int uid) {
		deck = Deck.createDurackGameDeck();
		deck.shuffleAndChooseTrump();
		cleaning = new Cleaning();
		players = new LinkedList<IPlayer>();
		this.uid = uid;
		log("created.");
	}

	private void log(String msg) {
		Logger.getInstance().log("Table(" + uid + "): " + msg);
	}

	public void addPlayer(IPlayer player) {
		IPlayer firstPlayer;
		if (players.size() > 0) {
			firstPlayer = players.getFirst();
			players.getLast().setNextPlayer(player);
		} else {
			firstPlayer = null;
		}
		player.setNextPlayer(firstPlayer);
		players.add(player);
		log("player " + player + " added.");
	}

	public void startGame() {
		Iterator<IPlayer> it = players.iterator();
		while (it.hasNext()) {
			IPlayer player = it.next();
			player.takeCardsFromDeck(deck);
		}
		log("game started.");
		nextMove(players.getFirst());
	}

	public void startBattle(IPlayer player) {
		if (player.equals(currentMovePlayer)) {
			battle = new Battle(deck.getTrumpSuit(), BATTLE_SIZE);
			Iterator<IPlayer> it = players.iterator();
			while (it.hasNext()) {
				IPlayer attackPlayer = it.next();
				if (!attackPlayer.equals(blockPlayer)) {
					battle.addAttacker(attackPlayer);
				}
			}
			log("battle started");
		} else {
			Logger.getInstance().log(
					"Table.startBattle wrong main attacker, curPlayer" + currentMovePlayer + " attacker = " + player);
		}
	}

	public boolean addToAttack(Card card) {
		log("card " + card + " added to attack");
		Pair pair = battle.attack(card);
		if (pair == null) {
			log("Cant attack with this " + card);
			return false;
		}
		blockPlayer.notifyAttack(pair);
		return true;
	}

	public void stopAttack(IPlayer player) {
		log("attack stopped from " + player);
		battle.stopAttack(player);
		checkBattle();
	}

	private void checkBattle() {
		log("check battle.");
		if (!battle.isActive() && battle.attackStopped()) {
			log("battle stopped.");
			IPlayer currentMovePlayer = this.currentMovePlayer.getNextPlayer();
			if (battle.isLost()) {
				currentMovePlayer = currentMovePlayer.getNextPlayer();
			} else {
				cleanBattle();
			}
			nextMove(currentMovePlayer);
		}
	}

	public void cleanBattle() {
		battle.clean(cleaning);
		log("Battle cleaned.");
	}

	public void nextMove(IPlayer nextPlayerToMove) {
		fillPlayerHands();
		currentMovePlayer = nextPlayerToMove;
		blockPlayer = currentMovePlayer.getNextPlayer();
		log("next move by curPlayer " + currentMovePlayer + " block by " + blockPlayer);
		if (currentMovePlayer.equals(blockPlayer)) {
			Iterator<IPlayer> it = players.iterator();
			while (it.hasNext()) {
				IPlayer player = it.next();
				if (player.equals(currentMovePlayer)) {
					player.notifyLose();
				} else {
					player.notifyWin();
				}
			}
		} else {
			currentMovePlayer.readyForAttack();
		}
	}

	public void fillPlayerHands() {
		log("Fill hands.");
		Iterator<IPlayer> it = players.iterator();
		while (it.hasNext()) {
			it.next().takeCardsFromDeck(deck);
		}
	}

	public boolean block(Pair attack, Card block) {
		log("pair " + attack + " blocked with " + block);
		if (battle.block(attack, block)) {
			checkBattle();
			return true;
		}
		return false;
	}

	public List<Card> takeBattle() {
		log("Battle failed. Cards taken.");
		return battle.takeCards();
	}
	
	public static void main(String[] args) {
		
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(" ---------------------- TABLE ---------------------------\r\n");
		str.append("ATTACKERS:\r\n");
		for (int i=0;i<players.size();i++) {
			IPlayer player = players.get(i);
			if (!player.equals(blockPlayer)) {
				str.append("Player " + player);
			}
		}
		str.append("\r\nBLOCKER:\r\n");
		str.append("Player " + blockPlayer);
		str.append("\r\nTrump suit:" + Card.SUIT_NAMES[deck.getTrumpSuit()]);
		return str.toString();
	}
}
