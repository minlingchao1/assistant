/**
 * @Project:assistant
 * @Title: IMenuDao.java
 * @date: 2015-2-21 下午1:59:14
 * @version 1.0
 */
package assistant.app.menu.dal;

import assistant.app.menu.model.Menu;

/**
 * @ClassName IMenuDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午1:59:14
 */
public interface IMenuDao {

	/**
	 * 添加
	 */
	public long insert(Long wechatId, Long userId, String menuJson);
	
	/**
	 * 更新
	 */
	public long update(Menu menu);

	/**
	 * 根据wechatId查找
	 */
	public Menu findByWechatId(Long wechatId);
}
