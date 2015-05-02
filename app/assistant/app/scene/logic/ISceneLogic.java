/**
 * @Project:assistant
 * @Title: ISceneLogic.java
 * @date: 2015-3-4 上午11:23:36
 * @version 1.0
 */
package assistant.app.scene.logic;

import java.util.List;

import assistant.app.scene.model.Scene;
import assistant.app.scene.model.SceneImg;

/**
 * @ClassName ISceneLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-4 上午11:23:36
 */
public interface ISceneLogic {
	
	public boolean addScene(long userId, long wechatId, String sceneName, long start, long end, String keyword,
			String pageImg, String descr, String shareTitle, String shareContent, int openSet, String openImg,
			int outSet, String outImg, int slide, String musicSet, String music, String openBtnImg, String outBtnImg,
			String openBtnUrl, String outBtnUrl, String openBtnPos, String outBtnPos);

	public List<Scene> getSceneList(long wechatId);

	public Scene findById(long id);

	public List<SceneImg> findBySceneId(long sceneId);

}
