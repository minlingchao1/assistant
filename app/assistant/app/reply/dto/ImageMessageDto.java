/**
 * @Project:assistant
 * @Title: ImageMessageDto.java
 * @date: 2015-2-7 下午3:06:39
 * @version 1.0
 */
package assistant.app.reply.dto;

import assistant.app.reply.dto.base.MessageDtoBase;

/**
 * @ClassName ImageMessageDto
 * @Description 图片消息DTO
 * @author minlingchao
 * @date 2015-2-7 下午3:06:39
 */
public class ImageMessageDto extends MessageDtoBase {

	/**
	 * 图片
	 */
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
