/**
 * @Project:assistant
 * @Title: Article.java
 * @date: 2015-2-28 下午10:41:42
 * @version 1.0
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName Article
 * @Description 单条图文
 * @author minlingchao
 * @date 2015-2-28 下午10:41:42
 */
@Entity(name = Article.TABLE_NAME)
public class Article extends BasicModel {

	public static final String TABLE_NAME = "article";

	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 摘要
	 */
	@Column(name = "abstracts")
	private String abstracts;

	/**
	 * 图片链接地址
	 */
	@Column(name = "img_url")
	private String imgUrl;

	/**
	 * 文章正文
	 */
	@Column(name = "content", columnDefinition = "text")
	private String content;

	/**
	 * 图文链接地址
	 */
	@Column(name = "url")
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
