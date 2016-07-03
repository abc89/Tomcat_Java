package com.myweb.exception;

public class MethodNoImplementException extends Exception {
	public void MethodNoImplementException() {
		System.out.println("方法未实现");
		super.printStackTrace();
	}
}