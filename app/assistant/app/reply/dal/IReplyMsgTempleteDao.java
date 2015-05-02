/**
 * @Project:assistant
 * @Title: IReplyMsgTempleteDao.java
 * @date: 2015-2-6 下午8:51:48
 * @version 1.0
 */
package assistant.app.reply.dal;

import java.util.List;

import assistant.app.reply.model.ReplyMsgTemplete;

/**
 * @ClassName IReplyMsgTempleteDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午8:51:48
 */
public interface IReplyMsgTempleteDao {

	public ReplyMsgTemplete findById(Long id);

	public long update(ReplyMsgTemplete templete, int type);

	public long update(ReplyMsgTemplete templete);

	public List<ReplyMsgTemplete> findAll();
}
