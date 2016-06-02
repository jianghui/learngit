package com.bsoft.ckb.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

public abstract class ResultProcessSet {
	
	
	public Map<String, Object> processResult(String result){
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(result)){
			resMap.put("code", ResultCode.ERROR);
	    	return resMap;
		}
		JSONObject json = JSONObject.parseObject(result);
		Object code = json.get("code");
		if(code == null){
			resMap.put("code", ResultCode.ERROR);
	    	return resMap;
		}
    	if(TypeUtils.castToInt(code).intValue() == 200){
    		resMap.put("code", ResultCode.SUCCESS);
    		if(json.get("body") == null){
    			resMap.put("data", null);
    			return resMap;
    		}
    		JSONObject body = JSONObject.parseObject(json.getString("body"));
    		if(body == null){
    			resMap.put("data", null);
    			return resMap;
    		}
    		resMap.put("data", jsonToJava(body));
			return resMap;
    	}
    	System.out.println("接口返回错误信息：" + json.getString("msg"));
    	resMap.put("code", ResultCode.ERROR);
    	return resMap;
	}
	
	protected abstract Object jsonToJava(JSONObject json); 

}
