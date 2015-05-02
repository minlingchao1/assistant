/**
 * @Project:assistant
 * @Title: SubscribeDaoImpl.java
 * @date: 2015-2-6 下午3:15:52
 * @version 1.0
 */
package assistant.app.subscribe.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.bind.dal.IWechatAccessTokenDao;
import assistant.app.bind.dal.IWechatInfoDao;
import assistant.app.bind.dal.impl.WechatAccessTokenDaoImpl;
import assistant.app.bind.dal.impl.WechatInfoDaoImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.wxutil.MessageUtil;
import assistant.app.common.util.wxutil.UploadMediaUtil;
import assistant.app.reply.dao.mapper.IReplyMsgTempleteMapper;
import assistant.app.reply.model.ReplyMsgTemplete;
import assistant.app.subscribe.dal.ISubscribeDao;
import assistant.app.subscribe.dao.mapper.ISubscribeMapper;
import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName SubscribeDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午3:15:52
 */
public class SubscribeDaoImpl implements ISubscribeDao {

	private static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;

	private static SubscribeDaoImpl instance = new SubscribeDaoImpl();

	private IWechatInfoDao wechatInfoDao = WechatInfoDaoImpl.getInstance();

	private IWechatAccessTokenDao wechatAccessTokenDao = WechatAccessTokenDaoImpl.getInstance();

	private SubscribeDaoImpl() {

	}

	public static SubscribeDaoImpl getInstance() {
		return instance;
	}


	@Override
	public boolean addSubscribeInfo(Long wechatId, Long userId, int type, String replyMsg) {
		boolean isSuccess = false;
		String mediaId = "0";
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
        	Long msgId=null;

			WechatAccountInfo wechatAccountInfo = wechatInfoDao.findById(Long.valueOf(wechatId));
			WechatAccessToken wechatAccessToken = wechatAccessTokenDao.findByWechatId(wechatAccountInfo.getId());
			// LOG.warn("wehcatId:{},accessToken:{}", wechatAccountInfo.getId(),
			// wechatAccountInfo.getAccessToken());
			LOG.warn("wechatId:{},accessToken:{}", wechatId, wechatAccessToken.getAccessToken());
			if (type != 0) {

				if (type == 1) {
					mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
							MessageUtil.RESP_MESSAGE_TYPE_IMAGE, replyMsg);
				} else if (type == 2) {
					mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
							MessageUtil.RESP_MESSAGE_TYPE_VOICE, replyMsg);
				} else {
					mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
							MessageUtil.RESP_MESSAGE_TYPE_VIDEO, replyMsg);
				}
			}
			ReplyMsgTemplete templete = new ReplyMsgTemplete();
			templete.setReplyMsg(replyMsg);
			templete.setMediaId(mediaId);
			templete.setCreateTime(System.currentTimeMillis());
			IReplyMsgTempleteMapper templeteMapper = session.getMapper(IReplyMsgTempleteMapper.class);
			templeteMapper.insert(templete);
			msgId = templete.getId();

			Subscribe subScribe = new Subscribe(wechatId, userId, msgId, type);
			ISubscribeMapper subscribeMapper = session.getMapper(ISubscribeMapper.class);
			subscribeMapper.insert(subScribe);
			isSuccess = true;
            session.commit();
        } catch(Exception e){
        	LOG.error(e.getMessage(),e);
        	session.rollback();
        }finally {
            session.close();
        }
		return isSuccess;
	}

	@Override
	public Subscribe findLimieOne() {
		return null;
	}

	@Override
	public long countByWechatId(long wechatId) {
		long result = 0L;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			ISubscribeMapper subscribeMapper = session.getMapper(ISubscribeMapper.class);
			result = subscribeMapper.countByWechatId(wechatId);
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public long delete(Subscribe subScribe) {
		return 0;
	}

	@Override
	public Subscribe findByWechatId(Long wechatId) {
		Subscribe result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			ISubscribeMapper subscribeMapper = session.getMapper(ISubscribeMapper.class);
			result = subscribeMapper.findByWechatId(wechatId);
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public Subscribe findByMsgId(long msgId) {
		Subscribe result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			ISubscribeMapper subscribeMapper = session.getMapper(ISubscribeMapper.class);
			result = subscribeMapper.findByMsgId(msgId);
		} finally {
			session.close();
		}
		return result;
	}


}
