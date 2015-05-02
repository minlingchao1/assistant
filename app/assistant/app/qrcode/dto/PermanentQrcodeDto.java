/**
 * @Project:assistant
 * @Title: PermanentQrcodeDto.java
 * @date: 2015-2-12 下午10:45:02
 * @version 1.0
 */
package assistant.app.qrcode.dto;

/**
 * @ClassName PermanentQrcodeDto
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-12 下午10:45:02
 */
public class PermanentQrcodeDto {

	private String action_name;

	private ActionInfo action_info;

	public PermanentQrcodeDto() {
		// TODO Auto-generated constructor stub
	}

	public PermanentQrcodeDto(String actionName, ActionInfo actionInfo) {
		this.action_name = actionName;
		this.action_info = actionInfo;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public ActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(ActionInfo action_info) {
		this.action_info = action_info;
	}

}
