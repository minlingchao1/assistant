/**
 * @Project:assistant
 * @Title: ISellerMessageRefMapper.java
 * @date: 2015-3-6 下午4:08:21
 * @version 1.0
 */
package assistant.app.lbs.dao.mapper;

import assistant.app.lbs.model.SellerMessageRef;

/**
 * @ClassName ISellerMessageRefMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-6 下午4:08:21
 */
public interface ISellerMessageRefMapper {

	public long insert(SellerMessageRef sellerMessageRef);

	public SellerMessageRef findBySellerId(long sellerId);

}
