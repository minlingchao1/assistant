/**
 * @Project:assistant
 * @Title: SceneImgDaoImpl.java
 * @date: 2015-3-5 下午12:43:54
 * @version 1.0
 */
package assistant.app.scene.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.scene.dal.ISceneImgDao;
import assistant.app.scene.dao.mapper.ISceneImgMapper;
import assistant.app.scene.model.SceneImg;

/**
 * @ClassName SceneImgDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午12:43:54
 */
public class SceneImgDaoImpl implements ISceneImgDao {

	private static final Logger LOG = LogCongfig.SCENELOG;

	private static SceneImgDaoImpl instance = new SceneImgDaoImpl();

	private SceneImgDaoImpl() {

	}

	public static SceneImgDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(SceneImg sceneImg) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			ISceneImgMapper sceneImgMapper = session.getMapper(ISceneImgMapper.class);
			result = sceneImgMapper.insert(sceneImg);
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
