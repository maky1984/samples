package com.maky.app.resource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.maky.util.StaticUtils;

public class RLinesMap extends RBytesMatrix {

	private static final int DEFAULT_TYPE_NUMBER = 4;
	private int typeNumber = DEFAULT_TYPE_NUMBER;
	private String name;
	private String description;

	public RLinesMap(int id) {
		super(id, Resource.TYPE_GAME_LINES_MAP);
	}

	public RLinesMap(int id, short[][] data, int typeNumber, String name, String description) {
		super(id, StaticUtils.shortArrayToByteArray(data));
		this.typeNumber = typeNumber;
		this.name = name;
		this.description = description;
	}

	public short[] getBalls() {
		short[] balls = new short[typeNumber];
		for (int i = 0; i < balls.length; i++) {
			balls[i] = (short) ((i + 1) << 2);
		}
		return balls;
	}

	public short[][] getLevelData() {
		return StaticUtils.byteArrayToShortArray(getData());
	}

	public void load(InputStream in) throws IOException {
		DataInputStream input = new DataInputStream(in);
		byte[] bytes = new byte[4];
		in.read(bytes);
		typeNumber = StaticUtils.byteArrayToInt(bytes);
		int stringSize = input.readInt();
		char[] line = new char[stringSize];
		for (int j = 0; j < stringSize; j++) {
			line[j] = input.readChar();
		}
		name = new String(line, 0, stringSize);
		stringSize = input.readInt();
		line = new char[stringSize];
		for (int j = 0; j < stringSize; j++) {
			line[j] = input.readChar();
		}
		description = new String(line, 0, stringSize);
		super.load(in);
	}

	public void save(OutputStream out) throws IOException {
		DataOutputStream output = new DataOutputStream(out);
		output.write(StaticUtils.intToByteArray(typeNumber));
		output.writeInt(name.length());
		output.writeChars(name);		
		output.writeInt(description.length());
		output.writeChars(description);		
		super.save(out);
	}
}
