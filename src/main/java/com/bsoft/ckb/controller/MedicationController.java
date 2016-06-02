package com.bsoft.ckb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.ckb.model.MedicationCatalog;
import com.bsoft.ckb.model.MedicationInstruction;
import com.bsoft.ckb.model.MedicationTrees;
import com.bsoft.ckb.model.Medications;
import com.bsoft.ckb.util.FieldUtil;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.ResultCode;
import com.bsoft.ckb.util.ResultProcessSet;

/**
 * 药品信息
 */
@Controller
@RequestMapping(value = "/medication")
public class MedicationController  extends BaseController{

    /**
     * 药品搜索
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryMedicationsByKey")
    @ResponseBody
    public Map<String, Object> queryMedicationsByKey(HttpServletRequest request,
    		Integer rows,Integer pageNum,String keyValue){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("keyValue", "\""+keyValue+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.medicationRpcServer","queryMedicationsByKey");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<Medications> list = JSONObject.parseArray(json.getString("medications"), Medications.class);
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 药品分类信息列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryMedicationTree")
    @ResponseBody
    public Map<String, Object> queryMedicationTree(HttpServletRequest request,
    		Integer type,Integer medicationCategory,String encodingId){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("medicationCategory", 2);
    	map.put("type", 0);
    	Map<String, Object> resMap = new HashMap<String, Object>();
    	String resultZhong = HTTPClient.request2(map, getToken(), "ckb.medicationRpcServer","queryMedicationTree");
    	map.clear();
    	map.put("type", 0);
    	map.put("medicationCategory", 1);
    	String resultXi = HTTPClient.request2(map, getToken(), "ckb.medicationRpcServer","queryMedicationTree");
    	JSONObject jsonZhong = JSONObject.parseObject(resultZhong);
    	JSONObject jsonXi = JSONObject.parseObject(resultXi);
    	if(jsonZhong.getInteger("code") == 200 && jsonXi.getInteger("code") == 200){
    		List<MedicationTrees> list = new ArrayList<>();
    		resMap.put("code", ResultCode.SUCCESS);
    		JSONObject bodyZhong = JSONObject.parseObject(jsonZhong.getString("body"));
    		if(bodyZhong != null){
    			List<MedicationTrees> listZhong = JSONObject.parseArray(bodyZhong.getString("medicationTrees"), MedicationTrees.class);
    			for (MedicationTrees medicationTrees : listZhong) {
    				medicationTrees.setType(1);
    			}
    			list.addAll(listZhong);
    		}
    		JSONObject bodyXi = JSONObject.parseObject(jsonXi.getString("body"));
    		if(bodyXi != null){
    			List<MedicationTrees> listXi = JSONObject.parseArray(bodyXi.getString("medicationTrees"), MedicationTrees.class);
    			for (MedicationTrees medicationTrees : listXi) {
    				medicationTrees.setType(2);
    			}
    			list.addAll(listXi);
    		}
    		resMap.put("data", list);
    		return resMap;
    	}
    	resMap.put("code", ResultCode.ERROR);
    	return resMap;
    }
    
    /**
     * 分类下药品列表服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryMedicationByTreeChild")
    @ResponseBody
    public Map<String, Object> queryMedicationByTreeChild(HttpServletRequest request,
    		String encodingId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("encodingId", encodingId);
    	map.put("type", 1);
    	String result = HTTPClient.request2(map, getToken(), "ckb.medicationRpcServer","queryMedicationTree");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<MedicationTrees> list = JSONObject.parseArray(json.getString("medicationTrees"), MedicationTrees.class);
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 分类下药品列表服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryMedicationByTree")
    @ResponseBody
    public Map<String, Object> queryMedicationByTree(HttpServletRequest request,
    		Integer rows,Integer pageNum,String encodingId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("encodingId", encodingId);
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	String result = HTTPClient.request(map, getToken(), "ckb.medicationRpcServer","queryMedicationByTree");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<Medications> list = JSONObject.parseArray(json.getString("medications"), Medications.class);
    			for (int i = 0; i < 7; i++) {
    				Medications medications = new Medications();
    				medications.setCommodityName("");
    				medications.setMedicationId("");
    				medications.setMedicationName("");
    				medications.setOriginName("创业医院");
    			}
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 药品目录列表服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryMedicationCatalogs")
    @ResponseBody
    public Map<String, Object> queryMedicationCatalogs(HttpServletRequest request,
    		String medicationId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("medicationId", medicationId);
    	String result = HTTPClient.request(map, getToken(), "ckb.medicationRpcServer","queryMedicationCatalogs");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<MedicationCatalog> list = JSONObject.parseArray(json.getString("medicationCatalogs"), MedicationCatalog.class);
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 药品目录信息服务
     * @author  jhui
     * @created 2016年4月29日 下午3:24:23
     * @param request
     * @param barcode:药品条码
     * @return
     * @return  Map<String,Object>
     */
    @RequestMapping(value = "queryMedicationCatalog")
    @ResponseBody
    public Map<String, Object> queryMedicationCatalog(HttpServletRequest request,
    		String barcode){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("barcode", barcode);
    	String result = HTTPClient.request(map, getToken(), "ckb.medicationRpcServer","queryMedicationCatalog");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			return JSONObject.parseObject(json.getString("body"), MedicationCatalog.class);
    		}
    	}.processResult(result);
    }
    /**
     * 药品说明书
     * @author  jhui
     * @created 2016年4月29日 下午3:24:23
     * @param request
     * @param barcode:药品条码
     * @return
     * @return  Map<String,Object>
     */
    @RequestMapping(value = "queryMedicationInstruction")
    @ResponseBody
    public Map<String, Object> queryMedicationInstruction(HttpServletRequest request,
    		String medicationId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("type", 0);
    	map.put("medicationId", medicationId);
    	String result = HTTPClient.request(map, getToken(), "ckb.medicationRpcServer","queryMedicationInstruction");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			MedicationInstruction mist = JSONObject.parseObject(json.toString(), MedicationInstruction.class);
    			FieldUtil.processField(mist);
    			return mist;
    		}
    	}.processResult(result);
    }
}
