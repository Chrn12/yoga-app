package com.woniu.utils;

import java.util.List;

public class JsonResult {
	private String code;
	private String message;
	private List<?> list;
	private Object obj;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public JsonResult() {}
	public JsonResult(String code, String message, List<?> list, Object obj) {
		this.code = code;
		this.message = message;
		this.list = list;
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", message=" + message + ", list=" + list + ", obj=" + obj + "]";
	}
	
	
}
