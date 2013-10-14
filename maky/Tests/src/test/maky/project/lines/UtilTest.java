package test.maky.project.lines;

import junit.framework.TestCase;

import com.maky.util.Util;

public class UtilTest extends TestCase {

	private boolean checkShorts(short[][] result, short[][] original) {
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

	private boolean checkBytes(byte[][] result, byte[][] original) {
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

	public void testShortToByteArrayConverter() {
		short[][] shorts = { { (short) 0xBFB5, 3 }, { 3, 4 } };
		byte[][] bytes = { { (byte) 0xBF, (byte) 0xB5, 2, 0 }, { 3, 0, 4, 0 } };
		byte[][] test1 = Util.shortArrayToByteArray(shorts);
		assertEquals(true, checkShorts(shorts, Util.byteArrayToShortArray(test1)));
		assertEquals(true, checkBytes(bytes, Util.shortArrayToByteArray(Util.byteArrayToShortArray(bytes))));
	}
}
