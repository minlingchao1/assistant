/**
 * @Project:assistant
 * @Title: ImageMessageDaoImpl.java
 * @date: 2015-2-7 下午3:42:05
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IImageMessageDao;
import assistant.app.reply.dao.mapper.IImageMessageMapper;
import assistant.app.reply.model.ImageMessage;

/**
 * @ClassName ImageMessageDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:42:05
 */
public class ImageMessageDaoImpl implements IImageMessageDao {

	private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

	private static ImageMessageDaoImpl instance = new ImageMessageDaoImpl();

	private ImageMessageDaoImpl() {

	}

	public static ImageMessageDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(ImageMessage imageMessage) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IImageMessageMapper imageMessageMapper = session.getMapper(IImageMessageMapper.class);
			result = imageMessageMapper.insert(imageMessage);
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
