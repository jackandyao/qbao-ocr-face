package com.qbao.reconginse.service.impl.card;

import com.qbao.reconginse.common.CommonService;
import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.service.ICardRecgService;
import com.qbao.reconginse.util.JsonChangeUtil;
/**
 *   行驶证识别服务实例
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class VehicleCardService extends CommonService implements ICardRecgService {

    public Object getResultObj(String imageUrl) {
        return JsonChangeUtil.getReusltObj(getJsonArray(imageUrl,OCRConstant.VEHICLE_CARD), OCRConstant.VEHICLE_CARD);
    }

}
