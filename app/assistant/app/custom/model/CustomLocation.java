/**
 * @Project:assistant
 * @Title: CustomLocation.java
 * @date: 2015-2-14 上午10:28:13
 * @version 1.0
 */
package assistant.app.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.reply.model.BaseMessage;

/**
 * @ClassName CustomLocation
 * @Description 用户地理位置
 * @author minlingchao
 * @date 2015-2-14 上午10:28:13
 */
@Entity(name = CustomLocation.TABLE_NAME)
public class CustomLocation extends BaseMessage{

	public static final String TABLE_NAME = "custom_location";
	
	/**
	 * 微信Id
	 */
	@Column(name = "wechat_id")
	private long wechatId;

	/**
	 * 经度
	 */
	@Column(name = "lng")
	private String lng;

	/**
	 * 纬度
	 */
	@Column(name = "lat")
	private String lat;

	/**
	 * 用户Openid
	 */
	@Column(name = "openId")
	private String openId;

	/**
	 * 精度
	 */
	@Column(name = "precisions")
	private String precisions;
	public long getWechatId() {
		return wechatId;
	}

	public void setWechatId(long wechatId) {
		this.wechatId = wechatId;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPrecisions() {
		return precisions;
	}

	public void setPrecisions(String precisions) {
		this.precisions = precisions;
	}


}
