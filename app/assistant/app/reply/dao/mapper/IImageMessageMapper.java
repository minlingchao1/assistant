/**
 * @Project:assistant
 * @Title: IImageMessageMapper.java
 * @date: 2015-2-6 下午5:23:14
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import assistant.app.reply.model.ImageMessage;

/**
 * @ClassName IImageMessageMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午5:23:14
 */
public interface IImageMessageMapper {

	public long insert(ImageMessage imageMessage);

	public long delete(Long id);
}
