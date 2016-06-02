package com.bsoft.ckb.cache;

import com.bsoft.ckb.util.Constants;

public class TokenCache {
	static BaseCache<String, String> tkCache = new BaseCache<String, String>(5000, 7 * 24 * 60 * 60);

	public static String getToken(){
		return tkCache.get(Constants.CKB_TOKEN);
	}
	
	public static void putToken(String token){
		tkCache.put(Constants.CKB_TOKEN, token); 
	}
}
