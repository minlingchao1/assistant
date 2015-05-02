/**
 * @Project:assistant
 * @Title: SceneLogicImpl.java
 * @date: 2015-3-4 上午11:23:44
 * @version 1.0
 */
package assistant.app.scene.logic.impl;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.scene.dal.ISceneDao;
import assistant.app.scene.dal.impl.SceneDaoImpl;
import assistant.app.scene.logic.ISceneLogic;
import assistant.app.scene.model.Scene;
import assistant.app.scene.model.SceneImg;

/**
 * @ClassName SceneLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-4 上午11:23:44
 */
public class SceneLogicImpl implements ISceneLogic {

	private static final Logger LOG = LogCongfig.SCENELOG;

	private static SceneLogicImpl instance = new SceneLogicImpl();

	private SceneLogicImpl() {

	}

	public static SceneLogicImpl getInstance() {
		return instance;
	}

	private ISceneDao sceneDao = SceneDaoImpl.getInstance();
	@Override
	public boolean addScene(long userId, long wechatId, String sceneName, long start, long end, String keyword,
			String pageImg,
			String descr, String shareTitle, String shareContent, int openSet, String openImg, int outSet,
			String outImg, int slide, String musicSet, String music, String openBtnImg, String outBtnImg,
			String openBtnUrl, String outBtnUrl, String openBtnPos, String outBtnPos) {

		return sceneDao.addScene(userId, wechatId, sceneName, start, end, keyword, pageImg, descr, shareTitle,
				shareContent, openSet, openImg, outSet, outImg, slide, musicSet, music, openBtnImg, outBtnImg,
				openBtnUrl, outBtnUrl, openBtnPos, outBtnPos);
	}

	@Override
	public List<Scene> getSceneList(long wechatId) {
		return sceneDao.getList(wechatId);
	}

	@Override
	public Scene findById(long id) {
		return sceneDao.findById(id);
	}

	@Override
	public List<SceneImg> findBySceneId(long sceneId) {
		return sceneDao.findBySceneId(sceneId);
	}

}
