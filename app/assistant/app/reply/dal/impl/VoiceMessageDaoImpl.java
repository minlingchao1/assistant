/**
 * @Project:assistant
 * @Title: VoiceMessageDaoImpl.java
 * @date: 2015-2-7 下午3:41:35
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IVoiceMessageDao;
import assistant.app.reply.dao.mapper.IVoiceMessageMapper;
import assistant.app.reply.model.VoiceMessage;

/**
 * @ClassName VoiceMessageDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:41:35
 */
public class VoiceMessageDaoImpl implements IVoiceMessageDao{

	private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

	private static VoiceMessageDaoImpl instance = new VoiceMessageDaoImpl();

	private VoiceMessageDaoImpl() {

	}

	public static VoiceMessageDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(VoiceMessage voiceMessage) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IVoiceMessageMapper voiceMessageMapper = session.getMapper(IVoiceMessageMapper.class);
			result = voiceMessageMapper.insert(voiceMessage);
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
