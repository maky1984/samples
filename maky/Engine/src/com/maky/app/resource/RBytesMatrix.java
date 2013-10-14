package com.maky.app.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.maky.util.StaticUtils;
import com.maky.util.log.Logger;

public class RBytesMatrix extends Resource {

	private byte[][] data;

	protected RBytesMatrix(int id, int type) {
		super(id, type);
	}

	public RBytesMatrix(int id) {
		super(id, TYPE_BYTES_MATRIX);
	}

	public RBytesMatrix(int id, byte[][] data) {
		super(id, TYPE_BYTES_MATRIX);
		this.data = data;
	}

	public byte[][] getData() {
		if (data == null) {
			data = new byte[10][10];
			Logger.getInstance(this).logError(" No data found in RBytesMatrix resource. Default data created.");
		}
		return data;
	}

	public void load(InputStream in) throws IOException {
		byte[] bytes = new byte[4];
		in.read(bytes);
		int size = StaticUtils.byteArrayToInt(bytes);
		data = new byte[size][];
		for (int i = 0; i < size; i++) {
			in.read(bytes);
			data[i] = new byte[StaticUtils.byteArrayToInt(bytes)];
			in.read(data[i]);
		}
	}

	public void save(OutputStream out) throws IOException {
		out.write(StaticUtils.intToByteArray(data.length));
		for (int i = 0; i < data.length; i++) {
			out.write(StaticUtils.intToByteArray(data[i].length));
			out.write(data[i]);
		}
	}

}
