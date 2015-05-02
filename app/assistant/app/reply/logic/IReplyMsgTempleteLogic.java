/**
 * @Project:assistant
 * @Title: IReplyMsgTempleteLogic.java
 * @date: 2015-2-6 下午8:55:51
 * @version 1.0
 */
package assistant.app.reply.logic;

import assistant.app.reply.model.ReplyMsgTemplete;

/**
 * @ClassName IReplyMsgTempleteLogic
 * @Description 回复信息模板的处理
 * @author minlingchao
 * @date 2015-2-6 下午8:55:51
 */
public interface IReplyMsgTempleteLogic {

	/**
	 * 根据Id查找
	 */
	public ReplyMsgTemplete findById(Long id);

	/**
	 * 更新
	 */
	public long update(ReplyMsgTemplete templete, int type);
}
