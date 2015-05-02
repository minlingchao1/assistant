/**
 * @Project:assistant
 * @Title: QrcodeCtrl.java
 * @date: 2015-2-12 下午6:09:18
 * @version 1.0
 */
package controllers.qrcode;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.qrcode.logic.IQrcodeLogic;
import assistant.app.qrcode.logic.impl.QrcodeLogicImpl;
import assistant.app.qrcode.model.Qrcode;
import controllers.base.BaseController;

/**
 * @ClassName QrcodeCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-12 下午6:09:18
 */
public class QrcodeCtrl extends BaseController {

    public static final Logger LOG = LogCongfig.QRCODELOG;

    private static IQrcodeLogic qrcodeLogic = QrcodeLogicImpl.getInstance();

    public static void getQrcodeList(Long wechatId) {
        List<Qrcode> qrcodes = qrcodeLogic.findByWechatId(wechatId);
        renderJsonAjaxResult(qrcodes);
    }

    public static void saveQrcodeInfo(Long wechatId, String sceneName, int sceneId, String actionName,
            int expireSeconds, String actionInfo) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        if (qrcodeLogic.createQrcode(request, wechatId, expireSeconds, sceneName, actionName, sceneId)) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }
}
