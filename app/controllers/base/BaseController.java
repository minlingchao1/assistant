package controllers.base;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.After;
import play.mvc.Before;
import play.mvc.Controller;
import assistant.app.base.config.BaseConfigs;
import assistant.app.base.config.ReturnCode;
import assistant.app.base.dto.PagingDto;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.JsonUtil;
import assistant.app.common.util.NetworkUtil;

/**
 * @author minlingchao
 * @date 2014-12-4 下午5:36:23
 * @description
 * @version
 */

public class BaseController extends Controller {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    public static final String TAG = "BaseController";

    private static IWechatInfoLogic wechatInfoLogic = WechatInfoLogicImpl.getInstance();

    @Before
    public static void startTime() {
        LOG.info(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_NAME) + "  Request For " + request.url + ":"
                + request.action + " Starts," + " IP :" + getRequestIP());
        request.args.put("_ts", System.currentTimeMillis());
    }

    @After
    public static void endTime() {
        LOG.info("Action [" + request.url + "] took "
                + (System.currentTimeMillis() - (Long) request.current().args.get("_ts")) + " ms");
    }

    protected static String getRequestIP() {
        String ip = session.get(BaseConfigs.SESSION.LOGIN_IP);

        if (StringUtils.isEmpty(ip)) {
            return NetworkUtil.getRemoteIPForNginx(request);
        }

        return ip;
    }

    protected static Long getMerchantId() {
        return Long.valueOf(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID));
    }

    protected static ProcessStatus validWechatInfo(Long merchantId, Long wechatId) {

        WechatAccountInfo weAccountInfo = wechatInfoLogic.findByUserIdAndWechatId(merchantId, wechatId);
        if (weAccountInfo == null) {
            return new ProcessStatus(false, "用户没有管理该微信号权限!");
        } else {
            return new ProcessStatus(true, null);
        }

    }

    protected static void renderJsonSuccess() {
        renderJSON(JsonUtil.getJson(new BaseAjaxResult()));
    }

    protected static void renderJsonSuccess(int code, String msg) {
        renderJSON(JsonUtil.getJson(new BaseAjaxResult(true, code, msg)));
    }

    protected static void renderJsonFail() {
        renderJSON(JsonUtil.getJson(new BaseAjaxResult(false, ReturnCode.FAIL.getCode(), ReturnCode.FAIL.getMsg())));
    }

    protected static void renderJsonFail(int code, String msg) {
        renderJSON(JsonUtil.getJson(new BaseAjaxResult(false, code, msg)));
    }

    protected static void renderJsonAjaxResult(Object obj) {
        renderJSON(JsonUtil.getJson(new AjaxResult(obj)));
    }

    protected static void renderJsonAjaxResult(int code, String msg, Object obj) {
        renderJsonAjaxResult(true, code, msg, obj);
    }

    protected static void renderJsonAjaxResult(boolean success, int code, String msg, Object obj) {
        renderJSON(JsonUtil.getJson(new AjaxResult(success, code, msg, obj)));
    }

    protected static void renderJsonAjaxPageResult(boolean success, int code, String msg, Object res, int curPage,
            int pageCount, long count, boolean hasRecords) {
        renderJSON(JsonUtil.getJson(new AjaxPageResult(success, code, msg, res, curPage, pageCount, count, hasRecords)));
    }

    protected static void renderJsonAjaxPageResult(boolean success, int code, String msg, Object res,
            PagingDto pagingDto) {
        renderJSON(JsonUtil.getJson(new AjaxPageResult(success, code, msg, res, pagingDto.getCurPage(), pagingDto
                .getPageCount(), pagingDto.getCount(), pagingDto.hasRecords())));
    }

    protected static void renderJsonUploadResult(int error, String url) {
        renderJSON(JsonUtil.getJson(new UploadImgResult(error, url)));
    }

    /**
     * 
     * @author lizhong.chen
     * @date 2014年12月3日上午10:50:52
     * @description 状态结果
     * @version V1.0
     */
    @JsonAutoDetect
    public static class BaseAjaxResult implements Serializable {

        @JsonProperty
        private boolean success = true;

        @JsonProperty
        private int code = 200;

        @JsonProperty
        private String message = StringUtils.EMPTY;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public BaseAjaxResult() {
        }

        public BaseAjaxResult(String message) {
            this.message = message;
        }

        public BaseAjaxResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public BaseAjaxResult(boolean success, int code, String message) {
            this.success = success;
            this.code = code;
            this.message = message;
        }

    }

    @JsonAutoDetect
    public static class AjaxResult extends BaseAjaxResult {

        /**
         * 结果
         */
        @JsonProperty
        private Object results;

        public Object getResults() {
            return results;
        }

        public void setResults(Object results) {
            this.results = results;
        }

        public AjaxResult(Object res) {
            super(true, 200, null);
            this.results = res;
        }

        public AjaxResult(Boolean success, String message, Object res) {
            super(success, message);
            this.results = res;
        }

        public AjaxResult(int code, String message, Object res) {
            this(true, code, message, res);
        }

        public AjaxResult(Boolean success, int code, String message, Object res) {
            super(success, code, message);
            this.results = res;
        }
    }

    @JsonAutoDetect
    public static class AjaxPageResult extends AjaxResult {
        /**
         * 当前页
         */
        @JsonProperty
        private int curPage = 1;

        /**
         * 总页数
         */
        @JsonProperty
        private int pageCount = 1;

        /**
         * 总数
         */
        @JsonProperty
        private long count = 0;

        /**
         * 是否还有结果
         */
        @JsonProperty
        private boolean hasRecords = true;

        public AjaxPageResult(Object res, int curPage, int pageCount, long count, boolean hasRecords) {
            super(res);
            this.curPage = curPage;
            this.pageCount = pageCount;
            this.count = count;
            this.hasRecords = hasRecords;
        }

        public AjaxPageResult(boolean success, int code, String msg, Object res, int curPage, int pageCount,
                long count, boolean hasRecords) {
            super(success, code, msg, res);
            this.curPage = curPage;
            this.pageCount = pageCount;
            this.count = count;
            this.hasRecords = hasRecords;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public boolean isHasRecords() {
            return hasRecords;
        }

        public void setHasRecords(boolean hasRecords) {
            this.hasRecords = hasRecords;
        }
    }

    /**
     * 保存上传图片之后返回的json
     * 
     * @author Administrator
     * 
     */

    @JsonAutoDetect
    public static class UploadImgResult {

        /**
         * 是否上传成功
         */
        private int error;

        /**
         * 返回的图片url
         */
        private String url;

        public UploadImgResult(int error, String url) {
            this.error = error;
            this.url = url;
        }

        public int getError() {
            return error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

}
