/**
 * @Project:assistant
 * @Title: IMenuReplyRuleDao.java
 * @date: 2015-2-21 下午5:00:33
 * @version 1.0
 */
package assistant.app.reply.dal;

import assistant.app.reply.model.MenuReplyRule;

/**
 * @ClassName IMenuReplyRuleDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午5:00:33
 */
public interface IMenuReplyRuleDao {

	public boolean add(Long wechatId, String msgType, String menuKey, String replyMsg);

	public MenuReplyRule findByKey(String eventKey, Long wechatId);

}
