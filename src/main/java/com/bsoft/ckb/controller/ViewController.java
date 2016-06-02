package com.bsoft.ckb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面定位
 */
@Controller
@RequestMapping(value = "/view")
public class ViewController  extends BaseController{
	
    @RequestMapping(value = "/medication")
    public ModelAndView medication(HttpServletRequest request){
    	ModelAndView m = new ModelAndView("medication/list");
    	return m;
    }
    @RequestMapping(value = "/medication_type")
    public ModelAndView medicationType(HttpServletRequest request,String twoEncodingId,
    		String oneLevelName,String medicationTreeName ){
    	ModelAndView m = new ModelAndView("medication/list_type");
    	m.addObject("twoEncodingId", twoEncodingId);
    	m.addObject("oneLevelName", oneLevelName);
    	m.addObject("medicationTreeName", medicationTreeName);
    	return m;
    }
    @RequestMapping(value = "/medication_detail")
    public ModelAndView medicationDetail(HttpServletRequest request,String medicationId){
    	ModelAndView m = new ModelAndView("medication/detail");
    	m.addObject("medicationId", medicationId);
    	return m;
    }
    
    @RequestMapping(value = "/clinicalpath")
    public ModelAndView clinicalpath(HttpServletRequest request){
    	ModelAndView m = new ModelAndView("clinicalpath/list");
    	return m;
    }
    
    @RequestMapping(value = "/clinicalpath_detail")
    public ModelAndView clinicalpathDetail(HttpServletRequest request,String clinicalPathId,String clinicalPathName){
    	ModelAndView m = new ModelAndView("clinicalpath/detail");
    	m.addObject("clinicalPathId", clinicalPathId);
    	m.addObject("clinicalPathName", clinicalPathName);
    	return m;
    }
    @RequestMapping(value = "/clinicalPath/formDetail")
    public ModelAndView formDetail(HttpServletRequest request,String pointName,String treatmentWorks,
    		String focusAdvice,String nursingWork,String clinicalPathName,String specialistName,String standardDays){
    	ModelAndView m = new ModelAndView("clinicalpath/formDetail");
    	m.addObject("pointName", pointName);
    	if(StringUtils.isNotBlank(treatmentWorks)){
    		treatmentWorks = treatmentWorks.replaceAll("\r\n", "</br>");
    	}
    	if(StringUtils.isNotBlank(focusAdvice)){
    		focusAdvice = focusAdvice.replaceAll("\r\n", "</br>");
    	}
    	if(StringUtils.isNotBlank(nursingWork)){
    		nursingWork = nursingWork.replaceAll("\r\n", "</br>");
    	}
    	m.addObject("treatmentWorks", treatmentWorks);
    	m.addObject("focusAdvice", focusAdvice);
    	m.addObject("nursingWork", nursingWork);
    	m.addObject("clinicalPathName", clinicalPathName);
    	m.addObject("specialistName", specialistName);
    	m.addObject("standardDays", standardDays);
    	return m;
    }
    
    @RequestMapping(value = "/disease")
    public ModelAndView disease(HttpServletRequest request){
    	ModelAndView m = new ModelAndView("disease/list");
    	return m;
    }
    @RequestMapping(value = "/disease_detail")
    public ModelAndView diseaseDetail(HttpServletRequest request,String diseaseEntityId,String diseaseEntityName){
    	ModelAndView m = new ModelAndView("disease/detail");
    	m.addObject("diseaseEntityId", diseaseEntityId);
    	m.addObject("diseaseEntityName", diseaseEntityName);
    	return m;
    }
    
    @RequestMapping(value = "/examine")
    public ModelAndView examine(HttpServletRequest request){
    	ModelAndView m = new ModelAndView("examine/list");
    	return m;
    }
    @RequestMapping(value = "/examine_type")
    public ModelAndView examineType(HttpServletRequest request,String sortName1,
    		String sortName2,String sort,Integer type ){
    	ModelAndView m = new ModelAndView("examine/list_type");
    	m.addObject("sortName1", sortName1);
    	m.addObject("sortName2", sortName2);
    	m.addObject("sort", sort);
    	m.addObject("type", type);
    	return m;
    }
    @RequestMapping(value = "/examine_detail")
    public ModelAndView examineDetail(HttpServletRequest request,String examineName,
    		String examineCode,Integer type){
    	ModelAndView m = new ModelAndView("examine/detail");
    	m.addObject("examineName", examineName);
    	m.addObject("examineCode", examineCode);
    	m.addObject("type", type);
    	return m;
    }
    
    @RequestMapping(value = "/inspection")
    public ModelAndView inspection(HttpServletRequest request){
    	ModelAndView m = new ModelAndView("inspection/list");
    	return m;
    }
    @RequestMapping(value = "/inspection_type")
    public ModelAndView inspectionType(HttpServletRequest request,String sortName1,
    		String sortName2,String sort ){
    	ModelAndView m = new ModelAndView("inspection/list_type");
    	m.addObject("sort", sort);
    	m.addObject("sortName2", sortName2);
    	m.addObject("sortName1", sortName1);
    	return m;
    }
    @RequestMapping(value = "/inspection_detail")
    public ModelAndView inspectionDetail(HttpServletRequest request,String inspectionCode,String inspectionName){
    	ModelAndView m = new ModelAndView("inspection/detail");
    	m.addObject("inspectionCode", inspectionCode);
    	m.addObject("inspectionName", inspectionName);
    	return m;
    }
    
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request){
    	ModelAndView m = new ModelAndView("index");
    	return m;
    }
}
