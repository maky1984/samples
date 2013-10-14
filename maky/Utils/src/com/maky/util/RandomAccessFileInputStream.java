package com.maky.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileInputStream extends InputStream {

	RandomAccessFile file;

	public RandomAccessFileInputStream(RandomAccessFile file) {
		this.file = file;
	}

	// @Override
	public int read() throws IOException {
		return file.read();
	}

	// @Override
	public int read(byte[] b) throws IOException {
		return file.read(b);
	}

	// @Override
	public int read(byte[] b, int off, int len) throws IOException {
		return file.read(b, off, len);
	}

	// @Override
	public boolean markSupported() {
		return false;
	}

	// @Override
	public int available() throws IOException {
		return (int) (file.length() - file.getFilePointer());
	}

	// @Override
	public long skip(long n) throws IOException {
		if (file.getFilePointer() + n > file.length()) {
			n = file.length() - file.getFilePointer();
		}
		file.seek(file.getFilePointer() + n);
		return n;
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
