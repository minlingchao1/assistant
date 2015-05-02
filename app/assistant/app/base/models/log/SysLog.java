package assistant.app.base.models.log;

import org.hibernate.annotations.Index;

import play.db.jpa.GenericModel;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity(name = SysLog.TABLE_NAME)
public class SysLog extends GenericModel implements Serializable {
    private static final long serialVersionUID                                                                                            =
        1L;
    public static final String TABLE_NAME                                                                                                 =
        "sys_log";
    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "text")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String message;
    @Column(columnDefinition = "text")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String stackTrace;
    @Index(name = TABLE_NAME + "_logTime")
    @Column(columnDefinition = "TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date logTime;

    public SysLog(String message, String stackTrace) {
        super();
        this.message                                                                                                                      = message;
        this.stackTrace                                                                                                                   = stackTrace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return "SysLog [id=" + id + ", message=" + message + ", stackTrace=" + stackTrace + ", logTime=" + logTime +
        "]";
    }
}
