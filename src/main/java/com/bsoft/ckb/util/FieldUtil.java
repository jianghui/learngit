package com.bsoft.ckb.util;

import java.lang.reflect.Field;

import com.alibaba.fastjson.JSONObject;

public class FieldUtil {
	
	public static void processField(Object obj){
		if(obj != null){
			Class<? extends Object> classz = obj.getClass();
			Field[] fields = classz.getDeclaredFields();
			for (Field field : fields) {
				if(!field.getName().equals("serialVersionUID")){
					try {
						field.setAccessible(true);
						String strValue = (String)field.get(obj);
						strValue = processFieldValue(strValue);
						strValue = strValue.replaceAll("\n", "</br>");
						strValue = strValue.replaceAll("\r\n", "</br>");
						field.set(obj, strValue);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static String processFieldValue(String strValue){
    	int temp = KMPUtil.KMP_Index(strValue.toCharArray(), "<a href='{".toCharArray());
    	if(temp == -1){
    		return strValue;
    	}
    	temp = temp + 9;
    	int end = strValue.indexOf("}");
    	end = end + 1;
    	String param = strValue.substring(temp, end);
    	JSONObject jp = JSONObject.parseObject(param);
    	strValue = strValue.replace(param, processUrl(jp));
    	return processFieldValue(strValue);
    } 
    
    public static String processUrl(JSONObject jp){
    	Integer type = jp.getInteger("type");
    	Object id = jp.get("id");
    	StringBuilder bf = new StringBuilder(Constants.HREF_PATH);
    	if(type == 1){
    		bf.append("medication_detail?medicationId=");
    	}
    	if(type == 2){
    		bf.append("disease_detail?diseaseEntityId=");
    	}
    	if(type == 3){
    		bf.append("examine_detail?type=1&examineCode=");
    	}
    	if(type == 4){
    		bf.append("examine_detail?type=1&=");
    	}
    	if(type == 5){
    		bf.append("inspection_detail?inspectionCode=");
    	}
    	bf.append(id);
    	return bf.toString();
    }

}
