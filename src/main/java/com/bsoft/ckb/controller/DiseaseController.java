package com.bsoft.ckb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.ckb.model.DiseaseDetail;
import com.bsoft.ckb.model.DiseaseEntity;
import com.bsoft.ckb.model.Specialist;
import com.bsoft.ckb.util.FieldUtil;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.ResultProcessSet;

/**
 * 疾病
 */
@Controller
@RequestMapping(value = "/disease")
public class DiseaseController  extends BaseController{

    /**
     * 搜索
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryDiseaseEntitiesByKey")
    @ResponseBody
    public Map<String, Object> queryDiseaseEntitiesByKey(HttpServletRequest request,
    		Integer rows,Integer pageNum,String keyValue){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("keyValue", "\""+keyValue+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.diseaseRpcServer","queryDiseaseEntitiesByKey");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<DiseaseEntity> list = JSONObject.parseArray(json.getString("diseaseEntitys"), DiseaseEntity.class);
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 专科疾病信息列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "querySpecialist")
    @ResponseBody
    public Map<String, Object> querySpecialist(HttpServletRequest request){
    	Map<String, Object> map = new HashMap<String, Object>();
    	String result = HTTPClient.request(map, getToken(), "ckb.diseaseRpcServer","querySpecialist");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<Specialist> list = JSONObject.parseArray(json.getString("specialists"), Specialist.class);
    			return list;
    		}
    	}.processResult(result);
    }
    
    /**
     * 分类下列表服务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "queryDiseaseEntitiesBySpecialist")
    @ResponseBody
    public Map<String, Object> queryDiseaseEntitiesBySpecialist(HttpServletRequest request,
    		Integer rows,Integer pageNum,String specialistId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("specialistId", "\""+specialistId+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.diseaseRpcServer","queryDiseaseEntitiesBySpecialist");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			List<DiseaseEntity> list = JSONObject.parseArray(json.getString("diseaseEntitys"), DiseaseEntity.class);
    			return list;
    		}
    	}.processResult(result);
    }
    /**
     * 详情
     * @author  jhui
     * @created 2016年5月4日 下午1:54:16
     * @param request
     * @param keyValue
     * @return
     * @return  Map<String,Object>
     */
    @RequestMapping(value = "queryGuidelines")
    @ResponseBody
    public Map<String, Object> queryGuidelines(HttpServletRequest request,
    		String keyValue){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("keyValue", "\""+keyValue+"\"");
    	map.put("type", 0);
    	String result = HTTPClient.request(map, getToken(), "ckb.diseaseRpcServer","queryGuidelines");
    	return new ResultProcessSet(){
    		@Override
    		protected Object jsonToJava(JSONObject json) {
    			DiseaseDetail dd = JSONObject.parseObject(json.getString("body"), DiseaseDetail.class);
        		FieldUtil.processField(dd);
        		return dd;
    		}
    	}.processResult(result);
    }
    
}
