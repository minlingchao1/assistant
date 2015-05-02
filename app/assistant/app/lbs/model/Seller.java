/**
 * @Project:assistant
 * @Title: Seller.java
 * @date: 2015-3-6 下午3:51:58
 * @version 1.0
 */
package assistant.app.lbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName Seller
 * @Description 商户信息
 * @author minlingchao
 * @date 2015-3-6 下午3:51:58
 */
@Entity(name = Seller.TABLE_NAME)
public class Seller extends BasicModel {

	public static final String TABLE_NAME = "seller";
	
	/**
	 * 微信Id
	 */
	@Column(name = "wechat_id")
	private Long wechatId;

	/**
	 * 商家名称
	 */
	@Column(name = "seller_name")
	private String sellerName;
	
	/**
	 * 门店简介
	 */
	@Column(name = "descr")
	private String descr;
	
	

	/**
	 * 商家类别
	 */
	@Column(name = "category")
	private String category;
	
	/**
	 * 地址
	 */
	@Column(name = "address")
	private String address;
	
	/**
	 * 省
	 */
	@Column(name = "prov")
	private String prov;

	/**
	 * 市
	 */
	@Column(name = "city")
	private String city;

	/**
	 * 区
	 */
	@Column(name = "dist")
	private String dist;

	/**
	 * 地理位置
	 */
	@Column(name = "lng_lat")
	private String lngLat;

	/**
	 * 电话/手机
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 商家展示图片
	 */
	@Column(name = "img")
	private String img;

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLngLat() {
		return lngLat;
	}

	public void setLngLat(String lngLat) {
		this.lngLat = lngLat;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

}
