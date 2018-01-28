package com.qbao.reconginse.util;

import com.alibaba.fastjson.JSONObject;
import com.qbao.reconginse.constant.OCRConstant;
@SuppressWarnings("all")
public class ScoreUtil {
    
    /**
     * 打印信息--表格
     * @param c0
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @param c5
     * @param c6
     * @param c7_left
     * @param c7_right
     * @param c8
     * @param c9
     */
    public static void printTable(double c0,double c1,double c2,double c3,double c4,double c5,double c6,double c7_left,double c7_right,double c8,double c9){
        StringBuffer sb = new StringBuffer();
        sb.append("微笑得分").append("\t")
        .append("眉头距离").append("\t")
        .append("眉毛中点到鼻子最低处的距离").append("\t")
        .append("眼角距离").append("\t")
        .append("鼻子宽度").append("\t")
        .append("脸的宽度  ").append("\t")
        .append("下巴到鼻子下方的高度").append("\t")
        .append("左眼").append("\t")
        .append("右眼").append("\t")
        .append("嘴巴").append("\t")
        .append("嘴巴出的脸的");
        
        System.err.println(sb.toString());
        
        System.out.println(parseInt(c0)+"\t"+parseInt(c1)+"\t"+parseInt(c2)+"\t\t\t"+parseInt(c3)+"\t"
                +parseInt(c4)+"\t"+parseInt(c5)+
                        "\t\t"+parseInt(c6)+"\t"+parseInt(c7_left)+"\t"+parseInt(c7_right)+"\t"+parseInt(c8)+"\t"
                +parseInt(c9));
     }
    
    /**
     * 打印信息--按行
     * @param c0
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @param c5
     * @param c6
     * @param c7_left
     * @param c7_right
     * @param c8
     * @param c9
     */
    public static void printInfo(double c0,double c1,double c2,double c3,double c4,double c5,double c6,double c7_left,double c7_right,double c8,double c9){
        System.err.println("微笑得分:"+parseInt(c0));
        System.err.println("眉头距离:"+parseInt(c1));
        System.err.println("眉毛中点到鼻子最低处的距离:"+parseInt(c2));
        System.err.println("眼角距离:"+parseInt(c3));
        System.err.println("鼻子宽度:"+parseInt(c4));
        System.err.println("脸的宽度:"+parseInt(c5));
        System.err.println("下巴到鼻子下方的高度:"+parseInt(c6));
        System.err.println("左眼:"+parseInt(c7_left));
        System.err.println("右眼:"+parseInt(c7_right));
        System.err.println("嘴巴:"+parseInt(c8));
        System.err.println("嘴巴出的脸的:"+parseInt(c9));
        
    }
    
    private static int parseInt(double value){
        return (int)value;
    }
    
    /**
     * 根据脸的评分 显示对应的评论信息
     * @param score
     * @return
     */
    public static String getFaceInfo(double score){
        if (score>0) {
            if (score>=94 && score<=100) {
                return "亲,你的美,已经无法用言语所能表达!";
            }
            else if(score>=87 && score<=94){
                return "亲,你长的真的好看,可以和明星比比了哦!";
            }
            else if(score>=82 && score <=87){
                return "亲,你的美,百看不厌，继续加油!";
            }
            else if(score>=72 && score<=82){
                return "亲,不要灰心,勉强还是可以看看的!";
            }
            else if(score>=67 && score<=72){
                return "亲,你虽然有点丑,但是丑的不太明显!";
            }
            else if(score>=62 && score<=67){
                return "亲,有钱吗,可以考虑去整整了!";
            }
            else{
                return "亲,你的丑已经吓死了很多人了,赶紧回家用镜子照照吧!";
            }
        }
        return "亲,你确定是你是人类吗";
    }
    
    /**
     * 计算颜值
     * @param c0
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @param c5
     * @param c6
     * @param c7_left
     * @param c7_right
     * @param c8
     * @param c9
     * @return
     */
    public static  JSONObject computeScore (JSONObject json,double c1,double c2,double c3,double c4
            ,double c5,double c6,double c7_left,double c7_right,double c8,double c9){
        double defaultScore=100.0;double faceScore=60;double bzScore=0.0;double yjScore=0.0;double zbScore=0.0;double xbScore=0.0;double mmScore=0.0;
        //计算微笑值==人微笑的时候应该比较甜美..所以这个因子对最终得分影响比较大...
        double c0=FaceDistanceUtil.parseDoubleByKey(json.getJSONObject("face_smile"), "value");
        String faceGender=json.getJSONObject("face_gender").getString("value");
        double faceAge=FaceDistanceUtil.parseDoubleByKey(json.getJSONObject("face_age"), "value");
        int commonDistance=25;
        //眼角距离为脸宽的1/5
        bzScore = abs((c3/c5)*100 - commonDistance);
        //鼻子宽度为脸宽的1/5
        yjScore = abs((c4/c5)*100 - commonDistance);
        //眼睛的宽度，应为同一水平脸部宽度的1/5
        double eyepj = (c7_left+c7_right)/2;
        zbScore = abs(eyepj/c5*100 - commonDistance);
        //理想嘴巴宽度应为同一脸部宽度的1/2
        double y = c8/c9;
        if(y>1/2){
            xbScore= abs(abs((c8/c9)*100 - 2*commonDistance)-c0/10);
        }else {
            xbScore = abs((c8 / c9) * 100 - 2 * commonDistance);
        }
        //下巴到鼻子下方的高度 == 眉毛中点到鼻子最低处的距离
//        double x = c6/c2;
//        mmScore = 50*(1-Math.pow(0.5,abs(x-1)/(x+1)));
        mmScore = abs((c6/c2)*25 - commonDistance);

        double sizeScore=0.0;
        //脸型高度与宽度比9/10
        double x = (c6+c2)/c5;
        if(x>=0.85 && x<=0.95) {
            sizeScore = abs(x * 54 - 2 * commonDistance);
        }else{
            sizeScore = abs(x * 108 - 4 * commonDistance);
        }

        double otherScore=bzScore+yjScore+zbScore+xbScore+mmScore+sizeScore;
        faceScore= MathUtil.round(defaultScore-otherScore,2);
        
        JSONObject resultObj=new JSONObject();
        
        JSONObject wdJson=new JSONObject();
        
        
        JSONObject scoreJson=new JSONObject();
        
        scoreJson.put("faceScore",parseDoubleByBigDecimal(faceScore));
        
        JSONObject obj1=new JSONObject();
        obj1.put(OCRConstant.FACE_SUM_SCORE, parseDoubleByBigDecimal(bzScore));
        obj1.put(OCRConstant.FACE_FALG, "鼻子");
        
        JSONObject obj2=new JSONObject();
        obj2.put(OCRConstant.FACE_SUM_SCORE, parseDoubleByBigDecimal(yjScore));
        obj2.put(OCRConstant.FACE_FALG, "眼睛");
        
        JSONObject obj3=new JSONObject();
        obj3.put(OCRConstant.FACE_SUM_SCORE, parseDoubleByBigDecimal(zbScore));
        obj3.put(OCRConstant.FACE_FALG, "嘴巴");
        JSONObject obj4=new JSONObject();
        obj4.put(OCRConstant.FACE_SUM_SCORE, parseDoubleByBigDecimal(xbScore));
        obj4.put(OCRConstant.FACE_FALG, "下巴");
        
        JSONObject obj5=new JSONObject();
        obj5.put(OCRConstant.FACE_SUM_SCORE, parseDoubleByBigDecimal(mmScore));
        obj5.put(OCRConstant.FACE_FALG, "眉毛");
      
        wdJson.put("bzScore", obj1);
        wdJson.put("yjScore", obj2);
        wdJson.put("zbScore", obj3);
        wdJson.put("xbScore", obj4);
        wdJson.put("mmScore", obj5);
        
        
        resultObj.put("scoreJson", scoreJson);
        resultObj.put("wdJson", wdJson);
        return resultObj;
    }
    
    /**
     * 判断性别
     * @param value
     * @return
     */
    private static String parseGender(String value){
        return value.equals("Female")?"女":"男";
    }
    
    /**
     * 格式化数字
     * @param value
     * @return
     */
    public static double parseDoubleByBigDecimal(Object value) {
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
            String val = df.format(value);
            if (val != null && !val.equals(null) && !val.equals("")) {
                return Double.parseDouble(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * 求绝对值
     * @param value
     * @return
     */
    private static double abs(double value){
        return MathUtil.abs(value);
    }
    
  
     
    
    /**
     * 字符串转换为JSON对象
     * @param json
     * @param key
     * @return
     */
    private static JSONObject parseJsonByKey(JSONObject json,String key){
        return (JSONObject) json.get(key);
    }
    
    

    private static double calScore(double x,double y,double z){
       return  Math.exp(abs( x-y)/y*(z));
    }
    
    
}
