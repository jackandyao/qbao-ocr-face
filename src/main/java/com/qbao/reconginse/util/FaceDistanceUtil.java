package com.qbao.reconginse.util;

import com.alibaba.fastjson.JSONObject;

/**
 *   计算人脸关键特征点的距离
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class FaceDistanceUtil {
    /**
     * 根据坐标,计算亮点之间的距离
     * @param x1 横坐标
     * @param y1 纵坐标
     * @param x2 横坐标
     * @param y2 纵坐标
     * @return 0.0
     */
    private static double calcDistance(double x1,double y1,double x2,double y2){
        return MathUtil.pointsDistance(x1,y1,x2,y2);
    }
    
    /**
     * 计算两眉头间的距离
     * @return
     */
    public static double calcEyeBrowDistance(JSONObject right_eyebrow_left_corner,
            JSONObject left_eyebrow_right_corner){
       
        double right_eyebrow_left_corner_x=parseDoubleByKey(right_eyebrow_left_corner, "x");
        double right_eyebrow_left_corner_y=parseDoubleByKey(right_eyebrow_left_corner, "y");
       
       
        double left_eyebrow_right_corner_x=parseDoubleByKey(left_eyebrow_right_corner, "x");
        double left_eyebrow_right_corner_y=parseDoubleByKey(left_eyebrow_right_corner, "y");
        
        return calcDistance(left_eyebrow_right_corner_x, left_eyebrow_right_corner_y, 
                right_eyebrow_left_corner_x, right_eyebrow_left_corner_y);
    }
    
    /**
     * 计算眉毛中点到鼻子最低处的距离 
     * @return
     */
    public static double calcBrowToNoseDistance(JSONObject right_eyebrow_left_corner,
            JSONObject left_eyebrow_right_corner,JSONObject nose_contour_lower_middle){
        
        double right_eyebrow_left_corner_x=parseDoubleByKey(right_eyebrow_left_corner, "x");
        double right_eyebrow_left_corner_y=parseDoubleByKey(right_eyebrow_left_corner, "y");
      
        double left_eyebrow_right_corner_x=parseDoubleByKey(left_eyebrow_right_corner, "x");
        double left_eyebrow_right_corner_y=parseDoubleByKey(left_eyebrow_right_corner, "y");
        
        double right_left_x=(right_eyebrow_left_corner_x-left_eyebrow_right_corner_x)/2+left_eyebrow_right_corner_x;
        double right_left_y=(right_eyebrow_left_corner_y-left_eyebrow_right_corner_y)/2+left_eyebrow_right_corner_y;
        
        
        double nose_contour_lower_middle_x=parseDoubleByKey(nose_contour_lower_middle, "x");
        double nose_contour_lower_middle_y=parseDoubleByKey(nose_contour_lower_middle, "y");
        
        return calcDistance(nose_contour_lower_middle_x, nose_contour_lower_middle_y,right_left_x,right_left_y);
    }
    
    /**
     * 计算眼角之间的距离
     * @return
     */
    public static double calcEyeBetwenDistance( JSONObject left_eye_right_corner,JSONObject right_eye_left_corner){
          
        double right_eye_left_corner_x=parseDoubleByKey(right_eye_left_corner, "x");
        double right_eye_left_corner_y=parseDoubleByKey(right_eye_left_corner, "y");
        
        double left_eye_right_corner_x=parseDoubleByKey(left_eye_right_corner, "x");
        double left_eye_right_corner_y=parseDoubleByKey(left_eye_right_corner, "y");
        
        return calcDistance(left_eye_right_corner_x, left_eye_right_corner_y,right_eye_left_corner_x,right_eye_left_corner_y);
    }
    /**
     * 计算鼻子之间的宽度
     * @return
     */
    public static double calcNoseBetwenDisatance( JSONObject nose_left, JSONObject nose_right){
        
        double nose_left_x=parseDoubleByKey(nose_left, "x");
        double nose_left_y=parseDoubleByKey(nose_left, "x");
        
        
        double nose_right_x=parseDoubleByKey(nose_right, "x");
        double nose_right_y=parseDoubleByKey(nose_right, "y");
        return calcDistance(nose_left_x, nose_left_y,nose_right_x,nose_right_y);
    }
    /**
     * 计算脸的宽度
     * @return
     */
    public static double calcFaceContourDistance(JSONObject contour_left1,JSONObject contour_right1){
        double contour_left1_x=parseDoubleByKey(contour_left1, "x");
        double contour_left1_y=parseDoubleByKey(contour_left1, "y");
        
        double contour_right1_x=parseDoubleByKey(contour_right1, "x");
        double contour_right1_y=parseDoubleByKey(contour_right1, "y");
        
        return calcDistance(contour_left1_x, contour_left1_y,contour_right1_x,contour_right1_y);
    }
    /**
     * 计算下巴到鼻子下方的高度
     * @return
     */
    public static double calcXiaBaToNoseDistance( JSONObject nose_contour_lower_middle,JSONObject contour_chin){
        double contour_chin_x=parseDoubleByKey(contour_chin, "x");
        double contour_chin_y=parseDoubleByKey(contour_chin, "y");
        
        double nose_contour_lower_middle_x=parseDoubleByKey(nose_contour_lower_middle, "x");
        double nose_contour_lower_middle_y=parseDoubleByKey(nose_contour_lower_middle, "y");
        
        return calcDistance(contour_chin_x,contour_chin_y,nose_contour_lower_middle_x,nose_contour_lower_middle_y);
    }
    /**
     * 计算左眼的大小
     * 
     * @return
     */
    public static double calcLeftEyeDistance(JSONObject left_eye_left_corner ,JSONObject left_eye_right_corner){
        double left_eye_right_corner_x=parseDoubleByKey(left_eye_right_corner, "x");
        double left_eye_right_corner_y=parseDoubleByKey(left_eye_right_corner, "y");
        
        double left_eye_left_corner_x=parseDoubleByKey(left_eye_left_corner, "x");
        double left_eye_left_corner_y=parseDoubleByKey(left_eye_left_corner, "y");
        
        return calcDistance(left_eye_left_corner_x, left_eye_left_corner_y,left_eye_right_corner_x,left_eye_right_corner_y);
    }
    /**
     * 计算右眼的大小
     * 
     * @return
     */
    public static double calcRightEyeDistance(JSONObject right_eye_left_corner,JSONObject right_eye_right_corner){
        double right_eye_left_corner_x=parseDoubleByKey(right_eye_left_corner, "x");
        double right_eye_left_corner_y=parseDoubleByKey(right_eye_left_corner, "y");
        
        double right_eye_right_corner_x=parseDoubleByKey(right_eye_right_corner, "x");
        double right_eye_right_corner_y=parseDoubleByKey(right_eye_right_corner, "y");
        
        return calcDistance(right_eye_left_corner_x,right_eye_left_corner_y,right_eye_right_corner_x,right_eye_right_corner_y);
    }
    /**
     * 计算嘴巴大小
     * @return
     */
    public static double calcMothDistance(JSONObject mouth_left_corner,JSONObject mouth_right_corner){
        double mouth_left_corner_x=parseDoubleByKey(mouth_left_corner, "x");
        double mouth_left_corner_y=parseDoubleByKey(mouth_left_corner, "y");
        
        double mouth_right_corner_x=parseDoubleByKey(mouth_right_corner, "x");
        double mouth_right_corner_y=parseDoubleByKey(mouth_right_corner, "y");
        
        return calcDistance(mouth_left_corner_x,mouth_left_corner_y,mouth_right_corner_x,mouth_right_corner_y);
    }
    
    /**
     * 计算嘴巴出的face大小
     * @param mouth_left_corner
     * @param mouth_right_corner
     * @return
     */
    public static double calcMothFaceDistance(JSONObject contour_left6,JSONObject contour_right6){
        double contour_left6_x=parseDoubleByKey(contour_left6, "x");
        double contour_left6_y=parseDoubleByKey(contour_left6, "y");
        
        double contour_right6_x=parseDoubleByKey(contour_right6,"x");
        double contour_right6_y=parseDoubleByKey(contour_right6,"y");
        
        return calcDistance(contour_left6_x, contour_left6_y, contour_right6_x, contour_right6_y);
    }
    
    /**
     * 字符串转换为DOUBLE类型
     * @param json
     * @param key
     * @return
     */
    public static double parseDoubleByKey(JSONObject json,String key){
        return Double.parseDouble(json.getString(key).toString());
    }
}
