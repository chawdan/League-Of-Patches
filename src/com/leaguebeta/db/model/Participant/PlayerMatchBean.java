package com.leaguebeta.db.model.Participant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * The League API splits into two types of data given to the developer. The matchv2.2 data is only allowed
 * for specific usages, and limited to ranked games and normal matches that are somehow given the ID's for. 
 * @author oneraynyday
 *
 */
public class PlayerMatchBean{
	//pre-made constants
	transient final int NUM_OF_ITEMS = 7;
	transient final int NUM_OF_SPELLS = 2;
	transient final int NUM_OF_CHAMPION_SPELLS = 6;//5 and 6 are summoner spells
	public static transient ArrayList<String> queryParams;
	static{
		queryParams = new ArrayList<String>();
		queryParams.add("summonerId");
		queryParams.add("championId");
		queryParams.add("matchId");
	}
	long matchId;
	/*
	 * individual summoner data
	 */
	int profileIcon;	//	int	Profile icon ID
	int summonerId;	//	long	Summoner ID
	String summonerName;
	/*
	 * individual, loose game info
	 */
	int championId;	//	int	Champion ID
	String highestAchievedSeasonTier;	//	string	Highest ranked tier achieved for the previous season, if any, otherwise null. Used to display border in game loading screen. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED)
	MasteryBean[] masteries;	//	List[Mastery]	List of mastery information
	int participantId;	//	int	Participant ID
	RuneBean[] runes;	//	List[Rune]	List of rune information
	int spell1Id;	//	int	First summoner spell ID
	int spell2Id; 	//	int	Second summoner spell ID
	int teamId;	//	int	Team ID
	
	/*
	 * individual tight game info
	 */
	int assists;	//	long	Number of assists
	int champLevel;	//	long	Champion level achieved
	int combatPlayerScore;	//	long	If game was a dominion game, player's combat score, otherwise 0
	int deaths;	//	long	Number of deaths
	int doubleKills;	//	long	Number of double kills
	boolean firstBloodAssist;	//	boolean	Flag indicating if participant got an assist on first blood
	boolean firstBloodKill;	//	boolean	Flag indicating if participant got first blood
	boolean firstInhibitorAssist;	//	boolean	Flag indicating if participant got an assist on the first inhibitor
	boolean firstInhibitorKill;	//	boolean	Flag indicating if participant destroyed the first inhibitor
	boolean firstTowerAssist;	//	boolean	Flag indicating if participant got an assist on the first tower
	boolean firstTowerKill;	//	boolean	Flag indicating if participant destroyed the first tower
	int goldEarned;	//	long	Gold earned
	int goldSpent;	//	long	Gold spent
	int inhibitorKills;	//	long	Number of inhibitor kills
	int[] items;	//	long	array of items
	int killingSprees;	//	long	Number of killing sprees
	int kills;	//	long	Number of kills
	int largestCriticalStrike;	//	long	Largest critical strike
	int largestKillingSpree;	//	long	Largest killing spree
	int largestMultiKill;	//	long	Largest multi kill
	int magicDamageDealt;	//	long	Magical damage dealt
	int magicDamageDealtToChampions;	//	long	Magical damage dealt to champions
	int magicDamageTaken;	//	long	Magic damage taken
	int minionsKilled;	//	long	Minions killed
	int neutralMinionsKilled;	//	long	Neutral minions killed
	int neutralMinionsKilledEnemyJungle;	//	long	Neutral jungle minions killed in the enemy team's jungle
	int neutralMinionsKilledTeamJungle;	//	long	Neutral jungle minions killed in your team's jungle
	int nodeCapture;	//	long	If game was a dominion game, number of node captures
	int nodeCaptureAssist;	//	long	If game was a dominion game, number of node capture assists
	int nodeNeutralize;	//	long	If game was a dominion game, number of node neutralizations
	int nodeNeutralizeAssist;	//	long	If game was a dominion game, number of node neutralization assists
	int objectivePlayerScore;	//	long	If game was a dominion game, player's objectives score, otherwise 0
	int pentaKills;	//	long	Number of penta kills
	int physicalDamageDealt;	//	long	Physical damage dealt
	int physicalDamageDealtToChampions;	//	long	Physical damage dealt to champions
	int physicalDamageTaken;	//	long	Physical damage taken
	int quadraKills;	//	long	Number of quadra kills
	int sightWardsBoughtInGame;	//	long	Sight wards purchased
	int teamObjective;	//	long	If game was a dominion game, number of completed team objectives (i.e., quests)
	int totalDamageDealt;	//	long	Total damage dealt
	int totalDamageDealtToChampions;	//	long	Total damage dealt to champions
	int totalDamageTaken;	//	long	Total damage taken
	int totalHeal;	//	long	Total heal amount
	int totalPlayerScore;	//	long	If game was a dominion game, player's total score, otherwise 0
	int totalScoreRank;	//	long	If game was a dominion game, team rank of the player's total score (e.g., 1-5)
	int totalTimeCrowdControlDealt;	//	long	Total dealt crowd control time
	int totalUnitsHealed;	//	long	Total units healed
	int towerKills;	//	long	Number of tower kills
	int tripleKills;	//	long	Number of triple kills
	int trueDamageDealt;	//	long	True damage dealt
	int trueDamageDealtToChampions;	//	long	True damage dealt to champions
	int trueDamageTaken;	//	long	True damage taken
	int unrealKills;	//	long	Number of unreal kills
	int visionWardsBoughtInGame;	//	long	Vision wards purchased
	int wardsKilled;	//	long	Number of wards killed
	int wardsPlaced;	//	long	Number of wards placed
	boolean winner;	//	boolean	Flag indicating whether or not the participant won
	
	/* player's timeline data */
	ParticipantTimelineBean timeline;
	
	/*time created*/
	int weekDate;
	int yearDate;
	
	public PlayerMatchBean(){/*empty because nothing is declared*/};

	public PlayerMatchBean(long matchId, int profileIcon, int summonerId, String summonerName, int championId,
			String highestAchievedSeasonTier, MasteryBean[] masteries, int participantId, RuneBean[] runes,
			int spell1Id, int spell2Id, int teamId, int assists, int champLevel, int combatPlayerScore, int deaths,
			int doubleKills, boolean firstBloodAssist, boolean firstBloodKill, boolean firstInhibitorAssist,
			boolean firstInhibitorKill, boolean firstTowerAssist, boolean firstTowerKill, int goldEarned, int goldSpent,
			int inhibitorKills, int[] items, int killingSprees, int kills, int largestCriticalStrike,
			int largestKillingSpree, int largestMultiKill, int magicDamageDealt, int magicDamageDealtToChampions,
			int magicDamageTaken, int minionsKilled, int neutralMinionsKilled, int neutralMinionsKilledEnemyJungle,
			int neutralMinionsKilledTeamJungle, int nodeCapture, int nodeCaptureAssist, int nodeNeutralize,
			int nodeNeutralizeAssist, int objectivePlayerScore, int pentaKills, int physicalDamageDealt,
			int physicalDamageDealtToChampions, int physicalDamageTaken, int quadraKills, int sightWardsBoughtInGame,
			int teamObjective, int totalDamageDealt, int totalDamageDealtToChampions, int totalDamageTaken,
			int totalHeal, int totalPlayerScore, int totalScoreRank, int totalTimeCrowdControlDealt,
			int totalUnitsHealed, int towerKills, int tripleKills, int trueDamageDealt, int trueDamageDealtToChampions,
			int trueDamageTaken, int unrealKills, int visionWardsBoughtInGame, int wardsKilled, int wardsPlaced,
			boolean winner, ParticipantTimelineBean timeline, int weekDate, int yearDate) {
		this.matchId = matchId;
		this.profileIcon = profileIcon;
		this.summonerId = summonerId;
		this.summonerName = summonerName;
		this.championId = championId;
		this.highestAchievedSeasonTier = highestAchievedSeasonTier;
		this.masteries = masteries;
		this.participantId = participantId;
		this.runes = runes;
		this.spell1Id = spell1Id;
		this.spell2Id = spell2Id;
		this.teamId = teamId;
		this.assists = assists;
		this.champLevel = champLevel;
		this.combatPlayerScore = combatPlayerScore;
		this.deaths = deaths;
		this.doubleKills = doubleKills;
		this.firstBloodAssist = firstBloodAssist;
		this.firstBloodKill = firstBloodKill;
		this.firstInhibitorAssist = firstInhibitorAssist;
		this.firstInhibitorKill = firstInhibitorKill;
		this.firstTowerAssist = firstTowerAssist;
		this.firstTowerKill = firstTowerKill;
		this.goldEarned = goldEarned;
		this.goldSpent = goldSpent;
		this.inhibitorKills = inhibitorKills;
		this.items = items;
		this.killingSprees = killingSprees;
		this.kills = kills;
		this.largestCriticalStrike = largestCriticalStrike;
		this.largestKillingSpree = largestKillingSpree;
		this.largestMultiKill = largestMultiKill;
		this.magicDamageDealt = magicDamageDealt;
		this.magicDamageDealtToChampions = magicDamageDealtToChampions;
		this.magicDamageTaken = magicDamageTaken;
		this.minionsKilled = minionsKilled;
		this.neutralMinionsKilled = neutralMinionsKilled;
		this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
		this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
		this.nodeCapture = nodeCapture;
		this.nodeCaptureAssist = nodeCaptureAssist;
		this.nodeNeutralize = nodeNeutralize;
		this.nodeNeutralizeAssist = nodeNeutralizeAssist;
		this.objectivePlayerScore = objectivePlayerScore;
		this.pentaKills = pentaKills;
		this.physicalDamageDealt = physicalDamageDealt;
		this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
		this.physicalDamageTaken = physicalDamageTaken;
		this.quadraKills = quadraKills;
		this.sightWardsBoughtInGame = sightWardsBoughtInGame;
		this.teamObjective = teamObjective;
		this.totalDamageDealt = totalDamageDealt;
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
		this.totalDamageTaken = totalDamageTaken;
		this.totalHeal = totalHeal;
		this.totalPlayerScore = totalPlayerScore;
		this.totalScoreRank = totalScoreRank;
		this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
		this.totalUnitsHealed = totalUnitsHealed;
		this.towerKills = towerKills;
		this.tripleKills = tripleKills;
		this.trueDamageDealt = trueDamageDealt;
		this.trueDamageDealtToChampions = trueDamageDealtToChampions;
		this.trueDamageTaken = trueDamageTaken;
		this.unrealKills = unrealKills;
		this.visionWardsBoughtInGame = visionWardsBoughtInGame;
		this.wardsKilled = wardsKilled;
		this.wardsPlaced = wardsPlaced;
		this.winner = winner;
		this.timeline = timeline;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
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
	
	/*getters and setters*/

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public int getProfileIcon() {
		return profileIcon;
	}

	public void setProfileIcon(int profileIcon) {
		this.profileIcon = profileIcon;
	}

	public int getSummonerId() {
		return summonerId;
	}

	public void setSummonerId(int summonerId) {
		this.summonerId = summonerId;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	public int getChampionId() {
		return championId;
	}

	public void setChampionId(int championId) {
		this.championId = championId;
	}

	public String getHighestAchievedSeasonTier() {
		return highestAchievedSeasonTier;
	}

	public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
		this.highestAchievedSeasonTier = highestAchievedSeasonTier;
	}

	public MasteryBean[] getMasteries() {
		return masteries;
	}

	public void setMasteries(MasteryBean[] masteries) {
		this.masteries = masteries;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public RuneBean[] getRunes() {
		return runes;
	}

	public void setRunes(RuneBean[] runes) {
		this.runes = runes;
	}

	public int getSpell1Id() {
		return spell1Id;
	}

	public void setSpell1Id(int spell1Id) {
		this.spell1Id = spell1Id;
	}

	public int getSpell2Id() {
		return spell2Id;
	}

	public void setSpell2Id(int spell2Id) {
		this.spell2Id = spell2Id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getChampLevel() {
		return champLevel;
	}

	public void setChampLevel(int champLevel) {
		this.champLevel = champLevel;
	}

	public int getCombatPlayerScore() {
		return combatPlayerScore;
	}

	public void setCombatPlayerScore(int combatPlayerScore) {
		this.combatPlayerScore = combatPlayerScore;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getDoubleKills() {
		return doubleKills;
	}

	public void setDoubleKills(int doubleKills) {
		this.doubleKills = doubleKills;
	}

	public boolean isFirstBloodAssist() {
		return firstBloodAssist;
	}

	public void setFirstBloodAssist(boolean firstBloodAssist) {
		this.firstBloodAssist = firstBloodAssist;
	}

	public boolean isFirstBloodKill() {
		return firstBloodKill;
	}

	public void setFirstBloodKill(boolean firstBloodKill) {
		this.firstBloodKill = firstBloodKill;
	}

	public boolean isFirstInhibitorAssist() {
		return firstInhibitorAssist;
	}

	public void setFirstInhibitorAssist(boolean firstInhibitorAssist) {
		this.firstInhibitorAssist = firstInhibitorAssist;
	}

	public boolean isFirstInhibitorKill() {
		return firstInhibitorKill;
	}

	public void setFirstInhibitorKill(boolean firstInhibitorKill) {
		this.firstInhibitorKill = firstInhibitorKill;
	}

	public boolean isFirstTowerAssist() {
		return firstTowerAssist;
	}

	public void setFirstTowerAssist(boolean firstTowerAssist) {
		this.firstTowerAssist = firstTowerAssist;
	}

	public boolean isFirstTowerKill() {
		return firstTowerKill;
	}

	public void setFirstTowerKill(boolean firstTowerKill) {
		this.firstTowerKill = firstTowerKill;
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

	public int getInhibitorKills() {
		return inhibitorKills;
	}

	public void setInhibitorKills(int inhibitorKills) {
		this.inhibitorKills = inhibitorKills;
	}

	public int[] getItems() {
		return items;
	}

	public void setItems(int[] items) {
		this.items = items;
	}

	public int getKillingSprees() {
		return killingSprees;
	}

	public void setKillingSprees(int killingSprees) {
		this.killingSprees = killingSprees;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
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

	public int getMagicDamageDealt() {
		return magicDamageDealt;
	}

	public void setMagicDamageDealt(int magicDamageDealt) {
		this.magicDamageDealt = magicDamageDealt;
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

	public int getNeutralMinionsKilledTeamJungle() {
		return neutralMinionsKilledTeamJungle;
	}

	public void setNeutralMinionsKilledTeamJungle(int neutralMinionsKilledTeamJungle) {
		this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
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

	public int getPhysicalDamageDealt() {
		return physicalDamageDealt;
	}

	public void setPhysicalDamageDealt(int physicalDamageDealt) {
		this.physicalDamageDealt = physicalDamageDealt;
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

	public int getQuadraKills() {
		return quadraKills;
	}

	public void setQuadraKills(int quadraKills) {
		this.quadraKills = quadraKills;
	}

	public int getSightWardsBoughtInGame() {
		return sightWardsBoughtInGame;
	}

	public void setSightWardsBoughtInGame(int sightWardsBoughtInGame) {
		this.sightWardsBoughtInGame = sightWardsBoughtInGame;
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

	public int getTowerKills() {
		return towerKills;
	}

	public void setTowerKills(int towerKills) {
		this.towerKills = towerKills;
	}

	public int getTripleKills() {
		return tripleKills;
	}

	public void setTripleKills(int tripleKills) {
		this.tripleKills = tripleKills;
	}

	public int getTrueDamageDealt() {
		return trueDamageDealt;
	}

	public void setTrueDamageDealt(int trueDamageDealt) {
		this.trueDamageDealt = trueDamageDealt;
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

	public int getUnrealKills() {
		return unrealKills;
	}

	public void setUnrealKills(int unrealKills) {
		this.unrealKills = unrealKills;
	}

	public int getVisionWardsBoughtInGame() {
		return visionWardsBoughtInGame;
	}

	public void setVisionWardsBoughtInGame(int visionWardsBoughtInGame) {
		this.visionWardsBoughtInGame = visionWardsBoughtInGame;
	}

	public int getWardsKilled() {
		return wardsKilled;
	}

	public void setWardsKilled(int wardsKilled) {
		this.wardsKilled = wardsKilled;
	}

	public int getWardsPlaced() {
		return wardsPlaced;
	}

	public void setWardsPlaced(int wardsPlaced) {
		this.wardsPlaced = wardsPlaced;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public ParticipantTimelineBean getTimeline() {
		return timeline;
	}

	public void setTimeline(ParticipantTimelineBean timeline) {
		this.timeline = timeline;
	}	
}
