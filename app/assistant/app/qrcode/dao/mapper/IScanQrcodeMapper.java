/**
 * @Project:assistant
 * @Title: IScanCodeMapper.java
 * @date: 2015-2-11 下午4:02:02
 * @version 1.0
 */
package assistant.app.qrcode.dao.mapper;

import assistant.app.qrcode.model.ScanQrcode;

/**
 * @ClassName IScanCodeMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-11 下午4:02:02
 */
public interface IScanQrcodeMapper {
	
	public long insert(ScanQrcode scanQrcode);

	public long update(ScanQrcode scanQrcode);

	public ScanQrcode findByWechatId(long wechatId);

}
