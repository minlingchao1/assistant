/**
 * @Project:assistant
 * @Title: ISubscribeDao.java
 * @date: 2015-2-6 下午3:15:41
 * @version 1.0
 */
package assistant.app.subscribe.dal;

import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName ISubscribeDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午3:15:41
 */
public interface ISubscribeDao {

	public boolean addSubscribeInfo(Long wechatId, Long userId, int type, String replyMsg);
	
	public Subscribe findLimieOne();
	
	public long countByWechatId(long wechatId);

	public long delete(Subscribe subScribe);

	public Subscribe findByWechatId(Long wechatId);

	public Subscribe findByMsgId(long msgId);


}
