/**
 * @Project:assistant
 * @Title: CustomGroupDaoImpl.java
 * @date: 2015-2-13 下午5:59:45
 * @version 1.0
 */
package assistant.app.custom.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.custom.dal.ICustomGroupDao;
import assistant.app.custom.dao.mapper.ICustomGroupMapper;
import assistant.app.custom.model.CustomGroup;

/**
 * @ClassName CustomGroupDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午5:59:45
 */
public class CustomGroupDaoImpl implements ICustomGroupDao {

	public static final Logger LOG = LogCongfig.CUSTOMLOG;

	private static CustomGroupDaoImpl instance = new CustomGroupDaoImpl();

	private CustomGroupDaoImpl() {

	}

	public static CustomGroupDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(CustomGroup group) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			ICustomGroupMapper groupMapper = session.getMapper(ICustomGroupMapper.class);
			result = groupMapper.insert(group);
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
	public long update(CustomGroup group) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			ICustomGroupMapper groupMapper = session.getMapper(ICustomGroupMapper.class);
			result = groupMapper.update(group);
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
	public CustomGroup findById(long wechatId, long id) {
		CustomGroup result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			ICustomGroupMapper groupMapper = session.getMapper(ICustomGroupMapper.class);
			result = groupMapper.findById(wechatId, id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
