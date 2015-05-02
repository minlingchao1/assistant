/**
 * @Project:assistant
 * @Title: NewsMessageDto.java
 * @date: 2015-2-13 下午11:50:43
 * @version 1.0
 */
package assistant.app.reply.dto;

import java.util.List;

import assistant.app.reply.dto.base.MessageDtoBase;

/**
 * @ClassName NewsMessageDto
 * @Description 图文信息DTO
 * @author minlingchao
 * @date 2015-2-13 下午11:50:43
 */
public class NewsMessageDto extends MessageDtoBase {

	// 图文信息个数
	private int ArticleCount;

	// 多条图文信息,，默认第一个为大图

	private List<ArticleDto> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ArticleDto> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleDto> articles) {
		Articles = articles;
	}

}
