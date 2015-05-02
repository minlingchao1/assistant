/**
 * @Project:assistant
 * @Title: LBSInfoCtrl.java
 * @date: 2015-3-6 下午4:36:46
 * @version 1.0
 */
package controllers.lbs;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.lbs.logic.ISellerLogic;
import assistant.app.lbs.logic.impl.SellerLogicImpl;
import assistant.app.lbs.model.Seller;
import controllers.base.BaseController;

/**
 * @ClassName LBSInfoCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-6 下午4:36:46
 */
public class LBSInfoCtrl extends BaseController {

    public static final Logger LOG = LogCongfig.LBSLOG;

    private static ISellerLogic sellerLogic = SellerLogicImpl.getInstance();

    // 添加信息
    public static void addLBSInfo(Long wechatId, String sellerName, String descr, String prov, String city,
            String dist, String category, String address, String mobile, String img, String lngLat) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        Long userId = getMerchantId();
        if (sellerLogic.addLBSInfo(userId, wechatId, sellerName, descr, prov, city, dist, category, address, mobile,
                img, lngLat)) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    // 获取列表
    public static void getLBSInfoList(Long wechatId) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        List<Seller> sellers = sellerLogic.getList(wechatId);
        renderJsonAjaxResult(sellers);
    }

}
