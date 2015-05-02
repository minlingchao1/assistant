/**
 * @Project:assistant
 * @Title: ISubScribeLogic.java
 * @date: 2015-2-6 下午6:34:11
 * @version 1.0
 */
package assistant.app.subscribe.logic;

import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName ISubScribeLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午6:34:11
 */
public interface ISubScribeLogic {
	
	public boolean addSubReply(Long wechatId, Long userId, int type, String replyMsg);
	
	public Subscribe findByWechatId(Long wechatId);

	public long countByWechatId(long wechatId);

	public String dealSubscribe(Long wechatId, String fromUserName, String toUserName);

}
