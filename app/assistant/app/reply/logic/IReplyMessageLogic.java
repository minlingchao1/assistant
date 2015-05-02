/**
 * @Project:assistant
 * @Title: IReplyMessageLogic.java
 * @date: 2015-2-7 下午3:47:45
 * @version 1.0
 */
package assistant.app.reply.logic;

import assistant.app.reply.dto.ImageMessageDto;
import assistant.app.reply.dto.NewsMessageDto;
import assistant.app.reply.dto.TextMessageDto;
import assistant.app.reply.dto.VideoMessageDto;
import assistant.app.reply.dto.VoiceMessageDto;


/**
 * @ClassName IReplyMessageLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:47:45
 */
public interface IReplyMessageLogic {

	/**
	 * 文本消息存储
	 */

	public long saveTextMessage(String fromUserName, String toUserName, String content);
	
	/**
	 * 图片消息存储
	 */

	public long saveImageMessage(String fromUserName, String toUserName, String mediaId);

	/**
	 * 语音消息存储
	 */
	public long saveVoiceMessage(String fromUserName, String toUserName, String mediaId);
	
	/**
	 * 视频消息存储
	 */

	public long saveVideoMessage(String fromUserName, String toUserName, String mediaId, String thumbMediaId);

	/**
	 * 创建文本消息
	 */
	public TextMessageDto createTextMessage(String fromUserName, String toUserName, String content);

	/**
	 * 创建图片信息
	 */
	public ImageMessageDto createImageMessage(String fromUserName, String toUserName, String mediaId);

	/**
	 * 创建语音信息
	 */
	public VoiceMessageDto createVoiceMessage(String fromUserName, String toUserName, String mediaId);

	/**
	 * 创建视频信息
	 */
	public VideoMessageDto createVideoMessage(String fromUserName, String toUserName, String mediaId,
			String thumbMediaId);

	/**
	 * 创建LBS图文消息
	 */
	public String createSellerNewsMessage(String center, String fromUserName, String toUserName);

	/**
	 * 创建图文消息
	 */
	public NewsMessageDto createNewsMessage(String fromUserName, String toUserName, String picUrl, String url,
			String description, String title);

	/**
	 * 创建媒体回复消息
	 */
	public String createReplyMessage(long msgId, int type, String fromUserName, String toUserName, String replyMsg,
			String mediaId);
	
	/**
	 * 创建图文回复XML
	 */
	public String createNewsMessage(Long msgId, String fromUserName, String toUserName);

	/**
	 * 创建微应用 图文消息XML
	 */
	public String createExtendNewsMessage(Long msgId, String fromUserName, String toUserName);

}
