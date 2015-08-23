package com.leaguebeta.server;
import com.google.gson.Gson;
import com.leaguebeta.dataAnalysis.PlayerStatAnalyzer;
import com.leaguebeta.dataAnalysis.Stats;
import com.leaguebeta.db.connection.ClientConnector;
import com.leaguebeta.db.model.Aggregate.ChampionBean;
import com.leaguebeta.db.model.Aggregate.ItemBean;
import com.leaguebeta.db.model.Aggregate.ItemInfo;
import com.leaguebeta.db.model.Aggregate.MatchPackageBean;
import com.leaguebeta.db.model.PlayerGameBean;
import com.leaguebeta.db.model.Aggregate.RankBean;
import com.leaguebeta.db.model.Aggregate.TimeStats;
import com.leaguebeta.db.model.Match.MatchBean;
import com.leaguebeta.db.model.Match.Timeline.EventBean;
import com.leaguebeta.db.model.Match.Timeline.ParticipantFrameBean;
import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Team.TeamBean;
import com.leaguebeta.db.transferBean.BeanDelegator;
import com.leaguebeta.db.transferBean.BeanParser;
import com.leaguebeta.db.transferBean.RankBeanMapper;
import com.mongodb.BasicDBObject;

import net.minidev.json.writer.BeansMapper.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Servlet implementation class BetaServlet
 */
@WebServlet(name = "League", urlPatterns = { "/League/*" })
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
		connection = new ClientConnector("na");//default is North America
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqURL = request.getPathInfo();
		switch(reqURL){
			//Service caching calls
			case "/showWeeklyAvg":
				//manageWeeklyAvg(request, response);
				break;
			//Rate-Limited API calls
			case "/callRiotMatch":
				JSONObject matchJson = caller.callRiotMatch(request.getParameter("region"), 
													request.getParameter("matchID"),
													Boolean.getBoolean(request.getParameter("includeTimeLine")));
				//do other things here
				System.out.println(matchJson);
				break;
			case "/callRiotLeague":
				JSONObject leagueJson = caller.callRiotLeague(request.getParameter("region"), Integer.getInteger(request.getParameter("summonerId")));
				break;
			case "/callRiotSummoner":
				JSONObject summonerJson = caller.callRiotSummoner(request.getParameter("region"), 
													request.getParameter("name"));
				break;
			default :
				response.sendRedirect("/");
				response.getWriter().append("Served at: ").append(request.getContextPath());
				break;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqURL = request.getPathInfo();
		if(reqURL != null){//this means there is an API call using AJAX
			System.out.println(reqURL);
			switch(reqURL){
				case "/postMatches":
					//manageMatchHistory(request, response);//takes care of match history from a summoner, and then 
					postMatches(readRequest(request), response);
					break;
				default://someone tried to access API that does not exist
					response.sendRedirect("/");
					break;
			}
		}
	}
	private JSONObject readRequest(HttpServletRequest request){
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
	private String generateQueryStringArray(String prefix, String arrayName, JSONObject root){
		try{
			JSONArray array = root.getJSONArray(arrayName);
			String[] list = new String[array.length()];
			for(int i = 0; i < list.length; i++)
				list[i] = array.getString(i);
			prefix += String.join(",", list);
		}catch(Exception e){
			return "";
		}
		return prefix;
	}
	private void postMatches(JSONObject info, HttpServletResponse res){
		int playerId = info.getInt("playerId");
		String region = info.getString("region");
		boolean includeTimeline = info.getBoolean("includeTimeline");
		long start = System.nanoTime();
		/*read in values for arrays*/
		String rankQueue = generateQueryStringArray("RankedQueues=", "rankedQueues", info);
		String season = generateQueryStringArray("seasons=", "seasons", info);
		String champion = generateQueryStringArray("championIds=", "championIds", info);
		
		/*use array values to send more api calls*/
		JSONObject matchList = caller.callRiotMatchList(playerId, region, rankQueue, season, champion);
		JSONArray matches = matchList.getJSONArray("matches");
		try {
			PrintWriter out = res.getWriter();
			out.write((new Gson()).toJson(matches));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < matches.length(); i++){
			JSONObject value = caller.callRiotMatch(region, ""+matches.getJSONObject(i).getInt("matchId"), includeTimeline);
			System.out.println(value);
			MatchPackageBean matchPackage = BeanDelegator.delegateMatchJson(value);
			/*create beanparser and send these items into the db as they are initialized*/
			MatchBean match = matchPackage.getMatch();
			//connection.insertJson(BeanParser.parseAnyBean(match), region + "_collection_match", MatchBean.queryParams);
			ParticipantFrameBean[] frames = match.getParticipantFrames();
			HashMap<String, Object>[] events = match.getEvents();
			Map<Integer, List<ItemInfo>> itemEvents = new HashMap<>();
			for(int j = 0; j < events.length; j++){
				HashMap<String, Object> event = events[j];
				String type = (String) event.get("eventType");
				if(type.equals("ITEM_PURCHASED") || type.equals("ITEM_DESTROYED")){
					int itemId = (int) event.get("itemId");
					int timeStamp = (int) event.get("timestamp");
					int participantId = (int) event.get("participantId");
					List<ItemInfo> itemList = itemEvents.computeIfAbsent(participantId, l -> new ArrayList<ItemInfo>());
					itemList.add(new ItemInfo(itemId, timeStamp, type));
				}
			}
			
			PlayerMatchBean[] players = matchPackage.getPlayers();
			for(PlayerMatchBean player : players){
				//each player has their own rankings - so call the api for it
				JSONArray leagues = null;
				try{
					leagues = caller.callRiotLeague(region, player.getSummonerId()).getJSONArray(""+player.getSummonerId());
				}catch(NullPointerException e){
					leagues = new JSONArray();
				}
				RankBean rank = RankBeanMapper.getSpecificRank(leagues, match.getQueueType());

				ItemBean[] items = ItemBean.playerMatchBeanToItemBean(player, itemEvents.getOrDefault(player.getParticipantId(), new ArrayList<ItemInfo>()), rank);
				
				//Map<String, RankBean> rankedMap = RankBeanMapper.simplifyBean(leagues); no need for a map right now
				ChampionBean champ = ChampionBean.playerMatchBeanToChampBean(player, rank);
				if(connection.insertJson(BeanParser.parseAnyBean(player), region + "_collection_player", PlayerMatchBean.queryParams)){
					connection.incrementJson(BeanParser.parseAnyBean(champ), region + "_collection_champ", ChampionBean.queryParams, ChampionBean.removeParams, false);
					for(ItemBean item : items){
						connection.incrementJson(BeanParser.parseAnyBean(item), region + "_collection_item", ItemBean.queryParams, ItemBean.removeParams, false);
					}
				}
			}
			TeamBean[] teams = matchPackage.getTeams();
			for(TeamBean team : teams){
				connection.insertJson(BeanParser.parseAnyBean(team), region + "_collection_team", TeamBean.queryParams);
			}
		}
		long end = System.nanoTime();
		System.out.println("successful! Time: " + (end - start)/1000000000);
	}
	/*private void manageMatchHistory(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject json = readRequest(request);
		int playerId = json.getInt("playerId");
		String region = json.getString("region");
		int sortBy = json.getInt("sortType");
		//if(connection.getRegion().split("_")[0]//if regions aren't matching, we have to change database collection
		//read in JSON into the obj, we know its approximate architecture, so take each game out
		JSONArray arr = json.getJSONArray("games");
		BeanParser<PlayerGameBean> parser = new BeanParser<PlayerGameBean>(PlayerGameBean.class);
		JSONArray rank = json.getJSONArray("rank");
		Map<String, RankBean> rankMap = RankBeanMapper.SimplifyBean(rank);
		for(int i = 0; i < arr.length(); i++){
			JSONObject game = arr.getJSONObject(i);
			PlayerGameBean gameBean = new PlayerGameBean(game, playerId, region);
			BasicDBObject dbobj = parser.parseBean(gameBean);
			System.out.println(dbobj);
			if(connection.insertJson(dbobj, rankMap))
				System.out.println("inserted");
			else
				System.out.println("not inserted - duplicate!!");
		}
		PrintWriter out = response.getWriter();
		//create Json Object
		Map<Integer, Stats> map = queryPlayerHistory(playerId, sortBy);
		Gson gson = new Gson();
		// finally output the json string   
		System.out.println(gson.toJson(map));
	    out.print(gson.toJson(map));
	}
	
	public void manageWeeklyAvg(HttpServletRequest req, HttpServletResponse res){
		int championId;
		String region = "";
		int filterOption, rankSelect, divSelect;
		try{
			System.out.println(req.getParameter("championId"));
			championId = Integer.parseInt(req.getParameter("championId"));
			filterOption = Integer.parseInt(req.getParameter("filterOption"));
			rankSelect = Integer.parseInt(req.getParameter("rankSelect"));
			divSelect = Integer.parseInt(req.getParameter("divSelect"));
		}catch(NumberFormatException e){
			return;
		}
		region = req.getParameter("region");
		ArrayList<ChampionBean> bean = connection.queryChampion(championId, region, new RankBean(rankSelect, divSelect), filterOption, Calendar.getInstance().get(Calendar.WEEK_OF_YEAR), Calendar.getInstance().get(Calendar.YEAR));
		Gson gson = new Gson();
		try {
			PrintWriter out = res.getWriter();
			out.print(gson.toJson(bean));
			System.out.println(gson.toJson(bean));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Map<Integer, Stats> queryPlayerHistory(int playerId, int sort){
		ArrayList<PlayerGameBean> charList = connection.queryJson(playerId);
		PlayerStatAnalyzer analyzer = new PlayerStatAnalyzer(charList, sort);
		Map<Integer, Stats> map = analyzer.calculateStats();
		return map;
	}*/
}
