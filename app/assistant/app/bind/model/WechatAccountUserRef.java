/**
 * @Project:assistant
 * @Title: WechatAccountUserRef.java
 * @date: 2015-2-1 下午6:31:56
 * @version 1.0
 */
package assistant.app.bind.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName WechatAccountUserRef
 * @Description 微信账户-用户关联表
 * @author minlingchao
 * @date 2015-2-1 下午6:31:56
 */
@Entity(name=WechatAccountUserRef.TABLE_NAME)
public class WechatAccountUserRef extends BasicModel {
	
	public static final String TABLE_NAME="wechat_account_user_ref";
	
	/**
	 * 用户Id
	 */
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * 微信账户Id
	 */
	@Column(name="wechat_id")
	private Long wechatId;
	
	public WechatAccountUserRef() {
		// TODO Auto-generated constructor stub
	}
	public WechatAccountUserRef(Long userId,Long wechatId){
		this.userId=userId;
		this.wechatId=wechatId;
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
