package com.ssm.rest.demo.common;

/*
 * restful unify Response class
 */
public class Response {

	private static final String OK = "ok";
	private static final String ERROR = "error";
	
	private Meta meta;
	private Object data;
	
	public Response success() {
		this.meta = new Meta(true, OK);
		return this;
	}
	
	public Response success(Object data) {
		this.meta = new Meta(true, OK);
		this.data = data;
		return this;
	}

	public Response failure() {
		this.meta = new Meta(false, ERROR);
		return this;
	}
	
	public Response failure(Object data) {
		this.meta = new Meta(false, ERROR);
		this.data = data;
		return this;
	}
	
	public Meta getMeta() {
		return meta;
	}
	
	public Object getData() {
		return data;
	}
}
