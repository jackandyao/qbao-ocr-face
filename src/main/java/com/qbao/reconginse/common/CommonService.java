package com.qbao.reconginse.common;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.reconginse.conf.ConfigurationManager;
import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.util.CreateErrorJsonUtil;
import com.qbao.reconginse.util.HttpClientUtil;
import com.qbao.reconginse.util.UrlStrUtil;
import com.qbao.reconginse.constant.OCRConstant;

/**
 *  
     * @author 贾红平
     * 共用服务
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class CommonService {
    
    protected static Map<String, String>errorMap=new HashMap<String, String>();
    
    static{
        errorMap.put("AUTHENTICATION_ERROR", "api_key和api_secret不匹配");
        errorMap.put("CONCURRENCY_LIMIT_EXCEEDED", "并发数超过限制");
        errorMap.put("MISSING_ARGUMENTS", "缺少某个必选参数");
        errorMap.put("BAD_ARGUMENTS", "某个参数解析出错");
        errorMap.put("COEXISTENCE_ARGUMENTS", "同时传入了要求是二选一或多选一的参数");
        errorMap.put("Request Entity Too Large", "客户发送的请求大小超过了2MB限制");
        errorMap.put("API_NOT_FOUND", "所调用的API不存在");
        errorMap.put("INTERNAL_ERROR", "服务器内部错误");
        errorMap.put("INVALID_IMAGE_URL", "对不起你输入的图片的URL是不合法的");
         
    }
    
    /**
     * 根据对方服务器请求结果转换成对应的
     * @param value
     * @return
     */
    protected String getReponseCode(String value){
        if (value.equals("CONCURRENCY_LIMIT_EXCEEDED") ||value.equals("AUTHENTICATION_ERROR")) {
            return OCRConstant.SUCCESS_CODE_CONERENCY;
        }
        if (value.equals("MISSING_ARGUMENTS")|| value.equals("BAD_ARGUMENTS")) {
            return OCRConstant.SUCCESS_CODE_ERROR_PARAM;
        }
        return "0";
    }
    
    /**
     * 获取HTTP请求结果
     * @param status
     * @param imageUrl
     * @param type
     * @return
     */
    private Object getHttpResultObj(String status,String imageUrl,String type) {
        Map<String, String> paramUrl = UrlStrUtil.getHttpUrl(status, type, imageUrl);
        try {
            Object obj = HttpClientUtil.sendPost(getHttpUrl(type), paramUrl, OCRConstant.CHARSET);
            return obj;
        } catch (Exception e){
           throw new RuntimeException(e);
        }finally {
            System.out.println(paramUrl.get(OCRConstant.API_KEY));
            UrlStrUtil.removeUsedKey(paramUrl.get(OCRConstant.API_KEY));
        }


//        if (type.equals(OCRConstant.FACE_ANAYLAYZER)|| type.equals(OCRConstant.FACE_COMPARE)) {
//            if (obj!=null) {
//                JSONObject json=(JSONObject) JSON.parse(obj.toString());
//                if (json.containsKey(OCRConstant.ERROR_KEY)) {
//                     status=getReponseCode(json.get(OCRConstant.ERROR_KEY).toString());
//                     if (status.equals("4")) {
//                         Object obj1=HttpClientUtil.sendPost(getHttpUrl(type),UrlStrUtil.getHttpUrl(status,type, imageUrl), OCRConstant.CHARSET);
//                         System.err.println("第二次请求:"+obj1);
//                         obj=obj1;
//                     }
//                }
//            }
//        }

    }
    
    /**
     * 抽取cards结果
     * @param imageUrl
     * @return
     */
    protected JSONArray getJsonArray(String imageUrl,String type){
        JSONArray array=null;
        Object obj=getHttpResultObj(null, imageUrl, type);
        if (obj!=null) {
            JSONObject json=(JSONObject) JSON.parse(obj.toString());
            if (json.containsKey(OCRConstant.ERROR_KEY)) {
                 array=new JSONArray();
                 JSONObject error=new JSONObject();
                 error.put(OCRConstant.ERROR_KEY, errorMap.get(json.get(OCRConstant.ERROR_KEY)));
                 error.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
                 error.put(OCRConstant.REPONSE_STATUS, getReponseCode(json.get(OCRConstant.ERROR_KEY).toString()));
                 array.add(error);
            }
            else{
                Object oobj=json.get(getParamKey(type));
                if (oobj!=null) {
                    array= (JSONArray) JSONArray.parse(oobj.toString());
                    if (array.size()>0) {
                        return array;
                    }
                    else{
                        //图片格式错误
                        array=new JSONArray();
                        array.add(CreateErrorJsonUtil.createImageFormatJson());
                    }
                }
            }
        }
        else{
            //http请求内部出现错误
            array=new JSONArray();
            array.add(CreateErrorJsonUtil.createHttpConnectionJson());
        }
        return array;
        
        
    }
    
    /**
     * 不同服务获取不同的key
     * @param type
     * @return
     */
    private String getParamKey(String type){
        if (type!=null) {
            if (type.equals(OCRConstant.ID_CARD)||type.equals(OCRConstant.DRIVER_CARD)|| type.equals(OCRConstant.VEHICLE_CARD)) {
                return OCRConstant.RESULT_KEY_CARDS;
            }
            else{
                return OCRConstant.RESULT_KEY_FACES;
            }
        }
        return null;
    }
    
    /**
     * 不同的服务请求不同的url
     * @param type
     * @return
     */
    private String getHttpUrl(String type){
        if (type!=null) {
            if (type.equals(OCRConstant.ID_CARD)) {
                return ConfigurationManager.getProperty(OCRConstant.ID_CARD_RECONGINSE_URL).toString();
            }
            if (type.equals(OCRConstant.DRIVER_CARD)) {
                return ConfigurationManager.getProperty(OCRConstant.DRIVER_CARD_RECONGINSE_API_URL).toString();
            }
            if (type.equals(OCRConstant.VEHICLE_CARD)) {
                return ConfigurationManager.getProperty(OCRConstant.VEHICLE_CARD_RECONGINSE_URL).toString();
            }
            if (type.equals(OCRConstant.FACE_ANAYLAYZER)) {
                return ConfigurationManager.getProperty(OCRConstant.IMAGE_RECONGINSE_URL).toString();
            }
        }
        return null;
    }
}
