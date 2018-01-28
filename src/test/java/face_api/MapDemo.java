package face_api;

import java.util.Random;

public class MapDemo {
    public static void main(String[] args) {
        String str="中国123ABx??";
        //String str = "欢迎光临我的JAVA世纪网www.java123.net";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i)+"").getBytes().length>1) {
                sb.append(str.charAt(i));
            }
        }
        System.out.println(sb.toString().replace("牌", ""));
    }
}
