/**
 * @Project:assistant
 * @Title: CustomGroup.java
 * @date: 2015-2-13 下午5:05:45
 * @version 1.0
 */
package assistant.app.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName CustomGroup
 * @Description 用户分组
 * @author minlingchao
 * @date 2015-2-13 下午5:05:45
 */
@Entity(name = CustomGroup.TABLE_NAME)
public class CustomGroup extends BasicModel {

	public static final String TABLE_NAME = "custom_group";
	
	/**
	 * 微信Id
	 */
	@Column(name="wechat_id")
	private long wechatId;

	/**
	 * 分组ID
	 */
	@Column(name="group_id")
	private long groupId;

	/**
	 * 分组名
	 */
	@Column(name = "group_name")
	private String groupName;

	/**
	 * 分组用户数
	 */
	@Column(name = "custom_count")
	private long customCount;

	public long getWechatId() {
		return wechatId;
	}

	public void setWechatId(long wechatId) {
		this.wechatId = wechatId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}



	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getCustomCount() {
		return customCount;
	}

	public void setCustomCount(long customCount) {
		this.customCount = customCount;
	}
	
	

}
