package com.leaguebeta.server.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.leaguebeta.db.connection.ClientConnector;
import com.leaguebeta.db.model.Aggregate.BannedChampionBean;
import com.leaguebeta.db.model.Aggregate.ChampionAggregateBean;
import com.leaguebeta.db.model.Aggregate.ChampionBean;
import com.leaguebeta.db.model.Aggregate.ItemAggregateBean;
import com.leaguebeta.db.model.Aggregate.ItemBean;
import com.leaguebeta.db.model.Aggregate.MasteryAggregateBean;
import com.leaguebeta.db.model.Aggregate.MatchPackageBean;
import com.leaguebeta.db.model.Aggregate.RankBean;
import com.leaguebeta.db.model.Aggregate.RuneAggregateBean;
import com.leaguebeta.db.model.Match.MatchBean;
import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Team.TeamBean;
import com.leaguebeta.db.transferBean.BeanDelegator;
import com.leaguebeta.db.transferBean.BeanParser;
import com.leaguebeta.db.transferBean.RankBeanMapper;
import com.leaguebeta.server.APICaller;


public class MatchProcessor {
	public final static int THREADS = 50;
    private static final ExecutorService service = Executors.newFixedThreadPool(THREADS);
    private static final boolean KILL_UPON_DUPLICATE = false;

    public static void processMatchList(final JSONArray matchList, APICaller caller, String region, boolean includeTimeline, ClientConnector connection) {
        service.submit(() -> runAnalysis(matchList, caller, region, includeTimeline, connection));
    }
    
    public static void processMatchList(final String[] matchList, APICaller caller, String region, boolean includeTimeline, ClientConnector connection) {
        service.submit(() -> runAnalysis(matchList, caller, region, includeTimeline, connection));
    }
    /*Overloaded - does the same thing*/
    private static void runAnalysis(final String[] matches, APICaller caller, String region, boolean includeTimeline, ClientConnector connection){
    	long start = System.nanoTime();

    	for (int i = 0; i < matches.length; i++) {
			JSONObject value = caller.callRiotMatch(region, matches[i], includeTimeline);
			if (value == null)
				continue;
			MatchPackageBean matchPackage = BeanDelegator.delegateMatchJson(value);
			/*
			 * create beanparser and send these items into the db as they are
			 * initialized
			 */
			MatchBean match = matchPackage.getMatch();
			long matchDuration = match.getMatchDuration();
			connection.insertJson(BeanParser.parseAnyBean(match), region + "_collection_match", MatchBean.queryParams);
			PlayerMatchBean[] players = matchPackage.getPlayers();
			String[] queryString = new String[players.length];
			for (int j = 0; j < players.length; j++) {
				queryString[j] = "" + players[j].getSummonerId();
			}
			String queryCompound = String.join(",", queryString);
			JSONObject allLeagues = caller.callRiotLeague(region, queryCompound);
			for (PlayerMatchBean player : players) {
				// each player has their own rankings - so call the api for it
				JSONArray league = null;
				try {
					league = allLeagues.getJSONArray("" + player.getSummonerId());
				} catch (JSONException e) {
					league = new JSONArray();
				}
				RankBean rank = RankBeanMapper.getSpecificRank(league, match.getQueueType());

				ItemBean[] items = ItemBean.playerMatchBeanToItemBean(player, rank, matchDuration);
				ItemAggregateBean[] itemAggregates = ItemAggregateBean.playerMatchBeanToItemAggregateBean(player);
				MasteryAggregateBean[] masteries = MasteryAggregateBean.playerMatchBeanToMasteryAggregateBean(player,
						rank, matchDuration);
				RuneAggregateBean[] runes = RuneAggregateBean.playerMatchBeanToRuneAggregateBean(player, rank,
						matchDuration);
			
				// Map<String, RankBean> rankedMap =
				// RankBeanMapper.simplifyBean(leagues); no need for a map right
				// now
				ChampionBean champ = ChampionBean.playerMatchBeanToChampBean(player, rank, matchDuration);
				ChampionAggregateBean champAggregate = ChampionAggregateBean.playerMatchBeanToChampionAggregateBean(player);
				if (connection.insertJson(BeanParser.parseAnyBean(player), region + "_collection_player",
						PlayerMatchBean.queryParams)) {
					connection.incrementJson(BeanParser.parseAnyBean(champ), region + "_collection_champ",
							ChampionBean.queryParams, ChampionBean.removeParams, false);
					connection.incrementJson(BeanParser.parseAnyBean(champAggregate), region + "_collection_champ_aggregate", 
							ChampionAggregateBean.queryParams, ChampionAggregateBean.removeParams, false);
					for (ItemBean item : items) {
						connection.incrementJson(BeanParser.parseAnyBean(item), region + "_collection_item",
								ItemBean.queryParams, ItemBean.removeParams, false);
					}
					for (MasteryAggregateBean mastery : masteries) {
						connection.incrementJson(BeanParser.parseAnyBean(mastery), region + "_collection_mastery",
								MasteryAggregateBean.queryParams, MasteryAggregateBean.removeParams, false);
					}
					for (RuneAggregateBean rune : runes) {
						connection.incrementJson(BeanParser.parseAnyBean(rune), region + "_collection_rune",
								RuneAggregateBean.queryParams, RuneAggregateBean.removeParams, false);
					}
					for (ItemAggregateBean item : itemAggregates){
						connection.incrementJson(BeanParser.parseAnyBean(item), region + "_collection_item_aggregate",
								ItemAggregateBean.queryParams, ItemAggregateBean.removeParams, false);
					}
				} else if (KILL_UPON_DUPLICATE) // we are done here -
												// IMPLEMENTATION-WISE
					i = matches.length;
			}
			TeamBean[] teams = matchPackage.getTeams();
			for (TeamBean team : teams) {
				connection.insertJson(BeanParser.parseAnyBean(team), region + "_collection_team", TeamBean.queryParams);
			}
		}
		long end = System.nanoTime();
		System.out.println("successful! Time: " + (end - start) / 1000000000);
    }
    private static void runAnalysis(final JSONArray matches, APICaller caller, String region, boolean includeTimeline, ClientConnector connection) {
		long start = System.nanoTime();

    	for (int i = 0; i < matches.length(); i++) {
			JSONObject value = caller.callRiotMatch(region, "" + matches.getJSONObject(i).getInt("matchId"),
					includeTimeline);
			if (value == null)
				continue;
			MatchPackageBean matchPackage = BeanDelegator.delegateMatchJson(value);
			/*
			 * create beanparser and send these items into the db as they are
			 * initialized
			 */
			MatchBean match = matchPackage.getMatch();
			long matchDuration = match.getMatchDuration();
			connection.insertJson(BeanParser.parseAnyBean(match), region + "_collection_match", MatchBean.queryParams);
			PlayerMatchBean[] players = matchPackage.getPlayers();
			BannedChampionBean[] bans = matchPackage.getBans();
			String[] queryString = new String[players.length];
			for (int j = 0; j < players.length; j++) {
				queryString[j] = "" + players[j].getSummonerId();
			}
			String queryCompound = String.join(",", queryString);
			JSONObject allLeagues = caller.callRiotLeague(region, queryCompound);
			for (BannedChampionBean ban : bans){
				connection.incrementJson(BeanParser.parseAnyBean(ban), region + "_collection_ban", 
						ChampionAggregateBean.queryParams, ChampionAggregateBean.removeParams, false);
			}
			for (PlayerMatchBean player : players) {
				// each player has their own rankings - so call the api for it
				JSONArray league = null;
				try {
					league = allLeagues.getJSONArray("" + player.getSummonerId());
				} catch (JSONException e) {
					league = new JSONArray();
				}
				RankBean rank = RankBeanMapper.getSpecificRank(league, match.getQueueType());

				ItemBean[] items = ItemBean.playerMatchBeanToItemBean(player, rank, matchDuration);
				ItemAggregateBean[] itemAggregates = ItemAggregateBean.playerMatchBeanToItemAggregateBean(player);
				MasteryAggregateBean[] masteries = MasteryAggregateBean.playerMatchBeanToMasteryAggregateBean(player,
						rank, matchDuration);
				RuneAggregateBean[] runes = RuneAggregateBean.playerMatchBeanToRuneAggregateBean(player, rank,
						matchDuration);
			
				// Map<String, RankBean> rankedMap =
				// RankBeanMapper.simplifyBean(leagues); no need for a map right
				// now
				ChampionBean champ = ChampionBean.playerMatchBeanToChampBean(player, rank, matchDuration);
				ChampionAggregateBean champAggregate = ChampionAggregateBean.playerMatchBeanToChampionAggregateBean(player);
				if (connection.insertJson(BeanParser.parseAnyBean(player), region + "_collection_player",
						PlayerMatchBean.queryParams)) {
					connection.incrementJson(BeanParser.parseAnyBean(champ), region + "_collection_champ",
							ChampionBean.queryParams, ChampionBean.removeParams, false);
					connection.incrementJson(BeanParser.parseAnyBean(champAggregate), region + "_collection_champ_aggregate", 
							ChampionAggregateBean.queryParams, ChampionAggregateBean.removeParams, false);
					for (ItemBean item : items) {
						connection.incrementJson(BeanParser.parseAnyBean(item), region + "_collection_item",
								ItemBean.queryParams, ItemBean.removeParams, false);
					}
					for (MasteryAggregateBean mastery : masteries) {
						connection.incrementJson(BeanParser.parseAnyBean(mastery), region + "_collection_mastery",
								MasteryAggregateBean.queryParams, MasteryAggregateBean.removeParams, false);
					}
					for (RuneAggregateBean rune : runes) {
						connection.incrementJson(BeanParser.parseAnyBean(rune), region + "_collection_rune",
								RuneAggregateBean.queryParams, RuneAggregateBean.removeParams, false);
					}
					for (ItemAggregateBean item : itemAggregates){
						connection.incrementJson(BeanParser.parseAnyBean(item), region + "_collection_item_aggregate",
								ItemAggregateBean.queryParams, ItemAggregateBean.removeParams, false);
					}
				} else if (KILL_UPON_DUPLICATE) // we are done here -
												// IMPLEMENTATION-WISE
					i = matches.length();
			}
			TeamBean[] teams = matchPackage.getTeams();
			for (TeamBean team : teams) {
				connection.insertJson(BeanParser.parseAnyBean(team), region + "_collection_team", TeamBean.queryParams);
			}
		}
		long end = System.nanoTime();
		System.out.println("successful! Time: " + (end - start) / 1000000000);
    }
}