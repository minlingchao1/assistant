/**
 * @Project:assistant
 * @Title: MenuReplyRule.java
 * @date: 2015-2-21 下午4:29:06
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName MenuReplyRule
 * @Description 菜单点击与消息模板相关联
 * @author minlingchao
 * @date 2015-2-21 下午4:29:06
 */
@Entity(name = MenuReplyRule.TABLE_NAME)
public class MenuReplyRule extends BasicModel {

	public static final String TABLE_NAME = "menu_reply_rule";

	/**
	 * 菜单key
	 */
	@Column(name = "menu_key")
	private String menuKey;

	/**
	 * 消息类型
	 */
	@Column(name = "msg_type")
	private String msgType;

	/**
	 * 消息ID
	 */
	@Column(name = "msg_id")
	private Long msgId;

	/**
	 * 微信号Id
	 */
	@Column(name = "wechat_id")
	private Long wechatId;


	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	

}
