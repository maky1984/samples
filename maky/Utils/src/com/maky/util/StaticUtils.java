package com.maky.util;

/**
 * 
 * Class with utility static functions
 * 
 * @author michael
 */
public class StaticUtils {

	/**
	 * Converts integer to byte array
	 * 
	 * @param value
	 *            - integer value
	 * @return
	 */
	public static final byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
	}

	/**
	 * Converts byte array to integer value
	 * 
	 * @param bytes
	 *            - bytes array
	 * @return
	 */
	public static final int byteArrayToInt(byte[] bytes) {
		return (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
	}

	/**
	 * Converts byte array to short array, combining two adjacent bytes
	 * 
	 * @param bytes
	 * @return
	 */
	public static final short[][] byteArrayToShortArray(byte[][] bytes) {
		short[][] result = new short[bytes.length][];
		for (int i = 0; i < bytes.length; i++) {
			result[i] = new short[bytes[i].length / 2];
			for (int j = 0; j < bytes[i].length / 2; j++) {
				result[i][j] = (short) (bytes[i][j * 2] & 0x00FF | bytes[i][j * 2 + 1] << 8);
			}
		}
		return result;
	}

	/**
	 * Converts short array to byte array, twice bigger then short
	 * 
	 * @param shorts
	 * @return
	 */
	public static final byte[][] shortArrayToByteArray(short[][] shorts) {
		byte[][] bytes = new byte[shorts.length][];
		for (int i = 0; i < shorts.length; i++) {
			bytes[i] = new byte[shorts[i].length * 2];
			for (int j = 0; j < shorts[i].length; j++) {
				bytes[i][j * 2] = (byte) (shorts[i][j] & 0xFF);
				bytes[i][j * 2 + 1] = (byte) ((shorts[i][j] >> 8) & 0xFF);
			}
		}
		return bytes;
	}

}
