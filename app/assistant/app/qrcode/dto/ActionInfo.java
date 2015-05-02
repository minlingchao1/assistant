/**
 * @Project:assistant
 * @Title: ActionInfo.java
 * @date: 2015-2-12 下午10:08:22
 * @version 1.0
 */
package assistant.app.qrcode.dto;

/**
 * @ClassName ActionInfo
 * @Description 二维码详细信息
 * @author minlingchao
 * @date 2015-2-12 下午10:08:22
 */
public class ActionInfo {
	
	private Scene scene;

	public ActionInfo() {
		// TODO Auto-generated constructor stub
	}

	public ActionInfo(Scene scene) {
		this.scene = scene;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
