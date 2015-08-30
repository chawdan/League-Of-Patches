package com.leaguebeta.db.model.Team;

import com.leaguebeta.db.model.Aggregate.BannedChampionBean;

public class TeamAndBanBeanWrapper {
	TeamBean[] teams;
	BannedChampionBean[] bans;
	public TeamAndBanBeanWrapper(TeamBean[] teams, BannedChampionBean[] bans) {
		super();
		this.teams = teams;
		this.bans = bans;
	}
	public TeamBean[] getTeams() {
		return teams;
	}
	public void setTeams(TeamBean[] teams) {
		this.teams = teams;
	}
	public BannedChampionBean[] getBans() {
		return bans;
	}
	public void setBans(BannedChampionBean[] bans) {
		this.bans = bans;
	}
	
}
