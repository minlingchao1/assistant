/**
 * @Project:assistant
 * @Title: INextOpenIdLogic.java
 * @date: 2015-2-13 下午6:46:00
 * @version 1.0
 */
package assistant.app.custom.logic;

import java.util.List;

import assistant.app.custom.model.NextOpenId;

/**
 * @ClassName INextOpenIdLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午6:46:00
 */
public interface INextOpenIdLogic {

	public long insert(NextOpenId nextOpenId);

	public long update(NextOpenId nextOpenId);

	public List<NextOpenId> getAll();

}
