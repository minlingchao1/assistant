package assistant.app.base.config;

/**
 * @author minlingchao
 * @date 2014-12-8 上午11:04:42
 * @description 基础配置
 * @version
 */

public class BaseConfigs {

    public static class SESSION {

        /**
         * 商户ID
         */
        public static final String SEESION_MERCHANT_ID = "session_merchant_id";

        /**
         * 商户名称
         */
        public static final String SEESION_MERCHANT_NAME = "session_merchant_name";

        /**
         * 商户信息
         */
        public static final String SEESION_MERCHANT = "session_merchant";

        /**
         * 管理员id
         */

        public static final String SESSION_OM_ADMIN_ID = "session_om_admin_id";

        /**
         * 管理员信息
         */

        public static final String SESSION_OM_ADMIN = "session_om_admin";


        /**
         * 登录IP
         */
        public static final String LOGIN_IP = "session_login_ip";

        /**
         * 验证码
         */
        public static final String VERTIFY = "session_vertify";
    }

}
