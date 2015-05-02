/**
 * @Project:assistant
 * @Title: ISellerDao.java
 * @date: 2015-3-6 下午4:10:37
 * @version 1.0
 */
package assistant.app.lbs.dal;

import java.util.List;

import assistant.app.lbs.model.Seller;

/**
 * @ClassName ISellerDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-6 下午4:10:37
 */
public interface ISellerDao {

	public long addLBSInfo(Long userId, Long wechatId, String sellerName, String descr, String prov, String city,
			String dist, String category, String address, String mobile, String img, String lngLat);

	public List<Seller> getList(long wehcatId);
}
