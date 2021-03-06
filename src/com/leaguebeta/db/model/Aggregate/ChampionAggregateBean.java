package com.leaguebeta.db.model.Aggregate;

import java.util.ArrayList;
import java.util.HashMap;

import com.leaguebeta.db.model.Participant.PlayerMatchBean;

public class ChampionAggregateBean {
	public static transient ArrayList<String> queryParams, removeParams;
	static{
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();

		queryParams.add("championId");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		
		removeParams.add("championId");
		removeParams.add("weekDate");
		removeParams.add("yearDate");
	}
	int qty;
	int championId;
	
	/*kda avg*/
	int assists;
	int deaths;
	int kills;
	
	/*lanes and roles*/
	int top;
	int mid;
	int middle;
	int bot;
	int jungle;
	
	int duo;
	int none;
	int solo;
	int duo_carry;
	int duo_support;
	
	/*avg win rate and stats*/
	int wins;
	/*time flag*/
	int weekDate, yearDate;
	public ChampionAggregateBean(int qty, int championId, int assists, int deaths, int kills,
			HashMap<String, Integer> laneQty, HashMap<String, Integer> roleQty, int wins, int weekDate, int yearDate) {
		super();
		this.qty = qty;
		this.championId = championId;
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.top = laneQty.getOrDefault("TOP", 0);
		this.middle = laneQty.getOrDefault("MIDDLE", 0);
		this.mid = laneQty.getOrDefault("MID", 0);
		this.bot = laneQty.getOrDefault("BOTTOM", 0);
		this.jungle = laneQty.getOrDefault("JUNGLE", 0);
		
		this.duo = roleQty.getOrDefault("DUO", 0);
		this.none = roleQty.getOrDefault("NONE", 0);
		this.solo = roleQty.getOrDefault("SOLO", 0);
		this.duo_carry = roleQty.getOrDefault("DUO_CARRY", 0);
		this.duo_support = roleQty.getOrDefault("DUO_SUPPORT", 0);
		this.wins = wins;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
	}
	
	public static ChampionAggregateBean playerMatchBeanToChampionAggregateBean(PlayerMatchBean bean){
		int qty = 1;
		int championId = bean.getChampionId();
		
		/*kda avg*/
		int assists = bean.getAssists();
		int deaths = bean.getDeaths();
		int kills = bean.getKills();
		
		HashMap<String, Integer> laneQty = new HashMap<String, Integer>();
		laneQty.put(bean.getTimeline().getLane(), 1);
		HashMap<String, Integer> roleQty = new HashMap<String, Integer>();
		roleQty.put(bean.getTimeline().getRole(), 1);
		int wins = bean.isWinner() ? 1 : 0;
		/*time flag*/
		int weekDate = bean.getWeekDate();
		int yearDate = bean.getYearDate();
		return new ChampionAggregateBean( qty,  championId,  assists,  deaths,  kills, 
				laneQty, roleQty,  wins,  weekDate,  yearDate);
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
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getMiddle() {
		return middle;
	}
	public void setMiddle(int middle) {
		this.middle = middle;
	}
	public int getBot() {
		return bot;
	}
	public void setBot(int bot) {
		this.bot = bot;
	}
	public int getJungle() {
		return jungle;
	}
	public void setJungle(int jungle) {
		this.jungle = jungle;
	}
	public int getDuo() {
		return duo;
	}
	public void setDuo(int duo) {
		this.duo = duo;
	}
	public int getNone() {
		return none;
	}
	public void setNone(int none) {
		this.none = none;
	}
	public int getSolo() {
		return solo;
	}
	public void setSolo(int solo) {
		this.solo = solo;
	}
	public int getDuo_carry() {
		return duo_carry;
	}
	public void setDuo_carry(int duo_carry) {
		this.duo_carry = duo_carry;
	}
	public int getDuo_support() {
		return duo_support;
	}
	public void setDuo_support(int duo_support) {
		this.duo_support = duo_support;
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
	
	
}
