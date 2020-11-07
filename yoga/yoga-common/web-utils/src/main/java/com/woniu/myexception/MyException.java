package com.woniu.myexception;

public class MyException extends RuntimeException{
	private String code;
	private String mess;
	
	
	public MyException(String code, String mess) {
		super();
		this.code = code;
		this.mess = mess;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	
	
	
	
	

}
