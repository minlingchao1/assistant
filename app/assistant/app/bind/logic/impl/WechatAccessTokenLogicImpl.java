/**
 * @Project:assistant
 * @Title: WechatAccessTokenLogicImpl.java
 * @date: 2015-2-10 下午3:56:00
 * @version 1.0
 */
package assistant.app.bind.logic.impl;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.bind.dal.IWechatAccessTokenDao;
import assistant.app.bind.dal.impl.WechatAccessTokenDaoImpl;
import assistant.app.bind.logic.IWechatAccessTokenLogic;
import assistant.app.bind.model.WechatAccessToken;

/**
 * @ClassName WechatAccessTokenLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 下午3:56:00
 */
public class WechatAccessTokenLogicImpl implements IWechatAccessTokenLogic {

	public static final Logger LOG = LogCongfig.WECHATBINDLOG;

	private static WechatAccessTokenLogicImpl instance = new WechatAccessTokenLogicImpl();

	private WechatAccessTokenLogicImpl() {

	}

	public static WechatAccessTokenLogicImpl getInstance() {
		return instance;
	}

	private IWechatAccessTokenDao wechatAccessTokenDao = WechatAccessTokenDaoImpl.getInstance();


	@Override
	public long update(WechatAccessToken wechatAccessToken) {
		return wechatAccessTokenDao.update(wechatAccessToken);
	}

	@Override
	public WechatAccessToken findByWechatId(long wechatId) {
		return wechatAccessTokenDao.findByWechatId(wechatId);
	}

	@Override
	public List<WechatAccessToken> findAll() {
		return wechatAccessTokenDao.findAll();
	}

}
