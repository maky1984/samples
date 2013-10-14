package com.maky.game.lines.model.level;

import java.util.Arrays;
import java.util.Random;

import com.maky.game.lines.model.LinesModel;
import com.maky.game.lines.view.ILinesView;
import com.maky.util.log.Logger;

/**
 * 
 * Level class describes map and objects of game field. Each object is represented by integer value.
 * 
 * @author michael
 *
 */
public class Level {
	
	public static final int UNDEFINED = -1;
	public static final int L1 = 1;
	public static final int L2 = 2;
	public static final int L3 = 3;
	public static final int L4 = 4;
	public static final int L5 = 5;
	public static final int L6 = 6;
	public static final int L7 = 7;

	public static final short GROUND_MASK = (short)0xF000;
	public static final short BALL_MASK = (short)0x0F80;
	public static final short FUTURE_BALL_MASK = (short)0x007C;
	
	public static final short GROUND_NOCELL = (short)0;
	public static final short GROUND_EMPTY = (short)0x1000;
	public static final short GROUND_WALL = (short)0x2000;
	public static final short GROUND_STRONGWALL = (short)0x3000;
	
	public static final short BALL_EMPTY = (short)0;
	public static final short BALL_1 = (short)0x0080;
	public static final short BALL_2 = (short)0x0100;
	public static final short BALL_3 = (short)0x0180;
	public static final short BALL_4 = (short)0x0200;
	public static final short BALL_5 = (short)0x0280;
	public static final short BALL_6 = (short)0x0300;
	public static final short BALL_7 = (short)0x0380;
	public static final short BALL_8 = (short)0x0400;
	public static final short BALL_9 = (short)0x0500;
	public static final short BALL_COMBO_1 = (short)0x0580;
	public static final short BALL_COMBO_2 = (short)0x0600;
	public static final short BALL_COMBO_3 = (short)0x0680;
	
	public static final short FUTURE_BALL_EMPTY = (short)0;
	public static final short FUTURE_BALL_1 = (short)0x0004;
	public static final short FUTURE_BALL_2 = (short)0x0008;
	public static final short FUTURE_BALL_3 = (short)0x000C;
	public static final short FUTURE_BALL_4 = (short)0x0010;
	public static final short FUTURE_BALL_5 = (short)0x0014;
		
	private static final int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	
	private short[][] map;
	private short[] balls;
	
	public static final boolean hasBall(short value) {
		return (value & BALL_MASK) != 0;
	}
	
	public static final short setEmptyBall(short value) {
		return (short)((short)(value & ~BALL_MASK) | BALL_EMPTY);
	}
	
	public static final short setBall(short value, short ball) {
		return (short)((short)(value & ~BALL_MASK) | (ball & BALL_MASK));
	}
	
	public static final short setFutureBall(short value, short ball) {
		return (short)((short)(value & ~FUTURE_BALL_MASK) | ball);
	}
	
	public static final boolean sameLineBalls(short value1, short value2) {
		return (value1 & BALL_MASK) == (value2 & BALL_MASK);
	}
	
	public static final boolean hasNoCell(short value) {
		return (value & GROUND_MASK) == GROUND_NOCELL;
	}
	
	public static final short getGround(short value) {
		return (short) (value & GROUND_MASK);
	}
	
	public static final boolean isEmptyGround(short value) {
		short ground = getGround(value);
		return (ground == GROUND_EMPTY || ground == GROUND_WALL || ground == GROUND_STRONGWALL);
	}
	
	public static final boolean isWall(short value) {
		short ground = getGround(value);
		return (ground == GROUND_WALL || ground == GROUND_STRONGWALL);
	}
	
	public static final short setGround(short value, short ground) {
		return (short)((value & ~GROUND_MASK) | ground);
	}
	
	public static final short setEmptyGround(short value) {
		return setGround(value, GROUND_EMPTY);
	}
	
	public static final short setWall(short value) {
		return setGround(value, GROUND_WALL);
	}
	
	public static final boolean isStrongWall(short value) {
		return (getGround(value) == GROUND_STRONGWALL);
	}
	
	public static final boolean hasFuture(short value) {
		return (value & FUTURE_BALL_MASK) != 0;
	}
	
	public static final int getFutureNumber(short value) {
		return ((value & FUTURE_BALL_MASK) >> 2);		
	}
	
	public static final int getBallNumber(short value) {
		return ((value & BALL_MASK) >> 7);
	}
	
	public static final short getFuture(short value) {
		return (short) ((value & FUTURE_BALL_MASK) << 5);
	}
	
	private static final byte PASSED = -2;

	private int LINE_LENGTH = 3;

	public int newBallAddition = 1;

	private int cellNumber;
	private int emptyCellNumber;
	private int wallNumber;

	private Logger logger = Logger.getInstance(this);

	private Random rand = new Random();

	private LinesModel model;

	public Level(short[][] map, short[] balls, LinesModel model) {
		this.model = model;
		this.map = map;
		this.balls = balls;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!hasNoCell(map[i][j])) {
					cellNumber++;
					if (isEmptyGround(map[i][j]) && !hasBall(map[i][j])) {
						emptyCellNumber++;
					}
					if (isWall(map[i][j])) {
						wallNumber++;
						if (isStrongWall(map[i][j])) {
							wallNumber++;
						}
					}
				}
			}
		}
	}

	/**
	 * Add random balls to the map
	 * 
	 * @param number
	 * @param score
	 * @return
	 */
	private int addRandomBalls(int number, int score) {
		if (emptyCellNumber <= 0 || number < 1) {
			return score;
		}
		int k = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (isEmptyGround(map[i][j]) && !hasBall(map[i][j]) && !hasFuture(map[i][j])) {
					k++;
				} else if (isEmptyGround(map[i][j]) && hasFuture(map[i][j])) {
					short ball = getFuture(map[i][j]);
					map[i][j] = setFutureBall(map[i][j], FUTURE_BALL_EMPTY);
					score = putBall(i, j, ball, score);
				}
			}
		}
		if (k <= number) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (isEmptyGround(map[i][j]) && !hasBall(map[i][j]) && !hasFuture(map[i][j])) {
						map[i][j] = setFutureBall(map[i][j], balls[rand.nextInt(balls.length)]);
						//score = putBall(i, j, balls[rand.nextInt(balls.length)], score);
					}
				}
			}
		} else {
			int[] randomNumbers = new int[number];
			for (int i = 0; i < number; i++) {
				randomNumbers[i] = rand.nextInt(k);
			}
			Arrays.sort(randomNumbers);
			int l = 0;
			k = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (isEmptyGround(map[i][j]) && !hasBall(map[i][j]) && !hasFuture(map[i][j]) && l < randomNumbers.length) {
						if (k++ == randomNumbers[l]) {
							map[i][j] = setFutureBall(map[i][j], balls[rand.nextInt(balls.length)]);							
//							score = putBall(i, j, balls[rand.nextInt(balls.length)], score);
							l++;
						} else {
							logger.logError(" ERROR in fill map with random values algorithm");
						}
					}
				}
			}
			if (l != randomNumbers.length) {
				logger.logError(" ERROR in check after random values fill algrithm");
			}
		}
		return score;
	}

	private int countBalls(int column, int raw, int directionColumn, int directionRaw) {
		short type = map[column][raw];
		boolean same = true;
		int result = 0;
		while (same) {
			column += directionColumn;
			raw += directionRaw;
			if (column >= 0 && raw >= 0 && column < map.length && raw < map[column].length && sameLineBalls(map[column][raw], type)) {
				result++;
			} else {
				same = false;
			}
		}
		return result;
	}

	private void removeBall(int column, int raw) {
		logger.logInfo(" Level.removeBall column=" + column + " raw=" + raw);
		short type = map[column][raw];
		if (isWall(type)) {
			if (isStrongWall(type)) {
				type = setWall(type);
				wallNumber--;
			} else {
				type = setEmptyGround(type);
				wallNumber--;
			}
		}
		map[column][raw] = setEmptyBall(type);
		emptyCellNumber++;
		for (int i=0;i<directions.length;i++) {
			column += directions[i][0];
			raw += directions[i][1];
			if (column >= 0 && column < map.length && raw >= 0 && raw < map[column].length && sameLineBalls(map[column][raw], type)) {
				removeBall(column, raw);
			}
			column -= directions[i][0];
			raw -= directions[i][1];
		}
	}

	private int checkAndCleanBall(int column, int raw, int score) {
		int right = countBalls(column, raw, 1, 0) + 1;
		int left = countBalls(column, raw, -1, 0) + 1;
		int up = countBalls(column, raw, 0, -1) + 1;
		int down = countBalls(column, raw, 0, 1) + 1;
		if (right >= LINE_LENGTH || left >= LINE_LENGTH || right + left - 1 >= LINE_LENGTH || up >= LINE_LENGTH
				|| down >= LINE_LENGTH || up + down - 1 >= LINE_LENGTH) {
			score += right;
			score += left;
			score += up;
			score += down;
			score -= 3; // the center ball should be counted only once
			// line found, clean balls
			removeBall(column, raw);
		}
		return score;
	}

	private int putBall(int column, int raw, short type, int score) {
		if (isEmptyGround(map[column][raw]) && !hasBall(map[column][raw])) {
			map[column][raw] = setBall(map[column][raw], type);
			emptyCellNumber--;
			model.notifyView(ILinesView.NOTIFY_UPDATE_LEVEL);
			score = checkAndCleanBall(column, raw, score);
			model.notifyView(ILinesView.NOTIFY_UPDATE_LEVEL);
		} else if (isEmptyGround(map[column][raw]) && type == BALL_EMPTY) {
			map[column][raw] = setEmptyBall(map[column][raw]);
			emptyCellNumber++;
			model.notifyView(ILinesView.NOTIFY_UPDATE_LEVEL);
		}
		return score;
	}

	/**
	 * Return true if there is a path from cell(fromCol,fromRaw) to the cell(toCol,toRaw) with EMPTY items
	 * 
	 * @param fromCol
	 * @param fromRaw
	 * @param toCol
	 * @param toRaw
	 * @return true - if path exist, otherwise - false
	 */
	private boolean checkPath(int fromCol, int fromRaw, int toCol, int toRaw) {
		short[][] mapCopy = new short[map.length][];
		for (int i = 0; i < map.length; i++) {
			mapCopy[i] = new short[map[i].length];
			for (int j = 0; j < map[i].length; j++) {
				mapCopy[i][j] = map[i][j];
			}
		}
		return checkPath(mapCopy, fromCol, fromRaw, toCol, toRaw);
	}

	private static boolean checkPath(short[][] map, int fromCol, int fromRaw, int toCol, int toRaw) {
		boolean result = false;
		if (fromCol == toCol && fromRaw == toRaw) {
			// Path found
			result = true;
		} else {
			map[fromCol][fromRaw] = PASSED;
			for (int i=0;i<directions.length;i++) {
				int newCol = fromCol + directions[i][0];
				int newRaw = fromRaw + directions[i][1];
				if (newCol < map.length && newCol >= 0 && newRaw < map[fromCol].length && newRaw >= 0 && !hasBall(map[newCol][newRaw])) {
					result = checkPath(map, newCol, newRaw, toCol, toRaw);					
				}
				if (result) break;
			}
		}
		return result;
	}

	public boolean isEnd() {
		return isLose() || isWin();
	}

	public boolean isLose() {
		return emptyCellNumber == 0;
	}

	public boolean isWin() {
		return wallNumber == 0 && cellNumber - emptyCellNumber < 3;
	}

	public int moveBall(int fromCol, int fromRaw, int toCol, int toRaw, int score) {
		if (toCol >= map.length || toRaw >= map[toCol].length) {
			logger.logError(" ERROR toCol = " + toCol + " toRaw = " + toRaw + " are more then map");
			return score;
		}
		if ((!hasBall(map[toCol][toRaw])) && checkPath(fromCol, fromRaw, toCol, toRaw)) {
			short oldType = map[fromCol][fromRaw];
			score = putBall(fromCol, fromRaw, BALL_EMPTY, score);
			score = putBall(toCol, toRaw, oldType, score);
			if (!isEnd()) {
				score = addRandomBalls(newBallAddition, score);
			}
			// Otherwise: user wins the battle
		}
		return score;
	}

	public boolean selectCell(int column, int raw) {
		if (map.length <= column || map[column].length <= raw) {
			logger.logInfo(" Column(" + column + "):raw(" + raw + ") not available");
			return false;
		}
		return true;
	}

	public short[][] getMap() {
		return map;
	}

	public short[] getBalls() {
		return balls;
	}

	public String toString() {
		StringBuffer str = new StringBuffer("Level:\r\n");
		str.append("Ball types:");
		str.append(Integer.toString(balls.length));
		str.append("\r\nMap:\r\n");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				str.append(Short.toString(map[i][j]));
				str.append("\t");
			}
			str.append("\r\n");
		}
		str.append("Empty balls number:");
		str.append(Integer.toString(emptyCellNumber));
		str.append("\r\n");
		return str.toString();
	}
}
