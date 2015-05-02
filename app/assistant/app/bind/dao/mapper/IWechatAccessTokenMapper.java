/**
 * @Project:assistant
 * @Title: IWechatAccessTokenMapper.java
 * @date: 2015-2-10 下午3:37:42
 * @version 1.0
 */
package assistant.app.bind.dao.mapper;

import java.util.List;

import assistant.app.bind.model.WechatAccessToken;

/**
 * @ClassName IWechatAccessTokenMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 下午3:37:42
 */
public interface IWechatAccessTokenMapper {

	public long insert(WechatAccessToken wechatAccessToken);

	public long update(WechatAccessToken wechatAccessToken);

	public WechatAccessToken findByWechatId(long wechatId);

	public List<WechatAccessToken> findAll();
}
