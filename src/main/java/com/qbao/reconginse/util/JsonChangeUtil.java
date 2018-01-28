package com.qbao.reconginse.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.reconginse.conf.ConfigurationManager;
import com.qbao.reconginse.constant.OCRConstant;

/**
 * 
 * @author 贾红平
 * 对返回的结果进行转换,获取指定的文字内容
 * $LastChangedDate$
 * $LastChangedRevision$
 * $LastChangedBy$
 */
@SuppressWarnings("all")
public class JsonChangeUtil {

    
    
    /**
     * 返回各种证件识别,并且保存指定字段的信息
     * @param array
     * @param type
     * @return
     */
    public static Object getReusltObj(JSONArray array, String type) {
        //[{"http_status":"1","response_status":"0"}]
        if (array!=null) {
            if (type.equals(OCRConstant.FACE_ANAYLAYZER)) {
                return getFaceResultArray(array);
            }
            JSONObject json = (JSONObject) array.get(0);
            if (json != null) {
               if (!json.containsKey(OCRConstant.ERROR_KEY)) {
                   if (ConfigurationManager.getInteger(OCRConstant.IS_RESULT_ALL)==1) {
                       if (type.equals(OCRConstant.ID_CARD)) {
                           return getIdCardReuslt(json);
                       }
                       if (type.equals(OCRConstant.DRIVER_CARD)) {
                           return getDriverCardReuslt(json);
                       }
                       if (type.equals(OCRConstant.VEHICLE_CARD)) {
                           return getVehicleCardReuslt(json);
                       }
                   }
               }
               else{
                   return json;
               }
            }
            return null;
        }
        else{
            JSONObject errorJson = new JSONObject();
            errorJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            errorJson.put(OCRConstant.HTTP_INFO, OCRConstant.COMMON_ERROR_MESSAGE);
            errorJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_IMAGE_FORMATE);
            return errorJson;
        }
        
        

    }




    /**
     * 抽取身份证返回特定字段
     * @param json
     * @return
     */
    private static Object getIdCardReuslt(JSONObject json) {
        if (json.containsKey(OCRConstant.ERROR_KEY)) {
            return json;
        }
        else{
            JSONObject idJson = new JSONObject();
            idJson.put(OCRConstant.USER_NAME, json.getString("name"));
            idJson.put(OCRConstant.ID_CARD_NUMBER, json.getString("id_card_number"));
            //idJson.put("is_id_card", json.getString("type").equals("1")?"身份证":"非身份证");
            idJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            idJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            return idJson;
        }
    }

    /**
     * 抽取驾驶证返回特定字段
     * @param json
     * @return
     */
    private static Object getDriverCardReuslt(JSONObject json) {
        if (json.containsKey(OCRConstant.ERROR_KEY)) {
            return json;
        }
        else{
            JSONObject driverJson = new JSONObject();
            driverJson.put(OCRConstant.USER_NAME, json.getString("name"));
            driverJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            driverJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            return driverJson;
        }
    }

    /**
     * 抽取行驶证返回特定字段
     * @param json
     * @return
     */
    private static Object getVehicleCardReuslt(JSONObject json) {
        if (json.containsKey(OCRConstant.ERROR_KEY)) {
            return json;
        }
        else{
            JSONObject vehicleJson = new JSONObject();
            vehicleJson.put(OCRConstant.USER_NAME, json.getString("owner"));
            vehicleJson.put(OCRConstant.CAR_CODE, json.getString("plate_no"));
            vehicleJson.put(OCRConstant.CAR_MODEL, getChineseStr(json.getString("model")));
            vehicleJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            vehicleJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            return vehicleJson;
        }
    }
    
    /**
     * 提取字符串的中文
     * @param str
     * @return
     */
    private static String getChineseStr(String str){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i)+"").getBytes().length>1) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString().replace("牌", "");
    }

    /**
     * 抽取人脸返回特定字段
     * @param json
     * @return
     */
    private static Object getFaceReuslt(JSONObject json) {
       if (json.containsKey(OCRConstant.ERROR_KEY)) {
            return json;
       }
       else{
           JSONObject faceJson = new JSONObject();
           boolean isTrue=ConfigurationManager.getInteger(OCRConstant.IMAGE_RECONGINSE_LAND_MARK)==1?true:false;
           if (isTrue) {
               faceJson.put("face_feature",json.get("landmark"));            
           }
           
           faceJson.put("face_smile", json.getJSONObject(("attributes")).getJSONObject("smile"));
           faceJson.put("face_gender", json.getJSONObject(("attributes")).getJSONObject("gender"));
           faceJson.put("face_age", json.getJSONObject(("attributes")).getJSONObject("age"));
           return faceJson;
       }
    }

    /**
     * 抽取多个人脸返回特定字段数组
     * @param json
     * @return
     */
    private static Object getFaceResultArray(JSONArray array){
        JSONArray returnArray = new JSONArray();
        if(array.size()>0){
            for(int i=0;i<array.size();i++){
                JSONObject json = array.getJSONObject(i);
                if (json.containsKey(OCRConstant.ERROR_KEY)) {
                    returnArray.add(json);
                }
                else{
                    try {
                        JSONObject faceJson = new JSONObject();
                        boolean isTrue = ConfigurationManager.getInteger(OCRConstant.IMAGE_RECONGINSE_LAND_MARK) == 1 ? true : false;
                        if (isTrue) {
                            faceJson.put("face_feature", json.get("landmark"));
                        }

                        faceJson.put("face_smile", json.getJSONObject(("attributes")).getJSONObject("smile"));
                        faceJson.put("face_gender", json.getJSONObject(("attributes")).getJSONObject("gender"));
                        faceJson.put("face_age", json.getJSONObject(("attributes")).getJSONObject("age"));
                        faceJson.put("face_rectangle", json.getJSONObject("face_rectangle"));
//                    return faceJson;
                        returnArray.add(faceJson);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return returnArray;

    }
    
    /**
     * 提取中文
     * @param str
     * @return
     */
    private static String getHanZi(String str){
        Pattern p = null;
        Matcher m = null;
        String regEx = "[\\u4e00-\\u9fa5]";
        String value = null;
        try {
            p = Pattern.compile(regEx);
            m = p.matcher(str);
            while (m.find()) {
                value = m.group(0);
                System.out.print(value);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    
    
    
    
}
