/**
 * @author mlc
 * @date 2015年3月27日 上午10:58:57
 * @version 1.0
 */
package controllers.reply;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.base.dto.Statistics;
import assistant.app.echart.dto.Series;
import assistant.app.reply.config.ClickMsgConfig;
import assistant.app.reply.logic.IScanCountLogic;
import assistant.app.reply.logic.impl.ScanCountLogicImpl;
import controllers.base.BaseController;

public class ScanCountCtrl extends BaseController {

    private static final Logger LOG = LogCongfig.SCANCOUNTLOG;

    private static IScanCountLogic scanCountLogic = ScanCountLogicImpl.getInstance();

    // 按类型统计
    public static void count(long wechatId) {

        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }

        List<Statistics> text = scanCountLogic.countByType(wechatId, ClickMsgConfig.TEXT);
        List<Statistics> menu = scanCountLogic.countByType(wechatId, ClickMsgConfig.MENU);
        List<Statistics> location = scanCountLogic.countByType(wechatId, ClickMsgConfig.LOCATION);
        List<Statistics> all = scanCountLogic.countAllByDays(wechatId);
        List<String> categoryList = new ArrayList<String>();
        List<Series> seriesList = new ArrayList<Series>();

        // 文本点击
        List<Integer> dataText = new ArrayList<Integer>();
        // Series seriesText = new Series();
        for (int i = 0; i < text.size(); i++) {
            dataText.add(text.get(i).getCount());

        }
        // seriesText.setMarkPoint(0);
        // seriesText.setData(dataText);
        // seriesList.add(seriesText);

        // 菜单点击
        List<Integer> dataMenu = new ArrayList<Integer>();
        // Series seriesMenu = new Series();
        for (int i = 0; i < menu.size(); i++) {
            dataMenu.add(menu.get(i).getCount());
        }
        // seriesMenu.setData(dataMenu);
        // seriesList.add(seriesMenu);

        // 地理位置
        List<Integer> dataLocation = new ArrayList<Integer>();
        // Series seriesLocation = new Series();
        for (int i = 0; i < location.size(); i++) {
            dataLocation.add(location.get(i).getCount());
        }
        // seriesLocation.setData(dataMenu);
        // seriesList.add(seriesLocation);

        // 总请求数
        List<Integer> dataAll = new ArrayList<Integer>();
        // Series seriesAll = new Series();
        for (int i = 0; i < all.size(); i++) {
            dataAll.add(all.get(i).getCount());
            categoryList.add(all.get(i).getDays());
        }
        // seriesAll.setData(dataMenu);
        // seriesList.add(seriesAll);

        JSONObject obj = new JSONObject();
        obj.put("dataText", dataText);
        obj.put("dataMenu", dataMenu);
        obj.put("dataLocation", dataLocation);
        obj.put("dataAll", dataAll);
        obj.put("categoryList", categoryList);
        renderJsonAjaxResult(obj);

    }
}
