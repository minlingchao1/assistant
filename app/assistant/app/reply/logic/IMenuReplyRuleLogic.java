/**
 * @Project:assistant
 * @Title: IMenuReplyRuleLogic.java
 * @date: 2015-2-21 下午5:12:14
 * @version 1.0
 */
package assistant.app.reply.logic;

import assistant.app.reply.model.MenuReplyRule;

/**
 * @ClassName IMenuReplyRuleLogic
 * @Description 消息回复规则
 * @author minlingchao
 * @date 2015-2-21 下午5:12:14
 */
public interface IMenuReplyRuleLogic {
	
	public MenuReplyRule findByKey(String eventKey, Long wechatId);

}
