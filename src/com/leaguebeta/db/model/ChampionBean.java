package com.leaguebeta.db.model;

import java.util.Calendar;

public class ChampionBean extends GenericBean{
	int rank;
	int division;
	int assists;
	int deaths;
	int kills;
	int qty;
	int championId;
	int wins;
	int weekDate, yearDate;
	/*You should not have a blank constructor for this bean*/
	public ChampionBean(int tier, int division, int assists, int deaths, int kills, int qty, int championId, int wins){
		this.rank = rank;
		this.division = division;
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.qty = qty;
		this.championId = championId;
		this.wins = wins;
		weekDate = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		yearDate = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public ChampionBean(RankBean rank, int assists, int deaths, int kills, int qty, int championId, int wins){
		this.rank = rank.getRank();
		this.division = rank.getDivision();
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.qty = qty;
		this.championId = championId;
		this.wins = wins;
		weekDate = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		yearDate = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public int getRank() {
		return rank;
	}

	public void setTier(int tier) {
		this.rank = tier;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
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

	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getChampionId() {
		return championId;
	}
	public void setChampionId(int championId) {
		this.championId = championId;
	}
}
