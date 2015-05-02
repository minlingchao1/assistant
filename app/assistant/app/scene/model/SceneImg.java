/**
 * @Project:assistant
 * @Title: SceneImg.java
 * @date: 2015-3-3 下午5:27:25
 * @version 1.0
 */
package assistant.app.scene.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName SceneImg
 * @Description 场景画面
 * @author minlingchao
 * @date 2015-3-3 下午5:27:25
 */
@Entity(name = SceneImg.TABLE_NAME)
public class SceneImg extends BasicModel {

	public static final String TABLE_NAME = "scene_img";

	/**
	 * 场景ID
	 */
	@Column(name = "scene_id")
	private long sceneId;

	/**
	 * 排序
	 */
	@Column(name = "re_order")
	private int reOrder;

	/**
	 * 画面URL
	 */
	@Column(name = "img_url")
	private String imgUrl;

	public long getSceneId() {
		return sceneId;
	}

	/**
	 * 画面样式
	 */
	@Column(name = "img_set")
	private int imgSet;

	/**
	 * 按钮图片
	 */
	@Column(name = "btn_img")
	private String btnImg;

	/**
	 * 按钮位置
	 */
	@Column(name = "btn_pos")
	private String btnPos;

	/**
	 * 按钮url
	 */
	@Column(name = "btn_url")
	private String btnUrl;

	public int getReOrder() {
		return reOrder;
	}

	public void setReOrder(int reOrder) {
		this.reOrder = reOrder;
	}

	public int getImgSet() {
		return imgSet;
	}

	public void setImgSet(int imgSet) {
		this.imgSet = imgSet;
	}

	public String getBtnImg() {
		return btnImg;
	}

	public void setBtnImg(String btnImg) {
		this.btnImg = btnImg;
	}

	public String getBtnPos() {
		return btnPos;
	}

	public void setBtnPos(String btnPos) {
		this.btnPos = btnPos;
	}

	public String getBtnUrl() {
		return btnUrl;
	}

	public void setBtnUrl(String btnUrl) {
		this.btnUrl = btnUrl;
	}

	public void setSceneId(long sceneId) {
		this.sceneId = sceneId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
