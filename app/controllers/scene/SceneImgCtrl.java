/**
 * @Project:assistant
 * @Title: SceneImgCtrl.java
 * @date: 2015-3-5 下午12:40:53
 * @version 1.0
 */
package controllers.scene;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.scene.logic.ISceneImgLogic;
import assistant.app.scene.logic.ISceneLogic;
import assistant.app.scene.logic.impl.SceneImgLogicImpl;
import assistant.app.scene.logic.impl.SceneLogicImpl;
import assistant.app.scene.model.SceneImg;
import controllers.base.BaseController;

/**
 * @ClassName SceneImgCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午12:40:53
 */
public class SceneImgCtrl extends BaseController {

	public static final Logger LOG = LogCongfig.SCENELOG;
	
	private static ISceneImgLogic sceneImgLogic = SceneImgLogicImpl.getInstance();

	private static ISceneLogic sceneLogic = SceneLogicImpl.getInstance();

	public static void addSceneImg(Long sceneId, int reorder, int imgSet, String img, String btnImg, String btnPos,
			String btnUrl) {
		if (sceneImgLogic.insert(sceneId, reorder, imgSet, img, btnImg, btnPos, btnUrl) > 0) {
			renderJsonSuccess();
		} else {
			renderJsonFail();
		}
	}

	public static void getSceneImgList(Long sceneId) {
		List<SceneImg> sceneImgs =sceneLogic.findBySceneId(sceneId);
		renderJsonAjaxResult(sceneImgs);
	}

}
