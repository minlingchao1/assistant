/**
 * @Project:assistant
 * @Title: OverallLogicImpl.java
 * @date: 2015-3-5 下午4:54:03
 * @version 1.0
 */
package assistant.app.overall.logic.impl;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;

import play.Play;
import assistant.app.base.config.LogCongfig;
import assistant.app.common.util.FIleUrlUtil;
import assistant.app.overall.dal.IOverallDao;
import assistant.app.overall.dal.impl.OverallDaoImpl;
import assistant.app.overall.logic.IOverallLogic;
import assistant.app.overall.model.Overall;

/**
 * @ClassName OverallLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午4:54:03
 */
public class OverallLogicImpl implements IOverallLogic {

	private static final Logger LOG = LogCongfig.OVERALLLOG;

	private static OverallLogicImpl instance = new OverallLogicImpl();

	private OverallLogicImpl() {

	}

	public static OverallLogicImpl getInstance() {
		return instance;
	}

	private IOverallDao overallDao = OverallDaoImpl.getInstance();
	@Override
	public boolean addOverall(long userId, long wechatId, String name, String keyword, String descr, String pageImg,
			String left, String right, String top, String bottom, String front, String behind, String musicSet,
			String backMusic) {
		return overallDao.addOverall(userId, wechatId, name, keyword, descr, pageImg, left, right, top, bottom, front,
				behind, musicSet, backMusic);

	}

	@Override
	public List<Overall> getList(long wechatId) {
		return overallDao.getList(wechatId);
	}

	@Override
	public Overall findById(long id) {
		return overallDao.findById(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public String renderXml(Overall overall) {

		String overallXml = null;
		XMLWriter writer = null;// 声明写XML的对象
		SAXReader reader = new SAXReader();

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");// 设置XML文件的编码格式
		String xmlPath = Play.applicationPath + FIleUrlUtil.getOverallXmlUrl();
		File file = new File(xmlPath);

		if (file.exists()) {
			try {
				Document document = reader.read(file);
				Element root = document.getRootElement();// 得到根节点
				Element input = root.element("input");
				//input.setAttributeValue("tile0url", "11111111111111111111111111111111");
				// input.attribute("title0url").setValue(arg0)
				input.attribute("tile0url").setValue(overall.getBehindUrl());
				input.attribute("tile1url").setValue(overall.getFrontUrl());
				input.attribute("tile2url").setValue(overall.getRightUrl());
				input.attribute("tile3url").setValue(overall.getLeftUrl());
				input.attribute("tile4url").setValue(overall.getTopUrl());
				input.attribute("tile5url").setValue(overall.getBottomUrl());

				overallXml = document.asXML();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return overallXml;
	}

}
