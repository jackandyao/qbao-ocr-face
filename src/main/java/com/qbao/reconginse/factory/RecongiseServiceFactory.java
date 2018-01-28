package com.qbao.reconginse.factory;

import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.service.ICardRecgService;
import com.qbao.reconginse.service.IFaceRecgService;
import com.qbao.reconginse.service.cmp.ClassInstanceCmp;
import com.qbao.reconginse.service.impl.card.DriverCardService;
import com.qbao.reconginse.service.impl.card.IdCardService;
import com.qbao.reconginse.service.impl.card.VehicleCardService;
import com.qbao.reconginse.service.impl.face.FaceCompareService;
import com.qbao.reconginse.service.impl.face.FaceAnalyzerService;

/**
 *   封装识别服务工厂
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class RecongiseServiceFactory {
    
    /**
     * 根据业务标志 获取具体的业务实现实例
     * @param type
     * @return
     */
    public static  ICardRecgService getCardServiceInstance(String type){
           ICardRecgService service=null;
           if (type!=null) {
              if (type.equals(OCRConstant.ID_CARD)) {
                  service= new IdCardService();
              }
              if (type.equals(OCRConstant.DRIVER_CARD)) {
                  service= new DriverCardService();
              }
              if (type.equals(OCRConstant.VEHICLE_CARD)) {
                  service= new VehicleCardService();
              }
              
          }
          return service;
    }
    
    /**
     * 获取人脸识别的具体实例服务
     * @param type
     * @return
     */
    public static IFaceRecgService getFaceServiceInstance(String type){
        IFaceRecgService faceRecgService=null;
        if (type!=null) {
            if (type.equals(OCRConstant.FACE_ANAYLAYZER)) {
                faceRecgService=new FaceAnalyzerService();
            }
            if (type.equals(OCRConstant.FACE_COMPARE)) {
                faceRecgService=new FaceCompareService();
            }
        }
        return faceRecgService;
    }
    
    
}
