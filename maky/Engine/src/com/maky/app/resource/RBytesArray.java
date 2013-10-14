package com.maky.app.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.maky.util.StaticUtils;

public class RBytesArray extends Resource {

	private byte[] data;

	public RBytesArray(int id) {
		super(id, TYPE_BYTES_ARRAY);
	}

	public RBytesArray(int id, byte[] data) {
		super(id, TYPE_BYTES_ARRAY);
		this.data = data;
	}

	public void load(InputStream in) throws IOException {
		byte[] bytes = new byte[4];
		in.read(bytes);
		int size = StaticUtils.byteArrayToInt(bytes);
		data = new byte[size];
		in.read(data);
	}

	public void save(OutputStream out) throws IOException {
		out.write(StaticUtils.intToByteArray(data.length));
		out.write(data);
	}

}
