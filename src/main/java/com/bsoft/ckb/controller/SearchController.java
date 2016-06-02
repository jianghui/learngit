package com.bsoft.ckb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bsoft.ckb.model.ExamineApply;
import com.bsoft.ckb.model.SearchVo;
import com.bsoft.ckb.util.HTTPClient;
import com.bsoft.ckb.util.ResultProcessSet;

/**
 * 全局搜索
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController  extends BaseController{
	
    @RequestMapping(value = "")
    @ResponseBody
    public Map<String, Object> queryDiseaseEntitiesByKey(HttpServletRequest request,
    		Integer rows,Integer pageNum,String keyValue){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("pageNum", pageNum);
    	map.put("rows", rows);
    	map.put("keyValue", "\""+keyValue+"\"");
    	String result = HTTPClient.request(map, getToken(), "ckb.medicalRpcServer","queryByKey");
    	return new ResultProcessSet() {
			@Override
			public Object jsonToJava(JSONObject json) {
				SearchVo vo = JSONObject.parseObject(json.toJSONString(), SearchVo.class);
    			List<ExamineApply> list = vo.getExamineApplys();
    			for (ExamineApply examineApply : list) {
    				examineApply.setExamineCode(examineApply.getExamineAppCode());
    				examineApply.setExamineAppCode(null);
    				examineApply.setExamineName(examineApply.getExamineAppName());
    				examineApply.setExamineAppName(null);
				}
				return vo;
			}
		}.processResult(result);
    }
}
