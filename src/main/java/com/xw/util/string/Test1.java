package com.xw.util.string;

public class Test1 {


	/**
	 * 确定字符互异
	 * @param iniString
	 * @return
	 */
	public boolean checkDifferent(String iniString) {
		return !iniString.matches(".*(.)(.*\\1).*");
	}
	
	public static void main(String[] args) {
		Test1 t1 = new Test1();
		System.out.println(t1.checkDifferent("ooooooo"));
	}
}
