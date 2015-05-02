/**
 * @Project:assistant
 * @Title: KeywordReply.java
 * @date: 2015-2-12 下午1:22:01
 * @version 1.0
 */
package assistant.app.reply.dto;

/**
 * @ClassName KeywordReply
 * @Description 前端显示关键字回复内容
 * @author minlingchao
 * @date 2015-2-12 下午1:22:01
 */
public class KeywordReplyInfoDto {

    /**
     * 关键字Id
     */
    private Long id;

    /**
     * 回复信息
     */
    private String replyMsg;

    /**
     * 规则名
     */
    private String ruleName;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 是否完全匹配
     */
    private int isAllMatch;

    /**
     * 回复消息类型
     */
    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getIsAllMatch() {
        return isAllMatch;
    }

    public void setIsAllMatch(int isAllMatch) {
        this.isAllMatch = isAllMatch;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

}
