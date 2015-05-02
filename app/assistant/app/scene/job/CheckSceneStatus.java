/**
 * @author mlc
 * @date 2015年3月24日 下午12:37:29
 * @version 1.0
 */
package assistant.app.scene.job;

import java.util.List;

import org.slf4j.Logger;

import play.jobs.Every;
import assistant.app.base.config.LogCongfig;
import assistant.app.base.job.base.OnceJob;
import assistant.app.scene.config.SceneConfig;
import assistant.app.scene.dal.ISceneDao;
import assistant.app.scene.dal.impl.SceneDaoImpl;
import assistant.app.scene.model.Scene;

@Every("10s")
public class CheckSceneStatus extends OnceJob<Scene> {

    private static final Logger LOG = LogCongfig.SCENELOG;

    private ISceneDao sceneDao = SceneDaoImpl.getInstance();

    @Override
    public List<Scene> getAllData() {

        return sceneDao.listAll();
    }

    @Override
    public boolean dealOne(Scene scene) {

        long now = System.currentTimeMillis();

        if (scene.getStart() > now) {
            scene.setStatus(SceneConfig.COMING.getCode());
        } else if (scene.getStart() < now && now < scene.getEnd()) {
            scene.setStatus(SceneConfig.GOING_ON.getCode());
        } else if (scene.getEnd() < now) {
            scene.setStatus(SceneConfig.OUT_DATE.getCode());
        }
        sceneDao.update(scene);
        return false;
    }

    @Override
    public Logger getLog() {

        return LOG;
    }

}
