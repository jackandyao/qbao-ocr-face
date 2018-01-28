package com.qbao.reconginse.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.qbao.reconginse.conf.ConfigurationManager;
import com.qbao.reconginse.constant.OCRConstant;

/**
 *   组装各个不同请求服务对应的实例URL
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class UrlStrUtil {
    
    static String httpUrl="";
    static String key="";
    static String sceret="";
//    static Map<String, String>map=null;

    static Map<String,String> keyUsedMap = new HashMap<String,String> ();
   
    /**
     * 获取访问实例的url
     * @param type
     * @param imageUrl
     * @return
     */
    public static Map<String, String> getHttpUrl(String status,String type,Object... imageUrl)  {
        try {
            if (type.equals(OCRConstant.ID_CARD)) {
                    return getIdCardUrl(imageUrl[0].toString(),status);
            }
            if (type.equals(OCRConstant.DRIVER_CARD)) {
                return getDriverCardUrl(imageUrl[0].toString(),status);
            }
            if (type.equals(OCRConstant.VEHICLE_CARD)) {
                return getVehicleCardUrl(imageUrl[0].toString(),status);
            }
            if (type.equals(OCRConstant.FACE_ANAYLAYZER)) {
                return getFaceAnaylyzerUrl(imageUrl[0].toString(),status);
            }
            if (type.equals(OCRConstant.FACE_COMPARE)) {
                return getFaceCompareUrl(status,imageUrl);
            }

        } catch (Exception e) {
           throw new RuntimeException("获取识别资源超时！");
        }
        return null;
    }
    
    /**
     * 组装参数KEY
     * @return
     */
    private synchronized static String[] getKeys(){
        String str=ConfigurationManager.getProperty(OCRConstant.CARD_PARAM);
        String []strs=str.split(",");
        for(String param:strs) {
            String[] ps = param.split(" ");
            if(keyUsedMap.containsKey(ps[0])){
                continue;
            }else{
                keyUsedMap.put(ps[0],ps[1]);
                return ps;
            }
        }
        return null;
    }

    private  static String[] getNotUsedKeys() throws Exception {
        int time = 0;
        while(time<1000){
            time++;
            String[] ps = getKeys();
            if(ps==null){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
//                System.out.println(ps[0]+"  "+ps[1]);
                return ps;
            }
        }
        throw new Exception("获取识别资源超时！");
    }

    public synchronized static void  removeUsedKey(String key){
        keyUsedMap.remove(key);
    }
    
    /**
     * 参数封装成map
     * @param image
     *
     * @return
     */
    private  static Map<String, String> getParamMap(String image,String status) throws Exception {
        Map<String, String>map =new HashMap<String, String>();
       
        // 解决并发
        if (status!=null && status.equals("4")) {
            System.out.println("进入并发模式..");
            map.put(OCRConstant.API_KEY,ConfigurationManager.getProperty(OCRConstant.IMAGE_RECONGINSE_API_KEY));
            map.put(OCRConstant.API_SCERET,ConfigurationManager.getProperty(OCRConstant.IMAGE_RECONGINSE_API_SECRET));
        }
        //解决非并发
        else{
            String str[]=getNotUsedKeys();
            map.put(OCRConstant.API_KEY,str[0]);
            map.put(OCRConstant.API_SCERET,str[1]);
        }
        map.put(OCRConstant.IMAGE_URL,image);
        return map;
    }
    
    /**
     * 身份证
     * @param image
     * @return
     */
    private static Map<String, String> getIdCardUrl(String image,String status) throws Exception {
       Map<String, String>idCardMap=getParamMap(image,status);
       idCardMap.put(OCRConstant.ID_CARD_LEGAITITY, ConfigurationManager.getProperty(OCRConstant.ID_CARD_RECONGINSE_LEGITITY));
       return idCardMap;
    }
    
    /**
     * 驾驶证
     * @param image
     * @return
     */
    private static Map<String, String> getDriverCardUrl(String image,String status) throws Exception {
        Map<String, String>driverCardMap=getParamMap(image,status);
        return driverCardMap;
    }
    
    /**
     * 行驶证
     * @param image
     * @return
     */
    private static Map<String, String> getVehicleCardUrl(String image,String status) throws Exception {
        Map<String, String>vehicleCardMap=getParamMap(image,status);
        return vehicleCardMap;
    }
    
    /**
     * 人脸比较
     * @param image
     * @return
     */
    private static Map<String, String> getFaceCompareUrl(String status,Object... image) throws Exception {
        Map<String, String>faceCompareMap=new HashMap<String, String>();
        // 解决并发
        if (status!=null && status.equals("4")) {
            System.out.println("进入并发模式....");
            faceCompareMap.put(OCRConstant.API_KEY,ConfigurationManager.getProperty(OCRConstant.IMAGE_RECONGINSE_API_KEY));
            faceCompareMap.put(OCRConstant.API_SCERET,ConfigurationManager.getProperty(OCRConstant.IMAGE_RECONGINSE_API_SECRET));
        }
        //解决非并发
        else{
            String str[]=getNotUsedKeys();
            faceCompareMap.put(OCRConstant.API_KEY,str[0]);
            faceCompareMap.put(OCRConstant.API_SCERET,str[1]);
        }
        faceCompareMap.put(OCRConstant.IMAGE_URL1,image[0].toString());
        faceCompareMap.put(OCRConstant.IMAGE_URL2,image[1].toString());
        return faceCompareMap;
    }
    
    /**
     * 人脸识别
     * @param image
     * @return
     */
    private static Map<String, String> getFaceAnaylyzerUrl(String image,String status) throws Exception {
        Map<String, String>faceMap=getParamMap(image,status);
        String attributes=ConfigurationManager.getProperty(OCRConstant.IMAGE_RECONGINSE_ATTRIBUTES);
        String mark=ConfigurationManager.getProperty(OCRConstant.IMAGE_RECONGINSE_LAND_MARK); 
        faceMap.put(OCRConstant.IMAGE_ATTRIBUTES,attributes);
        faceMap.put(OCRConstant.IMAGE_MARK,mark);
        return faceMap;
    }


    
}
