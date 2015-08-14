package com.leaguebeta.db.model;

public class ItemBean extends GenericBean{
	int itemId;
	int rank;
	int division;
	int assists;
	int deaths;
	int kills;
	int wins;
	int qty;
	int weekDate, yearDate;
	public ItemBean(int itemId, int tier, int division, int assists, int deaths, int kills, int wins, int qty,
			int weekDate, int yearDate) {
		this.itemId = itemId;
		this.rank = tier;
		this.division = division;
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.wins = wins;
		this.qty = qty;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
	}
	public ItemBean(int itemId, RankBean rank, int assists, int deaths, int kills, int wins, int qty,
			int weekDate, int yearDate) {
		this.itemId = itemId;
		this.rank = rank.getRank();
		this.division = rank.getDivision();
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.wins = wins;
		this.qty = qty;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
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
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
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
	@Override
	public String toString() {
		return "ItemBean [itemId=" + itemId + ", rank=" + rank + ", division=" + division + ", assists=" + assists
				+ ", deaths=" + deaths + ", kills=" + kills + ", wins=" + wins + ", qty=" + qty + ", weekDate="
				+ weekDate + ", yearDate=" + yearDate + "]";
	}	
}
