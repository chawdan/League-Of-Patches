package com.leaguebeta.server;

import com.google.gson.Gson;
import com.leaguebeta.dataAnalysis.PlayerStatAnalyzer;
import com.leaguebeta.dataAnalysis.Stats;
import com.leaguebeta.db.connection.ClientConnector;
import com.leaguebeta.db.model.Aggregate.ChampionBean;
import com.leaguebeta.db.model.Aggregate.ItemBean;
import com.leaguebeta.db.model.Aggregate.ItemInfo;
import com.leaguebeta.db.model.Aggregate.MasteryAggregateBean;
import com.leaguebeta.db.model.Aggregate.MatchPackageBean;
import com.leaguebeta.db.model.PlayerGameBean;
import com.leaguebeta.db.model.Aggregate.RankBean;
import com.leaguebeta.db.model.Aggregate.RuneAggregateBean;
import com.leaguebeta.db.model.Aggregate.TimeStats;
import com.leaguebeta.db.model.Match.MatchBean;
import com.leaguebeta.db.model.Match.Timeline.EventBean;
import com.leaguebeta.db.model.Match.Timeline.ParticipantFrameBean;
import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Team.TeamBean;
import com.leaguebeta.db.transferBean.BeanDelegator;
import com.leaguebeta.db.transferBean.BeanParser;
import com.leaguebeta.db.transferBean.RankBeanMapper;
import com.leaguebeta.server.async.AppAsyncListener;
import com.leaguebeta.server.async.AsyncRequestProcessor;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.util.JSON;

import net.minidev.json.writer.BeansMapper.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.BSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class BetaServlet
 */
@WebServlet(name = "League", urlPatterns = { "/League/*" }, asyncSupported = true)
public class BetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClientConnector connection;
	APICaller caller;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BetaServlet() {
		super();
		initializeConnectionPool();
		caller = new APICaller();
	}

	private void initializeConnectionPool() {
		connection = new ClientConnector("na");// default is North America
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		String reqURL = request.getPathInfo();
		ArrayList<BasicDBObject> answer = null;
		switch (reqURL) {
		// Service caching calls
		case "/showAggregateChampion":
			BasicDBObject queryChamp = buildDBObjectSkeleton(request);
			queryChamp.append("championId", Integer.parseInt(request.getParameter("championId")));
			System.out.println(queryChamp);
			answer = connection.queryJson(queryChamp, request.getParameter("region")+"_collection_champ", false); //queryChamp, request.getParameter("region")+"_collection_champ");
			for(BasicDBObject ans : answer){
				System.out.println(ans);
			}
			
			sendJsonToServer(response, answer.toArray(new BasicDBObject[answer.size()]));
			break;
		case "/showAggregateItem":
			BasicDBObject queryItem = buildDBObjectSkeleton(request);
			queryItem.append("itemId", Integer.parseInt(request.getParameter("itemId")));
			queryItem.append("championId", Integer.parseInt(request.getParameter("championId")));
			System.out.println(queryItem);
			answer = connection.queryJson(queryItem, request.getParameter("region")+"_collection_item", false);
			for(BasicDBObject ans : answer){
				System.out.println(ans);
			}
			sendJsonToServer(response, answer.toArray(new BasicDBObject[answer.size()]));
			break;
		case "/showAggregateRune":
			BasicDBObject queryRune = buildDBObjectSkeleton(request);
			queryRune.append("runeId", request.getParameter("runeId"));
			queryRune.append("championId", request.getParameter("championId"));
			System.out.println(queryRune);
			connection.queryJson(queryRune, request.getParameter("region")+"_collection_rune", false);
			sendJsonToServer(response, queryRune);
			break;
		case "/showAggregateMastery":
			BasicDBObject queryMastery = buildDBObjectSkeleton(request);
			queryMastery.append("masteryId", request.getParameter("masteryId"));
			queryMastery.append("championId", request.getParameter("championId"));
			System.out.println(queryMastery);
			connection.queryJson(queryMastery, request.getParameter("region")+"_collection_mastery", false);
			sendJsonToServer(response, queryMastery);
			break;

		case "/showWeeklyAvg":
			// manageWeeklyAvg(request, response);
			break;
		// Rate-Limited API calls
		case "/callRiotMatch":
			JSONObject matchJson = caller.callRiotMatch(request.getParameter("region"), request.getParameter("matchID"),
					Boolean.getBoolean(request.getParameter("includeTimeLine")));
			// do other things here
			System.out.println(matchJson);
			break;
		case "/callRiotLeague":
			JSONObject leagueJson = caller.callRiotLeague(request.getParameter("region"),
					request.getParameter("summonerId"));
			break;
		case "/callRiotSummoner":
			JSONObject summonerJson = caller.callRiotSummoner(request.getParameter("region"),
					request.getParameter("name"));
			break;
		default:
			response.sendRedirect("/");
			response.getWriter().append("Served at: ").append(request.getContextPath());
			break;
		}
	}

	private static BasicDBObject buildDBObjectSkeleton(HttpServletRequest request){
		BasicDBObject query = new BasicDBObject();
		Map<String, String[]> params = request.getParameterMap();

		query.putAll((BSONObject)ClientConnector.generateDateRangeQuery(Integer.parseInt(params.get("weekDate")[0]), 
				Integer.parseInt(params.get("weekDate")[1]),
				Integer.parseInt(params.get("yearDate")[0]),
				Integer.parseInt(params.get("yearDate")[1])));
		query.putAll((BSONObject)ClientConnector.generateDurationRangeQuery(Integer.parseInt(params.get("matchDuration")[0]), 
				Integer.parseInt(params.get("matchDuration")[1])));
		BasicDBObject lowerRank = ClientConnector.generateRankQuery(new RankBean(
				Integer.parseInt(params.get("rank")[0]), 
				Integer.parseInt(params.get("division")[0])), 1);
		BasicDBObject higherRank = ClientConnector.generateRankQuery(new RankBean(
				Integer.parseInt(params.get("rank")[1]), 
				Integer.parseInt(params.get("division")[1])), -1);
		BasicDBList and = new BasicDBList();
		and.add(lowerRank);
		and.add(higherRank);
		query.append("$and", and);
		query.putAll((BSONObject)ClientConnector.generateDurationRangeQuery(
				Integer.parseInt(params.get("matchDuration")[0]), 
				Integer.parseInt(params.get("matchDuration")[1])));
		System.out.println(query);
		return query;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		String reqURL = request.getPathInfo();
		if (reqURL != null) {// this means there is an API call using AJAX
			System.out.println(reqURL);
			AsyncContext asyncCtx = null;
			ThreadPoolExecutor executor = null;
			switch (reqURL) {
			case "/postMatches":
				asyncCtx = request.startAsync();
		        asyncCtx.addListener(new AppAsyncListener());
		        executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
		        executor.execute(new AsyncRequestProcessor(asyncCtx, caller, connection, AsyncRequestProcessor.READ_MATCHES_FROM_PLAYER));
				break;
			case "/postInitialMatches":
				asyncCtx = request.startAsync();
				asyncCtx.addListener(new AppAsyncListener());
		        executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
		        executor.execute(new AsyncRequestProcessor(asyncCtx, caller, connection, AsyncRequestProcessor.READ_MATCHES_FROM_LIST));
				break;
			default:// someone tried to access API that does not exist
				response.sendRedirect("/");
				break;
			}
		}
	}


	

	public void queryForItem(JSONObject info, HttpServletResponse response) {
		ArrayList<BasicDBObject> dbObjs = queryForAggregate(info, "_collection_item");
		BasicDBObject[] arrayOfObjs = dbObjs.toArray(new BasicDBObject[dbObjs.size()]);
		sendJsonToServer(response, arrayOfObjs);
	}

	public void queryForChamp(JSONObject info, HttpServletResponse response) {
		ArrayList<BasicDBObject> dbObjs = queryForAggregate(info, "_collection_champ");
		BasicDBObject[] arrayOfObjs = dbObjs.toArray(new BasicDBObject[dbObjs.size()]);
		sendJsonToServer(response, arrayOfObjs);
	}

	public void queryForMasteries(JSONObject info, HttpServletResponse response) {
		ArrayList<BasicDBObject> dbObjs = queryForAggregate(info, "_collection_mastery");
		BasicDBObject[] arrayOfObjs = dbObjs.toArray(new BasicDBObject[dbObjs.size()]);
		sendJsonToServer(response, arrayOfObjs);
	}

	public void queryForRunes(JSONObject info, HttpServletResponse response) {
		ArrayList<BasicDBObject> dbObjs = queryForAggregate(info, "_collection_runes");
		BasicDBObject[] arrayOfObjs = dbObjs.toArray(new BasicDBObject[dbObjs.size()]);
		sendJsonToServer(response, arrayOfObjs);
	}

	public ArrayList<BasicDBObject> queryForAggregate(JSONObject info, String postfix) {
		ArrayList<String> queryParams = ItemBean.getQueryParams();
		BasicDBObject query = new BasicDBObject();
		String region = info.getString("region");
		for (String queryParam : queryParams) {
			if (queryParam != null)
				query.put(queryParam, info.get(queryParam));
		}
		return connection.queryJson(query, region + postfix, false);
	}

	public void sendJsonToServer(HttpServletResponse response, BasicDBObject[] arrayOfObjs) {
		try {
			PrintWriter out = response.getWriter();
			out.write((new Gson()).toJson(arrayOfObjs));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendJsonToServer(HttpServletResponse response, BasicDBObject arrayOfObjs) {
		try {
			PrintWriter out = response.getWriter();
			out.write((new Gson()).toJson(arrayOfObjs));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void destroy() {
		connection.closeConnection();
	}
}
