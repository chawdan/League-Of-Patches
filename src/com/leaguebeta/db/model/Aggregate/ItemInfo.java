package com.leaguebeta.db.model.Aggregate;

public class ItemInfo {
	int itemId;
	int timestamp;
	String type;
	public ItemInfo(int itemId, int timestamp, String type) {
		super();
		this.itemId = itemId;
		this.timestamp = timestamp;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
}
