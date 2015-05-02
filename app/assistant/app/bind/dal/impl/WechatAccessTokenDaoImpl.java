/**
 * @Project:assistant
 * @Title: WechatAccessTokenDaoImpl.java
 * @date: 2015-2-10 下午3:39:15
 * @version 1.0
 */
package assistant.app.bind.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.bind.dal.IWechatAccessTokenDao;
import assistant.app.bind.dao.mapper.IWechatAccessTokenMapper;
import assistant.app.bind.model.WechatAccessToken;

/**
 * @ClassName WechatAccessTokenDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 下午3:39:15
 */
public class WechatAccessTokenDaoImpl implements IWechatAccessTokenDao {

	public static final Logger LOG = LogCongfig.WECHATBINDLOG;

	private static WechatAccessTokenDaoImpl instance = new WechatAccessTokenDaoImpl();

	private WechatAccessTokenDaoImpl() {

	}

	public static WechatAccessTokenDaoImpl getInstance() {
		return instance;
	}

	@Override
	public WechatAccessToken findByWechatId(long wechatId) {
		WechatAccessToken result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {

			IWechatAccessTokenMapper wechatAccessTokenMapper = session.getMapper(IWechatAccessTokenMapper.class);
			result = wechatAccessTokenMapper.findByWechatId(wechatId);
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<WechatAccessToken> findAll() {
		List<WechatAccessToken> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {

			IWechatAccessTokenMapper wechatAccessTokenMapper = session.getMapper(IWechatAccessTokenMapper.class);
			result = wechatAccessTokenMapper.findAll();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public long insert(WechatAccessToken wechatAccessToken) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {

			IWechatAccessTokenMapper wechatAccessTokenMapper = session.getMapper(IWechatAccessTokenMapper.class);
			result = wechatAccessTokenMapper.insert(wechatAccessToken);
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public long update(WechatAccessToken wechatAccessToken) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {

			IWechatAccessTokenMapper wechatAccessTokenMapper = session.getMapper(IWechatAccessTokenMapper.class);
			result = wechatAccessTokenMapper.update(wechatAccessToken);
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
