package com.leaguebeta.db.model.Aggregate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.leaguebeta.db.model.Participant.MasteryBean;
import com.leaguebeta.db.model.Participant.ParticipantTimelineDataBean;
import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Participant.RuneBean;

public class ChampionBean{
	public static transient ArrayList<String> queryParams, removeParams;
	static{
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();

		queryParams.add("championId");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		queryParams.add("rank");
		queryParams.add("division");
		
		removeParams.add("championId");
		removeParams.add("rank");
		removeParams.add("division");
		removeParams.add("weekDate");
		removeParams.add("yearDate");
	}
	int qty;
	int championId;
	
	/*kda avg*/
	int rank;
	int division;
	int assists;
	int deaths;
	int kills;
		
	/*damage avg*/
	int trueDamageTaken;
	int magicDamageTaken;
	int physicalDamageTaken;
	int totalDamageTaken;
	int magicDamageDealtToChampions;	//	long	Magical damage dealt to champions
	int physicalDamageDealtToChampions;
	int totalDamageDealtToChampions;
	int trueDamageDealtToChampions;
	
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
	
	/*avg participantframebeans*/
	
	double	assistedLaneDeathsPerMinDeltasZeroToTen;	double	assistedLaneDeathsPerMinDeltasTenToTwenty;	double	assistedLaneDeathsPerMinDeltasTwentyToThirty;	double	assistedLaneDeathsPerMinDeltasThirtyToEnd;	
	double	assistedLaneKillsPerMinDeltasZeroToTen;	double	assistedLaneKillsPerMinDeltasTenToTwenty;	double	assistedLaneKillsPerMinDeltasTwentyToThirty;	double	assistedLaneKillsPerMinDeltasThirtyToEnd;	
	double	creepsPerMinDeltasZeroToTen;	double	creepsPerMinDeltasTenToTwenty;	double	creepsPerMinDeltasTwentyToThirty;	double	creepsPerMinDeltasThirtyToEnd;	
	double	csDiffPerMinDeltasZeroToTen;	double	csDiffPerMinDeltasTenToTwenty;	double	csDiffPerMinDeltasTwentyToThirty;	double	csDiffPerMinDeltasThirtyToEnd;	
	double	damageTakenDiffPerMinDeltasZeroToTen;	double	damageTakenDiffPerMinDeltasTenToTwenty;	double	damageTakenDiffPerMinDeltasTwentyToThirty;	double	damageTakenDiffPerMinDeltasThirtyToEnd;	
	double	damageTakenPerMinDeltasZeroToTen;	double	damageTakenPerMinDeltasTenToTwenty;	double	damageTakenPerMinDeltasTwentyToThirty;	double	damageTakenPerMinDeltasThirtyToEnd;	
	double	goldPerMinDeltasZeroToTen;	double	goldPerMinDeltasTenToTwenty;	double	goldPerMinDeltasTwentyToThirty;	double	goldPerMinDeltasThirtyToEnd;	
	double	xpDiffPerMinDeltasZeroToTen;	double	xpDiffPerMinDeltasTenToTwenty;	double	xpDiffPerMinDeltasTwentyToThirty;	double	xpDiffPerMinDeltasThirtyToEnd;	
	double	xpPerMinDeltasZeroToTen;	double	xpPerMinDeltasTenToTwenty;	double	xpPerMinDeltasTwentyToThirty;	double	xpPerMinDeltasThirtyToEnd;
	
	/*frameTime*/
	int currentGoldZeroToTen, currentGoldTenToTwenty, currentGoldTwentyToThirty, currentGoldThirtyToEnd;
	int totalGoldZeroToTen, totalGoldTenToTwenty, totalGoldTwentyToThirty, totalGoldThirtyToEnd;
	int levelZeroToTen, levelTenToTwenty, levelTwentyToThirty, levelThirtyToEnd;
	int minionsKilledZeroToTen, minionsKilledTenToTwenty, minionsKilledTwentyToThirty, minionsKilledThirtyToEnd;

	
	/*avg win rate and stats*/
	int wins;
	int largestKillingSpree;
	int largestCriticalStrike;
	
	/*time flag*/
	int weekDate, yearDate;
	/*You should not have a blank constructor for this bean*/
	
	public ChampionBean(int qty, int championId, int rank, int division, int assists, int deaths, int kills,
			int trueDamageTaken, int magicDamageTaken, int physicalDamageTaken, int totalDamageTaken,
			int magicDamageDealtToChampions, int physicalDamageDealtToChampions, int totalDamageDealtToChampions,
			int trueDamageDealtToChampions, HashMap<String, Integer> laneQty, HashMap<String, Integer> roleQty,
			ParticipantTimelineDataBean assistedLaneDeathsPerMinDeltas,
			ParticipantTimelineDataBean assistedLaneKillsPerMinDeltas, ParticipantTimelineDataBean creepsPerMinDeltas,
			ParticipantTimelineDataBean csDiffPerMinDeltas, ParticipantTimelineDataBean damageTakenDiffPerMinDeltas,
			ParticipantTimelineDataBean damageTakenPerMinDeltas, ParticipantTimelineDataBean goldPerMinDeltas,
			ParticipantTimelineDataBean xpDiffPerMinDeltas, ParticipantTimelineDataBean xpPerMinDeltas, int wins, int largestKillingSpree,
			int largestCriticalStrike, int weekDate, int yearDate) {
		super();
		this.qty = qty;
		this.championId = championId;
		this.rank = rank;
		this.division = division;
		this.assists = assists;
		this.deaths = deaths;
		this.kills = kills;
		this.trueDamageTaken = trueDamageTaken;
		this.magicDamageTaken = magicDamageTaken;
		this.physicalDamageTaken = physicalDamageTaken;
		this.totalDamageTaken = totalDamageTaken;
		this.magicDamageDealtToChampions = magicDamageDealtToChampions;
		this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
		this.trueDamageDealtToChampions = trueDamageDealtToChampions;
		
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
		
		this.assistedLaneDeathsPerMinDeltasZeroToTen = assistedLaneDeathsPerMinDeltas.getZeroToTen();
		this.assistedLaneDeathsPerMinDeltasTenToTwenty = assistedLaneDeathsPerMinDeltas.getTenToTwenty();
		this.assistedLaneDeathsPerMinDeltasTwentyToThirty = assistedLaneDeathsPerMinDeltas.getTwentyToThirty();
		this.assistedLaneDeathsPerMinDeltasThirtyToEnd = assistedLaneDeathsPerMinDeltas.getThirtyToEnd();
		
		this.assistedLaneKillsPerMinDeltasZeroToTen = assistedLaneKillsPerMinDeltas.getZeroToTen();
		this.assistedLaneKillsPerMinDeltasTenToTwenty = assistedLaneKillsPerMinDeltas.getTenToTwenty();
		this.assistedLaneKillsPerMinDeltasTwentyToThirty = assistedLaneKillsPerMinDeltas.getTwentyToThirty();
		this.assistedLaneKillsPerMinDeltasThirtyToEnd = assistedLaneKillsPerMinDeltas.getThirtyToEnd();

		this.creepsPerMinDeltasZeroToTen = creepsPerMinDeltas.getZeroToTen();
		this.creepsPerMinDeltasTenToTwenty = creepsPerMinDeltas.getTenToTwenty();
		this.creepsPerMinDeltasTwentyToThirty = creepsPerMinDeltas.getTwentyToThirty();
		this.creepsPerMinDeltasThirtyToEnd = creepsPerMinDeltas.getThirtyToEnd();
		
		this.csDiffPerMinDeltasZeroToTen = csDiffPerMinDeltas.getZeroToTen();
		this.csDiffPerMinDeltasTenToTwenty = csDiffPerMinDeltas.getTenToTwenty();
		this.csDiffPerMinDeltasTwentyToThirty = csDiffPerMinDeltas.getTwentyToThirty();
		this.csDiffPerMinDeltasThirtyToEnd = csDiffPerMinDeltas.getThirtyToEnd();
		
		this.damageTakenDiffPerMinDeltasZeroToTen = damageTakenDiffPerMinDeltas.getZeroToTen();
		this.damageTakenDiffPerMinDeltasTenToTwenty = damageTakenDiffPerMinDeltas.getTenToTwenty();
		this.damageTakenDiffPerMinDeltasTwentyToThirty = damageTakenDiffPerMinDeltas.getTwentyToThirty();
		this.damageTakenDiffPerMinDeltasThirtyToEnd = damageTakenDiffPerMinDeltas.getThirtyToEnd();
		
		this.damageTakenPerMinDeltasZeroToTen = damageTakenPerMinDeltas.getZeroToTen();
		this.damageTakenPerMinDeltasTenToTwenty = damageTakenPerMinDeltas.getTenToTwenty();
		this.damageTakenPerMinDeltasTwentyToThirty = damageTakenPerMinDeltas.getTwentyToThirty();
		this.damageTakenPerMinDeltasThirtyToEnd = damageTakenPerMinDeltas.getThirtyToEnd();
		
		this.damageTakenPerMinDeltasZeroToTen = damageTakenPerMinDeltas.getZeroToTen();
		this.damageTakenPerMinDeltasTenToTwenty = damageTakenPerMinDeltas.getTenToTwenty();
		this.damageTakenPerMinDeltasTwentyToThirty = damageTakenPerMinDeltas.getTwentyToThirty();
		this.damageTakenPerMinDeltasThirtyToEnd = damageTakenPerMinDeltas.getThirtyToEnd();
		
		this.xpDiffPerMinDeltasZeroToTen = xpDiffPerMinDeltas.getZeroToTen();
		this.xpDiffPerMinDeltasTenToTwenty = xpDiffPerMinDeltas.getTenToTwenty();
		this.xpDiffPerMinDeltasTwentyToThirty = xpDiffPerMinDeltas.getTwentyToThirty();
		this.xpDiffPerMinDeltasThirtyToEnd = xpDiffPerMinDeltas.getThirtyToEnd();
		
		this.xpPerMinDeltasZeroToTen = xpPerMinDeltas.getZeroToTen();
		this.xpPerMinDeltasTenToTwenty = xpPerMinDeltas.getTenToTwenty();
		this.xpPerMinDeltasTwentyToThirty = xpPerMinDeltas.getTwentyToThirty();
		this.xpPerMinDeltasThirtyToEnd = xpPerMinDeltas.getThirtyToEnd();
		
		this.wins = wins;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
		this.wins = wins;
		this.largestKillingSpree = largestKillingSpree;
		this.largestCriticalStrike = largestCriticalStrike;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
	}

	public static ChampionBean playerMatchBeanToChampBean(PlayerMatchBean bean, RankBean rankBean){
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
		
		/*avg participantframebeans*/
		HashMap<String, Integer> laneQty = new HashMap<String, Integer>();
		laneQty.put(bean.getTimeline().getLane(), 1);
		HashMap<String, Integer> roleQty = new HashMap<String, Integer>();
		roleQty.put(bean.getTimeline().getRole(), 1);
		
		ParticipantTimelineDataBean	assistedLaneDeathsPerMinDeltas = bean.getTimeline().getAssistedLaneDeathsPerMinDeltas();	//	ParticipantTimelineDataBean	Assisted lane deaths per minute timeline data
		ParticipantTimelineDataBean	assistedLaneKillsPerMinDeltas = bean.getTimeline().getAssistedLaneKillsPerMinDeltas();	//	ParticipantTimelineDataBean	Assisted lane kills per minute timeline data
		ParticipantTimelineDataBean	creepsPerMinDeltas = bean.getTimeline().getCreepsPerMinDeltas();	//	ParticipantTimelineDataBean	Creeps per minute timeline data
		ParticipantTimelineDataBean	csDiffPerMinDeltas = bean.getTimeline().getCsDiffPerMinDeltas();	//	ParticipantTimelineDataBean	Creep score difference per minute timeline data
		ParticipantTimelineDataBean	damageTakenDiffPerMinDeltas = bean.getTimeline().getDamageTakenDiffPerMinDeltas();	//	ParticipantTimelineDataBean	Damage taken difference per minute timeline data
		ParticipantTimelineDataBean	damageTakenPerMinDeltas = bean.getTimeline().getDamageTakenPerMinDeltas();	//	ParticipantTimelineDataBean	Damage taken per minute timeline data
		ParticipantTimelineDataBean	goldPerMinDeltas = bean.getTimeline().getGoldPerMinDeltas();	
		ParticipantTimelineDataBean	xpDiffPerMinDeltas = bean.getTimeline().getXpDiffPerMinDeltas();
		ParticipantTimelineDataBean	xpPerMinDeltas = bean.getTimeline().getXpPerMinDeltas();
		
		/*avg win rate and stats*/
		int wins = bean.isWinner() ? 1 : 0;
		int largestKillingSpree = bean.getLargestKillingSpree();
		int largestCriticalStrike = bean.getLargestCriticalStrike();
		
		/*runes and masteries <Id, amount>*/
		HashMap<Integer, Integer> runeQty = new HashMap<Integer, Integer>();
		RuneBean[] runes = bean.getRunes();
		for(RuneBean rune : runes){
			if(runeQty.get(rune.getRuneId()) == null)
				runeQty.put(rune.getRuneId(), rune.getRuneRank());
			else
				runeQty.put(rune.getRuneId() , runeQty.get(rune.getRuneId()) + rune.getRuneRank());
		}
		HashMap<Integer, Integer> masteryQty = new HashMap<Integer, Integer>();
		MasteryBean[] masteries = bean.getMasteries();
		for(MasteryBean mastery : masteries){
			if(masteryQty.get(mastery.getMasteryId()) == null)
				masteryQty.put(mastery.getMasteryId(), mastery.getMasteryRank());
			else
				masteryQty.put(mastery.getMasteryId(), masteryQty.get(mastery.getMasteryId()) + mastery.getMasteryRank());
		}
		/*time flag*/
		int weekDate = bean.getWeekDate();
		int yearDate = bean.getYearDate();
		return new ChampionBean( qty,  championId,  rank,  division,  assists,  deaths,  kills,
				 trueDamageTaken,  magicDamageTaken,  physicalDamageTaken,  totalDamageTaken,
				 magicDamageDealtToChampions,  physicalDamageDealtToChampions,  totalDamageDealtToChampions,
				 trueDamageDealtToChampions, laneQty, roleQty,
				 assistedLaneDeathsPerMinDeltas,
				 assistedLaneKillsPerMinDeltas,  creepsPerMinDeltas,
				 csDiffPerMinDeltas,  damageTakenDiffPerMinDeltas,
				 damageTakenPerMinDeltas,  goldPerMinDeltas,
				 xpDiffPerMinDeltas,  xpPerMinDeltas,  wins,  largestKillingSpree,
				 largestCriticalStrike,	 weekDate,  yearDate);
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

	public double getAssistedLaneDeathsPerMinDeltasZeroToTen() {
		return assistedLaneDeathsPerMinDeltasZeroToTen;
	}

	public void setAssistedLaneDeathsPerMinDeltasZeroToTen(double assistedLaneDeathsPerMinDeltasZeroToTen) {
		this.assistedLaneDeathsPerMinDeltasZeroToTen = assistedLaneDeathsPerMinDeltasZeroToTen;
	}

	public double getAssistedLaneDeathsPerMinDeltasTenToTwenty() {
		return assistedLaneDeathsPerMinDeltasTenToTwenty;
	}

	public void setAssistedLaneDeathsPerMinDeltasTenToTwenty(double assistedLaneDeathsPerMinDeltasTenToTwenty) {
		this.assistedLaneDeathsPerMinDeltasTenToTwenty = assistedLaneDeathsPerMinDeltasTenToTwenty;
	}

	public double getAssistedLaneDeathsPerMinDeltasTwentyToThirty() {
		return assistedLaneDeathsPerMinDeltasTwentyToThirty;
	}

	public void setAssistedLaneDeathsPerMinDeltasTwentyToThirty(double assistedLaneDeathsPerMinDeltasTwentyToThirty) {
		this.assistedLaneDeathsPerMinDeltasTwentyToThirty = assistedLaneDeathsPerMinDeltasTwentyToThirty;
	}

	public double getAssistedLaneDeathsPerMinDeltasThirtyToEnd() {
		return assistedLaneDeathsPerMinDeltasThirtyToEnd;
	}

	public void setAssistedLaneDeathsPerMinDeltasThirtyToEnd(double assistedLaneDeathsPerMinDeltasThirtyToEnd) {
		this.assistedLaneDeathsPerMinDeltasThirtyToEnd = assistedLaneDeathsPerMinDeltasThirtyToEnd;
	}

	public double getAssistedLaneKillsPerMinDeltasZeroToTen() {
		return assistedLaneKillsPerMinDeltasZeroToTen;
	}

	public void setAssistedLaneKillsPerMinDeltasZeroToTen(double assistedLaneKillsPerMinDeltasZeroToTen) {
		this.assistedLaneKillsPerMinDeltasZeroToTen = assistedLaneKillsPerMinDeltasZeroToTen;
	}

	public double getAssistedLaneKillsPerMinDeltasTenToTwenty() {
		return assistedLaneKillsPerMinDeltasTenToTwenty;
	}

	public void setAssistedLaneKillsPerMinDeltasTenToTwenty(double assistedLaneKillsPerMinDeltasTenToTwenty) {
		this.assistedLaneKillsPerMinDeltasTenToTwenty = assistedLaneKillsPerMinDeltasTenToTwenty;
	}

	public double getAssistedLaneKillsPerMinDeltasTwentyToThirty() {
		return assistedLaneKillsPerMinDeltasTwentyToThirty;
	}

	public void setAssistedLaneKillsPerMinDeltasTwentyToThirty(double assistedLaneKillsPerMinDeltasTwentyToThirty) {
		this.assistedLaneKillsPerMinDeltasTwentyToThirty = assistedLaneKillsPerMinDeltasTwentyToThirty;
	}

	public double getAssistedLaneKillsPerMinDeltasThirtyToEnd() {
		return assistedLaneKillsPerMinDeltasThirtyToEnd;
	}

	public void setAssistedLaneKillsPerMinDeltasThirtyToEnd(double assistedLaneKillsPerMinDeltasThirtyToEnd) {
		this.assistedLaneKillsPerMinDeltasThirtyToEnd = assistedLaneKillsPerMinDeltasThirtyToEnd;
	}

	public double getCreepsPerMinDeltasZeroToTen() {
		return creepsPerMinDeltasZeroToTen;
	}

	public void setCreepsPerMinDeltasZeroToTen(double creepsPerMinDeltasZeroToTen) {
		this.creepsPerMinDeltasZeroToTen = creepsPerMinDeltasZeroToTen;
	}

	public double getCreepsPerMinDeltasTenToTwenty() {
		return creepsPerMinDeltasTenToTwenty;
	}

	public void setCreepsPerMinDeltasTenToTwenty(double creepsPerMinDeltasTenToTwenty) {
		this.creepsPerMinDeltasTenToTwenty = creepsPerMinDeltasTenToTwenty;
	}

	public double getCreepsPerMinDeltasTwentyToThirty() {
		return creepsPerMinDeltasTwentyToThirty;
	}

	public void setCreepsPerMinDeltasTwentyToThirty(double creepsPerMinDeltasTwentyToThirty) {
		this.creepsPerMinDeltasTwentyToThirty = creepsPerMinDeltasTwentyToThirty;
	}

	public double getCreepsPerMinDeltasThirtyToEnd() {
		return creepsPerMinDeltasThirtyToEnd;
	}

	public void setCreepsPerMinDeltasThirtyToEnd(double creepsPerMinDeltasThirtyToEnd) {
		this.creepsPerMinDeltasThirtyToEnd = creepsPerMinDeltasThirtyToEnd;
	}

	public double getCsDiffPerMinDeltasZeroToTen() {
		return csDiffPerMinDeltasZeroToTen;
	}

	public void setCsDiffPerMinDeltasZeroToTen(double csDiffPerMinDeltasZeroToTen) {
		this.csDiffPerMinDeltasZeroToTen = csDiffPerMinDeltasZeroToTen;
	}

	public double getCsDiffPerMinDeltasTenToTwenty() {
		return csDiffPerMinDeltasTenToTwenty;
	}

	public void setCsDiffPerMinDeltasTenToTwenty(double csDiffPerMinDeltasTenToTwenty) {
		this.csDiffPerMinDeltasTenToTwenty = csDiffPerMinDeltasTenToTwenty;
	}

	public double getCsDiffPerMinDeltasTwentyToThirty() {
		return csDiffPerMinDeltasTwentyToThirty;
	}

	public void setCsDiffPerMinDeltasTwentyToThirty(double csDiffPerMinDeltasTwentyToThirty) {
		this.csDiffPerMinDeltasTwentyToThirty = csDiffPerMinDeltasTwentyToThirty;
	}

	public double getCsDiffPerMinDeltasThirtyToEnd() {
		return csDiffPerMinDeltasThirtyToEnd;
	}

	public void setCsDiffPerMinDeltasThirtyToEnd(double csDiffPerMinDeltasThirtyToEnd) {
		this.csDiffPerMinDeltasThirtyToEnd = csDiffPerMinDeltasThirtyToEnd;
	}

	public double getDamageTakenDiffPerMinDeltasZeroToTen() {
		return damageTakenDiffPerMinDeltasZeroToTen;
	}

	public void setDamageTakenDiffPerMinDeltasZeroToTen(double damageTakenDiffPerMinDeltasZeroToTen) {
		this.damageTakenDiffPerMinDeltasZeroToTen = damageTakenDiffPerMinDeltasZeroToTen;
	}

	public double getDamageTakenDiffPerMinDeltasTenToTwenty() {
		return damageTakenDiffPerMinDeltasTenToTwenty;
	}

	public void setDamageTakenDiffPerMinDeltasTenToTwenty(double damageTakenDiffPerMinDeltasTenToTwenty) {
		this.damageTakenDiffPerMinDeltasTenToTwenty = damageTakenDiffPerMinDeltasTenToTwenty;
	}

	public double getDamageTakenDiffPerMinDeltasTwentyToThirty() {
		return damageTakenDiffPerMinDeltasTwentyToThirty;
	}

	public void setDamageTakenDiffPerMinDeltasTwentyToThirty(double damageTakenDiffPerMinDeltasTwentyToThirty) {
		this.damageTakenDiffPerMinDeltasTwentyToThirty = damageTakenDiffPerMinDeltasTwentyToThirty;
	}

	public double getDamageTakenDiffPerMinDeltasThirtyToEnd() {
		return damageTakenDiffPerMinDeltasThirtyToEnd;
	}

	public void setDamageTakenDiffPerMinDeltasThirtyToEnd(double damageTakenDiffPerMinDeltasThirtyToEnd) {
		this.damageTakenDiffPerMinDeltasThirtyToEnd = damageTakenDiffPerMinDeltasThirtyToEnd;
	}

	public double getDamageTakenPerMinDeltasZeroToTen() {
		return damageTakenPerMinDeltasZeroToTen;
	}

	public void setDamageTakenPerMinDeltasZeroToTen(double damageTakenPerMinDeltasZeroToTen) {
		this.damageTakenPerMinDeltasZeroToTen = damageTakenPerMinDeltasZeroToTen;
	}

	public double getDamageTakenPerMinDeltasTenToTwenty() {
		return damageTakenPerMinDeltasTenToTwenty;
	}

	public void setDamageTakenPerMinDeltasTenToTwenty(double damageTakenPerMinDeltasTenToTwenty) {
		this.damageTakenPerMinDeltasTenToTwenty = damageTakenPerMinDeltasTenToTwenty;
	}

	public double getDamageTakenPerMinDeltasTwentyToThirty() {
		return damageTakenPerMinDeltasTwentyToThirty;
	}

	public void setDamageTakenPerMinDeltasTwentyToThirty(double damageTakenPerMinDeltasTwentyToThirty) {
		this.damageTakenPerMinDeltasTwentyToThirty = damageTakenPerMinDeltasTwentyToThirty;
	}

	public double getDamageTakenPerMinDeltasThirtyToEnd() {
		return damageTakenPerMinDeltasThirtyToEnd;
	}

	public void setDamageTakenPerMinDeltasThirtyToEnd(double damageTakenPerMinDeltasThirtyToEnd) {
		this.damageTakenPerMinDeltasThirtyToEnd = damageTakenPerMinDeltasThirtyToEnd;
	}

	public double getGoldPerMinDeltasZeroToTen() {
		return goldPerMinDeltasZeroToTen;
	}

	public void setGoldPerMinDeltasZeroToTen(double goldPerMinDeltasZeroToTen) {
		this.goldPerMinDeltasZeroToTen = goldPerMinDeltasZeroToTen;
	}

	public double getGoldPerMinDeltasTenToTwenty() {
		return goldPerMinDeltasTenToTwenty;
	}

	public void setGoldPerMinDeltasTenToTwenty(double goldPerMinDeltasTenToTwenty) {
		this.goldPerMinDeltasTenToTwenty = goldPerMinDeltasTenToTwenty;
	}

	public double getGoldPerMinDeltasTwentyToThirty() {
		return goldPerMinDeltasTwentyToThirty;
	}

	public void setGoldPerMinDeltasTwentyToThirty(double goldPerMinDeltasTwentyToThirty) {
		this.goldPerMinDeltasTwentyToThirty = goldPerMinDeltasTwentyToThirty;
	}

	public double getGoldPerMinDeltasThirtyToEnd() {
		return goldPerMinDeltasThirtyToEnd;
	}

	public void setGoldPerMinDeltasThirtyToEnd(double goldPerMinDeltasThirtyToEnd) {
		this.goldPerMinDeltasThirtyToEnd = goldPerMinDeltasThirtyToEnd;
	}

	public double getXpDiffPerMinDeltasZeroToTen() {
		return xpDiffPerMinDeltasZeroToTen;
	}

	public void setXpDiffPerMinDeltasZeroToTen(double xpDiffPerMinDeltasZeroToTen) {
		this.xpDiffPerMinDeltasZeroToTen = xpDiffPerMinDeltasZeroToTen;
	}

	public double getXpDiffPerMinDeltasTenToTwenty() {
		return xpDiffPerMinDeltasTenToTwenty;
	}

	public void setXpDiffPerMinDeltasTenToTwenty(double xpDiffPerMinDeltasTenToTwenty) {
		this.xpDiffPerMinDeltasTenToTwenty = xpDiffPerMinDeltasTenToTwenty;
	}

	public double getXpDiffPerMinDeltasTwentyToThirty() {
		return xpDiffPerMinDeltasTwentyToThirty;
	}

	public void setXpDiffPerMinDeltasTwentyToThirty(double xpDiffPerMinDeltasTwentyToThirty) {
		this.xpDiffPerMinDeltasTwentyToThirty = xpDiffPerMinDeltasTwentyToThirty;
	}

	public double getXpDiffPerMinDeltasThirtyToEnd() {
		return xpDiffPerMinDeltasThirtyToEnd;
	}

	public void setXpDiffPerMinDeltasThirtyToEnd(double xpDiffPerMinDeltasThirtyToEnd) {
		this.xpDiffPerMinDeltasThirtyToEnd = xpDiffPerMinDeltasThirtyToEnd;
	}

	public double getXpPerMinDeltasZeroToTen() {
		return xpPerMinDeltasZeroToTen;
	}

	public void setXpPerMinDeltasZeroToTen(double xpPerMinDeltasZeroToTen) {
		this.xpPerMinDeltasZeroToTen = xpPerMinDeltasZeroToTen;
	}

	public double getXpPerMinDeltasTenToTwenty() {
		return xpPerMinDeltasTenToTwenty;
	}

	public void setXpPerMinDeltasTenToTwenty(double xpPerMinDeltasTenToTwenty) {
		this.xpPerMinDeltasTenToTwenty = xpPerMinDeltasTenToTwenty;
	}

	public double getXpPerMinDeltasTwentyToThirty() {
		return xpPerMinDeltasTwentyToThirty;
	}

	public void setXpPerMinDeltasTwentyToThirty(double xpPerMinDeltasTwentyToThirty) {
		this.xpPerMinDeltasTwentyToThirty = xpPerMinDeltasTwentyToThirty;
	}

	public double getXpPerMinDeltasThirtyToEnd() {
		return xpPerMinDeltasThirtyToEnd;
	}

	public void setXpPerMinDeltasThirtyToEnd(double xpPerMinDeltasThirtyToEnd) {
		this.xpPerMinDeltasThirtyToEnd = xpPerMinDeltasThirtyToEnd;
	}

	public int getLargestKillingSpree() {
		return largestKillingSpree;
	}

	public void setLargestKillingSpree(int largestKillingSpree) {
		this.largestKillingSpree = largestKillingSpree;
	}

	public int getLargestCriticalStrike() {
		return largestCriticalStrike;
	}

	public void setLargestCriticalStrike(int largestCriticalStrike) {
		this.largestCriticalStrike = largestCriticalStrike;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
