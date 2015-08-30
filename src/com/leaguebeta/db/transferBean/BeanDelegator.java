package com.leaguebeta.db.transferBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.leaguebeta.db.model.Aggregate.BannedChampionBean;
import com.leaguebeta.db.model.Team.TeamAndBanBeanWrapper;
import com.leaguebeta.db.model.Team.TeamBean;
import com.mongodb.connection.Stream;
import com.leaguebeta.db.model.Aggregate.MatchPackageBean;
import com.leaguebeta.db.model.Match.MatchBean;
import com.leaguebeta.db.model.Match.Timeline.EventBean;
import com.leaguebeta.db.model.Match.Timeline.ParticipantFrameBean;
import com.leaguebeta.db.model.Match.Timeline.PositionBean;
import com.leaguebeta.db.model.Participant.MasteryBean;
import com.leaguebeta.db.model.Participant.ParticipantTimelineBean;
import com.leaguebeta.db.model.Participant.ParticipantTimelineDataBean;
import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Participant.RuneBean;

public class BeanDelegator {
	public static final int NUM_ITEMS = 7;
	public static PlayerMatchBean[] delegatePlayerMatchBean(JSONObject json, int weekDate, int yearDate){
		/*take care of PlayerMatchBean*/
		
		
		JSONArray participantIdArray = json.getJSONArray("participantIdentities");
		JSONObject[] playerIdentities = new JSONObject[participantIdArray.length()];
		for(int i = 0; i <participantIdArray.length(); i++){
			JSONObject participant = participantIdArray.getJSONObject(i);
			playerIdentities[participant.getInt("participantId")-1] = participant.getJSONObject("player");//simply putting the player object into correct participant id slot
		}
		JSONArray participants = json.getJSONArray("participants");
		PlayerMatchBean[] players = new PlayerMatchBean[participants.length()];
		for(int i = 0; i < participants.length(); i++){
			JSONObject participant = participants.getJSONObject(i);
			
			/*taking care of RuneBean*/
			JSONArray runesArray = participant.optJSONArray("runes");
			RuneBean[] runes;
			try{
				runes = new RuneBean[runesArray.length()];
				for(int j = 0; j < runesArray.length(); j++){
					JSONObject rune = runesArray.getJSONObject(j);
					runes[j] = new RuneBean(rune.getInt("runeId"), rune.getInt("rank"));
				}
			}catch(Exception e){
				runes = new RuneBean[0];
			}
			
			/*taking care of MasteryBean*/
			JSONArray masteryArray = participant.optJSONArray("masteries");
			MasteryBean[] masteries;
			try{
				masteries = new MasteryBean[masteryArray.length()];
				for(int j = 0; j < masteryArray.length(); j++){
					JSONObject mastery = masteryArray.getJSONObject(j);
					masteries[j] = new MasteryBean(mastery.getInt("rank"), mastery.getInt("masteryId"));
				}
			}catch(Exception e){
				masteries = new MasteryBean[0];
			}
			
			/*taking care of loose individual info*/
			int championId = participant.getInt("championId");	//	int	Champion ID
			String highestAchievedSeasonTier = participant.getString("highestAchievedSeasonTier");	//	string	Highest ranked tier achieved for the previous season, if any, otherwise null. Used to display border in game loading screen. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED)
			int participantId = participant.getInt("participantId");	//	int	Participant ID
			int spell1Id = participant.getInt("spell1Id");	//	int	First summoner spell ID
			int spell2Id = participant.getInt("spell2Id"); 	//	int	Second summoner spell ID
			int teamId = participant.getInt("teamId");	//	int	Team ID
			
			/*taking care of timeline*/
			JSONObject participantTimelineObj = participant.getJSONObject("timeline");
			ParticipantTimelineDataBean	assistedLaneDeathsPerMinDeltas = loadParticipantTimelineDataBean("assistedLaneDeathsPerMinDeltas", participantTimelineObj);	//	ParticipantTimelineDataBean	Assisted lane deaths per minute timeline data
			ParticipantTimelineDataBean	assistedLaneKillsPerMinDeltas = loadParticipantTimelineDataBean("assistedLaneKillsPerMinDeltas", participantTimelineObj);	//	ParticipantTimelineDataBean	Assisted lane kills per minute timeline data
			ParticipantTimelineDataBean	creepsPerMinDeltas = loadParticipantTimelineDataBean("creepsPerMinDeltas", participantTimelineObj);	//	ParticipantTimelineDataBean	Creeps per minute timeline data
			ParticipantTimelineDataBean	csDiffPerMinDeltas = loadParticipantTimelineDataBean("csDiffPerMinDeltas", participantTimelineObj);	//	ParticipantTimelineDataBean	Creep score difference per minute timeline data
			ParticipantTimelineDataBean	damageTakenDiffPerMinDeltas = loadParticipantTimelineDataBean("damageTakenDiffPerMinDeltas", participantTimelineObj);	//	ParticipantTimelineDataBean	Damage taken difference per minute timeline data
			ParticipantTimelineDataBean	damageTakenPerMinDeltas = loadParticipantTimelineDataBean("damageTakenPerMinDeltas", participantTimelineObj);	//	ParticipantTimelineDataBean	Damage taken per minute timeline data
			ParticipantTimelineDataBean	goldPerMinDeltas = loadParticipantTimelineDataBean("goldPerMinDeltas", participantTimelineObj);	
			String lane = participantTimelineObj.getString("lane");	//	string	Participant's lane (Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
			String role = participantTimelineObj.getString("role");	//	string	Participant's role (Legal values: DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT)
			ParticipantTimelineDataBean	xpDiffPerMinDeltas = loadParticipantTimelineDataBean("xpDiffPerMinDeltas", participantTimelineObj);
			ParticipantTimelineDataBean	xpPerMinDeltas = loadParticipantTimelineDataBean("xpPerMinDeltas", participantTimelineObj);	
			
			ParticipantTimelineBean participantTimeline = new ParticipantTimelineBean( assistedLaneDeathsPerMinDeltas,
					 assistedLaneKillsPerMinDeltas,  creepsPerMinDeltas,
					 csDiffPerMinDeltas,  damageTakenDiffPerMinDeltas,
					 damageTakenPerMinDeltas,  goldPerMinDeltas,
					 lane,  role,  xpDiffPerMinDeltas,
					 xpPerMinDeltas);
			
			/*taking care of tight individual info*/
			JSONObject stats = participant.getJSONObject("stats");
			int assists = stats.optInt("assists");	//	long	Number of assists
			int champLevel = stats.optInt("champLevel");	//	long	Champion level achieved
			int combatPlayerScore= stats.optInt("combatPlayerScore");	//	long	If game was a dominion game, player's combat score, otherwise 0
			int deaths= stats.optInt("deaths");	;	//	long	Number of deaths
			int doubleKills=stats.optInt("doubleKills");	//	long	Number of double kills
			boolean firstBloodAssist=stats.optBoolean("firstBloodAssist");	//	boolean	Flag indicating if participant got an assist on first blood
			boolean firstBloodKill=stats.optBoolean("firstBloodKill");		//	boolean	Flag indicating if participant got first blood
			boolean firstInhibitorAssist=stats.optBoolean("firstInhibitorAssist");	//	boolean	Flag indicating if participant got an assist on the first inhibitor
            boolean firstInhibitorKill=stats.optBoolean("firstInhibitorKill");	//	boolean	Flag indicating if participant destroyed the first inhibitor
			boolean firstTowerAssist=stats.optBoolean("firstTowerAssist");	//	boolean	Flag indicating if participant got an assist on the first tower
			boolean firstTowerKill=stats.optBoolean("firstTowerKill");	//	boolean	Flag indicating if participant destroyed the first tower
			int goldEarned=stats.optInt("goldEarned");	//	long	Gold earned
			int goldSpent=stats.optInt("goldSpent");	//	long	Gold spent
			int inhibitorKills=stats.optInt("inhibitorKills");	//	long	Number of inhibitor kills
			int[] items = new int[NUM_ITEMS];	//	long	array of items
			for(int j = 0; j < NUM_ITEMS; j++){
				items[j] = stats.getInt("item"+j);
			}
			int killingSprees=stats.optInt("killingSprees");	//	long	Number of killing sprees
			int kills=stats.optInt("kills");	//	long	Number of kills
			int largestCriticalStrike=stats.optInt("largestCriticalStrike");	//	long	Largest critical strike
			int largestKillingSpree=stats.optInt("largestKillingSpree");	//	long	Largest killing spree
			int largestMultiKill=stats.optInt("largestMultiKill");	//	long	Largest multi kill
			int magicDamageDealt=stats.optInt("magicDamageDealt");	//	long	Magical damage dealt
			int magicDamageDealtToChampions=stats.optInt("magicDamageDealtToChampions");	//	long	Magical damage dealt to champions
			int magicDamageTaken=stats.optInt("magicDamageTaken");	//	long	Magic damage take
            int minionsKilled=stats.optInt("minionsKilled");	//	long	Minions killed
			int neutralMinionsKilled=stats.optInt("neutralMinionsKilled");	//	long	Neutral minions killed
			int neutralMinionsKilledEnemyJungle=stats.optInt("neutralMinionsKilledEnemyJungle");	//	long	Neutral jungle minions killed in the enemy team's jungle
			int neutralMinionsKilledTeamJungle=stats.optInt("neutralMinionsKilledTeamJungle");	//	long	Neutral jungle minions killed in your team's jungle
			
			/* Dominion */
			int nodeCapture=stats.optInt("nodeCapture");	//	long	If game was a dominion game, number of node captures
			int nodeCaptureAssist=stats.optInt("nodeCaptureAssist");	//	long	If game was a dominion game, number of node capture assists
			int nodeNeutralize=stats.optInt("nodeNeutralize");	//	long	If game was a dominion game, number of node neutralizations
			int nodeNeutralizeAssist=stats.optInt("nodeNeutralizeAssist");	//	long	If game was a dominion game, number of node neutralization assists
			int objectivePlayerScore=stats.optInt("objectivePlayerScore");	//	long	If game was a dominion game, player's objectives score, otherwise 0
			
			int pentaKills=stats.optInt("pentaKills");	//	long	Number of penta kills
			int physicalDamageDealt=stats.optInt("physicalDamageDealt");	//	long	Physical damage dealt
			int physicalDamageDealtToChampions=stats.optInt("physicalDamageDealtToChampions");	//	long	Physical damage dealt to champions
			int physicalDamageTaken=stats.optInt("physicalDamageTaken");	//	long	Physical damage taken
			int quadraKills=stats.optInt("quadraKills");	//	long	Number of quadra kills
			int sightWardsBoughtInGame=stats.optInt("sightWardsBoughtInGame");	//	long	Sight wards purchased
			int teamObjective=stats.optInt("teamObjective");	//	long	If game was a dominion game, number of completed team objectives (i.e., quests)
			int totalDamageDealt=stats.optInt("totalDamageDealt");	//	long	Total damage dealt
			int totalDamageDealtToChampions=stats.optInt("totalDamageDealtToChampions");	//	long	Total damage dealt to champions
			int totalDamageTaken=stats.optInt("totalDamageTaken");	//	long	Total damage taken
			int totalHeal=stats.optInt("totalHeal");	//	long	Total heal amount
			int totalPlayerScore=stats.optInt("totalPlayerScore");	//	long	If game was a dominion game, player's total score, otherwise 0
			int totalScoreRank=stats.optInt("totalScoreRank");	//	long	If game was a dominion game, team rank of the player's total score (e.g., 1-5)
			int totalTimeCrowdControlDealt=stats.optInt("totalTimeCrowdControlDealt");	//	long	Total dealt crowd control time
			int totalUnitsHealed=stats.optInt("totalUnitsHealed");	//	long	Total units healed
			int towerKills=stats.optInt("towerKills");	//	long	Number of tower kills
			int tripleKills=stats.optInt("tripleKills");	//	long	Number of triple kills
			int trueDamageDealt=stats.optInt("trueDamageDealt");	//	long	True damage dealt
			int trueDamageDealtToChampions=stats.optInt("trueDamageDealtToChampions");	//	long	True damage dealt to champions
			int trueDamageTaken=stats.optInt("trueDamageTaken");	//	long	True damage taken
			int unrealKills=stats.optInt("unrealKills");	//	long	Number of unreal kills
			int visionWardsBoughtInGame=stats.optInt("visionWardsBoughtInGame");	//	long	Vision wards purchased
			int wardsKilled=stats.optInt("wardsKilled");	//	long	Number of wards killed
			int wardsPlaced=stats.optInt("wardsPlaced");	//	long	Number of wards placed
			boolean winner=stats.optBoolean("winner");	//	boolean	Flag indicating whether or not the participant won
			players[i] = new PlayerMatchBean( json.getLong("matchId"), playerIdentities[participantId-1].getInt("profileIcon"), 
					playerIdentities[participantId-1].getInt("summonerId"), 
					playerIdentities[participantId-1].getString("summonerName"), championId,
					 highestAchievedSeasonTier, masteries, participantId, runes,
					 spell1Id, spell2Id, teamId, assists, champLevel, combatPlayerScore, deaths,
					 doubleKills, firstBloodAssist, firstBloodKill, firstInhibitorAssist, firstInhibitorKill,
					 firstTowerAssist, firstTowerKill, goldEarned, goldSpent, inhibitorKills, items,
					 killingSprees, kills, largestCriticalStrike, largestKillingSpree, largestMultiKill,
					 magicDamageDealt, magicDamageDealtToChampions, magicDamageTaken, minionsKilled,
					 neutralMinionsKilled, neutralMinionsKilledEnemyJungle, neutralMinionsKilledTeamJungle,
					 nodeCapture, nodeCaptureAssist, nodeNeutralize, nodeNeutralizeAssist,
					 objectivePlayerScore, pentaKills, physicalDamageDealt, physicalDamageDealtToChampions,
					 physicalDamageTaken, quadraKills, sightWardsBoughtInGame, teamObjective,
					 totalDamageDealt, totalDamageDealtToChampions, totalDamageTaken, totalHeal,
					 totalPlayerScore, totalScoreRank, totalTimeCrowdControlDealt, totalUnitsHealed,
					 towerKills, tripleKills, trueDamageDealt, trueDamageDealtToChampions, trueDamageTaken,
					 unrealKills, visionWardsBoughtInGame, wardsKilled, wardsPlaced, winner, participantTimeline, weekDate, yearDate);
		}
		return players;
	}
	public static TeamAndBanBeanWrapper delegateTeamBean(JSONObject json, int weekDate, int yearDate){
		/*takes care of TeamBean*/
		JSONArray teamArray = json.getJSONArray("teams");
		TeamBean[] teams = new TeamBean[teamArray.length()];
		ArrayList<BannedChampionBean> banList = new ArrayList<>();
		for(int i = 0; i < teamArray.length(); i++){
			JSONObject team = teamArray.getJSONObject(i);
			int baronKills = team.getInt("baronKills");	//	int	Number of times the team killed baron
			int dominionVictoryScore = team.getInt("dominionVictoryScore");	//	long	If game was a dominion game, specifies the points the team had at game end, otherwise null
			int dragonKills = team.getInt("dragonKills");	//	int	Number of times the team killed dragon
			boolean firstBaron = team.getBoolean("firstBaron");	//	boolean	Flag indicating whether or not the team got the first baron kill
			boolean firstBlood = team.getBoolean("firstBlood");	//	boolean	Flag indicating whether or not the team got first blood
			boolean	firstDragon = team.getBoolean("firstDragon");	//	boolean	Flag indicating whether or not the team got the first dragon kill
			boolean firstInhibitor = team.getBoolean("firstInhibitor");	//	boolean	Flag indicating whether or not the team destroyed the first inhibitor
			boolean firstTower = team.getBoolean("firstTower");	//	boolean	Flag indicating whether or not the team destroyed the first tower
			int inhibitorKills = team.getInt("inhibitorKills");	//	int	Number of inhibitors the team destroyed
			int teamId = team.getInt("teamId");
			int towerKills = team.getInt("towerKills");	//	int	Number of towers the team destroyed
			int vilemawKills = team.getInt("vilemawKills");	//	int	Number of times the team killed vilemaw
			boolean winner = team.getBoolean("winner");
			JSONArray banJson = team.getJSONArray("bans");
			for(int j = 0; j < banJson.length(); j++){
				JSONObject ban = banJson.getJSONObject(i);
				banList.add(new BannedChampionBean(ban.getInt("championId"), ban.getInt("pickTurn"), weekDate, yearDate, 0));//set 0 as stub!
			}
			teams[i] = new TeamBean(json.getLong("matchId"), baronKills, dominionVictoryScore, dragonKills, firstBaron, firstBlood, 
					firstDragon, firstInhibitor, firstTower, inhibitorKills, teamId, towerKills, vilemawKills, winner, weekDate, yearDate);
		}
		return new TeamAndBanBeanWrapper(teams, banList.toArray(new BannedChampionBean[banList.size()]));
	}
	public static MatchPackageBean delegateMatchJson(JSONObject json){
		/*time stuff, weekDate and yearDate are important*/
		long matchCreation = json.getLong("matchCreation"); //long	Match creation time. Designates when the team select lobby is created and/or the match is made through match making, not when the game actually starts.
		Date date = new Date(matchCreation);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDate = calendar.get(Calendar.WEEK_OF_YEAR);
		int yearDate = calendar.get(Calendar.YEAR);
		
		PlayerMatchBean[] players = delegatePlayerMatchBean(json, weekDate, yearDate);
		TeamAndBanBeanWrapper wrap = delegateTeamBean(json, weekDate, yearDate);
		TeamBean[] teams = wrap.getTeams();
		BannedChampionBean[] bans = wrap.getBans();
		
		int mapId = json.getInt("mapId"); //int	Match map ID
		long matchDuration = json.getLong("matchDuration"); //long	Match duration
		long matchId = json.getLong("matchId"); //long	ID of the match
		String matchMode = json.getString("matchMode"); //string	Match mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
		String matchType = json.getString("matchType"); //string	Match type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
		String matchVersion = json.getString("matchVersion"); //string	Match version
		String platformId = json.getString("platformId"); //string	Platform ID of the match
		String queueType = json.getString("queueType");	//string	Match queue type (Legal values: CUSTOM, NORMAL_5x5_BLIND, RANKED_SOLO_5x5, RANKED_PREMADE_5x5, BOT_5x5, NORMAL_3x3, RANKED_PREMADE_3x3, NORMAL_5x5_DRAFT, ODIN_5x5_BLIND, ODIN_5x5_DRAFT, BOT_ODIN_5x5, BOT_5x5_INTRO, BOT_5x5_BEGINNER, BOT_5x5_INTERMEDIATE, RANKED_TEAM_3x3, RANKED_TEAM_5x5, BOT_TT_3x3, GROUP_FINDER_5x5, ARAM_5x5, ONEFORALL_5x5, FIRSTBLOOD_1x1, FIRSTBLOOD_2x2, SR_6x6, URF_5x5, ONEFORALL_MIRRORMODE_5x5, BOT_URF_5x5, NIGHTMARE_BOT_5x5_RANK1, NIGHTMARE_BOT_5x5_RANK2, NIGHTMARE_BOT_5x5_RANK5, ASCENSION_5x5, HEXAKILL, BILGEWATER_ARAM_5x5, KING_PORO_5x5, COUNTER_PICK, BILGEWATER_5x5)
		String region = json.getString("region");	//	string	Region where the match was played
		String season = json.getString("season");	//	string	Season match was played (Legal values: PRESEASON3, SEASON3, PRESEASON2014, SEASON2014, PRESEASON2015, SEASON2015)
		MatchBean match = new MatchBean( mapId,  matchCreation,  matchDuration,  matchId,  matchMode,
				 matchType,  matchVersion,  platformId,  queueType,  region,  season, weekDate, yearDate);
//		JSONObject timeline = json.optJSONObject("timeline");
//		if(!(timeline == null)){/*that means the rest were all valued*/
//			JSONArray frames = timeline.optJSONArray("frames");
//			int frameInterval = timeline.optInt("frameInterval");
//			List<HashMap<String, Object>> eventBeans = new ArrayList<HashMap<String, Object>>();
//			List<ParticipantFrameBean> participants = new ArrayList<ParticipantFrameBean>();
//			for(int i = 0; i < frames.length(); i++){
//				JSONObject frame = frames.getJSONObject(i);
//				
//				int timeStamp = frame.getInt("timestamp"); //<-- this is not necessary - we already have frameInterval. keep it here for now.
//				
//				JSONArray events = frame.optJSONArray("events");//list
//				JSONObject participantFrames = frame.optJSONObject("participantFrames");//map	
//				Iterator<?> keys = participantFrames.keys();
//				/* Taking care of EventBean */
//				if(events != null){
//					for(int j = 0; j < events.length(); j++){
//						JSONObject eventObj = events.getJSONObject(j);
//						Iterator<?> eventKeys = eventObj.keys();
//						//String ascendedType = eventObj.optString("ascendedType");	//	string	The ascended type of the event. Only present if relevant. Note that CLEAR_ASCENDED refers to when a participants kills the ascended player. (Legal values: CHAMPION_ASCENDED, CLEAR_ASCENDED, MINION_ASCENDED)
//						//JSONArray assistArray = eventObj.optJSONArray("assistingParticipantIds");	//	List[int]	The assisting participant IDs of the event. Only present if relevant.
//						
//						//int[] assistingParticipantIds = null;
//						HashMap<String, Object> eventMap = new HashMap<String, Object>();
//						while(eventKeys.hasNext()){
//							String key = (String) eventKeys.next();
//							Object value = eventObj.get(key);
//						
//							if(value instanceof JSONObject && key.equals("position")){
//								JSONObject pos = (JSONObject)value;
//								eventMap.put(key, new PositionBean(pos.getInt("x"), pos.getInt("y")));
//							}
//							else if(value instanceof JSONArray && key.equals("assistingParticipantIds")){
//								JSONArray arr = ((JSONArray) value);
//								int[] ids = new int[arr.length()];
//								for(int k = 0; k < arr.length(); k++){
//									ids[k] = arr.getInt(k);
//								}
//								eventMap.put(key, ids);
//							}
//							else
//								eventMap.put(key, value);	
//						}
//						eventBeans.add(eventMap);
//					}
//				}
//				
//				/*taking care of ParticipantFrameBean*/
//				while(keys.hasNext()){
//					String key = (String)keys.next();
//					//key is the participantId, but not needed
//					JSONObject obj = participantFrames.getJSONObject(key);
//					int currentGold = obj.optInt("currentGold");	//	int	Participant's current gold
//					int dominionScore = obj.optInt("dominionScore");	//	int	Dominion score of the participant
//					int jungleMinionsKilled = obj.optInt("jungleMinionsKilled");	//	int	Number of jungle minions killed by participant
//					int level = obj.optInt("level");	//	int	Participant's current level
//					int minionsKilled = obj.optInt("minionsKilled");	//	int	Number of minions killed by participant
//					int participantId = obj.optInt("participantId");	//	int	Participant ID
//					JSONObject positionObj = obj.optJSONObject("position");	//	Position	Participant's position
//					PositionBean position = new PositionBean(-1, -1);	//means unavailable - null position!
//					if(positionObj != null)
//						position = new PositionBean(positionObj.getInt("x"), positionObj.getInt("y"));
//					int teamScore = obj.optInt("teamScore");	//	int	Team score of the participant
//					int totalGold = obj.optInt("totalGold");	//	int	Participant's total gold
//					int xp = obj.optInt("xp");	//	int	Experience earned by participant
//					
//					participants.add(new ParticipantFrameBean(currentGold, dominionScore, jungleMinionsKilled, level, minionsKilled,
//							participantId, position, teamScore, totalGold, xp, timeStamp));
//				}
//			}
			
//		}
//		else{//if timeline was not selected to be true
//			match = new MatchBean( mapId,  matchCreation,  matchDuration,  matchId,  matchMode,
//					 matchType,  matchVersion,  platformId,  queueType,  region,  season);
//		}
		return new MatchPackageBean(match, players, teams, bans);
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
	public static ParticipantTimelineDataBean loadParticipantTimelineDataBean(String field, JSONObject json){
		JSONObject specificObj = json.optJSONObject(field);
		if(specificObj == null)
			return new ParticipantTimelineDataBean(0,0,0,0);
		double zeroToTen = specificObj.optDouble("zeroToTen", 0);
		double tenToTwenty = specificObj.optDouble("tenToTwenty", 0);	
		double twentyToThirty = specificObj.optDouble("twentyTothirty", 0);	
		double thirtyToEnd = specificObj.optDouble("thirtyToEnd", 0);		
		ParticipantTimelineDataBean bean = new ParticipantTimelineDataBean(zeroToTen, tenToTwenty, twentyToThirty, thirtyToEnd);
		return bean;
	}
}
