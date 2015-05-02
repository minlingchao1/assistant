/**
 * @Project:assistant
 * @Title: MusicMessage.java
 * @date: 2015-2-6 下午5:16:35
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @ClassName MusicMessage
 * @Description 语音消息
 * @author minlingchao
 * @date 2015-2-6 下午5:16:35
 */
@Entity(name = VoiceMessage.TABLE_NAME)
public class VoiceMessage extends BaseMessage {

	public static final String TABLE_NAME = "voice_message";

	/**
	 * 媒体Id
	 */
	@Column(name = "media_id")
	private String mediaId;

	public VoiceMessage() {
		// TODO Auto-generated constructor stub
	}

	public VoiceMessage(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
