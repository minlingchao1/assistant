/**
 * @Project:assistant
 * @Title: IOverallLogic.java
 * @date: 2015-3-5 下午4:53:49
 * @version 1.0
 */
package assistant.app.overall.logic;

import java.util.List;

import assistant.app.overall.model.Overall;

/**
 * @ClassName IOverallLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午4:53:49
 */
public interface IOverallLogic {

	public boolean addOverall(long userId, long wechatId, String name, String keyword, String descr, String pageImg,
			String left, String right, String top, String bottom, String front, String behind, String musicSet,
			String backMusic);

	public List<Overall> getList(long wechatId);

	public Overall findById(long id);

	public String renderXml(Overall overall);
}
