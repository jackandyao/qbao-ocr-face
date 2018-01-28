package com.qbao.reconginse.service.impl.card;

import com.qbao.reconginse.common.CommonService;
import com.qbao.reconginse.constant.OCRConstant;
import com.qbao.reconginse.service.ICardRecgService;
import com.qbao.reconginse.util.JsonChangeUtil;
/**
 *   驾驶证识别服务实例
     * @author 贾红平
     * 返回指定字段信息
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class DriverCardService extends CommonService implements ICardRecgService {

    public Object getResultObj(String imageUrl) {
        return JsonChangeUtil.getReusltObj(getJsonArray(imageUrl,OCRConstant.DRIVER_CARD), OCRConstant.DRIVER_CARD);
    }

}
