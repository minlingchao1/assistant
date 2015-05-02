/**
 * @Project:assistant
 * @Title: ISubscribeMapper.java
 * @date: 2015-2-6 下午2:57:11
 * @version 1.0
 */
package assistant.app.subscribe.dao.mapper;

import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName ISubscribeMapper
 * @Description
 * @author minlingchao
 * @date 2015-2-6 下午2:57:11
 */
public interface ISubscribeMapper {

	public long insert(Subscribe subScribe);

	public Subscribe findLimitOneByDate(Long wechatId);

	public long countByWechatId(long wechatId);

	public long delete(Subscribe subScribe);

	public Subscribe findByWechatId(Long wechatId);

	public Subscribe findByMsgId(long msgId);

	public long update(Subscribe subscribe);

	public long deleteByWechatId(long wechatId);


}
