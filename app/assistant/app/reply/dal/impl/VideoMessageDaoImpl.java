/**
 * @Project:assistant
 * @Title: VideoIMessageDaoImpl.java
 * @date: 2015-2-7 下午3:41:46
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IVideoMessageDao;
import assistant.app.reply.dao.mapper.IVideoMessageMapper;
import assistant.app.reply.model.VideoMessage;

/**
 * @ClassName VideoIMessageDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:41:46
 */
public class VideoMessageDaoImpl implements IVideoMessageDao {

	private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

	private static VideoMessageDaoImpl instance = new VideoMessageDaoImpl();

	private VideoMessageDaoImpl() {

	}

	public static VideoMessageDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(VideoMessage videoMessage) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IVideoMessageMapper videoMessageMapper = session.getMapper(IVideoMessageMapper.class);
			result = videoMessageMapper.insert(videoMessage);
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
