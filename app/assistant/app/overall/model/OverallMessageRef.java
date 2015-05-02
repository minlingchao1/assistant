/**
 * @Project:assistant
 * @Title: OverallMessageRef.java
 * @date: 2015-3-5 下午4:20:43
 * @version 1.0
 */
package assistant.app.overall.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName OverallMessageRef
 * @Description 全景图与消息关联
 * @author minlingchao
 * @date 2015-3-5 下午4:20:43
 */
@Entity(name = OverallMessageRef.TABLE_NAME)
public class OverallMessageRef extends BasicModel {

	public static final String TABLE_NAME = "overall_message_ref";

	/**
	 * 全景图ID
	 */
	@Column(name = "overall_id")
	private Long overallId;

	/**
	 * 消息Id
	 */
	@Column(name = "msg_id")
	private Long msgId;

	public Long getOverallId() {
		return overallId;
	}

	public void setOverallId(Long overallId) {
		this.overallId = overallId;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

}
