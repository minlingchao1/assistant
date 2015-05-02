/**
 * @Project:assistant
 * @Title: SceneTemplCtrl.java
 * @date: 2015-3-4 下午5:09:04
 * @version 1.0
 */
package controllers.scene;

import java.util.List;

import org.slf4j.Logger;

import play.mvc.Controller;
import assistant.app.base.config.LogCongfig;
import assistant.app.scene.logic.ISceneLogic;
import assistant.app.scene.logic.impl.SceneLogicImpl;
import assistant.app.scene.model.Scene;
import assistant.app.scene.model.SceneImg;

/**
 * @ClassName SceneTemplCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-4 下午5:09:04
 */
public class SceneTemplCtrl extends Controller {

	public static final Logger LOG = LogCongfig.SCENELOG;
	private static ISceneLogic sceneLogic = SceneLogicImpl.getInstance();

	public static void scene(long sceneId) {
		Scene scene = sceneLogic.findById(sceneId);
		LOG.warn("scene status:{}", scene.getStatus());
		List<SceneImg> sceneImgs = sceneLogic.findBySceneId(sceneId);
		renderTemplate("/Application/scene/scene_templ.html", scene, sceneImgs);
	}
}
