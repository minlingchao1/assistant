/**
 * @Project:assistant
 * @Title: VoiceImage.java
 * @date: 2015-2-7 下午3:08:52
 * @version 1.0
 */
package assistant.app.reply.dto;

import assistant.app.reply.dto.base.MessageDtoBase;

/**
 * @ClassName VoiceImage
 * @Description 语音消息DTO
 * @author minlingchao
 * @date 2015-2-7 下午3:08:52
 */
public class VoiceMessageDto extends MessageDtoBase {

	/**
	 * 语音
	 */
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}



}
