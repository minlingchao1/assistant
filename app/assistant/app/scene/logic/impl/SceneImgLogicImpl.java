/**
 * @Project:assistant
 * @Title: SceneImgLogicImpl.java
 * @date: 2015-3-5 下午12:47:36
 * @version 1.0
 */
package assistant.app.scene.logic.impl;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.scene.dal.ISceneImgDao;
import assistant.app.scene.dal.impl.SceneImgDaoImpl;
import assistant.app.scene.logic.ISceneImgLogic;
import assistant.app.scene.model.SceneImg;

/**
 * @ClassName SceneImgLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午12:47:36
 */
public class SceneImgLogicImpl implements ISceneImgLogic {

	private static final Logger LOG = LogCongfig.SCENELOG;

	private static SceneImgLogicImpl instance = new SceneImgLogicImpl();

	private SceneImgLogicImpl() {

	}

	public static SceneImgLogicImpl getInstance() {
		return instance;
	}
	
	private ISceneImgDao sceneImgDao=SceneImgDaoImpl.getInstance();

	@Override
	public long insert(Long sceneId, int reorder, int imgSet, String img, String btnImg, String btnPos, String btnUrl) {
		SceneImg sceneImg=new SceneImg();
		sceneImg.setImgSet(imgSet);
		sceneImg.setImgUrl(img);
		sceneImg.setBtnImg(btnImg);
		sceneImg.setBtnPos(btnPos);
		sceneImg.setBtnUrl(btnUrl);
		sceneImg.setImgSet(imgSet);
		sceneImg.setSceneId(sceneId);
		sceneImg.setReOrder(reorder);
		return sceneImgDao.insert(sceneImg);
	}

}
