package com.leaguebeta.server;

public class URLObject {
	int flag;
	String url;
	public URLObject(int flag, String url) {
		super();
		this.flag = flag;
		this.url = url;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
