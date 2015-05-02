/**
 * @author mlc
 * @date 2015年3月27日 上午10:47:26
 * @version 1.0
 * 浏览量统计
 */
package assistant.app.reply.dal;

import java.util.List;

import assistant.app.base.dto.Statistics;
import assistant.app.reply.model.ScanCount;

public interface IScanCountDao {

    /**
     * 添加
     * 
     * @param scanCount
     * @return
     */
    public long insert(ScanCount scanCount);

    /**
     * 按类型统计
     * 
     * @param wechatId
     * @param type
     * @return
     */
    public List<Statistics> countByType(long wechatId, int type);

    /**
     * 统计总浏览量，按天
     * 
     * @param wechatId
     * @return
     */
    public List<Statistics> countAllByDays(long wechatId);

}
