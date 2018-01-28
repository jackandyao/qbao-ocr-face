package com.qbao.reconginse.service.impl.card;

import com.alibaba.fastjson.JSONArray;
import com.qbao.reconginse.common.CommonService;
import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.service.ICardRecgService;
import com.qbao.reconginse.util.JsonChangeUtil;
/**
 *  
     * @author 贾红平
     * 身份证识别服务
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class IdCardService extends CommonService implements ICardRecgService {
 
    
    public Object getResultObj(String imageUrl) {
        JSONArray array=getJsonArray(imageUrl,OCRConstant.ID_CARD);
        return JsonChangeUtil.getReusltObj(array, OCRConstant.ID_CARD);
    }

}
