/**
 * @Project:assistant
 * @Title: NextOpenIdDaoImpl.java
 * @date: 2015-2-13 下午6:43:22
 * @version 1.0
 */
package assistant.app.custom.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.custom.dal.INextOpenIdDao;
import assistant.app.custom.dao.mapper.INextOpenIdMapper;
import assistant.app.custom.model.NextOpenId;

/**
 * @ClassName NextOpenIdDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午6:43:22
 */
public class NextOpenIdDaoImpl implements INextOpenIdDao {

	public static final Logger LOG = LogCongfig.CUSTOMLOG;

	private static NextOpenIdDaoImpl instance = new NextOpenIdDaoImpl();

	private NextOpenIdDaoImpl() {

	}

	public static NextOpenIdDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(NextOpenId nextOpenId) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			INextOpenIdMapper openIdMapper = session.getMapper(INextOpenIdMapper.class);
			result = openIdMapper.insert(nextOpenId);
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
	public long update(NextOpenId nextOpenId) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			INextOpenIdMapper openIdMapper = session.getMapper(INextOpenIdMapper.class);
			result = openIdMapper.update(nextOpenId);
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
	public List<NextOpenId> getAll() {
		List<NextOpenId> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			INextOpenIdMapper openIdMapper = session.getMapper(INextOpenIdMapper.class);
			result = openIdMapper.getAll();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
