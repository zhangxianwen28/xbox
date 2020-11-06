package com.xw.util.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class IOUtil {
	public static void main(String[] args) {
		File file = new File("");
		System.out.println(txt2String(file));
	}

	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = "";
			while ((s = br.readLine()) != null) {
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}
}
