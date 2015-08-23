package com.leaguebeta.db.transferBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.leaguebeta.db.model.Aggregate.RankBean;

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
	/**
	 * Simplifies a jsonobject's leagues into a rank bean.
	 * @param json - entry of arrays
	 * @return
	 */
	public static Map<String, RankBean> simplifyBean(JSONArray json){
		Map<String, RankBean> ranks = new HashMap<String, RankBean>();
		for(int i = 0; i < json.length(); i++){
			JSONObject indivRank = json.getJSONObject(i);
			JSONArray entries = indivRank.getJSONArray("entries");
			//***LAZY IMPLEMENTATION*** find first one
			RankBean rank = new RankBean(rankWeight.get(indivRank.getString("tier")), divisionWeight.get(entries.getJSONObject(0).getString("division")));
			ranks.put(indivRank.getString("queue"), rank);
		}
		System.out.println(ranks);
		return ranks;
	}
	public static RankBean getSpecificRank(JSONArray json, String query){
		for(int i = 0; i < json.length(); i++){
			JSONObject obj = json.getJSONObject(i);
			if(obj.optString("queue").equals(query)){
				return new RankBean(rankWeight.get(obj.getString("tier")), divisionWeight.get(obj.getJSONArray("entries").getJSONObject(0).getString("division")));
			}
		}
		return new RankBean(0,0);
	}
}
