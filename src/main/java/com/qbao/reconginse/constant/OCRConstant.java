package com.qbao.reconginse.constant;
/**
 *   OCR识别会用到的相关常量信息
     * @author 贾红平
     *
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class OCRConstant {
     
    //图片识别参数开始
    public static final String IMAGE_RECONGINSE_API_KEY="image_key";
    public static final String IMAGE_RECONGINSE_API_SECRET="image_sceret";
    public static final String IMAGE_RECONGINSE_LAND_MARK="image_mark";
    public static final String IMAGE_RECONGINSE_ATTRIBUTES="image_attributes";
    public static final String IMAGE_RECONGINSE_URL="image_url";
    //图片识别参数结束
    
    //身份证识别参数开始
    public static final String ID_CARD_RECONGINSE_API_KEY="id_card_key";
    public static final String ID_CARD_RECONGINSE_API_SECRET="id_card_sceret";
    public static final String ID_CARD_RECONGINSE_LEGITITY="id_card_lagitity";
    public static final String ID_CARD_RECONGINSE_URL="id_card_url";
    //身份证识别参数结束
    
    //驾驶证识别参数开始
    public static final String VEHICLE_CARD_RECONGINSE_API_KEY="vehicle_card_key";
    public static final String VEHICLE_CARD_RECONGINSE_API_SECRET="vehicle_card_sceret";
    public static final String VEHICLE_CARD_RECONGINSE_URL="vehicle_card_url";
     //驾驶证识别参数结束
    
    //行驶证识别参数开始
    public static final String DRIVER_CARD_RECONGINSE_API_KEY="driver_card_key";
    public static final String DRIVER_CARD_RECONGINSE_API_SECRET="driver_card_sceret";
    public static final String DRIVER_CARD_RECONGINSE_API_URL="driver_card_url";
    //行驶证识别参数结束
    
    // 服务工厂识别标志
    public static final String ID_CARD="id_card";
    public static final String DRIVER_CARD="driver_card";
    public static final String VEHICLE_CARD="vehicle_card";
    public static final String FACE_ANAYLAYZER="face_analyzer";
    public static final String FACE_COMPARE="face_compare";
    
    //服务请求可选参数
    public static final String ID_CARD_LEGAITITY="legality";
    public static final String IMAGE_ATTRIBUTES="return_attributes";
    public static final String IMAGE_MARK="return_landmark";
    public static final String API_KEY="api_key";
    public static final String API_SCERET="api_secret";
    public static final String IMAGE_URL="image_url";
    public static final String IMAGE_URL1="image_url1";
    public static final String IMAGE_URL2="image_url2";
    public static final String CHARSET="utf-8";
    public static final String IS_RESULT_ALL="is_all";
    public static final String RESULT_KEY_CARDS="cards";
    public static final String RESULT_KEY_FACES="faces";
    
    public static final String CARD_PARAM="card_param";
    public static final String ERROR_KEY="error_message";
    public static final String HTTPS_STATUS="http_status";
    public static final String REPONSE_STATUS="response_status";
    //请求成功,且有正常的值返回
    public static final String SUCCESS_CODE_RESULT="1";
    //请求成功,但是上传的图片格式有问题
    public static final String SUCCESS_CODE_IMAGE_FORMATE="2";
    //请求成功,但是用户填写的参数有各种各样的错误
    public static final String SUCCESS_CODE_ERROR_PARAM="3";
    //请求成功,用户的请求超过并发连接数
    public static final String SUCCESS_CODE_CONERENCY="4";
    
    public static final String FAIL_CODE="0";
    
    public static final String HTTP_INFO="http_info";
    public static final String FACE_INFO = "face_info";
    public static final String FACE_SCORE="sum_score";
    public static final String FACE_DETAIL_SCORE="detalilScore";
    public static final String USER_NAME="user_name";
    public static final String ID_CARD_NUMBER="id_card";
    public static final String CAR_CODE="car_code";
    public static final String CAR_MODEL="car_model";
    public static final String COMMON_ERROR_MESSAGE="对不起您上传的照片格式不正确,请重新上传";
    public static final String SERVICE_TYPE_CARD="service_card";
    public static final String SERVICE_TYPE_FACE="service_face";
    public static final String FACE_COMPARE_URL="face_compare_url";
    public static final String FACE_CONDIENCE="confidence";
    public static final String FACE_SAME_FALG="face_samle_falg";
    public static final String FACE_MIN_CONDIENCE="face_min_confidence";
    
    public static final String RECONGISE_TYPE_CARD="rec_card";
    public static final String RECONGISE_TYPE_FACE="rec_face";
    
    
    public static final String FACE_SUM_SCORE="score";
    public static final String FACE_FALG="falg";
}
