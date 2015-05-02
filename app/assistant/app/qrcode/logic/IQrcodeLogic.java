/**
 * @Project:assistant
 * @Title: IQrcodeLogic.java
 * @date: 2015-2-12 下午6:07:05
 * @version 1.0
 */
package assistant.app.qrcode.logic;

import java.util.List;

import play.mvc.Http.Request;
import assistant.app.qrcode.model.Qrcode;

/**
 * @ClassName IQrcodeLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-12 下午6:07:05
 */
public interface IQrcodeLogic {

	/**
	 * 添加
	 */
	public long insert(Qrcode qrcode);
	
	/**
	 * 创建二维码
	 */
	public boolean createQrcode(Request request, long wechatId, int expireSeconds, String sceneName, String actionName,
			int sceneId);

	/**
	 * 发送二维码信息到微信服务器
	 */
	public String sendQrInfo(long wechatId, String actionName, String qrJson);

	/**
	 * 根据微信ID查找
	 */
	public List<Qrcode> findByWechatId(long wechatId);

	/**
	 * 获取二维码图片
	 */
	public String getQrImg(Request request, String ticket);

	/**
	 * 获取二维码图片存储地址
	 */
	public String getSavePath(Request request);

	/**
	 * 获取二维码图片真实地址
	 */
	public String getQrRealPath(Request request);

}
