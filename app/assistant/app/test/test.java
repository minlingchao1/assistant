/**
 * @Project:assistant
 * @Title: test.java
 * @date: 2015-2-12 下午12:49:18
 * @version 1.0
 */
package assistant.app.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 * @ClassName test
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-12 下午12:49:18
 */
public class test {

	public static long setEndTime(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -day);
		// c.setTimeInMillis(millis);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(c.getTime());
		System.out.println(yesterday);
		return c.getTimeInMillis();
	}

	public static void main(String[] args) {
		// String text = "基于java语言开发的轻量级的中文分词工具包";
		// // 创建分词对象
		// IKAnalyzer anal = new IKAnalyzer(true);
		// StringReader reader = new StringReader(text);
		// // 分词
		// TokenStream ts = anal.tokenStream("", reader);
		// CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		// // 遍历分词数据
		// try {
		// while (ts.incrementToken()) {
		// System.out.print(term.toString() + "|");
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// reader.close();
		// System.out.println();
		// Scene scene = new Scene();
		// scene.setSceneId(1);
		// ActionInfo actionInfo = new ActionInfo();
		// actionInfo.setScene(scene);
		// TempQrcodeDto qrcodeDto = new TempQrcodeDto();
		// qrcodeDto.setAction_info(actionInfo);
		// qrcodeDto.setAction_name("QR_SCENE");
		// qrcodeDto.setExpire_seconds(1800);
		// JSONObject jsonObject = JSONObject.fromObject(qrcodeDto);
		// System.out.println(jsonObject.toString());
		System.out.println(System.currentTimeMillis());

		long endTime = setEndTime(0);
		System.out.println(endTime);
	}
}
