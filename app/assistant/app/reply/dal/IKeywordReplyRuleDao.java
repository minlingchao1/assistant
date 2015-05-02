/**
 * @Project:assistant
 * @Title: IKeyWordReplyRuleDao.java
 * @date: 2015-2-11 下午12:30:30
 * @version 1.0
 */
package assistant.app.reply.dal;

import java.util.List;

import assistant.app.reply.dto.KeywordReplyInfoDto;
import assistant.app.reply.model.KeywordReplyRule;

/**
 * @ClassName IKeyWordReplyRuleDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-11 下午12:30:30
 */
public interface IKeywordReplyRuleDao {

    /**
     * 添加文本消息及媒体消息关键字
     */
    public boolean addRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            String replyMsg);

    /**
     * 添加图文回复关键字
     */
    public boolean addNewsRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            Long msgId);

    /**
     * 模糊查询
     */
    public List<KeywordReplyRule> fuzzyQuery(String keyword);

    /**
     * 精确查询
     */
    public List<KeywordReplyRule> exactMatch(String keyword);

    /**
     * 查找相应微信账号的所有关键字
     */
    public List<KeywordReplyInfoDto> findAll(long wechatId);

    /**
     * 根据Id查找
     */
    public KeywordReplyInfoDto findById(Long id);

    /**
     * 删除
     */
    public boolean delete(Long id);

}
