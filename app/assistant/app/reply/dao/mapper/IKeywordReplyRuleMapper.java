/**
 * @Project:assistant
 * @Title: IKeywordRuleMapper.java
 * @date: 2015-2-11 下午12:21:54
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import java.util.List;

import assistant.app.reply.dto.KeywordReplyInfoDto;
import assistant.app.reply.model.KeywordReplyRule;

/**
 * @ClassName IKeywordRuleMapper
 * @Description
 * @author minlingchao
 * @date 2015-2-11 下午12:21:54
 */
public interface IKeywordReplyRuleMapper {

    public long insert(KeywordReplyRule replyRule);

    public long delete(Long id);

    public KeywordReplyRule findMsgId(Long id);

    public KeywordReplyInfoDto findById(Long id);

    public List<KeywordReplyRule> fuzzyQuery(String keyword);

    public List<KeywordReplyRule> exactMatch(String keyword);

    public List<KeywordReplyInfoDto> findAll(long wechatId);

}
