/**
 * @Project:assistant
 * @Title: IScanCodeDao.java
 * @date: 2015-2-11 下午4:14:30
 * @version 1.0
 */
package assistant.app.qrcode.dal;

import assistant.app.qrcode.model.ScanQrcode;

/**
 * @ClassName IScanCodeDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-11 下午4:14:30
 */
public interface IScanQrcodeDao {

	public long insert(ScanQrcode scanQrcode);

	public long update(ScanQrcode scanQrcode);

	public ScanQrcode findByWechatId(long wechatId);

}
