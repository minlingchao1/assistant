/**
 * @Project:assistant
 * @Title: TextMessageDto.java
 * @date: 2015-2-7 下午2:17:49
 * @version 1.0
 */
package assistant.app.reply.dto;

import assistant.app.reply.dto.base.MessageDtoBase;

/**
 * @ClassName TextMessageDto
 * @Description 文本消息DTO
 * @author minlingchao
 * @date 2015-2-7 下午2:17:49
 */
public class TextMessageDto extends MessageDtoBase {

	/**
	 * 回复消息内容
	 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
