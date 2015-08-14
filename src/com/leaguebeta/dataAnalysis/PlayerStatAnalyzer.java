package com.leaguebeta.dataAnalysis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.leaguebeta.db.model.PlayerGameBean;

public class PlayerStatAnalyzer {
	//This class is the bulk of data analysis for player stats congregated by multiple games.
	//there should only be one PlayerStatAnalyzer for each player at the time.
	//Sort using championId's because we want to see their specific performances.
	
	public final static int SORT_NONE = 0;
	public final static int SORT_CHAMPION = 1;
	public final static int SORT_ROLE = 2;
	public final static int SORT_TEAM_COLOR = 3;
	List<PlayerGameBean> sortList; //we should never modify this
	Map<Integer, List<PlayerGameBean>> sortMap;
	int sortKey;
	public PlayerStatAnalyzer(ArrayList<PlayerGameBean> list){	//for when there's a list but no specific sorting
		sortList = list;
		sortMap = new HashMap<Integer, List<PlayerGameBean>>();
		update();
	}
	public PlayerStatAnalyzer(ArrayList<PlayerGameBean> list, int key){//for when there's a list and specific sorting
		sortList = list;
		sortKey = key;
		sortMap = new HashMap<Integer, List<PlayerGameBean>>();
		update();
	}
	public void changeSortKey(int key){
		sortKey = key;
		update();
	}
	public void update(){
		switch(sortKey){
		case SORT_NONE:
			sortMap.clear();
			sortMap.put(0, sortList);
			break;
		case SORT_CHAMPION:
			sortMap.clear();
			sortMap =  sortList.stream()
                    .collect(Collectors.groupingBy(PlayerGameBean::getChampionId));
			break;
		case SORT_ROLE:
			sortMap.clear();
			sortMap =  sortList.stream()
                    .collect(Collectors.groupingBy(PlayerGameBean::getPlayerRole));
			break;
		case SORT_TEAM_COLOR:
			sortMap.clear();
			sortMap =  sortList.stream()
	                    .collect(Collectors.groupingBy(PlayerGameBean::getTeamID));
			break;
		default://treat it as default SORT_NONE
			sortMap.clear();
			sortMap.put(0, sortList);
			break;
		}
		System.out.println("Updated PlayerStatAnalyzer");
	}
	public Map<Integer, Stats> calculateStats(){//using AvgStats object to take in the values analyzed
		Map<Integer, Stats> map = new HashMap<Integer, Stats>();
		for (Map.Entry<Integer, List<PlayerGameBean>> entry : sortMap.entrySet()) {
			int key = entry.getKey();
			List<PlayerGameBean> val = entry.getValue();
			Stats st = new Stats(val);
			System.out.println("KEY : " + key + "  VAL : " + st);
			map.put(key, st);
		}
		return map;
	}
}
