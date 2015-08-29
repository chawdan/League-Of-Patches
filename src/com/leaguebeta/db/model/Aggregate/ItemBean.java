package com.leaguebeta.db.model.Aggregate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.leaguebeta.db.model.Participant.PlayerMatchBean;

public class ItemBean{
	public static transient ArrayList<String> queryParams, removeParams;
	static{
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();
		
		queryParams.add("itemId");
		queryParams.add("rank");
		queryParams.add("division");
		queryParams.add("championId");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		queryParams.add("matchDuration");
		
		removeParams.add("championId");
		removeParams.add("rank");
		removeParams.add("division");
		removeParams.add("weekDate");
		removeParams.add("yearDate");
		removeParams.add("itemId");
		removeParams.add("matchDuration");
	}
	int itemId;
	int rank;
	int division;
	int championId;
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
	/*damage avg*/
	int trueDamageTaken;
	int magicDamageTaken;
	int physicalDamageTaken;
	int totalDamageTaken;
	int magicDamageDealtToChampions;	//	long	Magical damage dealt to champions
	int physicalDamageDealtToChampions;
	int totalDamageDealtToChampions;
	int trueDamageDealtToChampions;
	/*time flag*/
	int weekDate, yearDate;
	int matchDuration; // 0 for 0-10 minutes, 1 for 10-20 minutes, 2 for 20-30 minutes, 3 for 30-40 minutes, 4 for 40-50 minutes, and so on.

	
	public ItemBean(int itemId, int rank, int division, int championId, int assists, int deaths, int kills,
			int wins, HashMap<String, Integer> laneQty, HashMap<String, Integer> roleQty, int qty,
			int trueDamageTaken, int magicDamageTaken, int physicalDamageTaken, int totalDamageTaken,
			int magicDamageDealtToChampions, int physicalDamageDealtToChampions, int totalDamageDealtToChampions,
			int trueDamageDealtToChampions, int weekDate, int yearDate, int matchDuration) {
		super();
		this.itemId = itemId;
		this.rank = rank;
		this.division = division;
		this.championId = championId;
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.wins = wins;
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
		this.qty = qty;
		this.trueDamageTaken = trueDamageTaken;
		this.magicDamageTaken = magicDamageTaken;
		this.physicalDamageTaken = physicalDamageTaken;
		this.totalDamageTaken = totalDamageTaken;
		this.magicDamageDealtToChampions = magicDamageDealtToChampions;
		this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
		this.trueDamageDealtToChampions = trueDamageDealtToChampions;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
		this.matchDuration = matchDuration;
	}

	public static ItemBean[] playerMatchBeanToItemBean(PlayerMatchBean bean, RankBean rankBean, long matchLength){
		List<ItemBean> beans = new ArrayList<>();
		int qty = 1;
		int championId = bean.getChampionId();
		
		/*kda avg*/
		int rank = rankBean.getRank();
		int division = rankBean.getDivision();
		int assists = bean.getAssists();
		int deaths = bean.getDeaths();
		int kills = bean.getKills();
		
		/*damage avg*/
		int trueDamageTaken = bean.getTrueDamageTaken();
		int magicDamageTaken = bean.getMagicDamageTaken();
		int physicalDamageTaken= bean.getPhysicalDamageTaken();
		int totalDamageTaken= bean.getTotalDamageTaken();
		int magicDamageDealtToChampions= bean.getMagicDamageDealtToChampions();	//	long	Magical damage dealt to champions
		int physicalDamageDealtToChampions= bean.getPhysicalDamageDealtToChampions();
		int totalDamageDealtToChampions= bean.getTotalDamageDealtToChampions();
		int trueDamageDealtToChampions= bean.getTrueDamageDealtToChampions();
		
		int matchDur = (int)matchLength/60/10;
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
			beans.add(new ItemBean( itemId,  rank,  division,  championId,  
					 assists,  deaths,  kills, wins, laneQty, roleQty,  qty,
					 trueDamageTaken,  magicDamageTaken,  physicalDamageTaken,  totalDamageTaken,
					 magicDamageDealtToChampions,  physicalDamageDealtToChampions,  totalDamageDealtToChampions,
					 trueDamageDealtToChampions,  weekDate,  yearDate, matchDur));
		}
		return beans.toArray(new ItemBean[beans.size()]);
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

	public static ArrayList<String> getQueryParams() {
		return queryParams;
	}

	public static void setQueryParams(ArrayList<String> queryParams) {
		ItemBean.queryParams = queryParams;
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

	public int getTrueDamageTaken() {
		return trueDamageTaken;
	}

	public void setTrueDamageTaken(int trueDamageTaken) {
		this.trueDamageTaken = trueDamageTaken;
	}

	public int getMagicDamageTaken() {
		return magicDamageTaken;
	}

	public void setMagicDamageTaken(int magicDamageTaken) {
		this.magicDamageTaken = magicDamageTaken;
	}

	public int getPhysicalDamageTaken() {
		return physicalDamageTaken;
	}

	public void setPhysicalDamageTaken(int physicalDamageTaken) {
		this.physicalDamageTaken = physicalDamageTaken;
	}

	public int getTotalDamageTaken() {
		return totalDamageTaken;
	}

	public void setTotalDamageTaken(int totalDamageTaken) {
		this.totalDamageTaken = totalDamageTaken;
	}

	public int getMagicDamageDealtToChampions() {
		return magicDamageDealtToChampions;
	}

	public void setMagicDamageDealtToChampions(int magicDamageDealtToChampions) {
		this.magicDamageDealtToChampions = magicDamageDealtToChampions;
	}

	public int getPhysicalDamageDealtToChampions() {
		return physicalDamageDealtToChampions;
	}

	public void setPhysicalDamageDealtToChampions(int physicalDamageDealtToChampions) {
		this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
	}

	public int getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}

	public void setTotalDamageDealtToChampions(int totalDamageDealtToChampions) {
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
	}

	public int getTrueDamageDealtToChampions() {
		return trueDamageDealtToChampions;
	}

	public void setTrueDamageDealtToChampions(int trueDamageDealtToChampions) {
		this.trueDamageDealtToChampions = trueDamageDealtToChampions;
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
