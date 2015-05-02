/**
 * @Project:assistant
 * @Title: SceneStatistics.java
 * @date: 2015-3-3 下午5:28:23
 * @version 1.0
 */
package assistant.app.scene.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName SceneStatistics
 * @Description 场景浏览统计
 * @author minlingchao
 * @date 2015-3-3 下午5:28:23
 */
@Entity(name = SceneStatistics.TABLE_NAME)
public class SceneStatistics extends BasicModel {

	public static final String TABLE_NAME = "scene_statistics";

	/**
	 * 场景Id
	 */
	@Column(name = "scene_id")
	private long sceneId;

	/**
	 * 浏览量
	 */
	@Column(name = "pv")
	private long pv;

	/**
	 * 独立openId
	 */
	@Column(name = "uv")
	private long uv;

	/**
	 * 浏览时间
	 */
	@Column(name = "scan_time")
	private long scanTime;

	public long getSceneId() {
		return sceneId;
	}

	public void setSceneId(long sceneId) {
		this.sceneId = sceneId;
	}

	public long getPv() {
		return pv;
	}

	public void setPv(long pv) {
		this.pv = pv;
	}

	public long getUv() {
		return uv;
	}

	public void setUv(long uv) {
		this.uv = uv;
	}

	public long getScanTime() {
		return scanTime;
	}

	public void setScanTime(long scanTime) {
		this.scanTime = scanTime;
	}
}
