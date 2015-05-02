package assistant.app.base.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = JobLock.TABLE_NAME)
public class JobLock {

    public static final String TABLE_NAME = "job_lock";

    @Id
    private String jobId;
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

    private String machineName;
    private Integer bitStatus;
    private Long lastLockTs;

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Integer getBitStatus() {
        return bitStatus;
    }

    public void setBitStatus(Integer bitStatus) {
        this.bitStatus = bitStatus;
    }

    public Long getLastLockTs() {
        return lastLockTs;
    }

    public void setLastLockTs(Long lastLockTs) {
        this.lastLockTs = lastLockTs;
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
