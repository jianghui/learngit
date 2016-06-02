package com.bsoft.ckb;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.MD5;

public class Test {
	static String path = "http://124.160.104.16/app/";
	static String token = "607c9c61-d1b3-4ee6-85f9-64d267d32842";
	
	public static void main(String[] args) {
		login("15924139771", MD5.getMD5("bsoft123"));
//		queryMedicationByTree();

	}
	
	public static void login(String uid, String pwd) {
		String url = path + "logon/login";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("loginName", uid);
		map.put("rid", "patient");
		map.put("pwd", pwd);
		map.put("forAccessToken", true);
		String json = JSONObject.toJSONString(map);
		System.out.println("url :" + url + " , param :" + json);
		String result = HTTPClient.doPost(url, json);
		System.out.println(result);
	}
	
	
	public static void queryMedicationsByKey(){
		StringBuilder param = new StringBuilder("[");
		String url = path + "*.jsonRequest";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("keyValue", "盐酸");
		map.put("pageNum", 1);
		map.put("rows", 10);
		String json = JSONObject.toJSONString(map);
		param.append(json).append("]");
		System.out.println("url :" + url + " , param :" + json);
		long wsstart = System.currentTimeMillis();
		String result = HTTPClient.doPostAndHeader(url, param.toString(),token,"ckb.medicationRpcServer","queryMedicationsByKey");
		long wsend = System.currentTimeMillis();
		System.out.println("用时：" + (wsend - wsstart));
		System.out.println(result);
	}
	public static void queryMedicationTree(){
		StringBuilder param = new StringBuilder("[");
		String url = path + "*.jsonRequest";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("type", 0);
		map.put("medicationCategory", 1);//type为0时这个值选择1是西药，选择2是中药
//		map.put("encodingId", 101);//type为0时这个值选择1是西药，选择2是中药
		String json = JSONObject.toJSONString(map);
		param.append(json).append("]");
		System.out.println("url :" + url + " , param :" + json);
		long wsstart = System.currentTimeMillis();
		String result = HTTPClient.doPostAndHeader(url, param.toString(),token,"ckb.medicationRpcServer","queryMedicationTree");
		long wsend = System.currentTimeMillis();
		System.out.println("用时：" + (wsend - wsstart));
		System.out.println(result);
	}
	
	public static void queryMedicationByTree(){
		StringBuilder param = new StringBuilder("[");
		String url = path + "*.jsonRequest";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("encodingId", "115070802");
		map.put("pageNum", 1);
		map.put("rows", 10);
		String json = JSONObject.toJSONString(map);
		param.append(json).append("]");
		System.out.println("url :" + url + " , param :" + json);
		long wsstart = System.currentTimeMillis();
		String result = HTTPClient.doPostAndHeader(url, param.toString(),token,"ckb.medicationRpcServer","queryMedicationByTree");
		long wsend = System.currentTimeMillis();
		System.out.println("用时：" + (wsend - wsstart));
		System.out.println(result);
	}
	
	public static void queryMedicationCatalogs(){
		StringBuilder param = new StringBuilder("[");
		String url = path + "*.jsonRequest";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("medicationId", "90469");
		String json = JSONObject.toJSONString(map);
		param.append(json).append("]");
		System.out.println("url :" + url + " , param :" + json);
		long wsstart = System.currentTimeMillis();
		String result = HTTPClient.doPostAndHeader(url, param.toString(),token,"ckb.medicationRpcServer","queryMedicationCatalogs");
		long wsend = System.currentTimeMillis();
		System.out.println("用时：" + (wsend - wsstart));
		System.out.println(result);
	}

}
