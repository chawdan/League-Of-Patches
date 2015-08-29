package com.leaguebeta.db.model.Aggregate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.leaguebeta.db.model.Participant.PlayerMatchBean;

public class ItemAggregateBean {
	public static transient ArrayList<String> queryParams, removeParams;
	static{
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();
		
		queryParams.add("itemId");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		
		removeParams.add("weekDate");
		removeParams.add("yearDate");
		removeParams.add("itemId");
	}
	/*intended to be singleton in the database*/
	int itemId;
	/* HashMap<Champion ID, amount of x */
	int assists;
	int deaths;
	int kills;
	int wins;
	/*roles and lanes*/
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
	int qty;
	
	int weekDate, yearDate;

	public ItemAggregateBean(int itemId, int assists, int deaths, int kills, HashMap<String, Integer>laneQty,
			HashMap<String, Integer> roleQty, int qty, int weekDate,
			int yearDate, int wins) {
		super();
		this.itemId = itemId;
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
		this.qty = qty;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
	}
	
	public static ItemAggregateBean[] playerMatchBeanToItemAggregateBean(PlayerMatchBean bean){
		List<ItemAggregateBean> beans = new ArrayList<>();
		int qty = 1;
		
		int assists = bean.getAssists();
		int deaths = bean.getDeaths();
		int kills = bean.getKills();
		
		int wins = bean.isWinner() ? 1 : 0;

		int weekDate = bean.getWeekDate();
		int yearDate = bean.getYearDate();
		
		HashMap<String, Integer> laneQty = new HashMap<String, Integer>();
		laneQty.put(bean.getTimeline().getLane(), 1);
		HashMap<String, Integer> roleQty = new HashMap<String, Integer>();
		roleQty.put(bean.getTimeline().getRole(), 1);
		
		int[] items = bean.getItems();
		for(int i = 0; i < items.length; i++){
			int itemId = items[i];
			beans.add(new ItemAggregateBean( itemId,  assists,  deaths,  kills,  laneQty,  roleQty,  qty,  weekDate,
					 yearDate, wins));
		}
		return beans.toArray(new ItemAggregateBean[beans.size()]);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	
}
