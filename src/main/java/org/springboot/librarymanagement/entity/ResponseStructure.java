package org.springboot.librarymanagement.entity;

public class ResponseStructure<T> {

	private String message;
	private int statusCode;
	private T data;

	public ResponseStructure(String message, int statusCode, T data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}

	public ResponseStructure() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
