package com.leaguebeta.db.model.Participant;

public class RuneBean {
	int runeId;
	int runeRank;
	public RuneBean(int runeId, int runeRank) {
		this.runeId = runeId;
		this.runeRank = runeRank;
	}
	public int getRuneId() {
		return runeId;
	}
	public void setRuneId(int runeId) {
		this.runeId = runeId;
	}
	public int getRuneRank() {
		return runeRank;
	}
	public void setRuneRank(int runeRank) {
		this.runeRank = runeRank;
	}
	
}
