/**
 * @Project:assistant
 * @Title: KeyWordReplyRule.java
 * @date: 2015-2-11 下午12:02:42
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Index;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName KeyWordReplyRule
 * @Description 关键字及规则
 * @author minlingchao
 * @date 2015-2-11 下午12:02:42
 */
@Entity(name = KeywordReplyRule.TABLE_NAME)
public class KeywordReplyRule extends BasicModel {

	public static final String TABLE_NAME = "keyword_reply_rule";

	/**
	 * 规则名
	 */
	@Column(name = "rule_name")
	private String ruleName;

	/**
	 * 关键字
	 */
	@Column(name = "keyword")
	@Index(name = "IDX_ROOM_KEYWORD_REPLY_KEYWORD")
	private String keyword;

	/**
	 * 是否完全匹配
	 */
	@Column(name="is_all_match")
	private int isAllMatch;

	/**
	 * 消息ID
	 */
	@Column(name="msg_id")
	private Long msgId;

	/**
	 * 用户Id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 微信号Id
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 消息类型
	 */
	@Column(name = "type")
	private int type;

	public KeywordReplyRule() {
		// TODO Auto-generated constructor stub
	}

	public KeywordReplyRule(String ruleName, String keyword, int type, int isAllMatch, Long msgId, Long userId,
			Long wechatId) {

		this.ruleName = ruleName;
		this.keyword = keyword;
		this.isAllMatch = isAllMatch;
		this.msgId = msgId;
		this.userId = userId;
		this.wechatId = wechatId;
		this.type = type;
	}
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
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

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

}
