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
}
