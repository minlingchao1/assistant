/**
 * @Project:assistant
 * @Title: NewsListDto.java
 * @date: 2015-2-27 下午12:11:42
 * @version 1.0
 */
package assistant.app.reply.dto;

/**
 * @ClassName NewsListDto
 * @Description 图文列表Dto
 * @author minlingchao
 * @date 2015-2-27 下午12:11:42
 */
public class NewsListDto {
	
	private String msgType;
	
	private Long msgId;
	
	private Long newsId;

	private String newsJson;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getNewsJson() {
		return newsJson;
	}

	public void setNewsJson(String newsJson) {
		this.newsJson = newsJson;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

}
