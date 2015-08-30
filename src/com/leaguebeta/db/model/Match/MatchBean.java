package com.leaguebeta.db.model.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leaguebeta.db.model.Match.Timeline.EventBean;
import com.leaguebeta.db.model.Match.Timeline.ParticipantFrameBean;

public class MatchBean {
	//general game info
	public static transient ArrayList<String> queryParams, removeParams;
	static{
		queryParams = new ArrayList<String>();
		removeParams = new ArrayList<String>();
		
		queryParams.add("matchId");
		queryParams.add("season");
		queryParams.add("weekDate");
		queryParams.add("yearDate");
		queryParams.add("matchType");
		queryParams.add("queueType");
		queryParams.add("mapId");
		queryParams.add("region");
		queryParams.add("matchMode");
		
		removeParams.add("matchId");
		removeParams.add("season");
		removeParams.add("weekDate");
		removeParams.add("yearDate");
		removeParams.add("matchType");
		removeParams.add("queueType");
		removeParams.add("mapId");
		removeParams.add("region");
		removeParams.add("matchMode");

	}
	int mapId; //int	Match map ID
	long matchCreation; //long	Match creation time. Designates when the team select lobby is created and/or the match is made through match making, not when the game actually starts.
	long matchDuration; //long	Match duration
	long matchId; //long	ID of the match
	int weekDate, yearDate;
	String matchMode; //string	Match mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
	String matchType; //string	Match type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
	String matchVersion; //string	Match version
	String platformId; //string	Platform ID of the match
	String queueType;	//string	Match queue type (Legal values: CUSTOM, NORMAL_5x5_BLIND, RANKED_SOLO_5x5, RANKED_PREMADE_5x5, BOT_5x5, NORMAL_3x3, RANKED_PREMADE_3x3, NORMAL_5x5_DRAFT, ODIN_5x5_BLIND, ODIN_5x5_DRAFT, BOT_ODIN_5x5, BOT_5x5_INTRO, BOT_5x5_BEGINNER, BOT_5x5_INTERMEDIATE, RANKED_TEAM_3x3, RANKED_TEAM_5x5, BOT_TT_3x3, GROUP_FINDER_5x5, ARAM_5x5, ONEFORALL_5x5, FIRSTBLOOD_1x1, FIRSTBLOOD_2x2, SR_6x6, URF_5x5, ONEFORALL_MIRRORMODE_5x5, BOT_URF_5x5, NIGHTMARE_BOT_5x5_RANK1, NIGHTMARE_BOT_5x5_RANK2, NIGHTMARE_BOT_5x5_RANK5, ASCENSION_5x5, HEXAKILL, BILGEWATER_ARAM_5x5, KING_PORO_5x5, COUNTER_PICK, BILGEWATER_5x5)
	String region;	//	string	Region where the match was played
	String season;	//	string	Season match was played (Legal values: PRESEASON3, SEASON3, PRESEASON2014, SEASON2014, PRESEASON2015, SEASON2015)
	/* super tight game data from Frames */
	//EventBean[] events;	//	List[Event]	List of events for this frame. 
	//HashMap<String, Object>[] events;
	//ParticipantFrameBean[] participantFrames;	//	Map[string, ParticipantFrame]	Map of each participant ID to the participant's information for the frame.
	public MatchBean(){/*empty on purpose*/}
	public MatchBean(int mapId, long matchCreation, long matchDuration, long matchId, String matchMode,
			String matchType, String matchVersion, String platformId, String queueType, String region, String season,
			int weekDate, int yearDate) {
		super();
		this.mapId = mapId;
		this.matchCreation = matchCreation;
		this.matchDuration = matchDuration;
		this.matchId = matchId;
		this.matchMode = matchMode;
		this.matchType = matchType;
		this.matchVersion = matchVersion;
		this.platformId = platformId;
		this.queueType = queueType;
		this.region = region;
		this.season = season;
		this.weekDate = weekDate;
		this.yearDate = yearDate;
	}
	
	public List<String> getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(ArrayList<String> queryParams) {
		this.queryParams = queryParams;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public long getMatchCreation() {
		return matchCreation;
	}
	public void setMatchCreation(long matchCreation) {
		this.matchCreation = matchCreation;
	}
	public long getMatchDuration() {
		return matchDuration;
	}
	public void setMatchDuration(long matchDuration) {
		this.matchDuration = matchDuration;
	}
	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public String getMatchMode() {
		return matchMode;
	}
	public void setMatchMode(String matchMode) {
		this.matchMode = matchMode;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public String getMatchVersion() {
		return matchVersion;
	}
	public void setMatchVersion(String matchVersion) {
		this.matchVersion = matchVersion;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getQueueType() {
		return queueType;
	}
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
}
