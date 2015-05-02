/**
 * @Project:assistant
 * @Title: ReplyMsgTempleteDaoImpl.java
 * @date: 2015-2-6 下午8:52:18
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IReplyMsgTempleteDao;
import assistant.app.reply.dao.mapper.IReplyMsgTempleteMapper;
import assistant.app.reply.model.ReplyMsgTemplete;
import assistant.app.subscribe.dal.ISubscribeDao;
import assistant.app.subscribe.dal.impl.SubscribeDaoImpl;
import assistant.app.subscribe.dao.mapper.ISubscribeMapper;
import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName ReplyMsgTempleteDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午8:52:18
 */
public class ReplyMsgTempleteDaoImpl implements IReplyMsgTempleteDao {

	private static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;

	private static ReplyMsgTempleteDaoImpl instance = new ReplyMsgTempleteDaoImpl();



	private ReplyMsgTempleteDaoImpl() {

	}

	public static ReplyMsgTempleteDaoImpl getInstance() {
		return instance;
	}

	private ISubscribeDao subscribeDao = SubscribeDaoImpl.getInstance();

	@Override
	public ReplyMsgTemplete findById(Long id) {
		ReplyMsgTemplete result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IReplyMsgTempleteMapper subscribeMapper = session.getMapper(IReplyMsgTempleteMapper.class);
			result = subscribeMapper.findById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public long update(ReplyMsgTemplete templete, int type) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IReplyMsgTempleteMapper replyMsgTempleteMapper = session.getMapper(IReplyMsgTempleteMapper.class);

			Subscribe subscribe = subscribeDao.findByMsgId(templete.getId());
			subscribe.setType(type);
			ISubscribeMapper subscribeMapper = session.getMapper(ISubscribeMapper.class);
			subscribeMapper.update(subscribe);

			result = replyMsgTempleteMapper.update(templete);
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
	public List<ReplyMsgTemplete> findAll() {
		List<ReplyMsgTemplete> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IReplyMsgTempleteMapper subscribeMapper = session.getMapper(IReplyMsgTempleteMapper.class);
			result = subscribeMapper.findAll();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public long update(ReplyMsgTemplete templete) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			IReplyMsgTempleteMapper replyMsgTempleteMapper = session.getMapper(IReplyMsgTempleteMapper.class);
			result = replyMsgTempleteMapper.update(templete);
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
