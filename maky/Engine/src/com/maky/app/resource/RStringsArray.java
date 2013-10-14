package com.maky.app.resource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RStringsArray extends Resource {

	private String[] strings;

	public RStringsArray(int id) {
		super(id, TYPE_STRINGS_ARRAY);
	}

	public void load(InputStream in) throws IOException {
		DataInputStream input = new DataInputStream(in);
		int size = input.readInt();
		strings = new String[size];
		for (int i = 0; i < size; i++) {
			int lineSize = input.readInt();
			char[] line = new char[lineSize];
			for (int j = 0; j < lineSize; j++) {
				line[j] = input.readChar();
			}
			strings[i] = new String(line, 0, lineSize);
		}
	}

	public void save(OutputStream out) throws IOException {
		DataOutputStream output = new DataOutputStream(out);
		int size = strings.length;
		output.writeInt(size);
		for (int i = 0; i < size; i++) {
			output.writeInt(strings[i].length());
			output.writeChars(strings[i]);
		}
	}

}
