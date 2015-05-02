/**
 * @Project:assistant
 * @Title: MessageDtoBase.java
 * @date: 2015-2-7 下午2:19:55
 * @version 1.0
 */
package assistant.app.reply.dto.base;

/**
 * @ClassName MessageDtoBase
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午2:19:55
 */
public class MessageDtoBase {

	/**
	 * 发送方账号
	 */
	private String FromUserName;

	/**
	 * 接收方账号
	 */
	private String ToUserName;

	/**
	 * 消息类型
	 */
	private String MsgType;

	/**
	 * 创建时间
	 */
	private Long CreateTime;


	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

}
