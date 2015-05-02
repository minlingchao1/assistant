/**
 * 
 */
package assistant.app.base.config;

/**
 * 
 * @title ReturnCodeMsg.java
 * @author ChenLz
 * @data 2013-12-12下午12:20:17
 * @description 接口返回的code和msg
 * @version V1.0
 * 
 */
public enum ReturnCode {
    /**
     * 
     */
    SUCCESS(200, "操作正确"),
    /**
     * 
     */
    DATE_NOTFOUND(520, "数据不存在"),
    /**
     * 
     */
    PARAM_ISNOTVAILD(570, "参数不正确"),
    /**
     * 
     */
    FAIL(574, "内部异常"),
    /**
     * 
     */
    NOT_HAVE_CONTACT(575, "请先设置联系方式"),
    /**
     * 
     */
    PAGE_OUT_SIDE(576, "该页码无效"),
    /**
     * 
     */
    TEMP_MAX(577, "模板已达最多数量"),
    /**
     * 
     */
    EMAIL_FORMAT_ERR(578, "邮件格式出错"),
    /**
     * 
     */
    TEMP_LAST(579, "请至少保留一个模板"),
    /**
     * 
     */
    TEMP_IS_NULL(580, "请填写模板内容"),
    /**
     * 
     */
    TMALL_CANNOT_OPEN_FUNC(581, "自动评价暂不支持天猫用户"),
    /**
     * 
     */
    TELE_FORMAT_ERR(582, "手机号格式出错"),
    /**
     * 
     */
    SMS_NOT_ENOUGH(583, "短信余量不足"),
    /**
     * 没有设置邮箱
     */
    NOT_HAVE_EMAIL(584, "请先设置邮箱"),

    // 700 code is for user
    /**
     * 用户已存在
     */
    USER_IS_EXIST(700, "用户已存在"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(701, "用户不存在"),
    /**
     * 用户新增失败
     */
    USER_ADD_ERR(702, "新增失败"),
    /**
     * 设置头像失败
     */
    USER_SET_AVATER_FAIL(703, "用户设置头像失败"),
    /**
     * 用户不能设置其它用户信息
     */
    USER_SET_OTHER_ERR(704, "用户不能设置其它用户信息"),
    /**
     * 设置用户信息失败
     */
    USER_SET_FAIL(705, "用户设置用户信息失败"),
    /**
     * 用户操作非个人所有的数据
     */
    USER_SET_IMPERSONAL(706, "用户操作非个人所有的数据"),
    /**
     * 用户已签到
     */
    USER_HAS_SIGN(707, "用户已签到"),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(710, "用户未登录"),
    /**
     * 登录失败
     */
    LOGIN_FAIL(711, "登录失败,账号或密码错误"),
    /**
     * 用户昵称已存在
     */
    USER_NICK_EXIST(712, "昵称已被人占用"),
    /**
     * 账号已存在
     */
    USER_ACCOUNT_EXIST(713, "账号已被人占用"),
    /**
     * 用户昵称不能为空
     */
    USER_NICK_IS_NOT_NULL(714, "昵称不能为空"),
    /**
     * 账号不能为空
     */
    USER_ACCOUNT_IS_NOT_NULL(715, "账号不能为空"),
    /**
     * 密码不能为空
     */
    USER_PASSWORD_IS_NOT_NULL(716, "密码不能为空"),
    /**
     * 账号不存在
     */
    USER_ACCOUNT_IS_NOT_EXIST(717, "登录失败,账号不存在"),
    /**
     * 密码错误
     */
    USER_PASSWORD_IS_ERR(718, "登录失败,密码错误"),
    /**
     * 上传失败
     */
    UPLOAD_FAIL(801, "上传失败"),
    /**
     * 上传图片失败
     */
    UPLOAD_PIC_FAIL(802, "图片上传失败"),
    /**
     * 上传格式出错
     */
    UPLOAD_FORMAT_FAIL(803, "上传格式出错"),
    // ///////////////////bbs/////////////////////
    /**
     * 已收藏
     */
    IS_COLLECTED(850, "已收藏不用再收藏"),
    /**
     * 版块类型不存在
     */
    BBS_FORUM_TYPE_NOT_EXIST(900, "版块类型不存在"),

    ;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
