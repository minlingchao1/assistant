/**
 * @Project:assistant
 * @Title: INextOpenIdMapper.java
 * @date: 2015-2-13 下午6:35:59
 * @version 1.0
 */
package assistant.app.custom.dao.mapper;

import java.util.List;

import assistant.app.custom.model.NextOpenId;

/**
 * @ClassName INextOpenIdMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午6:35:59
 */
public interface INextOpenIdMapper {

	public long insert(NextOpenId nextOpenId);

	public long update(NextOpenId nextOpenId);

	public List<NextOpenId> getAll();

}
