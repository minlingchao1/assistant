/**
 * @Project:assistant
 * @Title: IMenuReplyRule.java
 * @date: 2015-2-21 下午4:47:43
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import org.apache.ibatis.annotations.Param;

import assistant.app.reply.model.MenuReplyRule;

/**
 * @ClassName IMenuReplyRule
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午4:47:43
 */
public interface IMenuReplyRuleMapper {

	public long insert(MenuReplyRule menuReplyRule);

	public MenuReplyRule findByKey(@Param("eventKey") String eventKey, @Param("wechatId") Long wechatId);

}
