/**
 * @Project:assistant
 * @Title: NewsTemplDto.java
 * @date: 2015-2-27 上午12:07:33
 * @version 1.0
 */
package assistant.app.reply.dto;

/**
 * @ClassName NewsTemplDto
 * @Description 图文消息模板
 * @author minlingchao
 * @date 2015-2-27 上午12:07:33
 */
public class NewsTemplDto {

	/**
	 * 信息ID
	 */

	private Long msgId;
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 图片链接
	 */
	private String imgUrl;

	/**
	 * 正文
	 */
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

}
