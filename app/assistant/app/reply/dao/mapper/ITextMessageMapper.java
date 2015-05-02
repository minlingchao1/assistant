
package assistant.app.reply.dao.mapper;

import assistant.app.reply.model.TextMessage;

/**
 * @ClassName ITextMessageMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午5:22:59
 */
public interface ITextMessageMapper {

	public long insert(TextMessage textMessage);
	
	public long delete(Long id);
}
