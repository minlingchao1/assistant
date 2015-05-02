/**
 * @Project:assistant
 * @Title: IQrcodeDao.java
 * @date: 2015-2-12 下午6:03:04
 * @version 1.0
 */
package assistant.app.qrcode.dal;

import java.util.List;

import assistant.app.qrcode.model.Qrcode;

/**
 * @ClassName IQrcodeDao
 * @Description 二维码信息处理
 * @author minlingchao
 * @date 2015-2-12 下午6:03:04
 */
public interface IQrcodeDao {

	/**
	 * 添加
	 */
	public long insert(Qrcode qrcode);

	/**
	 * 查找临时二维码
	 */
	public List<Qrcode> findTempQrcode();

	/**
	 * 更新
	 */
	public long update(Qrcode qrcode);

	/**
	 * 根据微信ID查找
	 */
	public List<Qrcode> findByWechatId(long wechatId);

}
