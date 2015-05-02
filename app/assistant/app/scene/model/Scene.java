/**
 * @Project:assistant
 * @Title: SceneInfo.java
 * @date: 2015-3-3 下午5:27:12
 * @version 1.0
 */
package assistant.app.scene.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName SceneInfo
 * @Description 场景信息
 * @author minlingchao
 * @date 2015-3-3 下午5:27:12
 */
@Entity(name = Scene.TABLE_NAME)
public class Scene extends BasicModel {

	public static final String TABLE_NAME = "scene";

	/**
	 * 微信ID
	 */
	@Column(name = "wechat_id")
	private long wechatId;

	/**
	 * 场景名称
	 */
	@Column(name = "scene_name")
	private String sceneName;

	/**
	 * 开始时间
	 */
	@Column(name = "start")
	private long start;

	/**
	 * 结束时间
	 */
	@Column(name = "end")
	private long end;

	/**
	 * 场景状态
	 */
	@Column(name = "status")
	private int status;

	/**
	 * 分享标题
	 */
	@Column(name = "share_title")
	private String shareTitle;

	/**
	 * 分享内容
	 */
	@Column(name = "share_content")
	private String shareContent;

	/**
	 * 开场画面
	 */
	@Column(name = "open_img")
	private String openImg;

	/**
	 * 开场设置
	 */
	@Column(name = "open_set")
	private int openSet;

	/**
	 * 过期画面
	 */
	@Column(name = "out_img")
	private String outImg;

	/**
	 * 过期设置
	 */
	@Column(name = "out_set")
	private int outSet;

	/**
	 * 滑动效果
	 */
	@Column(name = "slide")
	private int slide;

	/**
	 * 背景音乐
	 */
	@Column(name = "back_music")
	private String backMusic;

	/**
	 * 背景音乐设置
	 */
	@Column(name = "music_set")
	private String musicSet;

	/**
	 * 开场画面按钮
	 */
	@Column(name = "open_btn_img")
	private String openBtnImg;

	/**
	 * 开场画面按钮位置
	 */
	@Column(name = "open_btn_pos")
	private String openBtnPos;

	/**
	 * 开场画面URL
	 */
	@Column(name = "open_btn_url")
	private String openBtnUrl;

	/**
	 * 开场画面按钮
	 */
	@Column(name = "out_btn_img")
	private String outBtnImg;

	/**
	 * 开场画面按钮位置
	 */
	@Column(name = "out_btn_pos")
	private String outBtnPos;

	/**
	 * 开场画面URL
	 */
	@Column(name = "out_btn_url")
	private String outBtnUrl;

	public Scene() {
		// TODO Auto-generated constructor stub
	}

	public long getWechatId() {
		return wechatId;
	}

	public void setWechatId(long wechatId) {
		this.wechatId = wechatId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public String getOpenImg() {
		return openImg;
	}

	public void setOpenImg(String openImg) {
		this.openImg = openImg;
	}

	public int getOpenSet() {
		return openSet;
	}

	public void setOpenSet(int openSet) {
		this.openSet = openSet;
	}

	public String getOutImg() {
		return outImg;
	}

	public void setOutImg(String outImg) {
		this.outImg = outImg;
	}

	public int getOutSet() {
		return outSet;
	}

	public void setOutSet(int outSet) {
		this.outSet = outSet;
	}

	public int getSlide() {
		return slide;
	}

	public void setSlide(int slide) {
		this.slide = slide;
	}

	public String getBackMusic() {
		return backMusic;
	}

	public void setBackMusic(String backMusic) {
		this.backMusic = backMusic;
	}


	public String getOpenBtnImg() {
		return openBtnImg;
	}

	public void setOpenBtnImg(String openBtnImg) {
		this.openBtnImg = openBtnImg;
	}



	public String getOpenBtnUrl() {
		return openBtnUrl;
	}

	public void setOpenBtnUrl(String openBtnUrl) {
		this.openBtnUrl = openBtnUrl;
	}

	public String getOutBtnImg() {
		return outBtnImg;
	}

	public void setOutBtnImg(String outBtnImg) {
		this.outBtnImg = outBtnImg;
	}

	public String getMusicSet() {
		return musicSet;
	}

	public void setMusicSet(String musicSet) {
		this.musicSet = musicSet;
	}

	public String getOpenBtnPos() {
		return openBtnPos;
	}

	public void setOpenBtnPos(String openBtnPos) {
		this.openBtnPos = openBtnPos;
	}

	public String getOutBtnPos() {
		return outBtnPos;
	}

	public void setOutBtnPos(String outBtnPos) {
		this.outBtnPos = outBtnPos;
	}

	public String getOutBtnUrl() {
		return outBtnUrl;
	}

	public void setOutBtnUrl(String outBtnUrl) {
		this.outBtnUrl = outBtnUrl;
	}

}
