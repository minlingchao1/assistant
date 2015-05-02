/**
 * @Project:assistant
 * @Title: IVideoMessageDao.java
 * @date: 2015-2-7 下午3:39:00
 * @version 1.0
 */
package assistant.app.reply.dal;

import assistant.app.reply.model.VideoMessage;

/**
 * @ClassName IVideoMessageDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:39:00
 */
public interface IVideoMessageDao {

	/**
	 * 添加
	 */
	public long insert(VideoMessage videoMessage);
}
