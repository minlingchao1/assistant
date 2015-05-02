/**
 * @Project:assistant
 * @Title: QrcodeDaoImpl.java
 * @date: 2015-2-12 下午6:03:12
 * @version 1.0
 */
package assistant.app.qrcode.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.qrcode.dal.IQrcodeDao;
import assistant.app.qrcode.dao.mapper.IQrcodeMapper;
import assistant.app.qrcode.model.Qrcode;

/**
 * @ClassName QrcodeDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-12 下午6:03:12
 */
public class QrcodeDaoImpl implements IQrcodeDao {

	private static final Logger LOG = LogCongfig.QRCODELOG;

	private static QrcodeDaoImpl instance = new QrcodeDaoImpl();

	private QrcodeDaoImpl() {

	}

	public static QrcodeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(Qrcode qrcode) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IQrcodeMapper qrcpdeMapper = session.getMapper(IQrcodeMapper.class);
			result = qrcpdeMapper.insert(qrcode);
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
	public List<Qrcode> findTempQrcode() {
		List<Qrcode> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IQrcodeMapper qrcpdeMapper = session.getMapper(IQrcodeMapper.class);
			result = qrcpdeMapper.findTempQrcode();
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
	public long update(Qrcode qrcode) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IQrcodeMapper qrcpdeMapper = session.getMapper(IQrcodeMapper.class);
			result = qrcpdeMapper.update(qrcode);
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
	public List<Qrcode> findByWechatId(long wechatId) {
		List<Qrcode> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IQrcodeMapper qrcpdeMapper = session.getMapper(IQrcodeMapper.class);
			result = qrcpdeMapper.findByWechatId(wechatId);
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
