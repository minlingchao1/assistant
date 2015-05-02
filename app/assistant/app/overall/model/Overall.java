/**
 * @Project:assistant
 * @Title: Overall.java
 * @date: 2015-3-5 下午2:26:19
 * @version 1.0
 */
package assistant.app.overall.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName Overall
 * @Description 360全景
 * @author minlingchao
 * @date 2015-3-5 下午2:26:19
 */
@Entity(name = Overall.TABLE_NAME)
public class Overall extends BasicModel {

	public static final String TABLE_NAME = "overall";

	/**
	 * 微信Id
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 全景图名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 全景图描述
	 */
	@Column(name = "descr")
	private String descr;
	/**
	 * 左边图片
	 */
	@Column(name = "left_url")
	private String leftUrl;

	/**
	 * 右边
	 */
	@Column(name = "right_url")
	private String rightUrl;

	/**
	 * 上边图片
	 */
	@Column(name = "top_url")
	private String topUrl;

	/**
	 * 底下图片
	 */
	@Column(name = "bottom_url")
	private String bottomUrl;

	/**
	 * 前面图片
	 */
	@Column(name = "front_url")
	private String frontUrl;

	/**
	 * 后面图片
	 */
	@Column(name = "behind_url")
	private String behindUrl;

	/**
	 * 背景音乐设置
	 */
	@Column(name = "music_set")
	private String musicSet;

	/**
	 * 背景音乐
	 */
	@Column(name = "back_music")
	private String backMusic;

	public String getLeftUrl() {
		return leftUrl;
	}

	public void setLeftUrl(String leftUrl) {
		this.leftUrl = leftUrl;
	}

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getTopUrl() {
		return topUrl;
	}

	public void setTopUrl(String topUrl) {
		this.topUrl = topUrl;
	}

	public String getBottomUrl() {
		return bottomUrl;
	}

	public void setBottomUrl(String bottomUrl) {
		this.bottomUrl = bottomUrl;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getBehindUrl() {
		return behindUrl;
	}

	public void setBehindUrl(String behindUrl) {
		this.behindUrl = behindUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getMusicSet() {
		return musicSet;
	}

	public void setMusicSet(String musicSet) {
		this.musicSet = musicSet;
	}

	public String getBackMusic() {
		return backMusic;
	}

	public void setBackMusic(String backMusic) {
		this.backMusic = backMusic;
	}

}
