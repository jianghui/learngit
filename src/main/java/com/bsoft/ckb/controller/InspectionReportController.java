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
import com.bsoft.ckb.model.InspectionSorts;
import com.bsoft.ckb.model.Inspections;
import com.bsoft.ckb.model.InspectionsItem;
import com.bsoft.ckb.util.FieldUtil;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.ResultProcessSet;

/**
 * 检查信息
 */
@Controller
@RequestMapping(value = "/inspection")
public class InspectionReportController  extends BaseController{

    /**
     * 检查搜索
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryItemByKey")
    @ResponseBody
    public Map<String, Object> queryItemByKey(HttpServletRequest request,
    		Integer rows,Integer pageNum,String keyValue){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("keyValue", "\""+keyValue+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.inspectionRpcServer","queryItemByKey");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<Inspections> list = JSONObject.parseArray(json.getString("inspections"), Inspections.class);
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 检查分类信息列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "querySort")
    @ResponseBody
    public Map<String, Object> querySort(HttpServletRequest request){
    	Map<String, Object> map = new HashMap<String, Object>();
    	String result = HTTPClient.request(map, getToken(), "ckb.inspectionRpcServer","querySort");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<InspectionSorts> list = JSONObject.parseArray(json.getString("inspectionSorts"), InspectionSorts.class);
    			List<InspectionSorts> parent = new ArrayList<>();
    			List<InspectionSorts> child = new ArrayList<>();
    			for (InspectionSorts inspectionSorts : list) {
    				if(inspectionSorts.getParentSort().equals("0")){
    					parent.add(inspectionSorts);
    				}else{
    					child.add(inspectionSorts);
    				}
				}
    			for (InspectionSorts parentSort : parent) {
					for (InspectionSorts childSort : child) {
						if(childSort.getParentSort().equals(parentSort.getSort())){
							parentSort.getChilds().add(childSort);
						}
					}
				}
    			return parent;
    		}
    	}.processResult(result);
    }
    
    /**
     * 分类检查项目列表服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryItem")
    @ResponseBody
    public Map<String, Object> queryItem(HttpServletRequest request,
    		Integer rows,Integer pageNum,String inspectionSort){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("inspectionSort", "\""+inspectionSort+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.inspectionRpcServer","queryItem");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<Inspections> list = JSONObject.parseArray(json.getString("inspections"), Inspections.class);
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 检查项目知识服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryItemDetail")
    @ResponseBody
    public Map<String, Object> queryItemDetail(HttpServletRequest request,
    		String inspectionCode){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("inspectionCode", "\""+inspectionCode+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.inspectionRpcServer","queryItemDetail");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			InspectionsItem it = JSONObject.parseObject(json.getString("body"), InspectionsItem.class);
        		FieldUtil.processField(it);
    			return it;
    		}
    	}.processResult(result);
    }
}
