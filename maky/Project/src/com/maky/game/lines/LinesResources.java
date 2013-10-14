package com.maky.game.lines;

import java.io.IOException;

import com.maky.app.resource.IResources;
import com.maky.app.resource.RLinesMap;
import com.maky.app.resource.Resource;
import com.maky.app.resource.ResourceManager;
import com.maky.environment.GraphicsConfig;
import com.maky.game.lines.model.level.Level;

public class LinesResources implements IResources {
	
	public static final short NO_CELL = Level.GROUND_NOCELL;
	public static final short EM_CELL = Level.GROUND_EMPTY;
	public static final short B1_CELL = Level.GROUND_EMPTY | Level.BALL_1;
	public static final short B2_CELL = Level.GROUND_EMPTY | Level.BALL_2;
	public static final short B3_CELL = Level.GROUND_EMPTY | Level.BALL_3;
	public static final short B4_CELL = Level.GROUND_EMPTY | Level.BALL_4;
	public static final short B5_CELL = Level.GROUND_EMPTY | Level.BALL_5;
	public static final short B6_CELL = Level.GROUND_EMPTY | Level.BALL_6;
	public static final short B7_CELL = Level.GROUND_EMPTY | Level.BALL_7;
	public static final short B8_CELL = Level.GROUND_EMPTY | Level.BALL_8;
	public static final short B9_CELL = Level.GROUND_EMPTY | Level.BALL_9;
	public static final short B10_CELL = Level.GROUND_EMPTY | Level.BALL_COMBO_1;
	public static final short B11_CELL = Level.GROUND_EMPTY | Level.BALL_COMBO_2;
	public static final short B12_CELL = Level.GROUND_EMPTY | Level.BALL_COMBO_3;
	
	private static final short NO = NO_CELL;
	private static final short EM = EM_CELL;
	private static final short B1 = B1_CELL;
	private static final short B2 = B2_CELL;
	private static final short B3 = B3_CELL;
	private static final short B4 = B4_CELL;
	private static final short B5 = B5_CELL;
	private static final short B6 = B6_CELL;
	private static final short B7 = B7_CELL;
	private static final short B8 = B8_CELL;
	private static final short B9 = B9_CELL;
	private static final short B0 = B10_CELL;
	private static final short BA = B11_CELL;
	private static final short BB = B12_CELL;	
	private static final short WA = Level.GROUND_WALL;
	private static final short WS = Level.GROUND_STRONGWALL;
	
	
	private static final short[][][] maps = 
		{
			{// Level 1.1
				//1  2  3  4  5  6  7  8  9 10 11 12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 1
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 2
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 3
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 4
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 5
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 6
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 7
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 8
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 9
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //10
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //11
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //13
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //14
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}  //15
			},
			{// Level 1.2
				//1  2  3  4  5  6  7  8  9 10 11 12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 1
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 2
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 3
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 4
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 5
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 6
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 7
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 8
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 9
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //10
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //11
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //13
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //14
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}  //15
			},
			{//	Level 1.3
				//1  2  3  4  5  6  7  8  9 10 11 12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 1
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 2
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 3
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 4
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 5
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 6
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 7
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 8
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 9
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //10
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //11
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //13
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //14
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}  //15
			},
			{
				{NO_CELL,NO_CELL,NO_CELL,NO_CELL,EM_CELL,EM_CELL,EM_CELL},
				{NO_CELL,NO_CELL,NO_CELL,EM_CELL,EM_CELL,EM_CELL,EM_CELL},
				{NO_CELL,NO_CELL,EM_CELL,EM_CELL,EM_CELL,EM_CELL,B1_CELL},
				{NO_CELL,EM_CELL,EM_CELL,B2_CELL,B2_CELL,EM_CELL,B1_CELL},
				{EM_CELL,B1_CELL,WA     ,EM_CELL,EM_CELL,EM_CELL,NO_CELL},
				{EM_CELL,EM_CELL,EM_CELL,B1_CELL,B1_CELL,NO_CELL,NO_CELL},
				{B2_CELL,EM_CELL,EM_CELL,EM_CELL,NO_CELL,NO_CELL,NO_CELL}
			},
			{
				{NO_CELL,NO_CELL,NO_CELL,NO_CELL,EM_CELL,EM_CELL,EM_CELL},
				{NO_CELL,NO_CELL,EM_CELL,EM_CELL,EM_CELL,EM_CELL,EM_CELL},
				{NO_CELL,EM_CELL,B3_CELL,EM_CELL,EM_CELL,EM_CELL,B1_CELL},
				{NO_CELL,EM_CELL,EM_CELL,B2_CELL,B2_CELL,EM_CELL,B1_CELL},
				{EM_CELL,B1_CELL,EM_CELL,EM_CELL,EM_CELL,EM_CELL,EM_CELL},
				{EM_CELL,EM_CELL,EM_CELL,B1_CELL,B1_CELL,EM_CELL,NO_CELL},
				{B2_CELL,EM_CELL,EM_CELL,EM_CELL,NO_CELL,NO_CELL,NO_CELL}
			},
			{
				{EM_CELL,EM_CELL,EM_CELL},
				{EM_CELL,WA		,EM_CELL},
				{EM_CELL,EM_CELL,EM_CELL}
			},
			{
				//1  2  3  4  5  6  7  8  9 10 11 12
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 1
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 2
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 3
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 4
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 5
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 6
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 7
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 8
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, // 9
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, //10
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, //11
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, //12
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, //13
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}, //14
				{EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM,EM}  //15
			},
			{
				//1  2  3  4  5  6  7  8  9 10 11 12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 1
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 2
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, // 3
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 4
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 5
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 6
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 7
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 8
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, // 9
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //10
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //11
				{NO,NO,NO,EM,EM,EM,EM,EM,EM,NO,NO,NO}, //12
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //13
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}, //14
				{NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO,NO}  //15
			}

		};
	
	// Constants
	public static final int CELL_WIDTH = GraphicsConfig.getScaledX(40);
	public static final int CELL_HEIGHT = GraphicsConfig.getScaledY(40);

	// Bytes array
	public static final int MAP_LEVEL1 = 3001;
	public static final int MAP_LEVEL2 = 3002;
	public static final int MAP_LEVEL3 = 3003;
	public static final int MAP_LEVEL4 = 3004;
	public static final int MAP_LEVEL5 = 3005;
	public static final int MAP_LEVEL6 = 3006;
	public static final int MAP_LEVEL7 = 3007;

	// Levels
	public static final RLinesMap mapLevel1 = new RLinesMap(MAP_LEVEL1, maps[0], 1, "Level1", "Decription of level 1");
	public static final RLinesMap mapLevel2 = new RLinesMap(MAP_LEVEL2, maps[1], 3, "Level2", "Decription of level 2");
	public static final RLinesMap mapLevel3 = new RLinesMap(MAP_LEVEL3, maps[2], 3, "Level3", "Decription of level 3");
	public static final RLinesMap mapLevel4 = new RLinesMap(MAP_LEVEL4, maps[1], 3, "Level1", "Decription of level 1");
	public static final RLinesMap mapLevel5 = new RLinesMap(MAP_LEVEL5, maps[0], 3, "Level2", "Decription of level 2");
	
	// Game images
	public static final int IMAGE_BALL_EMPTY = 4001;
	public static final int IMAGE_BALL_WHITE = 4002;
	public static final int IMAGE_BALL_BLACK = 4003;
	public static final int IMAGE_BALL_BLUE = 4004;
	public static final int IMAGE_BALL_YELLOW = 4005;
	public static final int IMAGE_BALL_RED = 4006;
	public static final int IMAGE_BALL_GREEN = 4007;
	public static final int IMAGE_BALL_ORANGE = 4008;

	public static final int IMAGE_SPRITE_WALL = 4014;
	public static final int IMAGE_SPRITE_STRONG_WALL = 4015;

	// Pictures
	public static final int IMAGE_LOGO_BACKGROUND = 5000;
	public static final int IMAGE_SPLASH_BACKGROUND = 5001;
	public static final int IMAGE_SPLASH_BUTTON_OK = 5002;
	public static final int IMAGE_SPLASH_BUTTON_OK_HIGHLIGHTED = 5003;
	public static final int IMAGE_LEVEL_MAP_BACKGROUND = 5004;
	public static final int IMAGE_LEVEL1_BACKGROUND = 5005;
	public static final int IMAGE_LEVEL2_BACKGROUND = 5006;
	public static final int IMAGE_LEVEL3_BACKGROUND = 5007;
	public static final int IMAGE_LEVEL4_BACKGROUND = 5008;
	public static final int IMAGE_LEVEL5_BACKGROUND = 5009;
	public static final int IMAGE_LEVEL6_BACKGROUND = 5010;
	public static final int IMAGE_LEVEL7_BACKGROUND = 5011;
	public static final int IMAGE_MENU_BACKGROUND = 5012;

	public static final void configure() {
		ResourceManager.getInstance().addResource(mapLevel1);
		ResourceManager.getInstance().addResource(mapLevel2);
	}
	
	public static void main(String[] args) {
		try {
			mapLevel1.setSource(Resource.STORE_TYPE_FILE, "res/level1.map", 0);
			mapLevel1.save();
			mapLevel2.setSource(Resource.STORE_TYPE_FILE, "res/level2.map", 0);
			mapLevel2.save();
			mapLevel3.setSource(Resource.STORE_TYPE_FILE, "res/level3.map", 0);
			mapLevel3.save();
			mapLevel4.setSource(Resource.STORE_TYPE_FILE, "res/level4.map", 0);
			mapLevel4.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
