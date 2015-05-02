/**
 * @Project:assistant
 * @Title: ISellerMessageRefDao.java
 * @date: 2015-3-7 上午12:47:19
 * @version 1.0
 */
package assistant.app.lbs.dal;

import assistant.app.lbs.model.SellerMessageRef;

/**
 * @ClassName ISellerMessageRefDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-7 上午12:47:19
 */
public interface ISellerMessageRefDao {

	public SellerMessageRef findBySellerId(long sellerId);

}
