/**
 * @Project:assistant
 * @Title: IWechatAccountUserRefMapper.java
 * @date: 2015-2-1 下午6:46:04
 * @version 1.0
 */
package assistant.app.bind.dao.mapper;

import assistant.app.bind.model.WechatAccountUserRef;

/**
 * @ClassName IWechatAccountUserRefMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-1 下午6:46:04
 */
public interface IWechatAccountUserRefMapper {
	
	public long insert(WechatAccountUserRef wechatAccountUserRef);
	
	public Long findWechatId(Long userId);

}
