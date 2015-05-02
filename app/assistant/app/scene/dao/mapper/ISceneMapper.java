/**
 * @Project:assistant
 * @Title: ISceneMapper.java
 * @date: 2015-3-4 上午12:58:21
 * @version 1.0
 */
package assistant.app.scene.dao.mapper;

import java.util.List;

import assistant.app.scene.model.Scene;

/**
 * @ClassName ISceneMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-4 上午12:58:21
 */
public interface ISceneMapper {

    public long insert(Scene scene);

    public long update(Scene scene);

    public Scene findById(long id);

    public List<Scene> getList(long wechatId);

    public List<Scene> listAll();

}
