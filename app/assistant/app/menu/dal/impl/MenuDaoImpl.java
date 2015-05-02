/**
 * @Project:assistant
 * @Title: MenuDaoImpl.java
 * @date: 2015-2-21 下午1:59:21
 * @version 1.0
 */
package assistant.app.menu.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.menu.dal.IMenuDao;
import assistant.app.menu.dao.mapper.IMenuMapper;
import assistant.app.menu.model.Menu;

/**
 * @ClassName MenuDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午1:59:21
 */
public class MenuDaoImpl implements IMenuDao {

	public static final Logger LOG = LogCongfig.MENULOG;

	private static MenuDaoImpl instance = new MenuDaoImpl();

	private MenuDaoImpl() {

	}

	public static MenuDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(Long wechatId, Long userId, String menuJson) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			Menu menu = new Menu();
			menu.setWechatId(wechatId);
			menu.setUserId(userId);
			menu.setMenuJson(menuJson);

			IMenuMapper menuMapper = session.getMapper(IMenuMapper.class);
			result = menuMapper.insert(menu);
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
	public long update(Menu menu) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IMenuMapper menuMapper = session.getMapper(IMenuMapper.class);
			result = menuMapper.update(menu);
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
	public Menu findByWechatId(Long wechatId) {
		Menu result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IMenuMapper menuMapper = session.getMapper(IMenuMapper.class);
			result = menuMapper.findByWechatId(wechatId);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}


}
