/**
 * @Project:assistant
 * @Title: ICustomInfoDao.java
 * @date: 2015-2-13 下午5:58:39
 * @version 1.0
 */
package assistant.app.custom.dal;

import java.util.List;

import assistant.app.base.dto.Statistics;
import assistant.app.custom.model.CustomInfo;

/**
 * @ClassName ICustomInfoDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午5:58:39
 */
public interface ICustomInfoDao {

    /**
     * 添加
     */
    public long insert(CustomInfo customInfo);

    /**
     * 更新
     */
    public long update(CustomInfo customInfo);

    /**
     * 根据wehcatId查找
     */
    public long countByWechatId(Long wechatId);

    /**
     * 根据openId查找
     */
    public long countByOpenId(String openId);

    /**
     * 根据日统计关注者
     */
    public List<Statistics> countByDays(Long wechatId);

    /**
     * 获取关注用户列表
     */
    public List<CustomInfo> getList(long wechatId);

    /**
     * 用户信息详情
     */
    public CustomInfo showDetail(long id);

    /**
     * 日期查询
     */
    public List<CustomInfo> screen(long startTime, long endTime);

    /**
     * 日期查询客户数
     */
    public long countScreen(long startTime, long endTime);

}
