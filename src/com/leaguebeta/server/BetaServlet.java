package com.leaguebeta.server;
import com.google.gson.Gson;
import com.leaguebeta.dataAnalysis.PlayerStatAnalyzer;
import com.leaguebeta.dataAnalysis.Stats;
import com.leaguebeta.db.connection.ClientConnector;
import com.leaguebeta.db.model.ChampionBean;
import com.leaguebeta.db.model.PlayerGameBean;
import com.leaguebeta.db.model.RankBean;
import com.leaguebeta.db.transferBean.BeanParser;
import com.leaguebeta.db.transferBean.RankBeanMapper;
import com.mongodb.BasicDBObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BetaServlet() {
        super();
        initializeConnectionPool();
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
			case "/showWeeklyAvg":
				System.out.println("/showWeeklyAvg Accessed");
				manageWeeklyAvg(request, response);
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
					System.out.println("/postMatches Accessed");
					manageMatchHistory(request, response);//takes care of match history
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
	private void manageMatchHistory(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
	}
}
