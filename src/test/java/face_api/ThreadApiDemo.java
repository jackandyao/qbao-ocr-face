package face_api;

import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.facade.RecongiseServiceFacade;

public class ThreadApiDemo {
    public static void main(String[] args) {
       final String faceImage="https://qn-message.qbcdn.com/9fec79c4fbb3474fa1a098aae3a5f304";
       final String idCardImage="https://qn-message.qbcdn.com/609c78d1aa834996af4bae0903e84ce0";

            new Thread(new Runnable() {
                public void run() {

                    for (int i = 0; i < 300; i++) {
                      System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                    }
                   
//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);
                   
                }
            }).start();


        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 300; i++) {
                    System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                }

//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 300; i++) {
                    System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                }

//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 300; i++) {
                    System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                }

//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 300; i++) {
                    System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                }

//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);

            }
        }).start();


        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 300; i++) {
                    System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                }

//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);

            }
        }).start();


        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 300; i++) {
                    System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                }

//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 300; i++) {
                    System.out.println(RecongiseServiceFacade.getRecongiseResult(OCRConstant.FACE_ANAYLAYZER,
                            OCRConstant.RECONGISE_TYPE_FACE, faceImage));
                }

//                     Object idObj=RecongiseServiceFacade.getRecongiseResult(OCRConstant.ID_CARD,OCRConstant.RECONGISE_TYPE_CARD,idCardImage);
//                     System.out.println("身份证:"+idObj);

            }
        }).start();

    }
}
