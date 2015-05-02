
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName ReplyMsgTemplete
 * @Description 回复消息模板
 * @author minlingchao
 * @date 2015-2-6 下午7:25:12
 */

@Entity(name = ReplyMsgTemplete.TABLE_NAME)
public class ReplyMsgTemplete extends BasicModel {

	public static final String TABLE_NAME = "reply_msg_templete";

	/**
	 * 回复内容
	 * 
	 */

	@Column(name = "reply_msg", columnDefinition = "text")
	private String replyMsg;

	/**
	 * medidId
	 */
	@Column(name = "media_id")
	private String mediaId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Long createTime;

	public ReplyMsgTemplete() {
		// TODO Auto-generated constructor stub
	}

	public ReplyMsgTemplete(String replyMsg, String mediaId) {
		this.replyMsg = replyMsg;
		this.mediaId = mediaId;
	}
	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
