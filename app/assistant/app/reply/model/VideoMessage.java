/**
 * @Project:assistant
 * @Title: VideoMessage.java
 * @date: 2015-2-6 下午5:19:15
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @ClassName VideoMessage
 * @Description 视频消息
 * @author minlingchao
 * @date 2015-2-6 下午5:19:15
 */
@Entity(name = VideoMessage.TABLE_NAME)
public class VideoMessage extends BaseMessage{

	public static final String TABLE_NAME="video_message";
	
	/**
	 * 媒体ID
	 */
	@Column(name="media_id")
	private String mediaId;
	
	/**
	 * 缩略图Id
	 */
	@Column(name="thumb_media_id")
	private String thumbMediaId;

	public VideoMessage() {
		// TODO Auto-generated constructor stub
	}

	public VideoMessage(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	
}
