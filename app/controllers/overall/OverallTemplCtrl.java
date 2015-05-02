/**
 * @Project:assistant
 * @Title: OverallTemplCtrl.java
 * @date: 2015-3-5 下午9:11:51
 * @version 1.0
 */
package controllers.overall;

import play.mvc.Controller;
import assistant.app.overall.logic.IOverallLogic;
import assistant.app.overall.logic.impl.OverallLogicImpl;
import assistant.app.overall.model.Overall;

/**
 * @ClassName OverallTemplCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午9:11:51
 */
public class OverallTemplCtrl extends Controller {

	private static IOverallLogic overallLogic = OverallLogicImpl.getInstance();

	public static void overrallTempl(long overallId) {
		Overall overall = overallLogic.findById(overallId);
		renderTemplate("/Application/360/overall_templ.html", overall);
	}

	// 渲染模板
	public static void overall(long id) {	
		Overall overall = overallLogic.findById(id);
		String overalXml = overallLogic.renderXml(overall);
		renderXml(overalXml);
	}

}
