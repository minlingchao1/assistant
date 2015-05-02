/**
 * @Project:assistant
 * @Title: IWechatAccountInfoMapper.java
 * @date: 2015-2-1 下午6:44:38
 * @version 1.0
 */
package assistant.app.bind.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import assistant.app.bind.model.WechatAccountInfo;

/**
 * @ClassName IWechatAccountInfoMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-1 下午6:44:38
 */
public interface IWechatAccountInfoMapper {

    public long insert(WechatAccountInfo wechatAccountInfo);

    public WechatAccountInfo findById(Long id);

    public long update(WechatAccountInfo wechatAccountInfo);

    public WechatAccountInfo findByAppId(String appId);

    public List<WechatAccountInfo> getWXInfo(Long userId);

    public long delete(long id);

    public WechatAccountInfo findByUserIdAndWechatId(@Param("userId") Long userId, @Param("wechatId") long wechatId);

}
