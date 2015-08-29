package com.leaguebeta.server.async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.leaguebeta.db.connection.ClientConnector;
import com.leaguebeta.db.model.Aggregate.ChampionBean;
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

public class AsyncRequestProcessor implements Runnable {

	private AsyncContext asyncContext;
	private APICaller caller;
	private ClientConnector connection;
	private static final boolean KILL_UPON_DUPLICATE = true;
	public static final int READ_MATCHES_FROM_PLAYER = 0;
	public static final int READ_MATCHES_FROM_LIST = 1;
	private int type;
	public AsyncRequestProcessor() {
	}

	public AsyncRequestProcessor(AsyncContext asyncCtx, APICaller caller, ClientConnector connection, int type) {
		this.asyncContext = asyncCtx;
		this.caller = caller;
		this.connection = connection;
		this.type = type;
	}

	@Override
	public void run() {
		
		System.out.println("Async Supported? "
				+ asyncContext.getRequest().isAsyncSupported());
		if(type == READ_MATCHES_FROM_PLAYER)
			postMatches(asyncContext.getRequest(), asyncContext.getResponse());
		else
			postListedMatches(asyncContext.getRequest(), asyncContext.getResponse());
		//complete the processing
		asyncContext.complete();
	}

	private JSONObject readRequest(ServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = null;
		try {
			json = new JSONObject(jb.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	private String generateQueryStringArray(String prefix, String arrayName, JSONObject root) {
		try {
			JSONArray array = root.getJSONArray(arrayName);
			String[] list = new String[array.length()];
			for (int i = 0; i < list.length; i++)
				list[i] = array.getString(i);
			prefix += String.join(",", list);
		} catch (Exception e) {
			return "";
		}
		return prefix;
	}

	private void postMatches(ServletRequest req, ServletResponse res) {
		System.out.println("One thread in!");
		JSONObject info = readRequest(req);
		int playerId = info.getInt("playerId");
		String region = info.getString("region");
		boolean includeTimeline = info.getBoolean("includeTimeline");
		/* read in values for arrays */
		String rankQueue = generateQueryStringArray("RankedQueues=", "rankedQueues", info);
		String season = generateQueryStringArray("seasons=", "seasons", info);
		String champion = generateQueryStringArray("championIds=", "championIds", info);
		
		/* use array values to send more api calls */
		JSONObject matchList = caller.callRiotMatchList(playerId, region, rankQueue, season, champion);
		if (matchList == null) {
			try {
				res.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		JSONArray matches = matchList.getJSONArray("matches");
		System.out.println("Stops right before API call. Match length : " + matches.length());
		JSONObject recentMatches = caller.callRiotMatchHistory(region, "" + playerId);
		try {
			PrintWriter out = res.getWriter();
			out.write((new Gson()).toJson(recentMatches));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MatchProcessor.processMatchList(matches, caller, region, includeTimeline, connection);
	}
	private void postListedMatches(ServletRequest request, ServletResponse response){
		JSONObject info = readRequest(request);
		JSONArray matchList = info.getJSONArray("list");
		String[] matches = new String[matchList.length()];
		for(int i = 0; i < matches.length; i++){
			matches[i] = matchList.getString(i);
		}
		String region = info.getString("region");
		boolean includeTimeline = info.getBoolean("includeTimeline");
		MatchProcessor.processMatchList(matches, caller, region, includeTimeline, connection);
	}
}