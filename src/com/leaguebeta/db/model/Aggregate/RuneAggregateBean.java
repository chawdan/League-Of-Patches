package com.leaguebeta.db.model.Aggregate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Participant.RuneBean;

public class RuneAggregateBean {
	public static transient ArrayList<String> queryParams, removeParams;

	static {
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();

		queryParams.add("runeId");
		queryParams.add("championId");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		queryParams.add("rank");
		queryParams.add("division");
		queryParams.add("matchDuration");

		removeParams.add("runeId");
		removeParams.add("rank");
		removeParams.add("division");
		removeParams.add("weekDate");
		removeParams.add("yearDate");
		removeParams.add("matchDuration");
		removeParams.add("championId");

	}

	/* avg win rate and stats */
	int wins;

	int qty;
	int runeId;
	int championId;

	/* kda avg */
	int rank;
	int division;
	int assists;
	int deaths;
	int kills;

	int qtyUsed;// difference in that this can be more than qty because you can
				// use same rune in same page

	/* lanes and roles */
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

	/* time flag */
	int weekDate, yearDate;
	int matchDuration; // 0 for 0-10 minutes, 1 for 10-20 minutes, 2 for 20-30
						// minutes, 3 for 30-40 minutes, 4 for 40-50 minutes,
						// and so on.

	public RuneAggregateBean(int championId, int wins, int qty, int runeId, int rank, int division, int assists,
			int deaths, int kills, int qtyUsed, HashMap<String, Integer> laneQty, HashMap<String, Integer> roleQty,
			int weekDate, int yearDate, int matchDuration) {
		super();
		this.championId = championId;
		this.wins = wins;
		this.qty = qty;
		this.runeId = runeId;
		this.rank = rank;
		this.division = division;
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.qtyUsed = qtyUsed;
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
		this.weekDate = weekDate;
		this.yearDate = yearDate;
		this.matchDuration = matchDuration;
	}

	public static RuneAggregateBean[] playerMatchBeanToRuneAggregateBean(PlayerMatchBean bean, RankBean rankBean,
			long matchLength) {
		List<RuneAggregateBean> beans = new ArrayList<>();
		int qty = 1;
		int championId = bean.getChampionId();

		/* kda avg */
		int rank = rankBean.getRank();
		int division = rankBean.getDivision();
		int assists = bean.getAssists();
		int deaths = bean.getDeaths();
		int kills = bean.getKills();
		int matchDur = (int) matchLength / 60 / 10;
		int wins = bean.isWinner() ? 1 : 0;

		int weekDate = bean.getWeekDate();
		int yearDate = bean.getYearDate();

		HashMap<String, Integer> laneQty = new HashMap<String, Integer>();
		laneQty.put(bean.getTimeline().getLane(), 1);
		HashMap<String, Integer> roleQty = new HashMap<String, Integer>();
		roleQty.put(bean.getTimeline().getRole(), 1);

		RuneBean[] items = bean.getRunes();
		for (int i = 0; i < items.length; i++) {
			RuneBean itemId = items[i];
			beans.add(new RuneAggregateBean(championId, wins, qty, itemId.getRuneId(), rank, division, assists, deaths,
					kills, itemId.getRuneRank(), laneQty, roleQty, weekDate, yearDate, matchDur));
		}

		return beans.toArray(new RuneAggregateBean[beans.size()]);
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

	public int getRuneId() {
		return runeId;
	}

	public void setRuneId(int runeId) {
		this.runeId = runeId;
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

	public int getQtyUsed() {
		return qtyUsed;
	}

	public void setQtyUsed(int qtyUsed) {
		this.qtyUsed = qtyUsed;
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

}
