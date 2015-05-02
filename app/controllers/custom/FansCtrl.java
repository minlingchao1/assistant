/**
 * @Project:assistant
 * @Title: FansCtrl.java
 * @date: 2015-3-2 下午11:28:56
 * @version 1.0
 */
package controllers.custom;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.base.dto.Statistics;
import assistant.app.custom.logic.ICustomInfoLogic;
import assistant.app.custom.logic.impl.CustomInfoLogicImpl;
import assistant.app.custom.model.CustomInfo;
import assistant.app.echart.dto.Data;
import assistant.app.echart.dto.MarkLine;
import assistant.app.echart.dto.MarkPoint;
import assistant.app.echart.dto.Series;
import assistant.app.echart.logic.IEchartLogic;
import assistant.app.echart.logic.impl.EchartLogicImpl;
import controllers.base.BaseController;

/**
 * @ClassName FansCtrl
 * @Description 粉丝信息管理
 * @author minlingchao
 * @date 2015-3-2 下午11:28:56
 */
public class FansCtrl extends BaseController {

    private static ICustomInfoLogic customInfoLogic = CustomInfoLogicImpl.getInstance();

    private static IEchartLogic echartLogic = EchartLogicImpl.getInstance();

    public static void getFansList(long wechatId) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        List<CustomInfo> customList = customInfoLogic.getList(wechatId);
        renderJsonAjaxResult(customList);
    }

    public static void showDetail(long id) {
        CustomInfo customInfo = customInfoLogic.showDetail(id);
        renderJsonAjaxResult(customInfo);
    }

    public static void countScreen(int type) {
        long count = customInfoLogic.countScreen(type);
        renderJsonAjaxResult(count);
    }

    // 获取关注用户总数
    public static void countByWechatId(Long wechatId) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        long count = customInfoLogic.countByWechatId(wechatId);
        renderJsonAjaxResult(count);
    }

    // 统计日关注数
    public static void countByDays(Long wechatId) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        List<Statistics> statistics = customInfoLogic.countByDays(wechatId);
        List<String> categoryList = new ArrayList<String>();

        List<Integer> data = new ArrayList<Integer>();
        List<Series> seriesList = new ArrayList<Series>();

        // 设置显示最大/最小/平均值
        Data max = new Data("max", "最大值");
        Data min = new Data("min", "最小值");
        Data average = new Data("average", "平均值");

        MarkPoint markPoint = echartLogic.addMarkPoint(max, min);
        MarkLine markLine = echartLogic.addMarkLine(average);

        // // 添加关注人数
        Series series = new Series();
        series.setName("关注用户数");
        series.setType("line");
        series.setMarkPoint(markPoint);
        for (int i = 0; i < statistics.size(); i++) {
            data.add(statistics.get(i).getCount());
            categoryList.add(statistics.get(i).getDays());
        }
        // data.add(100);
        // categoryList.add("2015-02-22");
        //
        // data.add(500);
        // categoryList.add("2015-02-26");
        //
        // data.add(50);
        // categoryList.add("2015-03-01");
        //
        // data.add(700);
        // categoryList.add("2015-03-02");

        series.setData(data);
        seriesList.add(series);

        JSONObject obj = echartLogic.addSeries(categoryList, seriesList);

        renderJsonAjaxResult(obj);
    }
}
