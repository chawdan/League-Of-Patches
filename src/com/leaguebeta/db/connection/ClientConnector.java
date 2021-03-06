package com.leaguebeta.db.connection;

import static com.mongodb.client.model.Filters.*;

import com.leaguebeta.db.model.Aggregate.ChampionBean;
import com.leaguebeta.db.model.Aggregate.ItemBean;
import com.leaguebeta.db.model.PlayerGameBean;
import com.leaguebeta.db.model.Aggregate.RankBean;
import com.leaguebeta.db.transferBean.BeanParser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.Document;
import org.bson.types.BasicBSONList;
import org.json.JSONObject;

public class ClientConnector {
	DB db;
	//String constants for accessing normal database *singular*
	public final static String NA = "na_collection";
	public final static String EUW = "euw_collection";
	public final static String EUNE = "eune_collection";
	public final static String BR = "br_collection";
	public final static String OCE = "oce_collection";
	public final static String KR = "kr_collection";
	public final static String LAS = "las_collection";
	public final static String LAN = "lan_collection";
	public final static String RU = "ru_collection";
	public final static String TR = "tr_collection";

	//String constants for accessing match database *singular*
	public final static String NA_MATCH = "na_collection_match";
	public final static String EUW_MATCH = "euw_collection_match";
	public final static String EUNE_MATCH = "eune_collection_match";
	public final static String BR_MATCH = "br_collection_match";
	public final static String OCE_MATCH = "oce_collection_match";
	public final static String KR_MATCH = "kr_collection_match";
	public final static String LAS_MATCH = "las_collection_match";
	public final static String LAN_MATCH = "lan_collection_match";
	public final static String RU_MATCH = "ru_collection_match";
	public final static String TR_MATCH = "tr_collection_match";
	
	//String constants for accessing player match database *singular*
	public final static String NA_PLAYER = "na_collection_player";
	public final static String EUW_PLAYER = "euw_collection_player";
	public final static String EUNE_PLAYER = "eune_collection_player";
	public final static String BR_PLAYER = "br_collection_player";
	public final static String OCE_PLAYER = "oce_collection_player";
	public final static String KR_PLAYER = "kr_collection_player";
	public final static String LAS_PLAYER = "las_collection_player";
	public final static String LAN_PLAYER = "lan_collection_player";
	public final static String RU_PLAYER = "ru_collection_player";
	public final static String TR_PLAYER = "tr_collection_player";
	
	//String constants for accessing champion database *singular*
	public final static String NA_CHAMP = "na_collection_champ";
	public final static String EUW_CHAMP = "euw_collection_champ";
	public final static String EUNE_CHAMP = "eune_collection_champ";
	public final static String BR_CHAMP = "br_collection_champ";
	public final static String OCE_CHAMP = "oce_collection_champ";
	public final static String KR_CHAMP = "kr_collection_champ";
	public final static String LAS_CHAMP = "las_collection_champ";
	public final static String LAN_CHAMP = "lan_collection_champ";
	public final static String RU_CHAMP = "ru_collection_champ";
	public final static String TR_CHAMP = "tr_collection_champ";
	
	public final static String NA_CHAMP_AGGREGATE = "na_collection_champ_aggregate";
	public final static String EUW_CHAMP_AGGREGATE = "euw_collection_champ_aggregate";
	public final static String EUNE_CHAMP_AGGREGATE = "eune_collection_champ_aggregate";
	public final static String BR_CHAMP_AGGREGATE = "br_collection_champ_aggregate";
	public final static String OCE_CHAMP_AGGREGATE = "oce_collection_champ_aggregate";
	public final static String KR_CHAMP_AGGREGATE = "kr_collection_champ_aggregate";
	public final static String LAS_CHAMP_AGGREGATE = "las_collection_champ_aggregate";
	public final static String LAN_CHAMP_AGGREGATE = "lan_collection_champ_aggregate";
	public final static String RU_CHAMP_AGGREGATE = "ru_collection_champ_aggregate";
	public final static String TR_CHAMP_AGGREGATE = "tr_collection_champ_aggregate";
	
	//String constants for accessing team database *singular*
	public final static String NA_TEAM = "na_collection_team";
	public final static String EUW_TEAM = "euw_collection_team";
	public final static String EUNE_TEAM = "eune_collection_team";
	public final static String BR_TEAM = "br_collection_team";
	public final static String OCE_TEAM = "oce_collection_team";
	public final static String KR_TEAM = "kr_collection_team";
	public final static String LAS_TEAM = "las_collection_team";
	public final static String LAN_TEAM = "lan_collection_team";
	public final static String RU_TEAM = "ru_collection_team";
	public final static String TR_TEAM = "tr_collection_team";
	
	//String constants for accessing spells *aggregate*
	public final static String NA_SPELL = "na_collection_spell";
	public final static String EUW_SPELL = "euw_collection_spell";
	public final static String EUNE_SPELL = "eune_collection_spell";
	public final static String BR_SPELL = "br_collection_spell";
	public final static String OCE_SPELL = "oce_collection_spell";
	public final static String KR_SPELL = "kr_collection_spell";
	public final static String LAS_SPELL = "las_collection_spell";
	public final static String LAN_SPELL = "lan_collection_spell";
	public final static String RU_SPELL = "ru_collection_spell";
	public final static String TR_SPELL = "tr_collection_spell";
	
	//String constants for accessing item database *aggregate*
	public final static String NA_ITEM = "na_collection_item";
	public final static String EUW_ITEM = "euw_collection_item";
	public final static String EUNE_ITEM = "eune_collection_item";
	public final static String BR_ITEM = "br_collection_item";
	public final static String OCE_ITEM = "oce_collection_item";
	public final static String KR_ITEM = "kr_collection_item";
	public final static String LAS_ITEM = "las_collection_item";
	public final static String LAN_ITEM = "lan_collection_item";
	public final static String RU_ITEM = "ru_collection_item";
	public final static String TR_ITEM = "tr_collection_item";
	
	public final static String NA_ITEM_AGGREGATE = "na_collection_item_aggregate";
	public final static String EUW_ITEM_AGGREGATE = "euw_collection_item_aggregate";
	public final static String EUNE_ITEM_AGGREGATE = "eune_collection_item_aggregate";
	public final static String BR_ITEM_AGGREGATE = "br_collection_item_aggregate";
	public final static String OCE_ITEM_AGGREGATE = "oce_collection_item_aggregate";
	public final static String KR_ITEM_AGGREGATE = "kr_collection_item_aggregate";
	public final static String LAS_ITEM_AGGREGATE = "las_collection_item_aggregate";
	public final static String LAN_ITEM_AGGREGATE = "lan_collection_item_aggregate";
	public final static String RU_ITEM_AGGREGATE = "ru_collection_item_aggregate";
	public final static String TR_ITEM_AGGREGATE = "tr_collection_item_aggregate";
	
	//String constants for accessing banned champions database *aggregate*
	public final static String NA_BAN = "na_collection_ban";
	public final static String EUW_BAN = "euw_collection_ban";
	public final static String EUNE_BAN = "eune_collection_ban";
	public final static String BR_BAN = "br_collection_ban";
	public final static String OCE_BAN = "oce_collection_ban";
	public final static String KR_BAN = "kr_collection_ban";
	public final static String LAS_BAN = "las_collection_ban";
	public final static String LAN_BAN = "lan_collection_ban";
	public final static String RU_BAN = "ru_collection_ban";
	public final static String TR_BAN = "tr_collection_ban";

	MongoClient mongoClient;
	DBCollection collection;
	String region;
	String DB = "leaguedb";
	String col = "garbage_collection";
	/**
	 * Constructor for ClientConnector, should only have one per user at a time
	 * @param region String flag to assign to collection name
	 */
	public ClientConnector(String region){
		switch(region){
		case "na":
			col = NA;
			break;
		case "euw":
			col = EUW;
			break;
		case "eune":
			col = EUNE;
		case "br":
			col = BR;
		case "oce":
			col = OCE;
		case "kr":
			col = KR;
		}
		mongoClient = new MongoClient( "localhost" , 27017 );
		db = mongoClient.getDB( DB );//deprecated but the new Mongo 3.0.0 is a piece of shit
		collection = db.getCollection( col );
		
		System.out.println("Connection pool created.");
	}
	public ClientConnector(String path, int port, String region){
		switch(region){
		case "na":
			col = NA;
			break;
		case "euw":
			col = EUW;
			break;
		case "eune":
			col = EUNE;
		case "br":
			col = BR;
		case "oce":
			col = OCE;
		case "kr":
			col = KR;
		}
		mongoClient = new MongoClient( path , port );
		db = mongoClient.getDB( DB );
		collection = db.getCollection( col );
		System.out.println("Connection pool created.");
	}
	public void changeRegion(String region){
		switch(region){
		case "na":
			col = NA;
			break;
		case "euw":
			col = EUW;
			break;
		case "eune":
			col = EUNE;
		case "br":
			col = BR;
		case "oce":
			col = OCE;
		case "kr":
			col = KR;
		}
		collection = db.getCollection( col );
	}
	public String getRegion(){
		return col;
	}
	/**
	 * Where bulk of inserting Json objects occur. This function inserts PlayerGameObjects by itself
	 * and manages insertChampion() and insertItems()
	 * @param json individual parsed PlayerGameObject
	 * @param rankMap map of game mode String -> ranking in that game mode RankBean
	 * @return
	 */
	public boolean insertJson(BasicDBObject json, String colName, ArrayList<String> queryParams){
		/*make sure the game id AND the main player id can't both be the same. 
		If either/or, it's fine. We don't want duplicates.
		*/
		BasicDBObject query = new BasicDBObject();
		DBCollection collection = db.getCollection(colName);
		for(int i = 0; i < queryParams.size(); i++){
			String param = queryParams.get(i);
			query.put(param, json.get(param));
		}
		return !collection.update(query, new BasicDBObject("$setOnInsert",json) , true, false).isUpdateOfExisting();
	}
	public boolean incrementJson(BasicDBObject json, String colName, ArrayList<String> queryParams, ArrayList<String> removeParams, boolean multi){
		/*make sure the game id AND the main player id can't both be the same. 
		If either/or, it's fine. We don't want duplicates.
		*/
		BasicDBObject query = new BasicDBObject();
		DBCollection collection = db.getCollection(colName);
		for(int i = 0; i < queryParams.size(); i++){
			String param = queryParams.get(i);
			query.put(param, json.get(param));
		}
		for(String param : removeParams){
			json.remove(param);
		}
		return collection.update(query, new BasicDBObject("$inc", json), true, multi).isUpdateOfExisting();
	}
	
	/**
	 * Queries for any collection with any param with any id
	 * @param Id int id object to find the games involved with the player
	 * @return list of PlayerGameBeans that have playerId in it
	 */
	public ArrayList<BasicDBObject> queryJson(HashMap<String, Object> param, String colName, boolean createIndex){
		BasicDBObject query = new BasicDBObject();
		for (Map.Entry<String, Object> entry : param.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    query.put(entry.getKey(), entry.getValue());
		}
		DBCollection collection = db.getCollection(colName);
		DBCursor cursor = collection.find(query);
		ArrayList<BasicDBObject> dbList = new ArrayList<BasicDBObject>();
		while(cursor.hasNext()){
			dbList.add((BasicDBObject)cursor.next());
		}
		if(createIndex)
			createIndexIfNotExist(colName, param);
		return dbList;
	}
	/*Only works for single layered queries*/
	public ArrayList<BasicDBObject> queryJson(BasicDBObject query, String colName, boolean createIndex){
		DBCollection collection = db.getCollection(colName);
		DBCursor cursor = collection.find(query);
		ArrayList<BasicDBObject> dbList = new ArrayList<BasicDBObject>();
		while(cursor.hasNext()){
			BasicDBObject queryResult = (BasicDBObject)cursor.next();
			dbList.add(queryResult);
			System.out.println("queryResult : " +queryResult);
		}
		if(createIndex)
			createIndexIfNotExist(colName, query);
		return dbList;
	}
	
	public long getSpecifiedCollectionSize(BasicDBObject query, String colName, boolean createIndex){
		DBCollection collection = db.getCollection(colName);
		long count = collection.count(query);
		if(createIndex)
			createIndexIfNotExist(colName, query);
		return count;
	}
	/**
	 * Inserts item info into the database, formatted to ItemBeans(extracts up to 6 at a time due to 6 max items)
	 * @param bean, the entire PlayerGameBean json, as you can see there's for loops extracting each bean out
	 * @param rank, the rank of the player's game
	 * @return boolean, true if there were no errors in the insertion/update
	 */
	
	/**
	 * queries a champion by the region rank limits(comes with filter options in bounds) and date
	 * @param championId int Id 
	 * @param region region value that is PREDEFINED BY THE HTML CODE to have e.x. "na_collection_champ"
	 * @param rankLimits RankBean depicting the rank threshold to cut $gt and $lt at
	 * @param bounds an int flag. -2 = do not filter, -1 = less than/eq, 0 = eq, 1 = greater than/eq
	 * @param weekDate int for num of weeks
	 * @param yearDate int for year date
	 * @return ArrayList<ChampionBean> collection of champion objects
	 */
	public ArrayList<ChampionBean> queryChampion(int championId, String region, RankBean rankLimits, int bounds, int weekStart, int weekEnd, int yearStart, int yearEnd){
		BasicDBObject query = new BasicDBObject();
		BeanParser<ChampionBean> parser = new BeanParser<ChampionBean>(ChampionBean.class);
		DBCollection tempCol = db.getCollection(region);

		query.put("championId", championId);
		query.putAll((BSONObject)generateDateRangeQuery(weekStart, weekEnd, yearStart, yearEnd));
		ArrayList<ChampionBean> champList = new ArrayList<ChampionBean>();
		query.putAll((BSONObject)generateRankQuery(rankLimits, bounds));
		
		DBCursor cursor = tempCol.find(query);
		while(cursor.hasNext()){
			champList.add(parser.parseJSON((BasicDBObject)cursor.next()));
		}
		return champList;//there can be multiple
	}
	/**
	 * queries an item by the region rank limits(comes with filter options in bounds) and date
	 * @param itemId int id for item to query
	 * @param region region value that is PREDEFINED BY THE HTML CODE to have e.x. "na_collection_champ"
	 * @param rankLimits RankBean depicting the rank threshold to cut $gt and $lt at
	 * @param bounds an int flag. -2 = do not filter, -1 = less than/eq, 0 = eq, 1 = greater than/eq
	 * @param weekDate int for num of weeks
	 * @param yearDate int for year date
	 * @return ArrayList<ItemBean> collection of itembeans
	 */
	public ArrayList<ItemBean> queryItem(int itemId, String region, RankBean rankLimits, int bounds, int weekStart, int weekEnd, int yearStart, int yearEnd){
		BasicDBObject query = new BasicDBObject();
		BeanParser<ItemBean> parser = new BeanParser<ItemBean>(ItemBean.class);
		DBCollection tempCol = db.getCollection(region);
		
		query.put("itemId", itemId);
		query.putAll((BSONObject)generateDateRangeQuery(weekStart, weekEnd, yearStart, yearEnd));
		ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
		query.putAll((BSONObject)generateRankQuery(rankLimits, bounds));
		
		DBCursor cursor = tempCol.find(query);
		while(cursor.hasNext()){
			itemList.add(parser.parseJSON((BasicDBObject)cursor.next()));
		}
		return itemList;
	}

	/**
	 * Helps in query functions to help construct querying with rankings. 
	 * Every function that calls generateQuery() will also call createIndexIfNotExist()
	 * @param query, preconstructed BasicDBObject query
	 * @param rankLimits, RankBean depicting the rank threshold to cut $gt and $lt at
	 * @param bounds, an int flag. -2 = do not filter, -1 = less than/eq, 0 = eq, 1 = greater than/eq
	 * @param tempCol, DBCollection used to query
	 * @return array of DBCursors(only 2), one for ranks greater than, and one for same rank higher division for e.x.
	 */
	public static BasicDBObject generateRankQuery(RankBean rank, int bounds){
		if(bounds == -2)
			return new BasicDBObject();

		if(bounds < 0){
			BasicDBList or = new BasicDBList();
			
			if(rank.getRank() != -1){
				BasicDBObject clause1 = new BasicDBObject();
				BasicDBObject clause1lt = new BasicDBObject("$lt", rank.getRank());
				clause1.put("rank", clause1lt);//$or: [ { rank: { $gt: 3 } }, { division: { $gt: 2 } , rank : { 3 } ]
				or.add(clause1);
			}
			if(rank.getDivision() != -1){
				BasicDBObject clause2 = new BasicDBObject("rank", rank.getRank());
				BasicDBObject clause2lt = new BasicDBObject("$lte", rank.getDivision());
				clause2.put("division",clause2lt);
				or.add(clause2);
			}
			if(or.size() != 0)
				return new BasicDBObject("$or", or);
			else 
				return new BasicDBObject();
		}
		else if(bounds > 0){
			BasicDBList or = new BasicDBList();
			
			if(rank.getRank() != -1){
				BasicDBObject clause1 = new BasicDBObject();
				BasicDBObject clause1lt = new BasicDBObject("$gt", rank.getRank());
				clause1.put("rank", clause1lt);//$or: [ { rank: { $gt: 3 } }, { division: { $gt: 2 } , rank : { 3 } ]
				or.add(clause1);
			}
			if(rank.getDivision() != -1){
				BasicDBObject clause2 = new BasicDBObject("rank", rank.getRank());
				BasicDBObject clause2lt = new BasicDBObject("$gte", rank.getDivision());
				clause2.put("division",clause2lt);
				or.add(clause2);
			}
			if(or.size() != 0)
				return new BasicDBObject("$or", or);
			else 
				return new BasicDBObject();
		}
		else{
			BasicDBObject query = new BasicDBObject("rank", rank.getRank());
			query.append("division", rank.getDivision());
			return query;
		}
	}
	public static BasicDBObject generateDateRangeQuery(int weekStart, int weekEnd, int yearStart, int yearEnd){
		BasicDBObject week = new BasicDBObject();
		BasicDBObject year = new BasicDBObject();
		if(weekStart != -1)
			week.append("$gte", weekStart);
		if(yearStart != -1)
			year.append("$gte", yearStart);
		
		if(weekEnd != -1)
			week.append("$lte", weekEnd);
		if(yearStart != -1)
			year.append("$lte", yearEnd);
		BasicDBObject date = new BasicDBObject();
		date.append("weekDate", week);
		date.append("yearDate", year);
		System.out.println(date);
		return date;
	}
	public static BasicDBObject generateDurationRangeQuery(int timeStart, int timeEnd){
		BasicDBObject time = new BasicDBObject();
		if(timeStart != -1)
			time.append("$gte", timeStart);
		if(timeEnd != -1)
			time.append("$lte", timeEnd);

		return new BasicDBObject("matchDuration", time);
	}
	/**
	 * Creates an index for easy accessing database indices, so far takes care of _collection, _champ, _item
	 * @param collection to add index to
	 * @return boolean to display whether an index was added. True if added, false if not.
	 */
	public boolean createIndexIfNotExist(String colName, Map<String, Object> param){
		DBCollection collection = db.getCollection(colName);
		List<DBObject> indexes = collection.getIndexInfo();
		Set<String> keySet = param.keySet();
		//iterating over keys only
		for(DBObject index : indexes){
			if(keySet.equals(index.keySet()))
				return false; //it already exists
		}
		BasicDBObject newIndex = new BasicDBObject();
		for(String key : keySet){
			newIndex.put(key, -1);//sort everything descending?
		}
		collection.createIndex(newIndex, new BasicDBObject("background", true));//we always want background,
																				//index takes long time to make
		return true;
	}
	public void closeConnection(){
		mongoClient.close();
	}
}
