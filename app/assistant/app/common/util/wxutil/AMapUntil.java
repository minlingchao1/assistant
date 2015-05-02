/**
 * @Project:assistant
 * @Title: AMapUntil.java
 * @date: 2015-3-6 上午11:45:28
 * @version 1.0
 */
package assistant.app.common.util.wxutil;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;

import assistant.app.common.util.WebUtil;

/**
 * @ClassName AMapUntil
 * @Description 高德地图基本API
 * @author minlingchao
 * @date 2015-3-6 上午11:45:28
 */
public class AMapUntil {

	/**
	 * 高德地图密钥
	 */
	public static final String KEY = "03138e10227098542d2af2efd58e30e9";
	

	/**
	 * 高德地图数字签名
	 */

	public static final String SIGNATURE = "78455b8cef0bd22ebd14217380c9f3bd";
	
	/**
	 * TABLE_ID
	 */
	public static final String TABLE_ID="54f92a9ce4b090c79548495a";
	
	// //////////////////////////////API接口//////////////////////////////////////////////////
	/**
	 * 创建云图表
	 */
	public static final String CREATE_TABLE = "http://yuntuapi.amap.com/datamanage/table/create";
	
	/**
	 * 创建单条数据
	 */
	public static final String ADD_ONE_DATA = "http://yuntuapi.amap.com/datamanage/data/create";

	/**
	 * 更新一条数据
	 */
	public static final String UPDATE_ONE_DATA = "http://yuntuapi.amap.com/datamanage/data/update";
	
	/**
	 * 删除数据(单条/批量)
	 */
	public static final String DELETE_DATA = "http://yuntuapi.amap.com/datamanage/data/delete";
	
	/**
	 * 周边搜索
	 */
	public static final String AROUND_SEARCH = "http://yuntuapi.amap.com/datasearch/around";
	
	/**
	 * 路线规划
	 */
	public static final String ROUTE_PLANING = "http://m.amap.com/navi/";

	public static void main(String[] args) {
		String dataJSon = "{\"_name\":\"1\",\"_location\":\"104.394729,31.125698\",\"pic\":\"http:\\www.baidu.com\"}";
		String signature = SignUtil.amapValidSign(AMapUntil.KEY, AMapUntil.TABLE_ID, dataJSon);
		NameValuePair[] data = new NameValuePair[] { new NameValuePair("key", AMapUntil.KEY),
				new NameValuePair("tableid", AMapUntil.TABLE_ID), new NameValuePair("data", dataJSon)};
		String result;
		try {
			result = WebUtil.doPost(AMapUntil.ADD_ONE_DATA, data);
			System.out.println(result);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
