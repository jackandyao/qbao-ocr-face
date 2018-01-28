package face_api;

import java.net.URLEncoder;

import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.facade.RecongiseServiceFacade;
@SuppressWarnings("all")
public class ApiDemo {
    public static void main(String[] args)throws Exception {
//       
//          String url="http://imgserver.qbao.com/dfs/g1/M00/00/01/wKgO9lh3LCuAFIV3AACKapzk2nU010.jpg,"
//                  + "http://imgserver.qbao.com/dfs/g1/M00/00/01/wKgO9lh3LUeAfUQSAACR453cR2w591.jpg,"
//                  + "http://imgserver.qbao.com/dfs/g1/M00/00/01/wKgO9lh3LCuAFIV3AACKapzk2nU010.jpg,"
//                  + "http://imgserver.qbao.com/dfs/g1/M00/00/01/wKgO9lh3K3CAW-N-AAClU2s0Isw157.jpg"
//                  ;
//          String[]urls=url.split(",");
//           while(true){
//               for(int i=0;i<urls.length;i++){
//                   testIdCard(urls[i]);
//                   //testDrvierCard();
//                   //testVehicleCard();
//                   //testFaceCompare();
//                   //testFaceAnalyzer();
//                }
//           }
//        testIdCard("http://imgserver.qbao.com/dfs/group1/M00/00/02/rBAOjFiEcMSACgl4AAMfAlc4EyA297.jpg");
        testFaceAnalyzer();
    }
    
    private static void testIdCard(String idCardImage){
        Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
        System.out.println("身份证:"+idObj);
    }
    
    private static void testFaceAnalyzer()throws Exception{
        // http://img3.imgtn.bdimg.com/it/u=2212006985,627807064&fm=23&gp=0.jpg              
        // http://img0.imgtn.bdimg.com/it/u=796856992,3370474364&fm=23&gp=0.jpg
        String bhfaceImage="https://qn-message.qbcdn.com/c074ae552bd148979d5eb3ba9faff681";
//        String faceImage="https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1488186698&di=1c446c32ab123920f8d1fd087ce1e5b5&imgtype=jpg&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F6d81800a19d8bc3e1381315f8b8ba61ea9d3459d.jpg";
//        String faceImage="https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1488186923&di=471c08e29e2bd83c7e1e315783e34b10&imgtype=jpg&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F91529822720e0cf3e314786e0346f21fbf09aa47.jpg";
//        String faceImage= "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487934172137&di=3ead7442d4ef1981fd9f2e19d56775aa&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2F1%2F103%2F10343%2F1034314_500x500_0.jpg";
//        String faceImage= "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487940273192&di=204c0d9ea5f45daefe17f843d9e18fc2&imgtype=0&src=http%3A%2F%2Fimg.qt.baidu.com%2Fhiapk_news%2F201701%2F06%2F586f45318e9fc%257Ehiapk_autow600%257E.jpg";
//        String faceImage="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487940273191&di=223a5d52cafebecda2d751c6ebfab056&imgtype=0&src=http%3A%2F%2Fhiphotos.baidu.com%2Fdoc%2Fpic%2Fitem%2Fb999a9014c086e06000ff3e50b087bf40bd1cbfd.jpg";
        String faceImage= "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487942476631&di=e6e3248d9485c50996e383534a170a85&imgtype=0&src=http%3A%2F%2Fhiphotos.baidu.com%2Fdoc%2Fpic%2Fitem%2Fb999a9014c086e06000ff3e50b087bf40bd1cbfd.jpg";
        System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                OCRConstant.RECONGISE_TYPE_FACE,faceImage));
    }
    
    private static void testDrvierCard(){          
        // https://qn-message.qbcdn.com/42647f70ebea4ef3a60c1d58bc671b03
        // https://qn-message.qbcdn.com/174d5e8564f2486fa7f8588c687fbbeb
        String buhefImage="https://qn-message.qbcdn.com/c074ae552bd148979d5eb3ba9faff681";
        String driverImage="https://qn-message.qbcdn.com/174d5e8564f2486fa7f8588c687fbbeb";
        String bbImage="http://www.sfdai.com/data/upload/zb2.jpg";
        System.out.println("驾驶证:"+RecongiseServiceFacade.getRecongiseResult(OCRConstant.DRIVER_CARD,OCRConstant.RECONGISE_TYPE_CARD,bbImage));
    }
    
    private static void testVehicleCard(){
        // https://qn-message.qbcdn.com/c074ae552bd148979d5eb3ba9faff681
        // https://qn-message.qbcdn.com/e700b19dc193473db91e1450a5615753
        String buhfavehcileImage="https://qn-message.qbcdn.com/c074ae552bd148979d5eb3ba9faff681";
        String vehcileImage="https://qn-message.qbcdn.com/e700b19dc193473db91e1450a5615753";
        
        System.out.println("行驶证:"+RecongiseServiceFacade.getRecongiseResult(OCRConstant.VEHICLE_CARD,OCRConstant.RECONGISE_TYPE_CARD,vehcileImage));
    }
    
    
    public static void testFaceCompare(){
        String faceUrl1="http://imgserver.qbao.com/dfs/g1/M00/00/00/wKgO91h1oKSAXkFUAAfr7gABeDI001.jpg";
        String faceUrl2="http://imgserver.qbao.com/dfs/g1/M00/00/00/wKgO91h1-kWAIbvuAACdeWJsQnc883.jpg";
       
        String faceUrl3="https://qn-message.qbcdn.com/5ddcb6d9ed4c47aa837aa2412c9769b6";
        String faceUrl4="https://qn-message.qbcdn.com/c961822db46546b1b8fbe649afde5bc5";
        Object obj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_COMPARE,OCRConstant.RECONGISE_TYPE_FACE, faceUrl1,faceUrl2);
        System.out.println("人脸比较:"+obj);
    }
}
