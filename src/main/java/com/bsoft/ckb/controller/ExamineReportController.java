package com.bsoft.ckb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bsoft.ckb.model.Examine;
import com.bsoft.ckb.model.ExamineApplyDetail;
import com.bsoft.ckb.model.ExamineItemDetail;
import com.bsoft.ckb.model.ExamineSort;
import com.bsoft.ckb.util.FieldUtil;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.ResultCode;
import com.bsoft.ckb.util.ResultProcessSet;

/**
 * 检验信息
 */
@Controller
@RequestMapping(value = "/examine")
public class ExamineReportController  extends BaseController{

    /**
     * 检验搜索
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryReportByKey")
    @ResponseBody
    public Map<String, Object> queryReportByKey(HttpServletRequest request,
    		Integer rows,Integer pageNum,String keyValue){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("keyValue", "\""+keyValue+"\"");
    	Map<String, Object> resMap = new HashMap<String, Object>();
    	String result = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryReportByKey");
    	String resultApply = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryApplyByKey");
    	
    	
    	JSONObject json = JSONObject.parseObject(result);
    	JSONObject jsonApply = JSONObject.parseObject(resultApply);
    	if(json.getInteger("code") == 200 && jsonApply.getInteger("code") == 200){
    		resMap.put("code", ResultCode.SUCCESS);
    		List<Examine> listAll = new ArrayList<>();
    		JSONObject body = JSONObject.parseObject(json.getString("body"));
    		if(body != null){
    			List<Examine> list = JSONObject.parseArray(body.getString("examines"), Examine.class);
    			for (Examine examine : list) {
    				examine.setType(1);
				}
    			listAll.addAll(list);
    		}
    		JSONObject bodyApply = JSONObject.parseObject(jsonApply.getString("body"));
    		if(bodyApply != null){
    			JSONArray array = JSONObject.parseArray(bodyApply.getString("examineApplys"));
    			int len = array.size();
    			for (int i = 0; i < len; i++) {
    				Examine exam = new Examine();
    				JSONObject jsonObject = array.getJSONObject(i);
    				exam.setExamineName(jsonObject.getString("examineAppName"));
    				exam.setExamineCode(jsonObject.getString("examineAppCode"));
    				exam.setType(2);
    				listAll.add(exam);
				}
    		}
    		resMap.put("data", listAll);
    		return resMap;
    	}
    	resMap.put("code", ResultCode.ERROR);
    	return resMap;
    }
    
    /**
     * 检验申请(报告)项目列表服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryExamine")
    @ResponseBody
    public Map<String, Object> queryExamine(HttpServletRequest request){
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> resMap = new HashMap<String, Object>();
    	String result = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryReportSort");
    	String resultApply = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryApplySort");
    	JSONObject json = JSONObject.parseObject(result);
    	JSONObject jsonApply = JSONObject.parseObject(resultApply);
    	if(json.getInteger("code") == 200 && jsonApply.getInteger("code") == 200){
    		List<ExamineSort> listAll = new ArrayList<>();
    		resMap.put("code", ResultCode.SUCCESS);
    		JSONObject body = JSONObject.parseObject(json.getString("body"));
    		if(body != null){
    			List<ExamineSort> list = JSONObject.parseArray(body.getString("examineSorts"), ExamineSort.class);
    			if(list != null){
    				for (ExamineSort examineSort : list) {
    					examineSort.setType(1);
    				}
    				listAll.addAll(processChildz(list));
    			}
    		}
    		JSONObject bodyApply = JSONObject.parseObject(jsonApply.getString("body"));
    		if(bodyApply != null){
    			List<ExamineSort> listApply = JSONObject.parseArray(bodyApply.getString("examineApplySorts"), ExamineSort.class);
    			if(listApply != null){
    				for (ExamineSort examineSort : listApply) {
    					examineSort.setType(2);
    				}
    				listAll.addAll(processChildz(listApply));
    			}
    		}
    		resMap.put("data", listAll);
    		return resMap;
    	}
    	resMap.put("code", ResultCode.ERROR);
    	return resMap;
    }

	private List<ExamineSort> processChildz(List<ExamineSort> listAll) {
		List<ExamineSort> parent = new ArrayList<>();
		List<ExamineSort> child = new ArrayList<>();
		for (ExamineSort exam : listAll) {
			if(exam.getParentSort().equals("0")){
				parent.add(exam);
			}else{
				child.add(exam);
			}
		}
		for (ExamineSort parentSort : parent) {
			for (ExamineSort childSort : child) {
				if(childSort.getParentSort().equals(parentSort.getSort())){
					parentSort.getChilds().add(childSort);
				}
			}
		}
		return parent;
	}
    
    /**
     * 检验分类下信息列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "querySort")
    @ResponseBody
    public Map<String, Object> querySort(HttpServletRequest request,
    		Integer type,Integer examineSort,Integer rows,Integer pageNum){
    	Map<String, Object> resMap = new HashMap<String, Object>();
    	resMap.put("code", ResultCode.SUCCESS);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	String result = null;
    	String resultApply = null;
    	if(type == 1){
    		map.put("examineSort", "\""+examineSort+"\"");//报告
    		result = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryReportItem");
    		return new ResultProcessSet(){
    			@Override
    			protected Object jsonToJava(JSONObject json) {
    				List<Examine> list = JSONObject.parseArray(json.getString("examines"), Examine.class);
        			for (Examine examine : list) {
        				examine.setType(1);
					}
    				return list;
    			}
    		}.processResult(result);
    	}else{
    		map.put("examineAppSort", "\""+examineSort+"\"");//申请
    		resultApply = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryApplyItem");
    		return new ResultProcessSet(){
    			@Override
    			protected Object jsonToJava(JSONObject json) {
    				JSONArray array = JSONObject.parseArray(json.getString("examineApplys"));
        			if(array != null){
        				int len = array.size();
        				List<Examine> list = new ArrayList<>();
        				for (int i = 0; i < len; i++) {
        					Examine exam = new Examine();
        					JSONObject jsonObject = array.getJSONObject(i);
        					exam.setExamineName(jsonObject.getString("examineAppName"));
        					exam.setExamineCode(jsonObject.getString("examineAppCode"));
        					exam.setType(2);
        					list.add(exam);
        				}
        				return list;
        			}
    				return null;
    			}
    		}.processResult(resultApply);
    	}
    }
    
    /**
     * 检验报告详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryReportItemDetail")
    @ResponseBody
    public Map<String, Object> queryReportItemDetail(HttpServletRequest request,
    		String examineCode){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("examineCode", "\""+examineCode+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryReportItemDetail");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			ExamineItemDetail eid = JSONObject.parseObject(json.toString(), ExamineItemDetail.class);
    			FieldUtil.processField(eid);
    			return eid;
    		}
    	}.processResult(result);
    }
    /**
     * 检验申请详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryApplyItemDetail")
    @ResponseBody
    public Map<String, Object> queryApplyItemDetail(HttpServletRequest request,
    		String examineCode){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("examineAppCode", "\""+examineCode+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.examineRpcServer","queryApplyItemDetail");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			ExamineApplyDetail ead = JSONObject.parseObject(json.getString("body"), ExamineApplyDetail.class);
        		FieldUtil.processField(ead);
    			return ead;
    		}
    	}.processResult(result);
    }
}
