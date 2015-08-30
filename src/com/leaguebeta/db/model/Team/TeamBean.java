package com.leaguebeta.db.model.Team;

import java.util.ArrayList;

public class TeamBean {
	public static transient ArrayList<String> queryParams, removeParams;
	static{
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();
		
		queryParams.add("teamId");
		queryParams.add("matchId");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		
		removeParams.add("teamId");
		removeParams.add("matchId");
		removeParams.add("weekDate");
		removeParams.add("yearDate");
	}
	//team tight game info
	long matchId;
	int baronKills;	//	int	Number of times the team killed baron
	int dominionVictoryScore;	//	long	If game was a dominion game, specifies the points the team had at game end, otherwise null
	int dragonKills;	//	int	Number of times the team killed dragon
	boolean firstBaron;	//	boolean	Flag indicating whether or not the team got the first baron kill
	boolean firstBlood;	//	boolean	Flag indicating whether or not the team got first blood
	boolean	firstDragon;	//	boolean	Flag indicating whether or not the team got the first dragon kill
	boolean firstInhibitor;	//	boolean	Flag indicating whether or not the team destroyed the first inhibitor
	boolean firstTower;	//	boolean	Flag indicating whether or not the team destroyed the first tower
	int inhibitorKills;	//	int	Number of inhibitors the team destroyed
	int teamId;
	int towerKills;	//	int	Number of towers the team destroyed
	int vilemawKills;	//	int	Number of times the team killed vilemaw
	boolean winner;	//	boolean	Flag indicating whether or not the team won
	
	int weekDate;
	int yearDate;

	public TeamBean(long matchId, int baronKills, int dominionVictoryScore, int dragonKills,
			boolean firstBaron, boolean firstBlood, boolean firstDragon, boolean firstInhibitor, boolean firstTower,
			int inhibitorKills, int teamId, int towerKills, int vilemawKills, boolean winner, int weekDate, int yearDate) {
		this.matchId = matchId;
		this.baronKills = baronKills;
		this.dominionVictoryScore = dominionVictoryScore;
		this.dragonKills = dragonKills;
		this.firstBaron = firstBaron;
		this.firstBlood = firstBlood;
		this.firstDragon = firstDragon;
		this.firstInhibitor = firstInhibitor;
		this.firstTower = firstTower;
		this.inhibitorKills = inhibitorKills;
		this.teamId = teamId;
		this.towerKills = towerKills;
		this.vilemawKills = vilemawKills;
		this.winner = winner;
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

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public int getBaronKills() {
		return baronKills;
	}
	public void setBaronKills(int baronKills) {
		this.baronKills = baronKills;
	}
	public long getDominionVictoryScore() {
		return dominionVictoryScore;
	}
	public void setDominionVictoryScore(int dominionVictoryScore) {
		this.dominionVictoryScore = dominionVictoryScore;
	}
	public int getDragonKills() {
		return dragonKills;
	}
	public void setDragonKills(int dragonKills) {
		this.dragonKills = dragonKills;
	}
	public boolean isFirstBaron() {
		return firstBaron;
	}
	public void setFirstBaron(boolean firstBaron) {
		this.firstBaron = firstBaron;
	}
	public boolean isFirstBlood() {
		return firstBlood;
	}
	public void setFirstBlood(boolean firstBlood) {
		this.firstBlood = firstBlood;
	}
	public boolean isFirstDragon() {
		return firstDragon;
	}
	public void setFirstDragon(boolean firstDragon) {
		this.firstDragon = firstDragon;
	}
	public boolean isFirstInhibitor() {
		return firstInhibitor;
	}
	public void setFirstInhibitor(boolean firstInhibitor) {
		this.firstInhibitor = firstInhibitor;
	}
	public boolean isFirstTower() {
		return firstTower;
	}
	public void setFirstTower(boolean firstTower) {
		this.firstTower = firstTower;
	}
	public int getInhibitorKills() {
		return inhibitorKills;
	}
	public void setInhibitorKills(int inhibitorKills) {
		this.inhibitorKills = inhibitorKills;
	}
	public int getTowerKills() {
		return towerKills;
	}
	public void setTowerKills(int towerKills) {
		this.towerKills = towerKills;
	}
	public int getVilemawKills() {
		return vilemawKills;
	}
	public void setVilemawKills(int vilemawKills) {
		this.vilemawKills = vilemawKills;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}	
}
