package com.coredev.utils;


import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;

public class StreamUtils {
	public static String read(InputStream inputStream) throws IOException {
		while (inputStream.available() > 0) {
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			return new String(buffer);
		}
		return null;
	}

	public static void write(ServletOutputStream outputStream, String result) {
		try {
			outputStream.write(result.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}