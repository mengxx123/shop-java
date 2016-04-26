package com.cjh.eshop.util;

import java.io.Serializable;

/** API 返回的 Json 结果 */
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = -6226523971779227493L;

	private int code; // 状态码，若请求成功则为0，请求出错则是状态码
	private T data; // 如果请求成功，则是请求结果；若请求失败，则是错误描述文字

	public JsonResult(int code, T data) {
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
