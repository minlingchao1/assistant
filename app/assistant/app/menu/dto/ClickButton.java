/**
 * @Project:assistant
 * @Title: ClickButton.java
 * @date: 2015-2-21 上午10:53:06
 * @version 1.0
 */
package assistant.app.menu.dto;

/**
 * @ClassName ClickButton
 * @Description click类型的按钮
 * @author minlingchao
 * @date 2015-2-21 上午10:53:06
 */
public class ClickButton extends Button {

	/**
	 * 按钮类型
	 */
	private String type;

	/**
	 * 按钮键值
	 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
