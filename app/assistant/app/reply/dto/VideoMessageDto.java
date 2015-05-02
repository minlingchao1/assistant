/**
 * @Project:assistant
 * @Title: VideoMessage.java
 * @date: 2015-2-7 下午3:12:58
 * @version 1.0
 */
package assistant.app.reply.dto;

import assistant.app.reply.dto.base.MessageDtoBase;

/**
 * @ClassName VideoMessage
 * @Description 视频消息DTO
 * @author minlingchao
 * @date 2015-2-7 下午3:12:58
 */
public class VideoMessageDto extends MessageDtoBase {

	/**
	 * 视频
	 */
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
