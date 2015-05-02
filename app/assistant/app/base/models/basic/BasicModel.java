/**
 * @Project:wechat
 * @Title: BasicModel.java
 * @date: 2015-1-4 下午10:35:29
 * @version 1.0
 */
package assistant.app.base.models.basic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.db.jpa.GenericModel;

/**
 * @ClassName BasicModel
 * @Description Model基础
 * @author minlingchao
 * @date 2015-1-4 下午10:35:29
 */
@MappedSuperclass
public class BasicModel extends GenericModel implements Serializable {
	
    /**
     * 主键自增长
     */
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    /**
     * 插入时的时间戳
     */

    @Column(name = "gmt_created", columnDefinition = "  TIMESTAMP  DEFAULT '0000-00-00 00:00:00' ")
    private Date gmtCreated;

    /**
     * 更新时的时间戳
     */
    @Column(name = "gmt_modified", columnDefinition = " TIMESTAMP  NOT NULL DEFAULT now() ON UPDATE now()")
    private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

    
}
