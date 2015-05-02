package assistant.app.subscribe.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName SubScribe
 * @Description 关注回复设置
 * @author minlingchao
 * @date 2015-2-6 下午2:46:13
 */
@Entity(name = Subscribe.TABLE_NAME)
public class Subscribe extends BasicModel {

	public static final String TABLE_NAME = "subscribe";

	/**
	 * 回复消息类型
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 消息Id
	 */
	@Column(name = "msg_id")
	private Long msgId;

	/**
	 * 用户Id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 微信Id
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	public Subscribe() {
		// TODO Auto-generated constructor stub
	}

	public Subscribe(Long wechatId, Long userId, Long msgId, Integer type) {
		this.wechatId = wechatId;
		this.userId = userId;
		this.msgId = msgId;
		this.type = type;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

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

}
