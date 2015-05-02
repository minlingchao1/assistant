/**
 * @Project:assistant
 * @Title: ScanQrcode.java
 * @date: 2015-2-11 下午3:57:28
 * @version 1.0
 */
package assistant.app.qrcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName ScanQrcode
 * @Description 扫描二维码事件
 * @author minlingchao
 * @date 2015-2-11 下午3:57:28
 */
@Entity(name = ScanQrcode.TABLE_NAME)
public class ScanQrcode extends BasicModel {

	public static final String TABLE_NAME = "scan_qrcode";

	/**
	 * 微信ID
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 浏览人数统计
	 */
	@Column(name = "scan_count")
	private int scanCount;

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public int getScanCount() {
		return scanCount;
	}

	public void setScanCount(int scanCount) {
		this.scanCount = scanCount;
	}

}
