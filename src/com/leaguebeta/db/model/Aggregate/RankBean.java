package com.leaguebeta.db.model.Aggregate;

import org.json.JSONArray;
import org.json.JSONObject;

public class RankBean implements Comparable<RankBean>{
	int rank;
	int division;
	public RankBean(int rank, int division){
		this.rank = rank;
		this.division = division;
	}
	@Override
	public String toString() {
		return "RankBean [rank=" + rank + ", division=" + division + "]";
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	@Override
	public int compareTo(RankBean o) {
		if(getRank() < o.getRank())
			return -1;
		else if(getRank() > o.getRank())
			return 1;
		else{
			if(getDivision() < o.getRank())
				return -1;
			else if(getDivision() > o.getRank())
				return 1;
		}
		return 0;
	}
}
