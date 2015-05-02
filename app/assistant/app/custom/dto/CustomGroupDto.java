/**
 * @Project:assistant
 * @Title: CustomGroupDto.java
 * @date: 2015-2-13 下午7:46:02
 * @version 1.0
 */
package assistant.app.custom.dto;

/**
 * @ClassName CustomGroupDto
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午7:46:02
 */
public class CustomGroupDto {

	/**
	 * 分组id
	 */
	private int id;

	/**
	 * 分组名称
	 */
	private String name;

	/**
	 * 分组内的用户数
	 */
	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
