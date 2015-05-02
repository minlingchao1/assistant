/**
 * @Project:assistant
 * @Title: BaseMessage.java
 * @date: 2015-2-6 下午2:36:06
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName BaseMessage
 * @Description 消息基础类
 * @author minlingchao
 * @date 2015-2-6 下午2:36:06
 */
@MappedSuperclass
public class BaseMessage extends BasicModel {

	/**
	 * 发送方微信号
	 */
	@Column(name = "from_user_name")
	private String fromUserName;

	/**
	 * 接收方微信号
	 */
	@Column(name = "to_user_name")
	private String toUserName;

	/**
	 * 发送时间
	 */
	@Column(name = "create_time")
	private String createTime;

	/**
	 * 消息类型
	 */
	@Column(name = "msg_type")
	private String msgType;

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
