/**
 * @Project:assistant
 * @Title: ReplyMsgTempleteLogicImpl.java
 * @date: 2015-2-6 下午8:55:33
 * @version 1.0
 */
package assistant.app.reply.logic.impl;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IReplyMsgTempleteDao;
import assistant.app.reply.dal.impl.ReplyMsgTempleteDaoImpl;
import assistant.app.reply.logic.IReplyMsgTempleteLogic;
import assistant.app.reply.model.ReplyMsgTemplete;

/**
 * @ClassName ReplyMsgTempleteLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午8:55:33
 */
public class ReplyMsgTempleteLogicImpl implements IReplyMsgTempleteLogic {

	private static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;

	private static ReplyMsgTempleteLogicImpl instance = new ReplyMsgTempleteLogicImpl();

	private ReplyMsgTempleteLogicImpl() {

	}

	public static ReplyMsgTempleteLogicImpl getInstance() {
		return instance;
	}

	private IReplyMsgTempleteDao templeteDao = ReplyMsgTempleteDaoImpl.getInstance();
	@Override
	public ReplyMsgTemplete findById(Long id) {
		return templeteDao.findById(id);
	}

	@Override
	public long update(ReplyMsgTemplete templete, int type) {
		return templeteDao.update(templete, type);
	}

}
