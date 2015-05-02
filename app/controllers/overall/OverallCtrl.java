/**
 * @Project:assistant
 * @Title: OverallCtrl.java
 * @date: 2015-3-5 下午4:49:50
 * @version 1.0
 */
package controllers.overall;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.overall.logic.IOverallLogic;
import assistant.app.overall.logic.impl.OverallLogicImpl;
import assistant.app.overall.model.Overall;
import controllers.base.BaseController;

/**
 * @ClassName OverallCtrl
 * @Description 全景图操作
 * @author minlingchao
 * @date 2015-3-5 下午4:49:50
 */
public class OverallCtrl extends BaseController {

    public static final Logger LOG = LogCongfig.OVERALLLOG;

    private static IOverallLogic overallLogic = OverallLogicImpl.getInstance();

    // 添加全景图
    public static void addOverall(long wechatId, String name, String keyword, String descr, String pageImg,
            String left, String right, String top, String bottom, String front, String behind, String musicSet,
            String backMusic) {

        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }

        long userId = getMerchantId();
        if (overallLogic.addOverall(userId, wechatId, name, keyword, descr, pageImg, left, right, top, bottom, front,
                behind, musicSet, backMusic)) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    // 获取全景图列表

    public static void getList(long wechatId) {
        LOG.warn("overall wechatId:{}", wechatId);
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }

        List<Overall> overalls = overallLogic.getList(wechatId);
        renderJsonAjaxResult(overalls);
    }
}
