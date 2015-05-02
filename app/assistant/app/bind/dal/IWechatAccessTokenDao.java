/**
 * @Project:assistant
 * @Title: IWechatAccessTokenDao.java
 * @date: 2015-2-10 下午3:39:04
 * @version 1.0
 */
package assistant.app.bind.dal;

import java.util.List;

import assistant.app.bind.model.WechatAccessToken;

/**
 * @ClassName IWechatAccessTokenDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 下午3:39:04
 */
public interface IWechatAccessTokenDao {

	public WechatAccessToken findByWechatId(long wechatId);

	public List<WechatAccessToken> findAll();

	public long insert(WechatAccessToken wechatAccessToken);

	public long update(WechatAccessToken wechatAccessToken);

}
