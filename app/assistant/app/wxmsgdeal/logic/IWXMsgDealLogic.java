/**
 * @Project:assistant
 * @Title: IWXMsgDealLogic.java
 * @date: 2015-2-6 下午12:35:50
 * @version 1.0
 */
package assistant.app.wxmsgdeal.logic;

import play.mvc.Http.Request;

/**
 * @ClassName IWXMsgDealLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午12:35:50
 */
public interface IWXMsgDealLogic {

	/**
	 * 微信消息处理
	 */
	public String processRequest(Request request);
}
