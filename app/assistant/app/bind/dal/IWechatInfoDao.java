/**
 * @Project:assistant
 * @Title: IWechatInfoDao.java
 * @date: 2015-2-1 下午6:41:23
 * @version 1.0
 */
package assistant.app.bind.dal;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import assistant.app.bind.model.WechatAccountInfo;

/**
 * @ClassName IWechatInfoDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-1 下午6:41:23
 */
public interface IWechatInfoDao {

    /**
     * 添加微信账户信息
     */
    public long insert(Long userId, String accountName, String type, String authenticate, String appId,
            String appSecret, String wechatNumber, String accountId, String qrCode, String headImage, String url,
            String token, String accessToken);

    /**
     * 自动添加
     */
    public long insert(Long userId, WechatAccountInfo wechatAccountInfo, String accessToken);

    /**
     * 根据Id查找信息
     */
    public WechatAccountInfo findById(Long id);

    /**
     * 查找WechatId
     */
    public Long findWechatId(Long userId);

    /**
     * 更新
     */
    public long update(WechatAccountInfo wechatAccountInfo);

    /**
     * 根据AppId查找
     */
    public WechatAccountInfo findByAppId(String appId);

    /**
     * 根据userId查找微信帐号
     */
    public List<WechatAccountInfo> getWXInfo(Long userId);

    /**
     * 删除微信账号
     */
    public long deleteAllInfo(long wechatId);

    /**
     * 根据微信号和用户查找
     */
    public WechatAccountInfo findByUserIdAndWechatId(@Param("userId") Long userId, @Param("wechatId") long wechatId);
}
