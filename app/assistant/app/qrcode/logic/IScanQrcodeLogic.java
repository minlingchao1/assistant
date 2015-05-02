/**
 * @Project:assistant
 * @Title: IScanCodeLogic.java
 * @date: 2015-2-11 下午4:23:32
 * @version 1.0
 */
package assistant.app.qrcode.logic;

import assistant.app.qrcode.model.ScanQrcode;

/**
 * @ClassName IScanCodeLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-11 下午4:23:32
 */
public interface IScanQrcodeLogic {

	public long insert(ScanQrcode scanQrcode);

	public long update(ScanQrcode scanQrcode);

	public ScanQrcode findByWechatId(long wechatId);

}
