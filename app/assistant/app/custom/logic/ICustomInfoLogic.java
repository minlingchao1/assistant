/**
 * @Project:assistant
 * @Title: ICustomInfoLogic.java
 * @date: 2015-2-13 下午6:09:47
 * @version 1.0
 */
package assistant.app.custom.logic;

import java.util.List;

import assistant.app.base.dto.Statistics;
import assistant.app.custom.model.CustomInfo;

/**
 * @ClassName ICustomInfoLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午6:09:47
 */
public interface ICustomInfoLogic {

    /**
     * 添加
     */
    public long insert(CustomInfo customInfo);

    /**
     * 获取关注用户列表
     */
    public String getCustomList(Long wechatId, String accessToken, String nextOpenId);

    /**
     * 获取并保存用户基本信息
     */
    public long saveCustomInfo(Long wechatId, String accessToken, String openId);

    /**
     * 更新
     */
    public long update(CustomInfo customInfo);

    /**
     * 根据openId查找
     */
    public long countByWechatId(Long wechatId);

    /**
     * 根据openId查找
     */
    public long countByOpenId(String openId);

    /**
     * 根据日统计关注着人数
     */
    public List<Statistics> countByDays(Long wechatId);

    /**
     * 获取用户关注列表
     */
    public List<CustomInfo> getList(long wechatId);

    /**
     * 用户详情
     */
    public CustomInfo showDetail(long id);

    /**
     * 日期查询
     */
    public List<CustomInfo> screen(int type);

    /**
     * 日期查询客户数
     */
    public long countScreen(int type);
}
