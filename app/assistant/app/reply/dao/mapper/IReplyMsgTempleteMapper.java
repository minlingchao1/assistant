/**
 * @Project:assistant
 * @Title: IReplyMsgTempleteMapper.java
 * @date: 2015-2-6 下午7:34:05
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import java.util.List;

import assistant.app.reply.model.ReplyMsgTemplete;

/**
 * @ClassName IReplyMsgTempleteMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午7:34:05
 */
public interface IReplyMsgTempleteMapper {

    public long insert(ReplyMsgTemplete templete);

    public long delete(Long id);

    public ReplyMsgTemplete findById(Long id);

    public long update(ReplyMsgTemplete templete);

    public List<ReplyMsgTemplete> findAll();
}
