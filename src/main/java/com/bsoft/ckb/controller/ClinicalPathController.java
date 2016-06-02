package com.bsoft.ckb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.ckb.model.ClinicalPath;
import com.bsoft.ckb.model.ClinicalPathDetail;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.ResultProcessSet;

/**
 * 临床路径
 */
@Controller
@RequestMapping(value = "/clinicalPath")
public class ClinicalPathController extends BaseController{

    /**
     * 搜索
     *
     * @param request
     * @return
     */
	@RequestMapping(value = "queryClinicalPathByKey")
	@ResponseBody
	public Map<String, Object> queryClinicalPathByKey(HttpServletRequest request, Integer rows, Integer pageNum, String keyValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("rows", rows);
		map.put("keyValue", "\"" + keyValue + "\"");
		String result = HTTPClient.request(map, getToken(), "ckb.diseaseRpcServer", "queryClinicalPathByKey");
		return new ResultProcessSet() {
			@Override
			public Object jsonToJava(JSONObject json) {
				return JSONObject.parseArray(json.getString("clinicalPaths"), ClinicalPath.class);
			}
		}.processResult(result);
	}
    
    /**
     * 分类下列表服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryClinicalPathByZk")
    @ResponseBody
    public Map<String, Object> queryClinicalPathByZk(HttpServletRequest request,
    		Integer rows,Integer pageNum,String specialistId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("specialistId", "\""+specialistId+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.diseaseRpcServer","queryClinicalPathByZk");
    	return new ResultProcessSet(){
    		protected Object jsonToJava(JSONObject json) {
    			List<ClinicalPath> list = JSONObject.parseArray(json.getString("clinicalPaths"), ClinicalPath.class);
    			return list;
    		};
    	}.processResult(result);
    }
    /**
     * 详情
     * @author  jhui
     * @created 2016年5月4日 下午1:52:07
     * @param request
     * @param clinicalPathId
     * @return
     * @return  Map<String,Object>
     */
    @RequestMapping(value = "getClinicalPath")
    @ResponseBody
    public Map<String, Object> getClinicalPath(HttpServletRequest request,
    		String clinicalPathId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("clinicalPathId", "\""+clinicalPathId+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.diseaseRpcServer","getClinicalPath");
    	return new ResultProcessSet(){
    		protected Object jsonToJava(JSONObject json) {
    			ClinicalPathDetail cpd = JSONObject.parseObject(json.getString("body"), ClinicalPathDetail.class);
        		return cpd;
    		};
    	}.processResult(result);
    }
}
