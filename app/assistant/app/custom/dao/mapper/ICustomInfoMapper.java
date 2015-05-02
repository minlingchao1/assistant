/**
 * @Project:assistant
 * @Title: ICustomInfoMapper.java
 * @date: 2015-2-13 下午5:52:06
 * @version 1.0
 */
package assistant.app.custom.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import assistant.app.base.dto.Statistics;
import assistant.app.custom.model.CustomInfo;

/**
 * @ClassName ICustomInfoMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午5:52:06
 */
public interface ICustomInfoMapper {

    public long insert(CustomInfo customInfo);

    public long update(CustomInfo customInfo);

    public long countByWechatId(Long wechatId);

    public long countByOpenId(String openId);

    public List<CustomInfo> getList(long wechatId);

    public List<Statistics> countByDays(long wechatId);

    public CustomInfo showDetail(long id);

    public List<CustomInfo> screen(@Param("startTime") long startTime, @Param("endTime") long endTime);

    public long countScreen(@Param("startTime") long startTime, @Param("endTime") long endTime);
}
