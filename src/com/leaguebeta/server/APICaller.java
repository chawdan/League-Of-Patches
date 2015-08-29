package com.leaguebeta.server;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.json.JSONObject;

import com.leaguebeta.server.sync.RateLimiter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class APICaller {
	private static String API_KEY = "d59dcb6a-60f7-48a4-98f5-13b301357701";
	Client client;
	RateLimiter sync;
	BlockingQueue queue   = new PriorityBlockingQueue();
	/**
	 * Creates a Singleton ratelimiter as well as a suggested singleton of Client
	 */
	public APICaller(){
		client = Client.create();
		sync = RateLimiter.getInstance();
	}
	public String call(String url){
		try {
			WebResource webResource = client
			   .resource(url);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   /*throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus() + " header : " + response.getHeaders());*/
				System.out.println("Failed : HTTP error code : "
				+ response.getStatus() + " header : " + response.getHeaders());
				if(response.getStatus() == 404)
					return "404";
				if(response.getStatus() == 429)
					return "429";
				if(response.getStatus() == 503)
					return "503";
			}

			String output = response.getEntity(String.class);

			return output;

		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return null;
	}
	public JSONObject callRiotMatch(String region, String matchID, boolean includeTimeline){
		String url = "https://"+region+".api.pvp.net/api/lol/"+
				region+"/v2.2/match/"+matchID+"?includeTimeline="+includeTimeline+"&api_key=" + API_KEY;
		ResponseObject res = sync.tryRequest(this, url);
		if(!res.isRetrieved())
			return null;
		try{
			return new JSONObject(res.getJson());
		}
		catch(Exception e){
			System.out.println(res.getJson());
			e.printStackTrace();
		}
		return null;
	}
	public JSONObject callRiotMatchList(int playerId, String region, String rankQueues, String seasonArray, String champArray){		
		if(rankQueues.length() != 0)
			rankQueues += "&";
		if(champArray.length() != 0)
			champArray += "&";
		if(seasonArray.length() != 0)
			seasonArray += "&";
		String url = "https://"+region+".api.pvp.net/api/lol/" + region + "/v2.2/matchlist/by-summoner/" + playerId + "?" + champArray + "" + rankQueues + "" + seasonArray + "api_key=" + API_KEY;
		System.out.println(url);
		ResponseObject res = sync.tryRequest(this, url);
		if(!res.isRetrieved())
			return null;
		try{
			return new JSONObject(res.getJson());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public JSONObject callRiotLeague(String region, String summonerID){
		String url = "https://" + region + ".api.pvp.net/api/lol/" + 
				region + "/v2.5/league/by-summoner/" + summonerID + "/entry?api_key=" + API_KEY;
		ResponseObject res = null;
		res = sync.tryRequest(this, url);

		if(!res.isRetrieved())
			return null;
		try{
			return new JSONObject(res.getJson());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public JSONObject callRiotSummoner(String region, String name){
		String url = "https://" + region + ".api.pvp.net/api/lol/" + 
				region + "/v1.4/summoner/by-name/" + name + "?api_key=" + API_KEY;
		ResponseObject res = sync.tryRequest(this, url);
		if(!res.isRetrieved())
			return null;
		try{
			return new JSONObject(res.getJson());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public JSONObject callRiotMatchHistory(String region, String name){
		String url = "https://" + region + ".api.pvp.net/api/lol/" + 
				region + "/v2.2/matchhistory/" + name + "?api_key=" + API_KEY;
		ResponseObject res = sync.tryRequest(this, url);
		if(!res.isRetrieved())
			return null;
		try{
			return new JSONObject(res.getJson());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
