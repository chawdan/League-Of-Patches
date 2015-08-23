package com.leaguebeta.db.model.Participant;

public class MasteryBean {
	int masteryRank;
	int masteryId;
	public MasteryBean(int masteryRank, int masteryId) {
		super();
		this.masteryRank = masteryRank;
		this.masteryId = masteryId;
	}
	public int getMasteryRank() {
		return masteryRank;
	}
	public void setMasteryRank(int masteryRank) {
		this.masteryRank = masteryRank;
	}
	public int getMasteryId() {
		return masteryId;
	}
	public void setMasteryId(int masteryId) {
		this.masteryId = masteryId;
	}
	@Override
	public String toString() {
		return "MasteryBean [masteryRank=" + masteryRank + ", masteryId=" + masteryId + "]";
	}
}
