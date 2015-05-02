/**
 * @Project:assistant
 * @Title: IMenuLogic.java
 * @date: 2015-2-21 下午2:08:23
 * @version 1.0
 */
package assistant.app.menu.logic;

import net.sf.json.JSONObject;
import assistant.app.menu.dto.ClickButton;
import assistant.app.menu.dto.ViewButton;
import assistant.app.menu.model.Menu;

/**
 * @ClassName IMenuLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午2:08:23
 */
public interface IMenuLogic {

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

	/**
	 * 创建自定义菜单
	 */

	public boolean createMenuDto(Long wechatId, String menu, String eventKey);

	/**
	 * 创建自定义菜单
	 */

	public boolean createMenu(Long wechatId, String menu);

	/**
	 * 处理菜单click事件
	 */
	public String dealMenuClick(String eventKey, String fromUserName, String toUserName, Long wechatId);


	/**
	 * 创建click类型菜单
	 */
	public ClickButton createClickButton(JSONObject obj, String eventKey);

	/**
	 * 创建view类型菜单
	 */
	public ViewButton createViewButton(JSONObject obj);

	/**
	 * 创建复杂按钮
	 */
	public boolean createComplexButton(JSONObject obj);
	
	/**
	 * 创建菜单点击恢复规则
	 */
	public boolean createMenuReplyRule(Long wechatId, String menuKey, String menuType, String replyMsg);


}
