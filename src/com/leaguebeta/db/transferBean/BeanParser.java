package com.leaguebeta.db.transferBean;

import com.google.gson.Gson;
import com.leaguebeta.db.model.PlayerGameBean;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

//purely a static parser that can turn the bean into json and json into bean.
public class BeanParser<T>{
	final Class<T> classType;
	public BeanParser(Class<T> type){ /*we need to know the type so we can cast it*/ 
		classType = type;
	};
	public BasicDBObject parseBean(T bean){
		Gson gson = new Gson();
		BasicDBObject obj = (BasicDBObject)JSON.parse(gson.toJson(bean));
		return obj;
	}
	public T parseJSON(BasicDBObject json){
		Gson gson = new Gson();
		T obj = gson.fromJson(gson.toJson(json), classType);
		return obj;
	}
	public static <T> BasicDBObject parseAnyBean(T bean){
		Gson gson = new Gson();
		BasicDBObject obj = (BasicDBObject)JSON.parse(gson.toJson(bean));
		return obj;
	}
}
