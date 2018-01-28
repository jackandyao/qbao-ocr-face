package com.qbao.reconginse.service.impl.face;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbao.reconginse.common.CommonService;
import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.service.IFaceRecgService;
import com.qbao.reconginse.util.FaceDistanceUtil;
import com.qbao.reconginse.util.JsonChangeUtil;
import com.qbao.reconginse.util.MathUtil;
import com.qbao.reconginse.util.ScoreUtil;
import com.sun.swing.internal.plaf.synth.resources.synth;

/**
 *   人脸识别服务实例
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
@SuppressWarnings("all")
public class FaceAnalyzerService extends CommonService implements IFaceRecgService {
    
    /**
     *  人脸评分结果
     */
    public  Object getResultObj(Object... imageUrl) {
        JSONArray array=getJsonArray(imageUrl[0].toString(),OCRConstant.FACE_ANAYLAYZER);
        JSONObject faceJson=new JSONObject();
        JSONArray faceResArray = new JSONArray();
        if (array.size()>0) {
           JSONArray resJsonArray= (JSONArray)JsonChangeUtil.getReusltObj(array, OCRConstant.FACE_ANAYLAYZER);
           for(int  i=0;i<resJsonArray.size();i++){
               JSONObject resJson = resJsonArray.getJSONObject(i);
               if (resJson.containsKey(OCRConstant.ERROR_KEY)) {
                  return resJson;
               }
               else{
                   JSONObject faceScoreJson = new JSONObject();
                   JSONObject scoreJson=getFaceYanZhi(resJson);
                   double score=scoreJson.getJSONObject("scoreJson").getDoubleValue("faceScore");
                   String info=ScoreUtil.getFaceInfo(score);
                   faceScoreJson.put(OCRConstant.FACE_INFO, info);
                   faceScoreJson.put(OCRConstant.FACE_SCORE,score);
                   faceScoreJson.put(OCRConstant.FACE_DETAIL_SCORE, scoreJson.getJSONObject("wdJson"));
                   faceScoreJson.put("face_rectangle",resJson.getJSONObject("face_rectangle"));
                   faceResArray.add(faceScoreJson);
               }
           }
            faceJson.put(OCRConstant.HTTP_INFO,"OK");
            faceJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            faceJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
        }
        else{
            faceJson.put(OCRConstant.HTTP_INFO, OCRConstant.COMMON_ERROR_MESSAGE);
            faceJson.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
            faceJson.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_IMAGE_FORMATE);
        }
        faceJson.put("faces",faceResArray);
        return faceJson;            
    }
    
   
    /**
     * 根据人脸特征的各个部位的距离进行人脸颜值打分计算
     * @return
     */
    private JSONObject getFaceYanZhi(JSONObject json){
            double defaultScore=100.0;double faceScore=60;double bzScore=0.0;double yjScore=0.0;double zbScore=0.0;double xbScore=0.0;double mmScore=0.0;
            JSONObject featureJson=(JSONObject) json.get("face_feature");
           
            //计算两眉头间的距离
            double c1=FaceDistanceUtil.calcEyeBrowDistance(parseJsonByKey(featureJson,"right_eyebrow_left_corner"), parseJsonByKey(featureJson,"left_eyebrow_right_corner"));
            //眉毛中点到鼻子最低处的距离
            double c2=FaceDistanceUtil.calcBrowToNoseDistance(parseJsonByKey(featureJson,"right_eyebrow_left_corner"), parseJsonByKey(featureJson,"left_eyebrow_right_corner"), parseJsonByKey(featureJson,"nose_contour_lower_middle"));
            //眼角之间的距离
            double c3=FaceDistanceUtil.calcEyeBetwenDistance(parseJsonByKey(featureJson,"left_eye_right_corner"), parseJsonByKey(featureJson,"right_eye_left_corner"));
            //计算鼻子之间的宽度
            double c4=FaceDistanceUtil.calcEyeBetwenDistance(parseJsonByKey(featureJson,"nose_left"), parseJsonByKey(featureJson,"nose_right"));
            //计算脸的宽度  
            double c5=FaceDistanceUtil.calcFaceContourDistance(parseJsonByKey(featureJson,"contour_left1"), parseJsonByKey(featureJson,"contour_right1"));
            //下巴到鼻子下方的高度
            double c6=FaceDistanceUtil.calcXiaBaToNoseDistance(parseJsonByKey(featureJson,"nose_contour_lower_middle"), parseJsonByKey(featureJson,"contour_chin"));
            //计算左眼大小
            double c7_left=FaceDistanceUtil.calcLeftEyeDistance(parseJsonByKey(featureJson,"left_eye_left_corner"), parseJsonByKey(featureJson,"left_eye_right_corner"));
            //计算右眼大小
            double c7_right=FaceDistanceUtil.calcRightEyeDistance(parseJsonByKey(featureJson,"right_eye_left_corner"), parseJsonByKey(featureJson,"right_eye_right_corner"));
            //计算嘴巴大小
            double c8=FaceDistanceUtil.calcMothDistance(parseJsonByKey(featureJson,"mouth_left_corner"), parseJsonByKey(featureJson,"mouth_right_corner"));
            //计算嘴巴出的face的大小
            double c9=FaceDistanceUtil.calcMothFaceDistance(parseJsonByKey(featureJson,"contour_left6"), parseJsonByKey(featureJson,"contour_right6"));
            
            return ScoreUtil.computeScore(json,c1, c2, c3, c4, c5, c6, c7_left, c7_right, c8, c9);
    }

 
    
     
    
    /**
     * 字符串转换为JSON对象
     * @param json
     * @param key
     * @return
     */
    private JSONObject parseJsonByKey(JSONObject json,String key){
        return (JSONObject) json.get(key);
    }
    
    
    
}
