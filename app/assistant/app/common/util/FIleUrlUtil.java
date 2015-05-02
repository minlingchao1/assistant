/**
 * @Project:assistant
 * @Title: ImageUploadUtil.java
 * @date: 2015-2-2 下午2:02:29
 * @version 1.0
 */
package assistant.app.common.util;

import play.Play;

/**
 * @ClassName ImageUploadUtil
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-2 下午2:02:29
 */
public class FIleUrlUtil {

	/**
	 * 获取图片存储地址
	 */
	public static String getImageUrl() {
		String imgPath = Play.configuration.getProperty("imgdir");
		return imgPath;
	}

	/**
	 * 获取语音存储地址
	 */
	public static String getVoiceUrl() {
		String imgPath = Play.configuration.getProperty("voicedir");
		return imgPath;
	}

	/**
	 * 获取视频存储地址
	 */
	public static String getVideoUrl() {
		String imgPath = Play.configuration.getProperty("videodir");
		return imgPath;
	}

	/**
	 * 获取二维码存储地址
	 */

	public static String getQrUrl() {
		String imgPath = Play.configuration.getProperty("qrdir");
		return imgPath;
	}

	/**
	 * 获取全景图XMl地址
	 */
	public static String getOverallXmlUrl(){
		String overallXml = Play.configuration.getProperty("overallXmlDir");
		return overallXml;
	}

}
