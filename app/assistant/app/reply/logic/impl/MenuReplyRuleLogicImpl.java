/**
 * @Project:assistant
 * @Title: MenuReplyRuleLogicImpl.java
 * @date: 2015-2-21 下午5:14:14
 * @version 1.0
 */
package assistant.app.reply.logic.impl;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IMenuReplyRuleDao;
import assistant.app.reply.dal.impl.MenuReplyRuleDaoImpl;
import assistant.app.reply.logic.IMenuReplyRuleLogic;
import assistant.app.reply.model.MenuReplyRule;

/**
 * @ClassName MenuReplyRuleLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午5:14:14
 */
public class MenuReplyRuleLogicImpl implements IMenuReplyRuleLogic {

	private static final Logger LOG = LogCongfig.MENULOG;

	private static MenuReplyRuleLogicImpl instance = new MenuReplyRuleLogicImpl();

	private MenuReplyRuleLogicImpl() {

	}

	public static MenuReplyRuleLogicImpl getInstance() {
		return instance;
	}

	private IMenuReplyRuleDao menuReplyRuleDao = MenuReplyRuleDaoImpl.getInstance();


	@Override
	public MenuReplyRule findByKey(String eventKey, Long wechatId) {
		return menuReplyRuleDao.findByKey(eventKey, wechatId);
	}

}
