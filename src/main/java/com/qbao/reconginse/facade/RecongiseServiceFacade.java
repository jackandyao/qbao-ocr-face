package com.qbao.reconginse.facade;

import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.factory.RecongiseServiceFactory;

/**
 *   封装对外提供服务接口的面板
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class RecongiseServiceFacade {
    
    /**
     * 
     * @param serviceType: id_card(身份证识别),driver_card(驾驶证识别),vehicle_card(行驶证识别) face_analyzer(人脸分析,获取关键特征点坐标,用来做颜值评分), 
     *                    face_compare(人脸比较,用来比较两个照片是否属于同一个人)
     * @param recongiseType :rec_card(调用卡片识别),rec_face(人脸识别)
     * @param imageUrl
     * @return
     */
    public static Object getRecongiseResult(String serviceType,String recongiseType,Object...imageUrl){
        if (recongiseType!=null) {
            if (recongiseType.equals(OCRConstant.RECONGISE_TYPE_CARD)) {
                return getCardRecongiseResult(serviceType, imageUrl[0].toString());
            }
            if (recongiseType.equals(OCRConstant.RECONGISE_TYPE_FACE)) {
                return getFaceRecongiseResult(serviceType, imageUrl);
            }
        }
        return null;
    }
    
    /**
     * 证件识别API
     * @param imageUrl
     * @param type  id_card(身份证识别),driver_card(驾驶证识别),vehicle_card(行驶证识别)
     * @return
     */
    private static Object getCardRecongiseResult(String type,String imageUrl){
        return RecongiseServiceFactory.getCardServiceInstance(type).getResultObj(imageUrl);
    }
    
    /**
     * 人脸识别API
     * @param imageUrl
     * @param type face_analyzer(人脸分析,获取关键特征点坐标,用来做颜值评分), face_compare(人脸比较,用来比较两个照片是否属于同一个人)
     * @return
     */
    private static Object getFaceRecongiseResult(String type,Object... imageUrl){
        return RecongiseServiceFactory.getFaceServiceInstance(type).getResultObj(imageUrl);
    }
}
