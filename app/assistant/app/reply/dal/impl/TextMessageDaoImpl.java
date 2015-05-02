/**
 * @Project:assistant
 * @Title: TextMessageDaoImpl.java
 * @date: 2015-2-7 下午3:34:23
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.ITextMessageDao;
import assistant.app.reply.dao.mapper.ITextMessageMapper;
import assistant.app.reply.model.TextMessage;

/**
 * @ClassName TextMessageDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:34:23
 */
public class TextMessageDaoImpl implements ITextMessageDao {

	private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

	private static TextMessageDaoImpl instance = new TextMessageDaoImpl();

	private TextMessageDaoImpl() {

	}

	public static TextMessageDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(TextMessage textMessage) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			ITextMessageMapper textMessageMapper = session.getMapper(ITextMessageMapper.class);
			result = textMessageMapper.insert(textMessage);
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
