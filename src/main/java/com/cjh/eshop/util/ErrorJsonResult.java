package com.cjh.eshop.util;

public class ErrorJsonResult extends JsonResult<String> {

	private static final long serialVersionUID = 4334576292379156831L;
	
	/*
	public ErrorJsonResult(String message) {
		setCode(code);
	}
	*/
	
	public ErrorJsonResult(int code, String data) {
		super(code, data);
	}
}
