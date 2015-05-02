/**
 * @Project:assistant
 * @Title: ISceneDao.java
 * @date: 2015-3-4 上午1:04:29
 * @version 1.0
 */
package assistant.app.scene.dal;

import java.util.List;

import assistant.app.scene.model.Scene;
import assistant.app.scene.model.SceneImg;

/**
 * @ClassName ISceneDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-4 上午1:04:29
 */
public interface ISceneDao {

    public boolean addScene(long userId, long wechatId, String sceneName, long start, long end, String keyword,
            String pageImg, String descr, String shareTitle, String shareContent, int openSet, String openImg,
            int outSet, String outImg, int slide, String musicSet, String music, String openBtnImg, String outBtnImg,
            String openBtnUrl, String outBtnUrl, String openBtnPos, String outBtnPos);

    public List<Scene> getList(long wechatId);

    public Scene findById(long id);

    public List<SceneImg> findBySceneId(long sceneId);

    public long update(Scene scene);

    public List<Scene> listAll();

}
