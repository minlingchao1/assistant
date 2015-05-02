/**
 * @Project:assistant
 * @Title: ILBSLogic.java
 * @date: 2015-3-6 下午6:58:24
 * @version 1.0
 */
package assistant.app.lbs.logic;

import java.util.List;

import assistant.app.lbs.model.Seller;

/**
 * @ClassName ILBSLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-6 下午6:58:24
 */
public interface ISellerLogic {
	
	/**
	 * 添加LBS信息
	 */
	public boolean addLBSInfo(Long userId, Long wechatId, String sellerName, String descr, String prov, String city,
			String dist, String category, String address, String mobile, String img, String lngLat);

	/**
	 * 添加商户坐标信息到高德云地图
	 */
	public boolean addSellerToAMap(Long wechatId, String address, String sellerName, Long sellerId, String lngLat,
			String category);

	/**
	 * 获取列表
	 */
	public List<Seller> getList(long wehcatId);

	/**
	 * 周边搜索
	 */
	public String aroundSearch(String center, Integer radius, Integer limit, Integer page);

	/**
	 * 创建LBS图文消息
	 */
	public String createSellerNewsMessage(String message);

}
