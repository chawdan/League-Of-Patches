package com.leaguebeta.db.model.Aggregate;

import java.util.HashMap;

public class SpellBean {
	int spellId;
	int rank;
	int division;
	HashMap<Integer, Integer> assists;
	HashMap<Integer, Integer> deaths;
	HashMap<Integer, Integer> kills;
	HashMap<Integer, Integer> wins;
	HashMap<Integer, Integer> qty;
	int weekDate, yearDate;
}
