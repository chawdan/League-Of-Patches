package com.leaguebeta.db.transferBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.leaguebeta.db.model.RankBean;

public class RankBeanMapper {
	static Map<String, Integer> rankWeight = new HashMap<String, Integer>();
	static Map<String, Integer> divisionWeight = new HashMap<String, Integer>();
	static{
		rankWeight.put("UNRANKED", 0);//theoretically this should never happen
		rankWeight.put("BRONZE", 1);
		rankWeight.put("SILVER", 2);
		rankWeight.put("GOLD", 3);
		rankWeight.put("PLATINUM", 4);
		rankWeight.put("DIAMOND", 5);
		rankWeight.put("MASTER", 6);
		rankWeight.put("CHALLENGER", 7);
		divisionWeight.put("UNRANKED", 0);//theoretically this should never happen
		divisionWeight.put("V", 1);
		divisionWeight.put("IV", 2);
		divisionWeight.put("III", 3);
		divisionWeight.put("II", 4);
		divisionWeight.put("I", 5);
	}
	public static Map<String, RankBean> SimplifyBean(JSONArray json){
		Map<String, RankBean> ranks = new HashMap<String, RankBean>();
		for(int i = 0; i < json.length(); i++){
			JSONObject indivRank = json.getJSONObject(i);
			JSONArray entries = indivRank.getJSONArray("entries");
			for(int j = 0; j < entries.length(); j++){
				if(ranks.get(indivRank.getString("queue")) == null){
					ranks.put(indivRank.getString("queue"), new RankBean(rankWeight.get(indivRank.getString("tier")), divisionWeight.get(entries.getJSONObject(j).getString("division"))));
				}
				else{
					RankBean temp = new RankBean(rankWeight.get(indivRank.getString("tier")), divisionWeight.get(entries.getJSONObject(j).getString("division")));
					if(temp.compareTo(ranks.get(indivRank.getString("queue"))) > 0){
						ranks.put(indivRank.getString("queue"), temp);
					}
				}
			}
		}
		System.out.println(ranks);
		return ranks;
	}
}
