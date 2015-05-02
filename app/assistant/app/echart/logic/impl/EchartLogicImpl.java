/**
 * @author mlc
 * @date 2015年3月25日 上午9:33:33
 * @version 1.0
 */
package assistant.app.echart.logic.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.echart.dto.Data;
import assistant.app.echart.dto.MarkLine;
import assistant.app.echart.dto.MarkPoint;
import assistant.app.echart.dto.Series;
import assistant.app.echart.logic.IEchartLogic;

public class EchartLogicImpl implements IEchartLogic {

    public static final Logger LOG = LogCongfig.CUSTOMLOG;

    private static EchartLogicImpl instance = new EchartLogicImpl();

    private EchartLogicImpl() {

    }

    public static EchartLogicImpl getInstance() {
        return instance;
    }

    @Override
    public JSONObject addSeries(List<String> categoryList, List<Series> seriesList) {
        // 图例
        List<String> legendList = new ArrayList<String>();
        // 设置图例名称
        for (Series series : seriesList) {
            legendList.add(series.getName());
        }

        JSONObject obj = new JSONObject();
        obj.put("categoryList", categoryList);
        obj.put("legendList", legendList);
        obj.put("seriesList", seriesList);
        return obj;
    }

    @Override
    public MarkPoint addMarkPoint(Data... datas) {

        List<Data> markPointDatas = new ArrayList<Data>();

        for (Data data : datas) {
            markPointDatas.add(data);
        }
        MarkPoint markPoint = new MarkPoint();
        markPoint.setData(markPointDatas);

        return markPoint;
    }

    @Override
    public MarkLine addMarkLine(Data... datas) {

        List<Data> markLineDatas = new ArrayList<Data>();

        for (Data data : datas) {
            markLineDatas.add(data);
        }

        MarkLine markLine = new MarkLine();
        markLine.setData(markLineDatas);
        return markLine;
    }

}
