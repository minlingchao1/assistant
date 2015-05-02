/**
 * @Project:assistant
 * @Title: ICustomGroupMapper.java
 * @date: 2015-2-13 下午5:43:30
 * @version 1.0
 */
package assistant.app.custom.dao.mapper;

import org.apache.ibatis.annotations.Param;

import assistant.app.custom.model.CustomGroup;

/**
 * @ClassName ICustomGroupMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午5:43:30
 */
public interface ICustomGroupMapper {

	public long insert(CustomGroup group);
	
	public long update(CustomGroup group);

	public CustomGroup findById(@Param("wechatId") long wechatId, @Param("id") long id);

}
