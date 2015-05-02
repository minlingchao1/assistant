/**
 * @Project:assistant
 * @Title: ISellerMapper.java
 * @date: 2015-3-6 下午4:08:05
 * @version 1.0
 */
package assistant.app.lbs.dao.mapper;

import java.util.List;

import assistant.app.lbs.model.Seller;

/**
 * @ClassName ISellerMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-6 下午4:08:05
 */
public interface ISellerMapper {

	public long insert(Seller seller);

	public List<Seller> getList(long wechatId);

}
