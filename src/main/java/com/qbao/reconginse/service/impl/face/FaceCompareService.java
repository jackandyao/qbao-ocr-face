package com.qbao.reconginse.service.impl.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qbao.reconginse.common.CommonService;
import com.qbao.reconginse.conf.ConfigurationManager;
import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.service.IFaceRecgService;
import com.qbao.reconginse.util.HttpClientUtil;
import com.qbao.reconginse.util.UrlStrUtil;

/**
 *   
     * @author 贾红平
     * 人脸比较-比较两张照片是否属于同一个人
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class FaceCompareService extends CommonService implements IFaceRecgService {

    
    public Object getResultObj(Object... imageUrl) {
        JSONObject json=new JSONObject();
        Object obj=HttpClientUtil.sendPost(ConfigurationManager.getProperty(OCRConstant.FACE_COMPARE_URL),UrlStrUtil.getHttpUrl(null,OCRConstant.FACE_COMPARE, imageUrl[0].toString(),imageUrl[1].toString()), OCRConstant.CHARSET);
        if (obj!=null) {
            JSONObject resultJson=(JSONObject) JSON.parse(obj.toString());
            //有值返回
            if (!resultJson.containsKey(OCRConstant.ERROR_KEY)) {
                if (resultJson.containsKey(OCRConstant.FACE_CONDIENCE)) {
                    double confidence=resultJson.getDoubleValue(OCRConstant.FACE_CONDIENCE);
                    json.put(OCRConstant.FACE_SAME_FALG, isSameFace(confidence));
                    json.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
                    json.put(OCRConstant.HTTP_INFO, getCompareInfo(isSameFace(confidence)));
                    json.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
                }
                else{
                    json.put(OCRConstant.HTTPS_STATUS, OCRConstant.SUCCESS_CODE_RESULT);
                    json.put(OCRConstant.REPONSE_STATUS, OCRConstant.SUCCESS_CODE_IMAGE_FORMATE);
                    json.put(OCRConstant.ERROR_KEY,  "对不起,你们二位应该不是同一个世界的人,没有任何的可比较之处");
                }
            }
            //请求出现其它错误
            else{
                
                json.put(OCRConstant.HTTPS_STATUS, OCRConstant.FAIL_CODE);
                json.put(OCRConstant.ERROR_KEY,  errorMap.get(resultJson.get(OCRConstant.ERROR_KEY)));
                json.put(OCRConstant.REPONSE_STATUS,getReponseCode(resultJson.get(OCRConstant.ERROR_KEY).toString()));
            }
        }
        else{
            json.put(OCRConstant.ERROR_KEY, "对不起,服务请求有问题,请稍后重新发送请求");
            json.put(OCRConstant.HTTPS_STATUS, OCRConstant.FAIL_CODE);
            json.put(OCRConstant.REPONSE_STATUS, OCRConstant.FAIL_CODE);
        }
        return json;
    }
    
    /**
     * 根据置信度来判断是否属于同一个人
     * @param confidence
     * @return
     */
    private boolean isSameFace(double confidence){
        double min_score=Double.valueOf(ConfigurationManager.getProperty(OCRConstant.FACE_MIN_CONDIENCE));
        return confidence>min_score?true:false;
    }
    
    /**
     * 返回比较结果文字提示
     * @param isFace
     * @return
     */
    private String getCompareInfo(boolean isFace){
        return isFace==false?"对不起,你们并不是同一个人哦":"恭喜,你们长得完全是一个模子里面刻出来的哦";
    }

}
