/**
 * @Project:assistant
 * @Title: WechatAccessToken.java
 * @date: 2015-2-10 下午3:29:21
 * @version 1.0
 */
package assistant.app.bind.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName WechatAccessToken
 * @Description 微信AccessToken保存
 * @author minlingchao
 * @date 2015-2-10 下午3:29:21
 */
@Entity(name = WechatAccessToken.TABLE_NAME)
public class WechatAccessToken extends BasicModel{

	public static final String TABLE_NAME = "wechat_access_token";
	
	/**
	 * 微信号ID
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 微信请求接口凭证
	 */
	@Column(name = "access_token")
	private String accessToken;

	/**
	 * 创建时间
	 * 
	 */
	@Column(name = "create_time")
	private Long createTime;
	
	public WechatAccessToken() {
		// TODO Auto-generated constructor stub
	}
	
	public WechatAccessToken(Long wechatId, String accessToken, Long createTime) {
		this.wechatId = wechatId;
		this.accessToken = accessToken;
		this.createTime = createTime;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
