/**
 * @Project:assistant
 * @Title: Menu.java
 * @date: 2015-2-21 下午12:03:51
 * @version 1.0
 */
package assistant.app.menu.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName Menu
 * @Description 菜单model
 * @author minlingchao
 * @date 2015-2-21 下午12:03:51
 */
@Entity(name = "menu")
public class Menu extends BasicModel {

	public static final String TABLE_NAME = "menu";

	/**
	 * 微信号Id
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * JSON字符串
	 */
	@Column(name = "menu_json", columnDefinition = "text")
	private String menuJson;

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMenuJson() {
		return menuJson;
	}

	public void setMenuJson(String menuJson) {
		this.menuJson = menuJson;
	}

}
