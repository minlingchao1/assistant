/**
 * @Project:assistant
 * @Title: TextMessage.java
 * @date: 2015-2-6 下午2:34:11
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.reply.dto.TextMessageDto;

/**
 * @ClassName TextMessage
 * @Description 文本消息
 * @author minlingchao
 * @date 2015-2-6 下午2:34:11
 */
@Entity(name = TextMessage.TABLE_NAME)
public class TextMessage extends BaseMessage {

	public static final String TABLE_NAME = "text_message";
	
	/**
	 * 文本内容
	 */
	@Column(name = "content")
	private String content;

	public TextMessage() {
		// TODO Auto-generated constructor stub
	}

	public TextMessage(TextMessageDto messageDto) {
		// TODO Auto-generated constructor stub
		this.content = messageDto.getContent();
	}

	public TextMessage(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
