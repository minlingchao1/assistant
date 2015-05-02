/**
 * @Project:assistant
 * @Title: IWechatAccessTokenLogic.java
 * @date: 2015-2-10 下午3:55:47
 * @version 1.0
 */
package assistant.app.bind.logic;

import java.util.List;

import assistant.app.bind.model.WechatAccessToken;

/**
 * @ClassName IWechatAccessTokenLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 下午3:55:47
 */
public interface IWechatAccessTokenLogic {


	public long update(WechatAccessToken wechatAccessToken);

	public WechatAccessToken findByWechatId(long wehcatId);

	public List<WechatAccessToken> findAll();
}
