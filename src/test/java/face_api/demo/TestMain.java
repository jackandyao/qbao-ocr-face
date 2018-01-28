package face_api.demo;

import java.util.HashMap;
import java.util.Map;
//对接口进行测试
public class TestMain {
    
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;
	
	public TestMain(){
		httpClientUtil = new HttpClientUtil();
	}
	
	public void test2( ){
	      
        String httpOrgCreateTest ="https://api-cn.faceplusplus.com/imagepp/beta/recognizetext";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("api_key","ZumagZvqO4Vy2YOx2a8VHzPhoE959q_8");
        createMap.put("api_secret","kzZDpyGPLfUYoRB1GiOlVsh2LCPjyWmB");
         
        createMap.put("image_url","https://qn-message.qbcdn.com/65ea360652124326b6677eae8e679773");
     
      
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
        System.out.println(httpOrgCreateTestRtn);
 
    }
	
	public void test1( ){
      
	    String httpOrgCreateTest ="https://api-cn.faceplusplus.com/imagepp/beta/recognizetext";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("api_key","QhZ2IWxkxX9U1GkGQrPDjHdtfaOEZ566");
        createMap.put("api_secret","gY5B7QcUJV-nOVwz2yOw6D0PfVPTrdUF");
         
        createMap.put("image_url","https://qn-message.qbcdn.com/65ea360652124326b6677eae8e679773");
     
      
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
        System.out.println(httpOrgCreateTestRtn);
 
    }
	
	 
	public static void main(String[] args){
 
		TestMain main = new TestMain();
 
		main.test1();
	}
}