/**
 * @author mlc
 * @date 2015年3月25日 上午9:37:40
 * @version 1.0
 */
package assistant.app.echart.logic;

import java.util.List;

import net.sf.json.JSONObject;
import assistant.app.echart.dto.Data;
import assistant.app.echart.dto.MarkLine;
import assistant.app.echart.dto.MarkPoint;
import assistant.app.echart.dto.Series;

public interface IEchartLogic {

    /**
     * 添加图表
     */
    public JSONObject addSeries(List<String> categoryList, List<Series> seriesList);

    /**
     * 添加MarkPoint
     */
    public MarkPoint addMarkPoint(Data... datas);

    /**
     * 添加MarkLine
     */
    public MarkLine addMarkLine(Data... datas);
}
