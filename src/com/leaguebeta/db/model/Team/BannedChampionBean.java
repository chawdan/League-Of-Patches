package com.leaguebeta.db.model.Team;

public class BannedChampionBean {
	int championId;	//	int	Banned champion ID
	int pickTurn;	//	int	Turn during which the champion was banned
	
	public BannedChampionBean(int championId, int pickTurn) {
		this.championId = championId;
		this.pickTurn = pickTurn;
	}
	
	public int getChampionId() {
		return championId;
	}
	public void setChampionId(int championId) {
		this.championId = championId;
	}
	public int getPickTurn() {
		return pickTurn;
	}
	public void setPickTurn(int pickTurn) {
		this.pickTurn = pickTurn;
	}
	
}
