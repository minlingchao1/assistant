/**
 * @Project:assistant
 * @Title: ComplexButton.java
 * @date: 2015-2-21 上午11:01:13
 * @version 1.0
 */
package assistant.app.menu.dto;

/**
 * @ClassName ComplexButton
 * @Description 复合类型按钮
 * @author minlingchao
 * @date 2015-2-21 上午11:01:13
 */
public class ComplexButton extends Button {

	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

}
