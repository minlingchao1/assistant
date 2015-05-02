/**
 * @Project:assistant
 * @Title: IVoiceImageMessageDao.java
 * @date: 2015-2-7 下午3:38:49
 * @version 1.0
 */
package assistant.app.reply.dal;

import assistant.app.reply.model.VoiceMessage;


/**
 * @ClassName IVoiceImageMessageDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:38:49
 */
public interface IVoiceMessageDao {

	/**
	 * 添加
	 */
	public long insert(VoiceMessage voiceMessage);
}
