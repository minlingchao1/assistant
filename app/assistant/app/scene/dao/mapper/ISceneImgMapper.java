/**
 * @Project:assistant
 * @Title: ISceneImgMapper.java
 * @date: 2015-3-4 上午12:59:27
 * @version 1.0
 */
package assistant.app.scene.dao.mapper;

import java.util.List;

import assistant.app.scene.model.SceneImg;

/**
 * @ClassName ISceneImgMapper
 * @Description 场景画面
 * @author minlingchao
 * @date 2015-3-4 上午12:59:27
 */
public interface ISceneImgMapper {
	
	public long insert(SceneImg sceneImg);

	public List<SceneImg> findBySceneId(long sceneId);

}
