package assistant.app.base.models.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = Log.TABLE_NAME)
public class Log {
    public static final String TABLE_NAME = " log";
    public static final Integer LOGGER_RIN = 10086;
    public static final Integer LOG_TYPE_OM = 8;

    /**
     * 主键自增长
     */
    @Id
    @GeneratedValue
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

    /**
     * 日志记录者标示一下以便查找自己的日志 RIN:10086
     */
    private Integer logger;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 类型
     */
    private Integer type;

    @Column(columnDefinition = "text")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Log(Integer logger, Integer type, String message, Long userId) {
        super();
        this.logger = logger;
        this.type = type;
        this.message = message;
        this.userId = userId;
    }

    public Integer getLogger() {
        return logger;
    }

    public void setLogger(Integer logger) {
        this.logger = logger;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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
