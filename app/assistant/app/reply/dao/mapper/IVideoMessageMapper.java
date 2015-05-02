/**
 * @Project:assistant
 * @Title: IVideoMessageMapper.java
 * @date: 2015-2-6 下午5:23:39
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import assistant.app.reply.model.VideoMessage;

/**
 * @ClassName IVideoMessageMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午5:23:39
 */
public interface IVideoMessageMapper {

	public long insert(VideoMessage videoMessage);

	public long delete(Long id);
}
