/**
 * @Project:assistant
 * @Title: NewsMessageRef.java
 * @date: 2015-2-24 下午5:17:22
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName NewsMessageRef
 * @Description 图文消息关联
 * @author minlingchao
 * @date 2015-2-24 下午5:17:22
 */
@Entity(name = NewsMessageRef.TABLE_NAME)
public class NewsMessageRef extends BasicModel {

	public static final String TABLE_NAME = "news_message_ref";
	
	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 微信账号ID
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 消息类型
	 */
	@Column(name = "msg_type")
	private String msgType;

	/**
	 * 消息ID
	 */
	@Column(name = "msg_id")
	private String msgId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
