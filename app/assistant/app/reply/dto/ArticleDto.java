/**
 * @Project:assistant
 * @Title: Article.java
 * @date: 2015-2-13 下午11:52:47
 * @version 1.0
 */
package assistant.app.reply.dto;

/**
 * @ClassName Article
 * @Description 图文详情
 * @author minlingchao
 * @date 2015-2-13 下午11:52:47
 */
public class ArticleDto {

	/**
	 * 标题
	 */
	private String Title;

	/**
	 * 描述
	 */
	private String Description;

	/**
	 * 图片链接
	 */
	private String PicUrl;

	/**
	 * 点击图文信息跳转链接
	 */
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
