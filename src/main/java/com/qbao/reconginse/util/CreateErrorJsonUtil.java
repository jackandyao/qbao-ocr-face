package com.qbao.reconginse.util;

import com.alibaba.fastjson.JSONObject;
import com.qbao.reconginse.constant.OCRConstant;

/**
 *   创造错误时候要返回的json格式数据
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class CreateErrorJsonUtil {
    
    
    //图片格式错误
    public static Object createImageFormatJson(){
        JSONObject imageFormateJson=new JSONObject();
        imageFormateJson.put(OCRConstant.ERROR_KEY, OCRConstant.COMMON_ERROR_MESSAGE);
        imageFormateJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
        imageFormateJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_IMAGE_FORMATE);
        return imageFormateJson;
    }
    
    
    //并发错误提示
    //http请求没有通 
    public static Object createHttpConnectionJson(){
        JSONObject errorJson=new JSONObject();
        errorJson.put(OCRConstant.ERROR_KEY, "对不起,服务请求有问题,请重新稍后发送请求");
        errorJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.FAIL_CODE);
        errorJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.FAIL_CODE);
        return errorJson;
    }
}
