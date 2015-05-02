/**
 * @Project:assistant
 * @Title: QrcodeDto.java
 * @date: 2015-2-12 下午5:47:19
 * @version 1.0
 */
package assistant.app.qrcode.dto;

/**
 * @ClassName QrcodeDto
 * @Description 创建二维码所需要的信息DTO
 * @author minlingchao
 * @date 2015-2-12 下午5:47:19
 */
public class TempQrcodeDto {

	private int expire_seconds;

	private String action_name;

	private ActionInfo action_info;
	
	public TempQrcodeDto() {
		// TODO Auto-generated constructor stub
	}

	public TempQrcodeDto(int expireSeconds, String actionName, ActionInfo actionInfo) {
		this.expire_seconds = expireSeconds;
		this.action_name = actionName;
		this.action_info = actionInfo;
	}

	public int getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
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
