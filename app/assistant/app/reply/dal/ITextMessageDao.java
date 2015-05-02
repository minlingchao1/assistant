/**
 * @Project:assistant
 * @Title: ITextMessageDao.java
 * @date: 2015-2-7 下午3:33:26
 * @version 1.0
 */
package assistant.app.reply.dal;

import assistant.app.reply.model.TextMessage;

/**
 * @ClassName ITextMessageDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:33:26
 */
public interface ITextMessageDao {
	/**
	 * 添加
	 */

	public long insert(TextMessage textMessage);
	/**
	 * 删除
	 */

}
