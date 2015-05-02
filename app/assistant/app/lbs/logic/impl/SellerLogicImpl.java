/**
 * @Project:assistant
 * @Title: SellerLogicImpl.java
 * @date: 2015-3-6 下午6:59:39
 * @version 1.0
 */
package assistant.app.lbs.logic.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.common.util.WebUtil;
import assistant.app.common.util.wxutil.AMapUntil;
import assistant.app.lbs.dal.ISellerDao;
import assistant.app.lbs.dal.ISellerMessageRefDao;
import assistant.app.lbs.dal.impl.SellerDaoImpl;
import assistant.app.lbs.dal.impl.SellerMessageRefDaoImpl;
import assistant.app.lbs.logic.ISellerLogic;
import assistant.app.lbs.model.Seller;

/**
 * @ClassName SellerLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-6 下午6:59:39
 */
public class SellerLogicImpl implements ISellerLogic {

	public static final Logger LOG = LogCongfig.LBSLOG;

	private static SellerLogicImpl instance = new SellerLogicImpl();

	private SellerLogicImpl() {

	}

	public static SellerLogicImpl getInstance() {
		return instance;
	}

	private ISellerDao sellerDao = SellerDaoImpl.getInstance();

	private ISellerMessageRefDao sellerRefDao = SellerMessageRefDaoImpl.getInstance();

	@Override
	public boolean addLBSInfo(Long userId, Long wechatId, String sellerName, String descr, String prov, String city,
			String dist, String category, String address, String mobile, String img, String lngLat) {
		boolean isSuccess=false;
		long result=sellerDao.addLBSInfo(userId, wechatId, sellerName, descr, prov, city, dist, category, address, mobile, img,
				lngLat);
		if (result > 0) {
			isSuccess = addSellerToAMap(wechatId, address, sellerName, result, lngLat, category);
			return isSuccess;
		}
		return isSuccess;
	}

	@Override
	public boolean addSellerToAMap(Long wechatId, String address, String sellerName, Long sellerId, String lngLat,
			String category) {
		boolean isSuccess = false;
		String result = null;

		Map<String, String> sellerData = new HashMap<String, String>();
		// data.put(key, value)
		sellerData.put("_name", sellerName);
		sellerData.put("_location", lngLat);
		sellerData.put("_address", address);
		sellerData.put("wechatId", wechatId.toString());
		sellerData.put("category", category);
		sellerData.put("sellerId", sellerId.toString());
		String dataJson = JSONObject.fromObject(sellerData).toString();

		NameValuePair[] data = new NameValuePair[] { new NameValuePair("key", AMapUntil.KEY),
				new NameValuePair("tableid", AMapUntil.TABLE_ID), new NameValuePair("data", dataJson) };
		try {
			result = WebUtil.doPost(AMapUntil.ADD_ONE_DATA, data);

			JSONObject resObj = JSONObject.fromObject(result);
			if (resObj.getString("status").equals("1")) {
				isSuccess = true;
			} else {
				LOG.error("add sellerInfo to Amap error:{},", resObj.getString("info"));
			}

			System.out.println(result);
		} catch (HttpException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return isSuccess;
	}

	@Override
	public List<Seller> getList(long wehcatId) {
		return sellerDao.getList(wehcatId);
	}

	public static void main(String[] args) {
		ISellerLogic sellerLogic = SellerLogicImpl.getInstance();

		// Map<String, String> sellerData = new HashMap<String, String>();
		// // data.put(key, value)
		// sellerData.put("_name", "qwqwq");
		// sellerData.put("_location", "104.394729,31.125698");
		// sellerData.put("_address", "qwqwq");
		// sellerData.put("wechatId", "qwqwq");
		// sellerData.put("category", "qwqwqwq");
		// String dataJson = JSONObject.fromObject(sellerData).toString();
		// {"category":"2121","wechatId":"2","_name":"2121","_location":"39.107758,117.184749","_address":"天津市和平区新兴街道天津医科大学基础医学院"}
		// {"category":"2121","wechatId":"1","_name":"seller","_location":"104.394729,31.125698","_address":"山东临沂"}
		// System.out.println(dataJson);
		// sellerLogic.addSellerToAMap(1L, "23232", "212121",
		// "39.107758,117.184749", "1212121");
		// 天津市和平区新兴街道天津医科大学基础医学院
	}

	@Override
	public String aroundSearch(String center, Integer radius, Integer limit, Integer page) {
		
		String result = null;

		NameValuePair[] data = new NameValuePair[] { new NameValuePair("key", AMapUntil.KEY),
				new NameValuePair("tableid", AMapUntil.TABLE_ID), new NameValuePair("center", center),
				new NameValuePair("radius", radius.toString()) };
					
		try {
			result = WebUtil.doPost(AMapUntil.AROUND_SEARCH, data);
			LOG.warn("around sraerch:{}", result);
			JSONObject resJson = JSONObject.fromObject(result);
			if (resJson.getString("status").equals("1")) {
				return result;

			} else {
				LOG.error("around search error:{}", resJson.getString("info"));
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String createSellerNewsMessage(String message) {

		
		return null;
	}

}
