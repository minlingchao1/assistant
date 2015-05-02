/**
 * @Project:assistant
 * @Title: SceneMessageRef.java
 * @date: 2015-3-4 上午12:20:23
 * @version 1.0
 */
package assistant.app.scene.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName SceneMessageRef
 * @Description 场景消息关联
 * @author minlingchao
 * @date 2015-3-4 上午12:20:23
 */
@Entity(name = SceneMessageRef.TABLE_NAME)
public class SceneMessageRef extends BasicModel {

	public static final String TABLE_NAME = "scene_message_ref";

	/**
	 * 场景Id
	 */
	@Column(name = "scene_id")
	private long sceneId;

	/**
	 * 图文消息Id
	 */
	@Column(name = "msg_id")
	private long msgId;

	public long getSceneId() {
		return sceneId;
	}

	public void setSceneId(long sceneId) {
		this.sceneId = sceneId;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

}
