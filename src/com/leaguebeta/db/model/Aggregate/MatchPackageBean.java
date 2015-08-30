package com.leaguebeta.db.model.Aggregate;

import com.leaguebeta.db.model.Match.MatchBean;
import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Team.TeamBean;

public class MatchPackageBean {
	MatchBean match;
	PlayerMatchBean[] players;
	TeamBean[] teams;
	BannedChampionBean[] bans;
	
	public MatchPackageBean(MatchBean match, PlayerMatchBean[] players, TeamBean[] teams, BannedChampionBean[] bans) {
		super();
		this.match = match;
		this.players = players;
		this.teams = teams;
		this.bans = bans;
	}

	public BannedChampionBean[] getBans() {
		return bans;
	}

	public void setBans(BannedChampionBean[] bans) {
		this.bans = bans;
	}

	public MatchBean getMatch() {
		return match;
	}

	public void setMatch(MatchBean match) {
		this.match = match;
	}

	public PlayerMatchBean[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerMatchBean[] players) {
		this.players = players;
	}

	public TeamBean[] getTeams() {
		return teams;
	}

	public void setTeams(TeamBean[] teams) {
		this.teams = teams;
	}
	
	
}
