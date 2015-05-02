/**
 * @Project:assistant
 * @Title: SubscribeLogicImpl.java
 * @date: 2015-2-6 下午6:35:55
 * @version 1.0
 */
package assistant.app.subscribe.logic.impl;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IReplyMsgTempleteDao;
import assistant.app.reply.dal.impl.ReplyMsgTempleteDaoImpl;
import assistant.app.reply.logic.IReplyMessageLogic;
import assistant.app.reply.logic.impl.ReplyMessageLogicImpl;
import assistant.app.reply.model.ReplyMsgTemplete;
import assistant.app.subscribe.dal.ISubscribeDao;
import assistant.app.subscribe.dal.impl.SubscribeDaoImpl;
import assistant.app.subscribe.logic.ISubScribeLogic;
import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName SubscribeLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午6:35:55
 */
public class SubscribeLogicImpl implements ISubScribeLogic {

	private static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;

	private static SubscribeLogicImpl instance = new SubscribeLogicImpl();

	private SubscribeLogicImpl() {

	}

	public static SubscribeLogicImpl getInstance() {
		return instance;
	}

	private ISubscribeDao subscribeDao = SubscribeDaoImpl.getInstance();

	private IReplyMessageLogic replyMessageLogic = ReplyMessageLogicImpl.getInstance();
	
	private IReplyMsgTempleteDao replyMsgTempleteDao = ReplyMsgTempleteDaoImpl.getInstance();

	@Override
	public boolean addSubReply(Long wechatId, Long userId, int type, String replyMsg) {
		return subscribeDao.addSubscribeInfo(wechatId, userId, type, replyMsg);
	}

	@Override
	public Subscribe findByWechatId(Long wechatId) {
		return subscribeDao.findByWechatId(wechatId);
	}

	@Override
	public long countByWechatId(long wechatId) {
		return subscribeDao.countByWechatId(wechatId);
	}

	@Override
	public String dealSubscribe(Long wechatId, String fromUserName, String toUserName) {
		String reqXml = null;
		Subscribe subscribe = findByWechatId(wechatId);
		if (subscribe != null) {
			ReplyMsgTemplete templete = replyMsgTempleteDao.findById(subscribe.getMsgId());
			reqXml = replyMessageLogic.createReplyMessage(templete.getId(), subscribe.getType(), fromUserName,
					toUserName, templete.getReplyMsg(), templete.getMediaId());
		}
		return reqXml;
	}

}
