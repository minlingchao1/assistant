/**
 * @Project:assistant
 * @Title: NextOpenId.java
 * @date: 2015-2-13 下午6:34:28
 * @version 1.0
 */
package assistant.app.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName NextOpenId
 * @Description 保存nextOpenID
 * @author minlingchao
 * @date 2015-2-13 下午6:34:28
 */
@Entity(name = NextOpenId.TABLE_NAME)
public class NextOpenId extends BasicModel {

	public static final String TABLE_NAME = "next_openid";

	/**
	 * 微信Id
	 */
	@Column(name = "wechat_id")
	private long wechatId;

	/**
	 * nextOpenId
	 */
	@Column(name = "next_openid")
	private String nextOpenId;

	public long getWechatId() {
		return wechatId;
	}

	public void setWechatId(long wechatId) {
		this.wechatId = wechatId;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

}
