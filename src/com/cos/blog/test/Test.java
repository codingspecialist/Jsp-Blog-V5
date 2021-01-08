package com.cos.blog.test;

public class Test {
	public static void main(String[] args) {
		String n = System.getProperty("sun.boot.class.path");
		//System.out.println(n);
		String n2 = System.getProperty("java.class.path");
		String n3[] = n2.split(";");
		for (String s : n3) {
			System.out.println(s);
		}
	}
}
