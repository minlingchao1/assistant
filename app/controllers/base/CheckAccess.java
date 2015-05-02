/**
 * @Project:assistant
 * @Title: CheckAccess.java
 * @date: 2015-2-4 下午3:49:48
 * @version 1.0
 */
package controllers.base;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.Before;
import play.mvc.Http.Cookie;
import assistant.app.base.config.BaseConfigs;

/**
 * @ClassName CheckAccess
 * @Description 检查是否登录
 * @author minlingchao
 * @date 2015-2-4 下午3:49:48
 */
public class CheckAccess extends BaseController {

	public static final Logger LOG = LoggerFactory.getLogger(CheckAccess.class);

	@Before(unless = { "login", "index", "register" })
	static void check() {
		// 从session中获取
		String merchantId = session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID);

		// 查找cookie中是否存在merchantId
		if (StringUtils.isEmpty(merchantId)) {
			Cookie cookie = request.cookies.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID);
			if (cookie != null) {
				merchantId = cookie.value;
			}
		}
		if (merchantId == null) {
			render("/Application/index.html");
        }
    }
}
