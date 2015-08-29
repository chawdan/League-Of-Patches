package com.leaguebeta.server.sync;

import com.leaguebeta.server.APICaller;
import com.leaguebeta.server.ResponseObject;

public class RateLimiter {
	public static final int MAX_RATE_1 = 3000;
	public static final int MAX_RATE_2 = 18000;
	public static final long RATE_TIME_1 = 10000000000L;
	public static final long RATE_TIME_2 = 10000000000L * 60;
	private static RateLimiter INSTANCE;
	public static boolean IS_DEVELOPER_KEY = true;

	public static RateLimiter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RateLimiter();
		}
		return INSTANCE;
	}

	private Object lock;
	private long lastNanoTime_1, lastNanoTime_2;
	private int count_1, count_2;

	private RateLimiter() {
		// create lock
		lock = new Object();
		lastNanoTime_1 = System.nanoTime();
		lastNanoTime_2 = System.nanoTime();
	}

	/**
	 * Tries to execute an API request.
	 * 
	 * @param someRequest
	 *      The request to try
	 * @return
	 *      True if the request succeeded, false if rate limit has been reached
	 */
	public ResponseObject tryRequest(APICaller api, String url) {
		if(IS_DEVELOPER_KEY){
			synchronized(lock){
				String json = api.call(url);
				//System.out.println(json);
				if(json.equals("404")){//a 404 message surfaced
					return new ResponseObject(false, null);
				}
				else if(json.equals("429") || json.equals("503")){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					json = tryRequest(api, url).getJson();
				}
				return new ResponseObject(true, json);
			}
		}
		else{
			long currentTime = System.nanoTime();
			long delta_1 = currentTime - lastNanoTime_1;
			long delta_2 = currentTime - lastNanoTime_2;
			if(count_1 >= MAX_RATE_1){//if called 10 times, it's max
				try {
					if(delta_1 < RATE_TIME_1){
						System.out.println("Sleeping <10 seconds> for..." + ((RATE_TIME_1 - (delta_1))/1000000 + 1000));
						Thread.sleep((RATE_TIME_1 - (delta_1))/1000000 + 1000);
					}
					count_1 = 0;//wait for 7 seconds if 3 seconds have passed
					lastNanoTime_1 = System.nanoTime();
					System.out.println("Wake up!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(count_2 >= MAX_RATE_2){
				try {
					if(delta_2 < RATE_TIME_2){
						System.out.println("Sleeping <10 min> for..." + ((RATE_TIME_2 - (delta_2))/1000000 + 1000));
						Thread.sleep((RATE_TIME_2 - (delta_2))/1000000 + 1000);
					}
					count_2 = 0;//wait for 7 seconds if 3 seconds have passed
					lastNanoTime_2 = System.nanoTime();
					System.out.println("Wake up!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//now that the 7 seconds are done, change count to 0
			synchronized(lock){				
				// do request stuff
				count_1++;
				count_2++;
				//System.out.println("count_1 : " + count_1);
				//System.out.println("count_2 : " + count_2);
				String json = api.call(url);
				if(json.equals("404")){//a 404 message surfaced
					return new ResponseObject(false, null);
				}
				else if(json.equals("429")){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					json = tryRequest(api, url).getJson();
				}
	//			if(json == null)
	//				json = tryRequest(api, url).getJson();
				return new ResponseObject(true, json);
				
			}
		}
	}
}