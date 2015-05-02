/**
 * @Project:assistant
 * @Title: SellerMessageRefDaoImpl.java
 * @date: 2015-3-7 上午12:47:46
 * @version 1.0
 */
package assistant.app.lbs.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.lbs.dal.ISellerMessageRefDao;
import assistant.app.lbs.dao.mapper.ISellerMessageRefMapper;
import assistant.app.lbs.model.SellerMessageRef;

/**
 * @ClassName SellerMessageRefDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-7 上午12:47:46
 */
public class SellerMessageRefDaoImpl implements ISellerMessageRefDao {

	public static final Logger LOG = LogCongfig.LBSLOG;

	private static SellerMessageRefDaoImpl instance = new SellerMessageRefDaoImpl();

	private SellerMessageRefDaoImpl() {

	}

	public static SellerMessageRefDaoImpl getInstance() {
		return instance;
	}

	@Override
	public SellerMessageRef findBySellerId(long sellerId) {
		SellerMessageRef result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			ISellerMessageRefMapper sellerRefMapper = session.getMapper(ISellerMessageRefMapper.class);
			result = sellerRefMapper.findBySellerId(sellerId);
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
