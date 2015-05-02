/**
 * @Project:assistant
 * @Title: Video.java
 * @date: 2015-2-7 下午3:10:19
 * @version 1.0
 */
package assistant.app.reply.dto;

/**
 * @ClassName Video
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:10:19
 */
public class Video {
	/**
	 * 媒体Id
	 */
	private String MediaId;

	/**
	 * 缩略图ID
	 */
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
