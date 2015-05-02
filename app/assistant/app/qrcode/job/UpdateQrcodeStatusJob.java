/**
 * @Project:assistant
 * @Title: UpdateQrcodeStatusJob.java
 * @date: 2015-2-13 下午12:09:22
 * @version 1.0
 */
package assistant.app.qrcode.job;

import java.util.List;

import org.slf4j.Logger;

import play.jobs.Every;
import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.base.job.base.OnceJob;
import assistant.app.qrcode.dal.IQrcodeDao;
import assistant.app.qrcode.dal.impl.QrcodeDaoImpl;
import assistant.app.qrcode.model.Qrcode;

/**
 * @ClassName UpdateQrcodeStatusJob
 * @Description 更新二维码状态
 * @author minlingchao
 * @date 2015-2-13 下午12:09:22
 */
@Every("10s")
public class UpdateQrcodeStatusJob extends OnceJob<Qrcode> {

	public static final Logger LOG = LogCongfig.QRCODELOG;
	
	private IQrcodeDao qrcodeDao = QrcodeDaoImpl.getInstance();

	@Override
	public List<Qrcode> getAllData() {
		return qrcodeDao.findTempQrcode();
	}

	@Override
	public boolean dealOne(Qrcode qrcode) {
		long now = System.currentTimeMillis();
		if (now - qrcode.getCreateTime() > qrcode.getExpireSeconds() * 1000L) {
			qrcode.setStatus(WechatConfig.QR_STATUS_OUT);
			qrcodeDao.update(qrcode);
		}
		return false;
	}

	@Override
	public Logger getLog() {
		return LOG;
	}

}
