/**
 * @Project:assistant
 * @Title: Qrcode.java
 * @date: 2015-2-12 下午5:24:58
 * @version 1.0
 */
package assistant.app.qrcode.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.config.WechatConfig;
import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName Qrcode
 * @Description 创建的二维码相关信息
 * @author minlingchao
 * @date 2015-2-12 下午5:24:58
 */
@Entity(name = Qrcode.TABLE_NAME)
public class Qrcode extends BasicModel{
	
	public static final String TABLE_NAME="qrcode";

	/**
	 * 微信Id
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 二维码有效时间
	 */
	@Column(name = "expire_seconds")
	private int expireSeconds;

	/**
	 * 二维码类型
	 */
	@Column(name = "action_name")
	private String actionName;



		/**
	 * 场景值ID
	 */
	@Column(name = "scene_id")
	private int sceneId;

	/**
	 * 场景名称
	 */
	@Column(name = "scene_name")
	private String sceneName;

	/**
	 * 获取的二维码ticket
	 */
	@Column(name = "ticket")
	private String ticket;

	/**
	 * 二维码状态:有效/失效
	 */
	@Column(name = "status")
	private int status;

	/**
	 * 二维码创建时间
	 */
	@Column(name = "create_time")
	private long createTime;

	/**
	 * 二维码图片地址
	 */
	@Column(name = "qr_img")
	private String qrImg;

	public Qrcode() {
		// TODO Auto-generated constructor stub
	}

	public Qrcode(long wechatId, int expireSeconds, String actionName, String ticket, String sceneName, int sceneId) {
		this.wechatId=wechatId;
		this.expireSeconds = expireSeconds;
		this.actionName=actionName;
		this.ticket=ticket;
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.status = WechatConfig.QR_STATUS_EFFECT;
		this.createTime = new Date().getTime();
	}
	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getQrImg() {
		return qrImg;
	}

	public void setQrImg(String qrImg) {
		this.qrImg = qrImg;
	}

}
