package test.maky.project.lines.level;

import junit.framework.TestCase;

import com.maky.game.lines.model.LinesModel;
import com.maky.game.lines.model.level.Level;

public class LevelTest extends TestCase {

	private short[][] map = { { Level.GROUND_EMPTY + Level.BALL_1, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
			{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
			{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
			{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY } };

	private boolean checkMaps(short[][] result, short[][] original) {
		if (result.length != original.length)
			return false;
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				if (result[i][j] != original[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public void testMoveBall() {
		int typeNumber = 2;
		LinesModel model = new LinesModel();
		Level level = new Level(map, new short[] { Level.FUTURE_BALL_1, Level.FUTURE_BALL_2 }, model);
		level.newBallAddition = 0;
		int score = level.moveBall(0, 0, 1, 1, 0);
		short[][] newmap = level.getMap();
		short[][] resmap = { { Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY + Level.BALL_1, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY } };
		assertEquals(true, checkMaps(newmap, resmap));
		assertEquals(0, score);

		short[][] map2 = { { Level.GROUND_EMPTY + Level.BALL_1, Level.GROUND_EMPTY + Level.BALL_1, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY + Level.BALL_2, Level.GROUND_EMPTY + Level.BALL_2, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY + Level.BALL_1 } };
		typeNumber = 4;
		level = new Level(map2, new short[] { Level.FUTURE_BALL_1, Level.FUTURE_BALL_2, Level.FUTURE_BALL_3,
				Level.FUTURE_BALL_4 }, model);
		level.newBallAddition = 0;
		score = level.moveBall(3, 3, 0, 2, 0);
		short[][] newmap2 = level.getMap();
		short[][] resmap2 = { { Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY + Level.BALL_2, Level.GROUND_EMPTY + Level.BALL_2, Level.GROUND_EMPTY },
				{ Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY, Level.GROUND_EMPTY } };
		assertEquals(true, checkMaps(newmap2, resmap2));
		assertEquals(3, score);
	}

}
