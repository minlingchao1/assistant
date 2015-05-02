/**
 * @Project:assistant
 * @Title: INewsMessageRefMapper.java
 * @date: 2015-2-24 下午5:25:07
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import assistant.app.reply.model.NewsMessageRef;

/**
 * @ClassName INewsMessageRefMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-24 下午5:25:07
 */
public interface INewsMessageRefMapper {

	public long insert(NewsMessageRef newsMessageRef);

	public List<NewsMessageRef> getNews(@Param("userId") long userId, @Param("wechatId") long wechatId);

	public NewsMessageRef findById(long id);
}
