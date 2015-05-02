/**
 * @Project:assistant
 * @Title: IMenuMapper.java
 * @date: 2015-2-21 下午1:49:43
 * @version 1.0
 */
package assistant.app.menu.dao.mapper;

import assistant.app.menu.model.Menu;

/**
 * @ClassName IMenuMapper
 * @Description 菜单增删改查
 * @author minlingchao
 * @date 2015-2-21 下午1:49:43
 */
public interface IMenuMapper {

	public long insert(Menu menu);

	public long update(Menu menu);

	public Menu findByWechatId(Long wechatId);
}
