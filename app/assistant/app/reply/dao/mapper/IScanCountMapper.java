/**
 * @author mlc
 * @date 2015年3月27日 上午10:29:39
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import assistant.app.base.dto.Statistics;
import assistant.app.reply.model.ScanCount;

public interface IScanCountMapper {

    public long insert(ScanCount scanCount);

    public List<Statistics> countByType(@Param("wechatId") long wechatId, @Param("type") int type);

    public List<Statistics> countAllByDays(long wechatId);

}
