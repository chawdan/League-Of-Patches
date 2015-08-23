package com.leaguebeta.server;

public class ResponseObject {
	boolean retrieved;
	String json;
	public ResponseObject(boolean ret, String j){
		retrieved = ret;
		json = j;
	}
	public boolean isRetrieved() {
		return retrieved;
	}
	public void setRetrieved(boolean retrieved) {
		this.retrieved = retrieved;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
}
