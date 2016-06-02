package com.bsoft.ckb.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.ckb.cache.TokenCache;
import com.bsoft.ckb.util.Constants;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.MD5;

/**
 * 全局搜索
 */
@Controller
public class BaseController {
	
	public String getToken(){
		String token = TokenCache.getToken();
		if(StringUtils.isBlank(token)){
			String url = Constants.LOGIN + "logon/login";
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("tenantId", "hcn.zhongshan");
			map.put("loginName", "15924139771");
			map.put("rid", "patient");
			map.put("pwd", MD5.getMD5("bsoft123"));
			map.put("forAccessToken", true);
			String json = JSONObject.toJSONString(map);
			System.out.println("url :" + url + " , param :" + json);
			String result = HTTPClient.doPost(url, json);
			JSONObject jsonR = JSONObject.parseObject(result);
			if(jsonR.getInteger("code") == 200){
				JSONObject properties = jsonR.getJSONObject("properties");
				String accessToken = properties.getString("accessToken");
				if(StringUtils.isNotBlank(accessToken)){
					TokenCache.putToken(accessToken);
					return accessToken;
				}
			}
		}
		return token;
	}
	
}
