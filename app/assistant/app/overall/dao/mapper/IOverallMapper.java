/**
 * @Project:assistant
 * @Title: IOverallMapper.java
 * @date: 2015-3-5 下午4:32:29
 * @version 1.0
 */
package assistant.app.overall.dao.mapper;

import java.util.List;

import assistant.app.overall.model.Overall;

/**
 * @ClassName IOverallMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午4:32:29
 */
public interface IOverallMapper {

	public long insert(Overall overall);
	
	public List<Overall> getList(long wechatId);

	public Overall findById(long id);

}
