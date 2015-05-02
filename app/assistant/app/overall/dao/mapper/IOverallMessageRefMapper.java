/**
 * @Project:assistant
 * @Title: IOverallMessageRefMapper.java
 * @date: 2015-3-5 下午4:32:45
 * @version 1.0
 */
package assistant.app.overall.dao.mapper;

import assistant.app.overall.model.OverallMessageRef;

/**
 * @ClassName IOverallMessageRefMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午4:32:45
 */
public interface IOverallMessageRefMapper {

	public long insert(OverallMessageRef overallMessageRef);

	public OverallMessageRef findByOverallId(long overallId);

}
