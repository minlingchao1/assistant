/**
 * @Project:assistant
 * @Title: ScanCodeDaoImpl.java
 * @date: 2015-2-11 下午4:15:16
 * @version 1.0
 */
package assistant.app.qrcode.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.qrcode.dal.IScanQrcodeDao;
import assistant.app.qrcode.dao.mapper.IScanQrcodeMapper;
import assistant.app.qrcode.model.ScanQrcode;

/**
 * @ClassName ScanCodeDaoImpl
 * @Description 二维码扫描的处理
 * @author minlingchao
 * @date 2015-2-11 下午4:15:16
 */
public class ScanQrcodeDaoImpl implements IScanQrcodeDao {

	private static final Logger LOG = LogCongfig.QRCODELOG;

	private static ScanQrcodeDaoImpl instance = new ScanQrcodeDaoImpl();

	private ScanQrcodeDaoImpl() {

	}

	public static ScanQrcodeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(ScanQrcode scanQrcode) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IScanQrcodeMapper scanCodeMapper = session.getMapper(IScanQrcodeMapper.class);
			result = scanCodeMapper.insert(scanQrcode);
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public long update(ScanQrcode scanQrcode) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IScanQrcodeMapper scanCodeMapper = session.getMapper(IScanQrcodeMapper.class);
			result = scanCodeMapper.update(scanQrcode);
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public ScanQrcode findByWechatId(long wechatId) {
		ScanQrcode result = null;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IScanQrcodeMapper scanCodeMapper = session.getMapper(IScanQrcodeMapper.class);
			result = scanCodeMapper.findByWechatId(wechatId);
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
