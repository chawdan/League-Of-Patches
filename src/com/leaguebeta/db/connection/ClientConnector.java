package com.leaguebeta.db.connection;

import static com.mongodb.client.model.Filters.*;

import com.leaguebeta.db.model.ChampionBean;
import com.leaguebeta.db.model.ItemBean;
import com.leaguebeta.db.model.PlayerGameBean;
import com.leaguebeta.db.model.RankBean;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.bson.types.BasicBSONList;

public class ClientConnector {
	DB db;
	//String constants for accessing normal database
	public final static String NA = "na_collection";
	public final static String EUW = "euw_collection";
	public final static String EUNE = "eune_collection";
	public final static String BR = "br_collection";
	public final static String OCE = "oce_collection";
	public final static String KR = "kr_collection";
	//String constants for accessing champion database
	public final static String NA_CHAMP = "na_collection_champ";
	public final static String EUW_CHAMP = "euw_collection_champ";
	public final static String EUNE_CHAMP = "eune_collection_champ";
	public final static String BR_CHAMP = "br_collection_champ";
	public final static String OCE_CHAMP = "oce_collection_champ";
	public final static String KR_CHAMP = "kr_collection_champ";
	//String constants for accessing item database
	public final static String NA_ITEM = "na_collection_item";
	public final static String EUW_ITEM = "euw_collection_item";
	public final static String EUNE_ITEM = "eune_collection_item";
	public final static String BR_ITEM = "br_collection_item";
	public final static String OCE_ITEM = "oce_collection_item";
	public final static String KR_ITEM = "kr_collection_item";

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
	public boolean insertJson(BasicDBObject json, Map<String,RankBean> rankMap){
		/*make sure the game id AND the main player id can't both be the same. 
		If either/or, it's fine. We don't want duplicates.
		*/
		BasicDBObject query = new BasicDBObject();
		query.put("gameID", json.get("gameID"));
		query.put("mainSummonerID", json.get("mainSummonerID"));
		if(collection.find(query).hasNext()){//means it exists, break out!
			return false;
		}
		collection.insert(json);
		String subType = json.getString("subType");
		RankBean officialRankBean;
		if(!subType.equals("RANKED_SOLO_5x5") && !subType.equals("RANKED_TEAM_3x3") && !subType.equals("RANKED_TEAM_5x5"))
			officialRankBean = new RankBean(0,0);//this means unranked
		else
			officialRankBean = rankMap.get(subType);
		/*if this gamebean was saved successfuly, that means the components must also be saved*/
		insertChampion(json, officialRankBean);
		insertItems(json, officialRankBean);
		return true;
	}
	
	/**
	 * Queries for the PlayerGameObject with playerId
	 * @param playerId int id object to find the games involved with the player
	 * @return list of PlayerGameBeans that have playerId in it
	 */
	public ArrayList<PlayerGameBean> queryJson(int playerId){
		BasicDBObject query = new BasicDBObject();
		query.put("mainSummonerID", playerId);
		DBCursor cursor = collection.find(query);
		ArrayList<PlayerGameBean> charList = new ArrayList<PlayerGameBean>();
		BeanParser<PlayerGameBean> parser = new BeanParser<PlayerGameBean>(PlayerGameBean.class);
		while(cursor.hasNext()){
			PlayerGameBean bean = parser.parseJSON((BasicDBObject)cursor.next());
			charList.add(bean);
		}
		createIndexIfNotExist(collection);
		return charList;
	}
	
	/**
	 * Inserts item info into the database, formatted to ItemBeans(extracts up to 6 at a time due to 6 max items)
	 * @param bean, the entire PlayerGameBean json, as you can see there's for loops extracting each bean out
	 * @param rank, the rank of the player's game
	 * @return boolean, true if there were no errors in the insertion/update
	 */
	public boolean insertItems(BasicDBObject bean, RankBean rank){
		List<BasicDBObject> query = new ArrayList<BasicDBObject>();
		DBCollection tempCol = db.getCollection(collection.getName()+ "_item");
		BasicDBList list = (BasicDBList) bean.get("itemsID");
		for(int i = 0; i < list.size(); i++){
			BasicDBObject obj = new BasicDBObject();
			obj.put("itemId", list.get(i));
			obj.put("weekDate",bean.get("weekDate"));
			obj.put("yearDate",bean.get("yearDate"));
			obj.put("rank", rank.getRank());
			obj.put("division", rank.getDivision());
			query.add(obj);
		}
		for(int i = 0; i < list.size(); i++){
			BasicDBObject q = query.get(i);
			DBCursor cursor = tempCol.find(q);
			int win = ((boolean) bean.get("win")) ? 1 : 0;
			if(cursor.hasNext()){//if this entry does exist
				BasicDBObject incDoc = 
						new BasicDBObject().append("$inc", 
						new BasicDBObject()
							.append("kills", bean.get("championsKilled"))
							.append("assists", bean.get("assists"))
							.append("deaths", bean.get("numDeaths"))
							.append("qty", 1)
							.append("wins", win));
				tempCol.update(q, incDoc);
				System.out.println("updated item data!");
			}
			else{//if doesn't, just add a new obj
				BasicDBObject dbObj = new BasicDBObject();
				dbObj.append("itemId", list.get(i));
				dbObj.append("kills", bean.get("championsKilled"));
				dbObj.append("assists", bean.get("assists"));
				dbObj.append("deaths", bean.get("numDeaths"));
				dbObj.append("qty", 1);
				dbObj.append("weekDate", bean.get("weekDate"));
				dbObj.append("yearDate", bean.get("yearDate"));
				dbObj.append("wins", win);
				dbObj.append("rank", rank.getRank());
				dbObj.append("division", rank.getDivision());
				tempCol.insert(dbObj);
				System.out.println("inserted item data!");
			}
		}
		return true;
	}   
	/**
	 * Inserts champion info into the database, formatted to ChampionBeans
	 * @param bean entire Json object of PlayerGameBean
	 * @param rank the rank of the player's game
	 * @return boolean true if there were no errors in the insertion/update
	 */
	public boolean insertChampion(BasicDBObject bean, RankBean rank){
		BasicDBObject query = new BasicDBObject();
		DBCollection tempCol = db.getCollection(collection.getName()+"_champ");
		query.put("championId", bean.get("championId"));
		query.put("weekDate", bean.get("weekDate"));
		query.put("yearDate", bean.get("yearDate"));
		query.put("rank", rank.getRank());
		query.put("division", rank.getDivision());
		DBCursor cursor = tempCol.find(query);
		int win = ((boolean) bean.get("win")) ? 1 : 0;
		System.out.println(bean.getBoolean("wins"));
		if(cursor.hasNext()){//if this entry does exist
			BasicDBObject incDoc = 
					new BasicDBObject().append("$inc", 
					new BasicDBObject()
						.append("kills", bean.get("championsKilled"))
						.append("assists", bean.get("assists"))
						.append("deaths", bean.get("numDeaths"))
						.append("qty", 1)
						.append("wins", win));
			//updating database
			tempCol.update(query, incDoc);
			System.out.println("updated champion data!");
			return true;
			//now we have the updated version - return the dbobject as a json
		}
		else{//if doesn't, just add a new obj
			BasicDBObject dbObj = new BasicDBObject();
			dbObj.append("championId", bean.get("championId"));
			dbObj.append("kills", bean.get("championsKilled"));
			dbObj.append("assists", bean.get("assists"));
			dbObj.append("deaths", bean.get("numDeaths"));
			dbObj.append("qty", 1);
			dbObj.append("weekDate", bean.get("weekDate"));
			dbObj.append("yearDate", bean.get("yearDate"));
			dbObj.append("wins", win);
			dbObj.append("rank", rank.getRank());
			dbObj.append("division", rank.getDivision());
			tempCol.insert(dbObj);
			System.out.println("inserted champion data!");
			return true;
		}
	}
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
	public ArrayList<ChampionBean> queryChampion(int championId, String region, RankBean rankLimits, int bounds, int weekDate, int yearDate){
		BasicDBObject query = new BasicDBObject();
		BeanParser<ChampionBean> parser = new BeanParser<ChampionBean>(ChampionBean.class);
		DBCollection tempCol = db.getCollection(region);
		System.out.println(region);
		query.put("championId", championId);
		query.put("weekDate", weekDate);//Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		query.put("yearDate", yearDate);//Calendar.getInstance().get(Calendar.YEAR);
		DBCursor[] cursorArray = generateQuery(query, rankLimits, bounds, tempCol);
		ArrayList<ChampionBean> champList = new ArrayList<ChampionBean>();
		DBCursor cursor = cursorArray[0];
		DBCursor copyCursor = cursorArray[1];
		if(copyCursor != null)
			while(copyCursor.hasNext()){
				champList.add(parser.parseJSON((BasicDBObject)copyCursor.next()));
			}
		while(cursor.hasNext()){
			champList.add(parser.parseJSON((BasicDBObject)cursor.next()));
		}
		return champList;//there can be multiple
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
	public DBCursor[] generateQuery(BasicDBObject query, RankBean rankLimits, int bounds, DBCollection tempCol){
		BasicDBObject queryCopy = null;
		if(rankLimits !=null){
			if(bounds != -2){
				BasicDBObject rankObj = new BasicDBObject();
				BasicDBObject divObj = new BasicDBObject();
				if(bounds > 0){
					queryCopy = (BasicDBObject) query.copy();
					rankObj.put("$gt", rankLimits.getRank());
					query.put("rank", rankObj);
					divObj.put("$gte", rankLimits.getDivision());
					//we want to put the division in another query
					queryCopy.put("rank", rankLimits.getRank());
					queryCopy.put("division", divObj);
				}
				else if(bounds < 0){
					queryCopy = (BasicDBObject) query.copy();
					rankObj.put("$lt" , rankLimits.getRank());
					rankObj.put("$gt", 0);//"What about that dark shadowy place dad?" 
					//"That is the unranked side of the query. You must never go there Simba."
					query.put("rank", rankObj);
					divObj.put("$lte", rankLimits.getDivision());
					divObj.put("$gt", 0);
					//we want to put the division in another query
					queryCopy.put("rank", rankLimits.getRank());
					queryCopy.put("division", divObj);
				}
				else {
					query.put("rank", rankLimits.getRank());
					query.put("division", rankLimits.getDivision());
				}
			}
		}
		System.out.println(query);
		DBCursor cursor = tempCol.find(query);
		DBCursor copyCursor = null;
		if(queryCopy != null){
			copyCursor = tempCol.find(queryCopy);
		}
		System.out.println("dicks");
		createIndexIfNotExist(tempCol);
		//returns copyCursor, but might be null
		return new DBCursor[]{cursor, copyCursor};
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
	public ArrayList<ItemBean> queryItem(int itemId, String region, RankBean rankLimits, int bounds, int weekDate, int yearDate){
		BasicDBObject query = new BasicDBObject();
		BeanParser<ItemBean> parser = new BeanParser<ItemBean>(ItemBean.class);
		DBCollection tempCol = db.getCollection(region);
		System.out.println(region);
		query.put("itemId", itemId);
		query.put("weekDate", weekDate);//Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		query.put("yearDate", yearDate);//Calendar.getInstance().get(Calendar.YEAR);
		DBCursor[] cursorArray = generateQuery(query, rankLimits, bounds, tempCol);
		ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
		DBCursor cursor = cursorArray[0];
		DBCursor copyCursor = cursorArray[1];
		if(copyCursor != null)
			while(copyCursor.hasNext()){
				itemList.add(parser.parseJSON((BasicDBObject)copyCursor.next()));
			}
		while(cursor.hasNext()){
			itemList.add(parser.parseJSON((BasicDBObject)cursor.next()));
		}
		return itemList;//there can be multiple
	}
	/**
	 * Creates an index for easy accessing database indices, so far takes care of _collection, _champ, _item
	 * @param collection to add index to
	 * @return boolean to display whether an index was added. True if added, false if not.
	 */
	public boolean createIndexIfNotExist(DBCollection collection){
		if(collection.getIndexInfo().size() == 1){
			String name = collection.getName();
			String postfix = name.split("_")[name.split("_").length-1];
			System.out.println("POSTFIX :: " + postfix);
			if(postfix.equals("collection")){
				BasicDBObject sortCriteria = new BasicDBObject();
				sortCriteria.put("mainSummonerID", 1);
				collection.createIndex(sortCriteria, new BasicDBObject("background", true));
			}
			else if(postfix.equals("champ")){
				BasicDBObject sortCriteria = new BasicDBObject();
				sortCriteria.put("champId", 1);
				sortCriteria.put("weekDate", -1);
				sortCriteria.put("yearDate", -1);
				sortCriteria.put("rank", 1);
				sortCriteria.put("division", 1);
				collection.createIndex(sortCriteria, new BasicDBObject("background", true));
			}
			else if(postfix.equals("item")){
				BasicDBObject sortCriteria = new BasicDBObject();
				sortCriteria.put("itemId", 1);
				sortCriteria.put("weekDate", -1);
				sortCriteria.put("yearDate", -1);
				sortCriteria.put("rank", 1);
				sortCriteria.put("division", 1);
				collection.createIndex(sortCriteria, new BasicDBObject("background", true));
			}
			return true;
		}
		return false;
	}
	public void closeConnection(){
		mongoClient.close();
	}
}
