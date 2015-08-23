package com.leaguebeta.db.model.Aggregate;

import java.util.HashMap;

public class TimeStats{
	int qty;
	int currentGold;
	int totalGold;
	int level;
	int minionsKilled;
	public TimeStats(int qty, int currentGold, int totalGold, int level, int minionsKilled) {
		super();
		this.currentGold = currentGold;
		this.totalGold = totalGold;
		this.level = level;
		this.minionsKilled = minionsKilled;
	}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getCurrentGold() {
		return currentGold;
	}
	public void setCurrentGold(int currentGold) {
		this.currentGold = currentGold;
	}
	public int getTotalGold() {
		return totalGold;
	}
	public void setTotalGold(int totalGold) {
		this.totalGold = totalGold;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMinionsKilled() {
		return minionsKilled;
	}
	public void setMinionsKilled(int minionsKilled) {
		this.minionsKilled = minionsKilled;
	}
	
}
