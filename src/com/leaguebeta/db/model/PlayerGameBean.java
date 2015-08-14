package com.leaguebeta.db.model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayerGameBean extends GenericBean{
	//pre-made constants
	transient final int NUM_OF_ITEMS = 7;
	transient final int NUM_OF_SPELLS = 2;
	transient final int NUM_OF_CHAMPION_SPELLS = 6;//5 and 6 are summoner spells
	//list of players
	public ArrayList<Integer> summonerID;
	//general game info
	public String region;
	public int gameID;
	public String gameType;
	public int accountLevel;
	public int mapID;
	public String subType;
	public String gameMode;
	public int championId;
	public int weekDate;//timePlayed's format is epoch milliseconds
	public int yearDate;
	/**
	 * more specific data about the player
	 */
	public int mainSummonerID;
	public int gameDuration;
	//lists of items(6) and spells(2)
	public ArrayList<Integer> itemsID;
	public ArrayList<Integer> spellsID;
	int assists;
	int barracksKilled;	//# of inhibitors
	int championsKilled;	
	int combatPlayerScore;		
	int consumablesPurchased;		
	int damageDealtPlayer;		
	int doubleKills;	
	int firstBlood;	
	int gold;	
	int goldEarned;	
	int goldSpent;				
	int itemsPurchased;		
	int killingSprees;		
	int largestCriticalStrike;		
	int largestKillingSpree;		
	int largestMultiKill;		
	int legendaryItemsCreated;	//Number of tier 3 items built.
	int level;
	int magicDamageDealtPlayer;	
	int magicDamageDealtToChampions;	
	int magicDamageTaken;	
	int minionsDenied;	
	int minionsKilled;	
	int neutralMinionsKilled;
	int neutralMinionsKilledEnemyJungle;
	int neutralMinionsKilledYourJungle;
	boolean nexusKilled;	//boolean	Flag specifying if the summoner got the killing blow on the nexus.
	int nodeCapture;
	int nodeCaptureAssist;
	int nodeNeutralize;
	int nodeNeutralizeAssist;	
	int numDeaths;
	int numItemsBought;
	int objectivePlayerScore;
	int pentaKills;
	int physicalDamageDealtPlayer;
	int physicalDamageDealtToChampions;	
	int physicalDamageTaken;	
	int playerPosition;	//Player position (Legal values: TOP(1), MIDDLE(2), JUNGLE(3), BOT(4))
	public int playerRole;		//Player role (Legal values: DUO(1), SUPPORT(2), CARRY(3), SOLO(4))
	int quadraKills;	
	int sightWardsBought;		
	ArrayList<Integer> spellsCast;	//		Number of times champion spells were cast.
	int summonSpell1Cast;		
	int summonSpell2Cast;		
	int superMonsterKilled;
	public int teamID;
	int teamObjective;
	int totalDamageDealt;
	int totalDamageDealtToChampions;
	int totalDamageTaken;
	int totalHeal;
	int totalPlayerScore;
	int totalScoreRank;
	int totalTimeCrowdControlDealt;
	int totalUnitsHealed;
	int tripleKills;
	int trueDamageDealtPlayer;
	int trueDamageDealtToChampions;
	int trueDamageTaken;
	int turretsKilled;
	int unrealKills;
	int victoryPoTotal;
	int visionWardsBought;
	int wardKilled;
	int wardPlaced;
	boolean win;//win condition
	public ArrayList<Integer> getSummonerID() {
		return summonerID;
	}

	public void setSummonerID(ArrayList<Integer> summonerID) {
		this.summonerID = summonerID;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public int getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(int accountLevel) {
		this.accountLevel = accountLevel;
	}

	public int getMapID() {
		return mapID;
	}

	public void setMapID(int mapID) {
		this.mapID = mapID;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public int getChampionId() {
		return championId;
	}

	public void setChampionId(int championId) {
		this.championId = championId;
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

	public int getMainSummonerID() {
		return mainSummonerID;
	}

	public void setMainSummonerID(int mainSummonerID) {
		this.mainSummonerID = mainSummonerID;
	}

	public int getGameDuration() {
		return gameDuration;
	}

	public void setGameDuration(int gameDuration) {
		this.gameDuration = gameDuration;
	}

	public ArrayList<Integer> getItemsID() {
		return itemsID;
	}

	public void setItemsID(ArrayList<Integer> itemsID) {
		this.itemsID = itemsID;
	}

	public ArrayList<Integer> getSpellsID() {
		return spellsID;
	}

	public void setSpellsID(ArrayList<Integer> spellsID) {
		this.spellsID = spellsID;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getBarracksKilled() {
		return barracksKilled;
	}

	public void setBarracksKilled(int barracksKilled) {
		this.barracksKilled = barracksKilled;
	}

	public int getChampionsKilled() {
		return championsKilled;
	}

	public void setChampionsKilled(int championsKilled) {
		this.championsKilled = championsKilled;
	}

	public int getCombatPlayerScore() {
		return combatPlayerScore;
	}

	public void setCombatPlayerScore(int combatPlayerScore) {
		this.combatPlayerScore = combatPlayerScore;
	}

	public int getConsumablesPurchased() {
		return consumablesPurchased;
	}

	public void setConsumablesPurchased(int consumablesPurchased) {
		this.consumablesPurchased = consumablesPurchased;
	}

	public int getDamageDealtPlayer() {
		return damageDealtPlayer;
	}

	public void setDamageDealtPlayer(int damageDealtPlayer) {
		this.damageDealtPlayer = damageDealtPlayer;
	}

	public int getDoubleKills() {
		return doubleKills;
	}

	public void setDoubleKills(int doubleKills) {
		this.doubleKills = doubleKills;
	}

	public int getFirstBlood() {
		return firstBlood;
	}

	public void setFirstBlood(int firstBlood) {
		this.firstBlood = firstBlood;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getGoldEarned() {
		return goldEarned;
	}

	public void setGoldEarned(int goldEarned) {
		this.goldEarned = goldEarned;
	}

	public int getGoldSpent() {
		return goldSpent;
	}

	public void setGoldSpent(int goldSpent) {
		this.goldSpent = goldSpent;
	}

	public int getItemsPurchased() {
		return itemsPurchased;
	}

	public void setItemsPurchased(int itemsPurchased) {
		this.itemsPurchased = itemsPurchased;
	}

	public int getKillingSprees() {
		return killingSprees;
	}

	public void setKillingSprees(int killingSprees) {
		this.killingSprees = killingSprees;
	}

	public int getLargestCriticalStrike() {
		return largestCriticalStrike;
	}

	public void setLargestCriticalStrike(int largestCriticalStrike) {
		this.largestCriticalStrike = largestCriticalStrike;
	}

	public int getLargestKillingSpree() {
		return largestKillingSpree;
	}

	public void setLargestKillingSpree(int largestKillingSpree) {
		this.largestKillingSpree = largestKillingSpree;
	}

	public int getLargestMultiKill() {
		return largestMultiKill;
	}

	public void setLargestMultiKill(int largestMultiKill) {
		this.largestMultiKill = largestMultiKill;
	}

	public int getLegendaryItemsCreated() {
		return legendaryItemsCreated;
	}

	public void setLegendaryItemsCreated(int legendaryItemsCreated) {
		this.legendaryItemsCreated = legendaryItemsCreated;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMagicDamageDealtPlayer() {
		return magicDamageDealtPlayer;
	}

	public void setMagicDamageDealtPlayer(int magicDamageDealtPlayer) {
		this.magicDamageDealtPlayer = magicDamageDealtPlayer;
	}

	public int getMagicDamageDealtToChampions() {
		return magicDamageDealtToChampions;
	}

	public void setMagicDamageDealtToChampions(int magicDamageDealtToChampions) {
		this.magicDamageDealtToChampions = magicDamageDealtToChampions;
	}

	public int getMagicDamageTaken() {
		return magicDamageTaken;
	}

	public void setMagicDamageTaken(int magicDamageTaken) {
		this.magicDamageTaken = magicDamageTaken;
	}

	public int getMinionsDenied() {
		return minionsDenied;
	}

	public void setMinionsDenied(int minionsDenied) {
		this.minionsDenied = minionsDenied;
	}

	public int getMinionsKilled() {
		return minionsKilled;
	}

	public void setMinionsKilled(int minionsKilled) {
		this.minionsKilled = minionsKilled;
	}

	public int getNeutralMinionsKilled() {
		return neutralMinionsKilled;
	}

	public void setNeutralMinionsKilled(int neutralMinionsKilled) {
		this.neutralMinionsKilled = neutralMinionsKilled;
	}

	public int getNeutralMinionsKilledEnemyJungle() {
		return neutralMinionsKilledEnemyJungle;
	}

	public void setNeutralMinionsKilledEnemyJungle(int neutralMinionsKilledEnemyJungle) {
		this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
	}

	public int getNeutralMinionsKilledYourJungle() {
		return neutralMinionsKilledYourJungle;
	}

	public void setNeutralMinionsKilledYourJungle(int neutralMinionsKilledYourJungle) {
		this.neutralMinionsKilledYourJungle = neutralMinionsKilledYourJungle;
	}

	public boolean isNexusKilled() {
		return nexusKilled;
	}

	public void setNexusKilled(boolean nexusKilled) {
		this.nexusKilled = nexusKilled;
	}

	public int getNodeCapture() {
		return nodeCapture;
	}

	public void setNodeCapture(int nodeCapture) {
		this.nodeCapture = nodeCapture;
	}

	public int getNodeCaptureAssist() {
		return nodeCaptureAssist;
	}

	public void setNodeCaptureAssist(int nodeCaptureAssist) {
		this.nodeCaptureAssist = nodeCaptureAssist;
	}

	public int getNodeNeutralize() {
		return nodeNeutralize;
	}

	public void setNodeNeutralize(int nodeNeutralize) {
		this.nodeNeutralize = nodeNeutralize;
	}

	public int getNodeNeutralizeAssist() {
		return nodeNeutralizeAssist;
	}

	public void setNodeNeutralizeAssist(int nodeNeutralizeAssist) {
		this.nodeNeutralizeAssist = nodeNeutralizeAssist;
	}

	public int getNumDeaths() {
		return numDeaths;
	}

	public void setNumDeaths(int numDeaths) {
		this.numDeaths = numDeaths;
	}

	public int getNumItemsBought() {
		return numItemsBought;
	}

	public void setNumItemsBought(int numItemsBought) {
		this.numItemsBought = numItemsBought;
	}

	public int getObjectivePlayerScore() {
		return objectivePlayerScore;
	}

	public void setObjectivePlayerScore(int objectivePlayerScore) {
		this.objectivePlayerScore = objectivePlayerScore;
	}

	public int getPentaKills() {
		return pentaKills;
	}

	public void setPentaKills(int pentaKills) {
		this.pentaKills = pentaKills;
	}

	public int getPhysicalDamageDealtPlayer() {
		return physicalDamageDealtPlayer;
	}

	public void setPhysicalDamageDealtPlayer(int physicalDamageDealtPlayer) {
		this.physicalDamageDealtPlayer = physicalDamageDealtPlayer;
	}

	public int getPhysicalDamageDealtToChampions() {
		return physicalDamageDealtToChampions;
	}

	public void setPhysicalDamageDealtToChampions(int physicalDamageDealtToChampions) {
		this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
	}

	public int getPhysicalDamageTaken() {
		return physicalDamageTaken;
	}

	public void setPhysicalDamageTaken(int physicalDamageTaken) {
		this.physicalDamageTaken = physicalDamageTaken;
	}

	public int getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getPlayerRole() {
		return playerRole;
	}

	public void setPlayerRole(int playerRole) {
		this.playerRole = playerRole;
	}

	public int getQuadraKills() {
		return quadraKills;
	}

	public void setQuadraKills(int quadraKills) {
		this.quadraKills = quadraKills;
	}

	public int getSightWardsBought() {
		return sightWardsBought;
	}

	public void setSightWardsBought(int sightWardsBought) {
		this.sightWardsBought = sightWardsBought;
	}

	public ArrayList<Integer> getSpellsCast() {
		return spellsCast;
	}

	public void setSpellsCast(ArrayList<Integer> spellsCast) {
		this.spellsCast = spellsCast;
	}

	public int getSummonSpell1Cast() {
		return summonSpell1Cast;
	}

	public void setSummonSpell1Cast(int summonSpell1Cast) {
		this.summonSpell1Cast = summonSpell1Cast;
	}

	public int getSummonSpell2Cast() {
		return summonSpell2Cast;
	}

	public void setSummonSpell2Cast(int summonSpell2Cast) {
		this.summonSpell2Cast = summonSpell2Cast;
	}

	public int getSuperMonsterKilled() {
		return superMonsterKilled;
	}

	public void setSuperMonsterKilled(int superMonsterKilled) {
		this.superMonsterKilled = superMonsterKilled;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public int getTeamObjective() {
		return teamObjective;
	}

	public void setTeamObjective(int teamObjective) {
		this.teamObjective = teamObjective;
	}

	public int getTotalDamageDealt() {
		return totalDamageDealt;
	}

	public void setTotalDamageDealt(int totalDamageDealt) {
		this.totalDamageDealt = totalDamageDealt;
	}

	public int getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}

	public void setTotalDamageDealtToChampions(int totalDamageDealtToChampions) {
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
	}

	public int getTotalDamageTaken() {
		return totalDamageTaken;
	}

	public void setTotalDamageTaken(int totalDamageTaken) {
		this.totalDamageTaken = totalDamageTaken;
	}

	public int getTotalHeal() {
		return totalHeal;
	}

	public void setTotalHeal(int totalHeal) {
		this.totalHeal = totalHeal;
	}

	public int getTotalPlayerScore() {
		return totalPlayerScore;
	}

	public void setTotalPlayerScore(int totalPlayerScore) {
		this.totalPlayerScore = totalPlayerScore;
	}

	public int getTotalScoreRank() {
		return totalScoreRank;
	}

	public void setTotalScoreRank(int totalScoreRank) {
		this.totalScoreRank = totalScoreRank;
	}

	public int getTotalTimeCrowdControlDealt() {
		return totalTimeCrowdControlDealt;
	}

	public void setTotalTimeCrowdControlDealt(int totalTimeCrowdControlDealt) {
		this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
	}

	public int getTotalUnitsHealed() {
		return totalUnitsHealed;
	}

	public void setTotalUnitsHealed(int totalUnitsHealed) {
		this.totalUnitsHealed = totalUnitsHealed;
	}

	public int getTripleKills() {
		return tripleKills;
	}

	public void setTripleKills(int tripleKills) {
		this.tripleKills = tripleKills;
	}

	public int getTrueDamageDealtPlayer() {
		return trueDamageDealtPlayer;
	}

	public void setTrueDamageDealtPlayer(int trueDamageDealtPlayer) {
		this.trueDamageDealtPlayer = trueDamageDealtPlayer;
	}

	public int getTrueDamageDealtToChampions() {
		return trueDamageDealtToChampions;
	}

	public void setTrueDamageDealtToChampions(int trueDamageDealtToChampions) {
		this.trueDamageDealtToChampions = trueDamageDealtToChampions;
	}

	public int getTrueDamageTaken() {
		return trueDamageTaken;
	}

	public void setTrueDamageTaken(int trueDamageTaken) {
		this.trueDamageTaken = trueDamageTaken;
	}

	public int getTurretsKilled() {
		return turretsKilled;
	}

	public void setTurretsKilled(int turretsKilled) {
		this.turretsKilled = turretsKilled;
	}

	public int getUnrealKills() {
		return unrealKills;
	}

	public void setUnrealKills(int unrealKills) {
		this.unrealKills = unrealKills;
	}

	public int getVictoryPoTotal() {
		return victoryPoTotal;
	}

	public void setVictoryPoTotal(int victoryPoTotal) {
		this.victoryPoTotal = victoryPoTotal;
	}

	public int getVisionWardsBought() {
		return visionWardsBought;
	}

	public void setVisionWardsBought(int visionWardsBought) {
		this.visionWardsBought = visionWardsBought;
	}

	public int getWardKilled() {
		return wardKilled;
	}

	public void setWardKilled(int wardKilled) {
		this.wardKilled = wardKilled;
	}

	public int getWardPlaced() {
		return wardPlaced;
	}

	public void setWardPlaced(int wardPlaced) {
		this.wardPlaced = wardPlaced;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}
	
	public PlayerGameBean(){/*empty because nothing is declared*/};
	
	public PlayerGameBean(JSONObject json, int playerId, String region){
		this.region = region;
		mainSummonerID = playerId;
		gameID = (int) json.getInt("gameId");
		mapID = json.getInt("mapId");
		gameType = json.getString("gameType");
		subType = json.getString("subType");
		gameMode = json.getString("gameMode");
		accountLevel = json.getInt("level");
		teamID = json.getInt("teamId");
		championId = json.getInt("championId");
		itemsID = new ArrayList<Integer>();
		spellsID = new ArrayList<Integer>();
		spellsCast = new ArrayList<Integer>();
		JSONObject stats = json.getJSONObject("stats");

		gameDuration = loadJsonInt("timePlayed", stats); 
		assists = loadJsonInt("assists", stats);
		barracksKilled = loadJsonInt("barracksKilled", stats);	//# of inhibitors
		championsKilled = loadJsonInt("championsKilled", stats);	
		combatPlayerScore = loadJsonInt("combatPlayerScore", stats);		
		consumablesPurchased = loadJsonInt("consumablesPurchased", stats);		
		damageDealtPlayer = loadJsonInt("damageDealtPlayer", stats);		
		doubleKills = loadJsonInt("doubleKills", stats);	
		firstBlood = loadJsonInt("firstBlood", stats);	
		gold = loadJsonInt("gold", stats);	
		goldEarned = loadJsonInt("goldEarned", stats);	
		goldSpent = loadJsonInt("goldSpent", stats);				
		itemsPurchased = loadJsonInt("itemsPurchased", stats);		
		killingSprees = loadJsonInt("killingSprees", stats);		
		largestCriticalStrike = loadJsonInt("largestCriticalStrike", stats);		
		largestKillingSpree = loadJsonInt("largestKillingSpree", stats);		
		largestMultiKill = loadJsonInt("largestMultiKill", stats);		
		legendaryItemsCreated = loadJsonInt("legendaryItemsCreated", stats);	//Number of tier 3 items built.
		level = loadJsonInt("level", stats);
		magicDamageDealtPlayer = loadJsonInt("magicDamageDealtPlayer", stats);	
		magicDamageDealtToChampions = loadJsonInt("magicDamageDealtToChampions", stats);	
		magicDamageTaken = loadJsonInt("magicDamageTaken", stats);	
		minionsDenied = loadJsonInt("minionsDenied", stats);	
		minionsKilled = loadJsonInt("minionsKilled", stats);	
		neutralMinionsKilled = loadJsonInt("neutralMinionsKilled", stats);
		neutralMinionsKilledEnemyJungle = loadJsonInt("neutralMinionsKilledEnemyJungle", stats);
		neutralMinionsKilledYourJungle = loadJsonInt("neutralMinionsKilledYourJungle", stats);
		nexusKilled = loadJsonBool("nexusKilled", stats);	//boolean	Flag specifying if the summoner got the killing blow on the nexus.
		nodeCapture = loadJsonInt("nodeCapture", stats);
		nodeCaptureAssist = loadJsonInt("nodeCaptureAssist", stats);
		nodeNeutralize = loadJsonInt("nodeNeutralize", stats);
		nodeNeutralizeAssist = loadJsonInt("nodeNeutralizeAssist", stats);	
		numDeaths = loadJsonInt("numDeaths", stats);
		numItemsBought = loadJsonInt("numItemsBought", stats);
		objectivePlayerScore = loadJsonInt("objectivePlayerScore", stats);
		pentaKills = loadJsonInt("pentaKills", stats);
		physicalDamageDealtPlayer = loadJsonInt("physicalDamageDealtPlayer", stats);
		physicalDamageDealtToChampions = loadJsonInt("physicalDamageDealtToChampions", stats);	
		physicalDamageTaken = loadJsonInt("physicalDamageTaken", stats);	
		playerPosition = loadJsonInt("playerPosition", stats);	//Player position (Legal values: TOP(1), MIDDLE(2), JUNGLE(3), BOT(4))
		playerRole = loadJsonInt("playerRole", stats);		//Player role (Legal values: DUO(1), SUPPORT(2), CARRY(3), SOLO(4))
		quadraKills = loadJsonInt("quadraKills", stats);	
		sightWardsBought = loadJsonInt("sightWardsBought", stats);		
		superMonsterKilled = loadJsonInt("superMonsterKilled", stats);
		teamObjective = loadJsonInt("teamObjective", stats);
		totalDamageDealt = loadJsonInt("totalDamageDealt", stats);
		totalDamageDealtToChampions = loadJsonInt("totalDamageDealtToChampions", stats);
		totalDamageTaken = loadJsonInt("totalDamageTaken", stats);
		totalHeal = loadJsonInt("totalHeal", stats);
		totalPlayerScore = loadJsonInt("totalPlayerScore", stats);
		totalScoreRank = loadJsonInt("totalScoreRank", stats);
		totalTimeCrowdControlDealt = loadJsonInt("totalTimeCrowdControlDealt", stats);
		totalUnitsHealed = loadJsonInt("totalUnitsHealed", stats);
		tripleKills = loadJsonInt("tripleKills", stats);
		trueDamageDealtPlayer = loadJsonInt("trueDamageDealtPlayer", stats);
		trueDamageDealtToChampions = loadJsonInt("trueDamageDealtToChampions", stats);
		trueDamageTaken = loadJsonInt("trueDamageTaken", stats);
		turretsKilled = loadJsonInt("turretsKilled", stats);
		unrealKills = loadJsonInt("unrealKills", stats);
		victoryPoTotal = loadJsonInt("victoryPoTotal", stats);
		visionWardsBought = loadJsonInt("visionWardsBought", stats);
		wardKilled = loadJsonInt("wardKilled", stats);
		wardPlaced = loadJsonInt("wardPlaced", stats);
		win = loadJsonBool("win", stats);//win condition
		
		long timePlayed = json.getLong("createDate");//will not be null - has to have a time.
		Date time = new Date(timePlayed);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		weekDate = calendar.get(Calendar.WEEK_OF_YEAR);
		yearDate = calendar.get(Calendar.YEAR);

		for(int i=0; i < NUM_OF_ITEMS; i++){
			itemsID.add(loadJsonInt("item" + i, stats));
		}
		for(int i=1; i <= NUM_OF_SPELLS; i++){
			spellsID.add(loadJsonInt("spell" + i, json));
		}
		for(int i=1; i <= NUM_OF_CHAMPION_SPELLS; i++){
			if(i < 5)
				spellsCast.add(loadJsonInt("spell"+i+"Cast", stats));
			else
				spellsCast.add(loadJsonInt("summonSpell" + (i-4) +"Cast", stats));   
		}
	}
	public static int loadJsonInt(String param, JSONObject json){
		int field;
		try{
			field = json.getInt(param);
		}catch(JSONException e){
			field = 0;
		}
		return field;
	}
	public static String loadJsonString(String param, JSONObject json){
		String field = null;
		try{
			field = json.getString(param);
		}catch(JSONException e){//should not have any nulls
			e.printStackTrace();
		}
		return field;
	}
	public static boolean loadJsonBool(String param, JSONObject json){
		boolean field;
		try{
			field = json.getBoolean(param);
		}catch(JSONException e){//should not have any nulls
			field = false;
		}
		return field;
	}
}
