/**
 * @Project:assistant
 * @Title: ISceneStatisticsMapper.java
 * @date: 2015-3-4 上午12:58:48
 * @version 1.0
 */
package assistant.app.scene.dao.mapper;

import assistant.app.scene.model.SceneStatistics;

/**
 * @ClassName ISceneStatisticsMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-4 上午12:58:48
 */
public interface ISceneStatisticsMapper {
	
	public long insert(SceneStatistics sceneStatistics);
	
	public SceneStatistics findBySceneId(long id);

}
