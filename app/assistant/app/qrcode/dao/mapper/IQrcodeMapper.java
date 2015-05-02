/**
 * @Project:assistant
 * @Title: IQrcodeMapper.java
 * @date: 2015-2-12 下午5:50:30
 * @version 1.0
 */
package assistant.app.qrcode.dao.mapper;

import java.util.List;

import assistant.app.qrcode.model.Qrcode;

/**
 * @ClassName IQrcodeMapper
 * @Description 二维码信息操作
 * @author minlingchao
 * @date 2015-2-12 下午5:50:30
 */
public interface IQrcodeMapper {

	public long insert(Qrcode qrcode);

	public List<Qrcode> findByWechatId(long wechatId);

	public List<Qrcode> findTempQrcode();
	
	public long update(Qrcode qrcode);
}
