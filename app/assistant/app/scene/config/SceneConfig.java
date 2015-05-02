/**
 * @Project:assistant
 * @Title: SceneConfig.java
 * @date: 2015-3-4 上午1:06:20
 * @version 1.0
 */
package assistant.app.scene.config;

/**
 * @ClassName SceneConfig
 * @Description 场景参数配置
 * @author minlingchao
 * @date 2015-3-4 上午1:06:20
 */
public enum SceneConfig {
	
	/**
	 * 未开始
	 */
	COMING(0, "coming", "未开始"),
	/**
	 * 进行中
	 */
	GOING_ON(1, "going_on", "进行中"),
	/**
	 * 已过期
	 */
	OUT_DATE(2, "out_date", "已过期"),
	/**
	 * 纯图片样式
	 */
	PIC(0, "pic", "纯图片样式"),
	/**
	 * 开启音乐
	 */
	ON(1, "on", "开启"),
	/**
	 * 关闭音乐
	 */
	OFF(0, "off", "关闭");
 
	private int code;
	
	private String cn;

	private String en;
	
	SceneConfig(int code, String en, String cn) {
		this.code = code;
		this.cn = cn;
		this.en = en;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getEn() {
		return en;
	}


	public void setEn(String en) {
		this.en = en;
	}


	public String getCn() {
		return cn;
	}


	public void setCn(String cn) {
		this.cn = cn;
	}
}
