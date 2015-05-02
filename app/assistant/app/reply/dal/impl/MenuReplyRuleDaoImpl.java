/**
 * @Project:assistant
 * @Title: MenuReplyRuleDaoImpl.java
 * @date: 2015-2-21 下午5:01:40
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.menu.config.MenuType;
import assistant.app.reply.dal.IMenuReplyRuleDao;
import assistant.app.reply.dao.mapper.IMenuReplyRuleMapper;
import assistant.app.reply.dao.mapper.IReplyMsgTempleteMapper;
import assistant.app.reply.model.MenuReplyRule;
import assistant.app.reply.model.ReplyMsgTemplete;

/**
 * @ClassName MenuReplyRuleDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午5:01:40
 */
public class MenuReplyRuleDaoImpl implements IMenuReplyRuleDao {

	private static final Logger LOG = LogCongfig.MENULOG;

	private static MenuReplyRuleDaoImpl instance = new MenuReplyRuleDaoImpl();

	private MenuReplyRuleDaoImpl() {

	}

	public static MenuReplyRuleDaoImpl getInstance() {
		return instance;
	}

	@Override
	public boolean add(Long wechatId, String msgType, String menuKey, String replyMsg) {
		boolean isSuccess = false;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			Long msgId = null;

			if (msgType.equals(MenuType.REPLY_TEXT)) {
				// 创建消息模板
				ReplyMsgTemplete templete = new ReplyMsgTemplete();
				templete.setReplyMsg(replyMsg);
				templete.setCreateTime(System.currentTimeMillis());
				IReplyMsgTempleteMapper templeteMapper = session.getMapper(IReplyMsgTempleteMapper.class);
				templeteMapper.insert(templete);
				msgId = templete.getId();

				LOG.warn("msgId:{}", msgId);
			}

			// 创建菜单消息回复规则
			MenuReplyRule menuReplyRule = new MenuReplyRule();
			menuReplyRule.setMenuKey(menuKey);
			menuReplyRule.setMsgId(msgId);
			menuReplyRule.setWechatId(wechatId);
			menuReplyRule.setMsgType(msgType);
			IMenuReplyRuleMapper menuReplyRuleMapper = session.getMapper(IMenuReplyRuleMapper.class);
			menuReplyRuleMapper.insert(menuReplyRule);
			isSuccess = true;
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return isSuccess;
	}

	@Override
	public MenuReplyRule findByKey(String eventKey, Long wechatId) {
		MenuReplyRule menuReplyRule = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IMenuReplyRuleMapper menuReplyRuleMapper = session.getMapper(IMenuReplyRuleMapper.class);
			menuReplyRule = menuReplyRuleMapper.findByKey(eventKey, wechatId);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return menuReplyRule;
	}

}
