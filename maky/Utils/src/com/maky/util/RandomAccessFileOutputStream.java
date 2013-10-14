package com.maky.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileOutputStream extends OutputStream {

	RandomAccessFile file;

	public RandomAccessFileOutputStream(RandomAccessFile file) {
		this.file = file;
	}

	// @Override
	public void write(int b) throws IOException {
		file.write(b);
	}

	// @Override
	public void write(byte b[]) throws IOException {
		file.write(b);
	}

	public void write(byte[] b, int off, int len) throws IOException {
		file.write(b, off, len);
	}

	public long length() throws IOException {
		return file.length();
	}

	public long getFilePointer() throws IOException {
		return file.getFilePointer();
	}

	public void seek(long n) throws IOException {
		file.seek(n);
	}
}
