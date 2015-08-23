package com.leaguebeta;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.leaguebeta.db.model.Aggregate.MatchPackageBean;
import com.leaguebeta.db.model.Match.MatchBean;
import com.leaguebeta.db.model.Participant.PlayerMatchBean;
import com.leaguebeta.db.model.Team.TeamBean;
import com.leaguebeta.db.transferBean.BeanDelegator;
import com.leaguebeta.server.APICaller;

public class TestingAPICall {
	public final static int ITERATIONS = 100;
	public static void main(String[] args){
		TestingAPICall call = new TestingAPICall();
		call.run();
	}
	public void run(){
		APICaller caller = new APICaller();
		Gson gson = new Gson();
		for(int i = 0; i < ITERATIONS; i++){
			JSONObject value = caller.callRiotMatch("na", "1920751370", true);
			//System.out.print(i);
			MatchPackageBean matchPackage = BeanDelegator.delegateMatchJson(value);
			MatchBean match = matchPackage.getMatch();
			PlayerMatchBean[] players = matchPackage.getPlayers();
			TeamBean[] teams = matchPackage.getTeams();
			System.out.println("MATCH : " + gson.toJson(match));
			for(PlayerMatchBean player : players)
				System.out.println("PLAYER : " + gson.toJson(player));
			for(TeamBean team : teams)
				System.out.println("TEAM : " + gson.toJson(team));
		}
	}
}
