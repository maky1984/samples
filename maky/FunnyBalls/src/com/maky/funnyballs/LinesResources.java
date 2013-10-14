package com.maky.funnyballs;

public class LinesResources {

	private static final short NO = LevelModel.GROUND_NOCELL;
	private static final short EM = LevelModel.GROUND_EMPTY;
	private static final short B1 = LevelModel.GROUND_EMPTY | LevelModel.BALL_1;
	private static final short B2 = LevelModel.GROUND_EMPTY | LevelModel.BALL_2;
	private static final short B3 = LevelModel.GROUND_EMPTY | LevelModel.BALL_3;
	private static final short B4 = LevelModel.GROUND_EMPTY | LevelModel.BALL_4;
	private static final short B5 = LevelModel.GROUND_EMPTY | LevelModel.BALL_5;
	private static final short B6 = LevelModel.GROUND_EMPTY | LevelModel.BALL_6;
	private static final short B7 = LevelModel.GROUND_EMPTY | LevelModel.BALL_7;
	private static final short B8 = LevelModel.GROUND_EMPTY | LevelModel.BALL_8;
	private static final short B9 = LevelModel.GROUND_EMPTY | LevelModel.BALL_9;
	private static final short B0 = LevelModel.GROUND_EMPTY | LevelModel.BALL_COMBO_1;
	private static final short BA = LevelModel.GROUND_EMPTY | LevelModel.BALL_COMBO_2;
	private static final short BB = LevelModel.GROUND_EMPTY | LevelModel.BALL_COMBO_3;
	private static final short WA = LevelModel.GROUND_WALL;
	private static final short WS = LevelModel.GROUND_STRONGWALL;

	public static int ballNumber(short levelBall) {
		int number = -1;
		switch (levelBall) {
		case LevelModel.BALL_1:
			number = 0;
			break;
		case LevelModel.BALL_2:
			number = 1;
			break;
		case LevelModel.BALL_3:
			number = 2;
			break;
		case LevelModel.BALL_4:
			number = 3;
			break;
		case LevelModel.BALL_5:
			number = 4;
			break;
		}
		return number;
	}

	// [level number][sub-level number][]
	public static short[][][] balls = {
			// Level 1
			{
			// Level 1.1
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3 },
			// Level 1.2
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4 },
			// Level 1.3
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4 }
			},
			// Level 2
			{
			// Level 2.1
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4 },
			// Level 2.2
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4 },
			// Level 2.3
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4 }
			},
			// Level 3
			{
			// Level 3.1
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 },
			// Level 3.2
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 },
			// Level 3.3
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 }
			}, 
			// Level 4
			{
			// Level 4.1
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 },
			// Level 4.2
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 },
			// Level 4.3
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 }
			}, 
			// Level 5
			{
			// Level 5.1
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 },
			// Level 5.2
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 },
			// Level 5.3
			{ LevelModel.BALL_1, LevelModel.BALL_2, LevelModel.BALL_3, LevelModel.BALL_4, LevelModel.BALL_5 }
			}
			};

	// [level number][sub-level number][][]
	public static final short[][][][] maps = {
			// Level 1
			{ {
					// Level 1.1
					// 1  2   3   4   5   6   7   8   9   10  11  12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, B3, B1, B2, B3, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			 {
				// Level 1.2
				// 1 2 3 4 5 6 7 8 9 10 11 12
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
				{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
				{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
				{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
				{ NO, NO, NO, B4, B3, B1, B2, B3, B4, NO, NO, NO }, // 8
				{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
				{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
				{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
				{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			 },
			 {
					// Level 1.3
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, EM, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, EM, EM, EM, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, NO, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, EM, NO, NO }, // 6
					{ NO, NO, EM, EM, EM, EM, EM, EM, EM, EM, EM, NO }, // 7
					{ NO, EM, B2, B4, B3, B1, B2, B2, B3, B4, B2, EM }, // 8
					{ NO, NO, EM, EM, EM, EM, EM, EM, EM, EM, EM, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, EM, NO, NO }, // 10
					{ NO, NO, NO, NO, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, EM, EM, EM, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, EM, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
				 }},
			// Level 2.
			{
			// Level 2.1
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 2.2
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 2.3
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			} 
			},
			// Level 3
			{
			// Level 3.1.
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 3.2
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 3.3
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			} 
			},
			// Level 4
			{
			// Level 4.1.
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 4.2
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 4.3
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			} 
			},
			// Level 5
			{
			// Level 5.1.
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 5.2
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			},
			// Level 5.3
			{
					// 1 2 3 4 5 6 7 8 9 10 11 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 4
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 12
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
			} 
			} 
			};
//			{ { { NO, NO, NO, NO, EM, EM, EM }, { NO, NO, NO, EM, EM, EM, EM }, { NO, NO, EM, EM, EM, EM, B1 },
//					{ NO, EM, EM, B2, B2, EM, B1 }, { EM, B1, WA, EM, EM, EM, NO }, { EM, EM, EM, B1, B1, NO, NO },
//					{ B2, EM, EM, EM, NO, NO, NO } } },
//
//			{ { { NO, NO, NO, NO, EM, EM, EM }, { NO, NO, EM, EM, EM, EM, EM }, { NO, EM, B3, EM, EM, EM, B1 },
//					{ NO, EM, EM, B2, B2, EM, B1 }, { EM, B1, EM, EM, EM, EM, EM }, { EM, EM, EM, B1, B1, EM, NO },
//					{ B2, EM, EM, EM, NO, NO, NO } } },
//
//			{ { { EM, EM, EM }, { EM, WA, EM }, { EM, EM, EM } } },
//
//			{ {
//					// 1 2 3 4 5 6 7 8 9 10 11 12
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 1
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 2
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 3
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 4
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 5
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 6
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 7
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 8
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 9
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 10
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 11
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 12
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 13
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM }, // 14
//					{ EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM, EM } // 15
//			} },
//
//			{ {
//					// 1 2 3 4 5 6 7 8 9 10 11 12
//					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 1
//					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 2
//					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 3
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 4
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 5
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 6
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 7
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 8
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 9
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 10
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 11
//					{ NO, NO, NO, EM, EM, EM, EM, EM, EM, NO, NO, NO }, // 12
//					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 13
//					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO }, // 14
//					{ NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO, NO } // 15
//			} } };
}
