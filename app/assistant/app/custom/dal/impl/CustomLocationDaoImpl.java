/**
 * @Project:assistant
 * @Title: CustomLocationDaoImpl.java
 * @date: 2015-2-14 上午10:46:25
 * @version 1.0
 */
package assistant.app.custom.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.custom.dal.ICustomLocationDao;
import assistant.app.custom.dao.mapper.ICustomLocationMapper;
import assistant.app.custom.model.CustomLocation;

/**
 * @ClassName CustomLocationDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-14 上午10:46:25
 */
public class CustomLocationDaoImpl implements ICustomLocationDao {

	public static final Logger LOG = LogCongfig.CUSTOMLOG;

	private static CustomLocationDaoImpl instance = new CustomLocationDaoImpl();

	private CustomLocationDaoImpl() {

	}

	public static CustomLocationDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(CustomLocation customLocation) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			ICustomLocationMapper locationMapper = session.getMapper(ICustomLocationMapper.class);
			result = locationMapper.insert(customLocation);
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
	public CustomLocation findByWechatId(long wedchatId) {
		CustomLocation result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			ICustomLocationMapper locationMapper = session.getMapper(ICustomLocationMapper.class);
			result = locationMapper.findByWechatId(wedchatId);
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
