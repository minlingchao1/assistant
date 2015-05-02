/**
 * @Project:assistant
 * @Title: IVoiceMessageMapper.java
 * @date: 2015-2-6 下午5:23:28
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import assistant.app.reply.model.VoiceMessage;

/**
 * @ClassName IVoiceMessageMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午5:23:28
 */
public interface IVoiceMessageMapper {

	public long insert(VoiceMessage voiceMessage);

	public long delete(Long id);
}
