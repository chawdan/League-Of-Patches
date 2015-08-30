package com.leaguebeta.db.model.Aggregate;

import java.util.ArrayList;

public class BannedChampionBean {
	public static transient ArrayList<String> queryParams, removeParams;
	static{
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();

		queryParams.add("championId");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		queryParams.add("rank");
		queryParams.add("pickTurn");
		
		removeParams.add("championId");
		removeParams.add("pickTurn");
		removeParams.add("rank");
		removeParams.add("weekDate");
		removeParams.add("yearDate");
	}
	int championId;	//	int	Banned champion ID
	int pickTurn;	//	int	Turn during which the champion was banned
	int weekDate, yearDate;
	int rank;
	int qty;
	
	public BannedChampionBean(int championId, int pickTurn, int weekDate, int yearDate, int rank) {
		super();
		this.championId = championId;
		this.pickTurn = pickTurn;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
		this.rank = rank;
		this.qty = 1;
	}

	
	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public int getWeekDate() {
		return weekDate;
	}


	public void setWeekDate(int weekDate) {
		this.weekDate = weekDate;
	}


	public int getYearDate() {
		return yearDate;
	}


	public void setYearDate(int yearDate) {
		this.yearDate = yearDate;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
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
