/**
 * @Project:assistant
 * @Title: ImageMessage.java
 * @date: 2015-2-6 下午5:07:14
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @ClassName ImageMessage
 * @Description 图片消息
 * @author minlingchao
 * @date 2015-2-6 下午5:07:14
 */
@Entity(name = ImageMessage.TABLE_NAME)
public class ImageMessage extends BaseMessage {

	public static final String TABLE_NAME = "image_message";

	/**
	 * 媒体文件ID
	 */
	@Column(name = "media_id")
	private String mediaId;

	public ImageMessage() {
		// TODO Auto-generated constructor stub
	}

	public ImageMessage(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
