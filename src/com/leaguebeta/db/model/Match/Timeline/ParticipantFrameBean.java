package com.leaguebeta.db.model.Match.Timeline;

public class ParticipantFrameBean {
	int currentGold;	//	int	Participant's current gold
	int dominionScore;	//	int	Dominion score of the participant
	int jungleMinionsKilled;	//	int	Number of jungle minions killed by participant
	int level;	//	int	Participant's current level
	int minionsKilled;	//	int	Number of minions killed by participant
	int participantId;	//	int	Participant ID
	PositionBean position;	//	Position	Participant's position
	int teamScore;	//	int	Team score of the participant
	int totalGold;	//	int	Participant's total gold
	int xp;	//	int	Experience earned by participant
	int timestamp;
	
	public ParticipantFrameBean(int currentGold, int dominionScore, int jungleMinionsKilled, int level,
			int minionsKilled, int participantId, PositionBean position, int teamScore, int totalGold, int xp, int timestamp) {
		super();
		this.currentGold = currentGold;
		this.dominionScore = dominionScore;
		this.jungleMinionsKilled = jungleMinionsKilled;
		this.level = level;
		this.minionsKilled = minionsKilled;
		this.participantId = participantId;
		this.position = position;
		this.teamScore = teamScore;
		this.totalGold = totalGold;
		this.xp = xp;
		this.timestamp = timestamp;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public int getCurrentGold() {
		return currentGold;
	}
	public void setCurrentGold(int currentGold) {
		this.currentGold = currentGold;
	}
	public int getDominionScore() {
		return dominionScore;
	}
	public void setDominionScore(int dominionScore) {
		this.dominionScore = dominionScore;
	}
	public int getJungleMinionsKilled() {
		return jungleMinionsKilled;
	}
	public void setJungleMinionsKilled(int jungleMinionsKilled) {
		this.jungleMinionsKilled = jungleMinionsKilled;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMinionsKilled() {
		return minionsKilled;
	}
	public void setMinionsKilled(int minionsKilled) {
		this.minionsKilled = minionsKilled;
	}
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	public PositionBean getPosition() {
		return position;
	}
	public void setPosition(PositionBean position) {
		this.position = position;
	}
	public int getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(int teamScore) {
		this.teamScore = teamScore;
	}
	public int getTotalGold() {
		return totalGold;
	}
	public void setTotalGold(int totalGold) {
		this.totalGold = totalGold;
	}
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	
}
