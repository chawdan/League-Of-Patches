package com.leaguebeta.db.model.Aggregate;

import java.util.HashMap;

public class SpellBean {
	/*avg win rate and stats*/
	int wins;
	
	int qty;
	int spellId;
	
	/*kda avg*/
	int rank;
	int division;
	int assists;
	int deaths;
	int kills;
	
	int qtyUsed;//difference in that this can be more than qty because you can use same rune in same page
	
	/*lanes and roles*/
	int top;
	int mid;
	int middle;
	int bot;
	int jungle;
	
	int duo;
	int none;
	int solo;
	int duo_carry;
	int duo_support;
	
	/*time flag*/
	int weekDate, yearDate;
	int matchDuration; // 0 for 0-10 minutes, 1 for 10-20 minutes, 2 for 20-30 minutes, 3 for 30-40 minutes, 4 for 40-50 minutes, and so on.
	
}
