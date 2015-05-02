/**
 * @Project:assistant
 * @Title: NewsMultiDto.java
 * @date: 2015-2-27 下午1:17:15
 * @version 1.0
 */
package assistant.app.reply.dto;

import java.util.List;

/**
 * @ClassName NewsMultiDto
 * @Description 多图文消息dto
 * @author minlingchao
 * @date 2015-2-27 下午1:17:15
 */
public class NewsMultiDto {

	private Long msgId;

	private List<NewsTemplDto> multiTempl;

	public List<NewsTemplDto> getMultiTempl() {
		return multiTempl;
	}

	public void setMultiTempl(List<NewsTemplDto> multiTempl) {
		this.multiTempl = multiTempl;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}



}
