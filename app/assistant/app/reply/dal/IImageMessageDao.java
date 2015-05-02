/**
 * @Project:assistant
 * @Title: IImageMessageDao.java
 * @date: 2015-2-7 下午3:38:37
 * @version 1.0
 */
package assistant.app.reply.dal;

import assistant.app.reply.model.ImageMessage;

/**
 * @ClassName IImageMessageDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:38:37
 */
public interface IImageMessageDao {

	/**
	 * 添加
	 */
	public long insert(ImageMessage imageMessage);

}
