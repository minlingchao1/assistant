/**
 * @Project:assistant
 * @Title: ViewButton.java
 * @date: 2015-2-21 上午10:56:53
 * @version 1.0
 */
package assistant.app.menu.dto;

/**
 * @ClassName ViewButton
 * @Description view类型的按钮
 * @author minlingchao
 * @date 2015-2-21 上午10:56:53
 */
public class ViewButton extends Button {

	/**
	 * 按钮类型
	 */
	private String type;

	/**
	 * 链接地址
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
