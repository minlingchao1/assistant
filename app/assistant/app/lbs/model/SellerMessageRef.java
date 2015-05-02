/**
 * @Project:assistant
 * @Title: SellerMessageRef.java
 * @date: 2015-3-6 下午3:52:11
 * @version 1.0
 */
package assistant.app.lbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName SellerMessageRef
 * @Description 商户-图文消息关联
 * @author minlingchao
 * @date 2015-3-6 下午3:52:11
 */
@Entity(name = SellerMessageRef.TABLE_NAME)
public class SellerMessageRef extends BasicModel {

	public static final String TABLE_NAME = "seller_message_ref";

	/**
	 * 商户信息ID
	 */
	@Column(name = "seller_id")
	private Long sellerId;

	/**
	 * 图文消息Id
	 */
	@Column(name = "msg_id")
	private Long msgId;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

}
