/**
 * @Project:assistant
 * @Title: WechatAccountInfo.java
 * @date: 2015-1-22 下午11:00:16
 * @version 1.0
 */
package assistant.app.bind.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName WechatAccountInfo
 * @Description 微信公众号基本信息
 * @author minlingchao
 * @date 2015-1-22 下午11:00:16
 */
@Entity(name = WechatAccountInfo.TABLE_NAME)
public class WechatAccountInfo extends BasicModel {

    public static final String TABLE_NAME = "wechat_account_info";
    

    /**
	 * 原始ID
	 */
    @Column(name = "account_id")
    private String accountId;

    /**
	 * 微信号名称
	 */
    @Column(name = "account_name")
    private String accountName;

    /**
	 * 头像URL
	 */
    @Column(name = "head_image")
    private String headImage;

    /**
	 * 微信号
	 */
    @Column(name = "wechat_number")
    private String wechatNumber;

    /**
	 * 微信号类型
	 */
    @Column(name = "type")
    private String type;

    /**
	 * 认证情况
	 */
    @Column(name = "authenticate")
    private String authenticate;

    /**
	 * 二维码
	 */
    @Column(name = "qrcode")
    private String qrcode;

    public WechatAccountInfo() {
		// TODO Auto-generated constructor stub
	}
    
    /**
	 * 公众号AppId
	 */
    @Column(name = "app_id")
    private String appId;

    /**
	 * 公众号Appsecret
	 */
    @Column(name = "app_secret")
    private String appSecret;
    
    /**
     * url
     */
    @Column(name="url")
    private String url;
    
    /**
     * token
     */
    @Column(name="token")
    private String token;

	/**
	 * 接口获取凭证
	 */
	@Column(name = "access_token")
	private String accessToken;

	public WechatAccountInfo(String accountName, String type, String authenticate, String wechatNumber,
			String accountId, String appId, String appSecret, String url, String token, String qrcode,
			String headImage, String accessToken) {
    	this.accountName=accountName;
    	this.type=type;
    	this.authenticate=authenticate;
    	this.accountId=accountId;
    	this.wechatNumber=wechatNumber;
    	this.appId=appId;
    	this.appSecret=appSecret;
    	this.url=url;
    	this.token=token;
		this.qrcode = qrcode;
		this.headImage = headImage;
		this.accessToken = accessToken;
    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


    

}
