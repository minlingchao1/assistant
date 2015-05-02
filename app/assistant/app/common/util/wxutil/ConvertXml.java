/**
 * @Project:assistant
 * @Title: ConvertXml.java
 * @date: 2015-2-6 下午12:42:08
 * @version 1.0
 */
package assistant.app.common.util.wxutil;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;

import play.mvc.Http.Request;
import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dto.ArticleDto;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @ClassName ConvertXml
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午12:42:08
 */
public class ConvertXml {
	
	public static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;
	
	/**
	 * 解析XMl
	 */
	public static Map<String, String> parseXml(Request request) {

		Map<String, String> map = new HashMap<String, String>();

		String body = request.params.get("body");
		LOG.warn("request body:{}", body);
		
		try {
			
			Document document = DocumentHelper.parseText(body);
			// 得到XML根元素
			Element root = document.getRootElement();
			// 得到根元素下的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			for (Element element : elementList) {
				map.put(element.getName(), element.getText());
			}
		} catch (DocumentException e) {
			LOG.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xStream = new XStream(new XppDriver(){

		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out){
				boolean cdata=true;
				
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}
				protected void writeText(QuickWriter writer,String text){
					if(cdata){
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}else{
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 消息转换成XML
	 */
	public static String messageToXml(Object obj) {
		xStream.alias("xml", obj.getClass());
		return xStream.toXML(obj);
	}

	/**
	 * 图文消息转换成XML
	 */
	public static String newsMessageToXml(Object obj) {
		xStream.alias("xml", obj.getClass());
		xStream.alias("item", new ArticleDto().getClass());
		return xStream.toXML(obj);
	}
}
