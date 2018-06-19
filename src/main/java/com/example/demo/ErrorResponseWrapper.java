package com.example.demo;

public class ErrorResponseWrapper
{
    private int code;
    private String reason;

    public ErrorResponseWrapper(int code, String reason) {
        this.code = code;
        this.reason = reason;
	}
	
	public ErrorResponseWrapper(String reason) {
        this(420, reason);
    }

    public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}