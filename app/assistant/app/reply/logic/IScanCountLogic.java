/**
 * @author mlc
 * @date 2015年3月27日 上午10:55:14
 * @version 1.0
 */
package assistant.app.reply.logic;

import java.util.List;

import assistant.app.base.dto.Statistics;

public interface IScanCountLogic {

    /**
     * 添加
     * 
     * @param scanCount
     * @return
     */
    public long insert(long wechatId, int type, String clickTime);

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
