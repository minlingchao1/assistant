/**
 * @Project:assistant
 * @Title: CustomInfo.java
 * @date: 2015-2-13 下午5:10:49
 * @version 1.0
 */
package assistant.app.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName CustomInfo
 * @Description 用户基本信息
 * @author minlingchao
 * @date 2015-2-13 下午5:10:49
 */
@Entity(name = CustomInfo.TABLE_NAME)
public class CustomInfo extends BasicModel {

	public static final String TABLE_NAME = "custom_info";

	/**
	 * 微信号Id
	 */
	@Column(name = "wechat_id")
	private long wechatId;

	/**
	 * openId
	 */
	@Column(name = "open_id")
	private String openId;

	/**
	 * 关注状态
	 */
	@Column(name = "subscribe")
	private int subscribe;
	/**
	 * 关注时间
	 */
	@Column(name = "subscribe_time")
	private Long subscribeTime;

	/**
	 * 用户昵称
	 */
	@Column(name = "nick_name")
	private String nickName;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	private int sex;

	/**
	 * 国家
	 */
	@Column(name="country")
	private String country;

	/**
	 * 省份
	 */
	@Column(name = "province")
	private String province;

	/**
	 * 城市
	 */
	@Column(name = "city")
	private String city;

	/**
	 * 语音
	 */
	@Column(name = "language")
	private String language;

	/**
	 * 头像URL
	 */
	@Column(name = "head_img_url")
	private String headImgUrl;

	public long getWechatId() {
		return wechatId;
	}

	public void setWechatId(long wechatId) {
		this.wechatId = wechatId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}



	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
