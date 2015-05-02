/**
 * @Project:assistant
 * @Title: ScanCodeLogicImpl.java
 * @date: 2015-2-11 下午4:23:59
 * @version 1.0
 */
package assistant.app.qrcode.logic.impl;
	
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.qrcode.dal.IScanQrcodeDao;
import assistant.app.qrcode.dal.impl.ScanQrcodeDaoImpl;
import assistant.app.qrcode.logic.IScanQrcodeLogic;
import assistant.app.qrcode.model.ScanQrcode;

/**
 * @ClassName ScanCodeLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-11 下午4:23:59
 */
public class ScanQrcodeLogicImpl implements IScanQrcodeLogic              {

	private static final Logger LOG = LogCongfig.QRCODELOG;

	private static ScanQrcodeLogicImpl instance = new ScanQrcodeLogicImpl();

	private ScanQrcodeLogicImpl() {

	}

	public static ScanQrcodeLogicImpl getInstance() {
		return instance;
	}

	private IScanQrcodeDao scanCodeDao = ScanQrcodeDaoImpl.getInstance();
	@Override
	public long insert(ScanQrcode scanQrcode) {
		return scanCodeDao.insert(scanQrcode);
	}

	@Override
	public long update(ScanQrcode scanQrcode) {
		return scanCodeDao.update(scanQrcode);
	}

	@Override
	public ScanQrcode findByWechatId(long wechatId) {
		return scanCodeDao.findByWechatId(wechatId);
	}

}
