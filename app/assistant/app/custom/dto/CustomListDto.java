/**
 * @Project:assistant
 * @Title: CustomListDto.java
 * @date: 2015-2-13 下午6:23:28
 * @version 1.0
 */
package assistant.app.custom.dto;

import java.util.List;

/**
 * @ClassName CustomListDto
 * @Description 用户关注着列表
 * @author minlingchao
 * @date 2015-2-13 下午6:23:28
 */
public class CustomListDto {

	/**
	 * 总关注用户数
	 */
	private int total;

	/**
	 * 获取的openid数
	 */
	private int count;

	/**
	 * openId列表
	 */
	private List<String> openIdList;


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

}
