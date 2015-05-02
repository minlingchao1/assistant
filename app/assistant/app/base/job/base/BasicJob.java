package assistant.app.base.job.base;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.Play;
import play.db.jpa.NoTransaction;
import play.jobs.Job;
import play.mvc.Http.Request;

//import yunbei.common.jdbc.TransactionManager;
//
//
//
//import dao.JobLockDao;
//import dao.LogDao;
//import dao.SysLogDao;


@NoTransaction
public abstract class BasicJob<T> extends Job<T> {
    // public final static Logger log = LoggerFactory.getLogger(BasicJob.class);
    //
    // static {
    // JobLockDao.deleteJobLockILocked(NetworkUtil.getMachineName());
    // }
    // public String DEFAULT_JOB_ID = this.getClass().getName();
    // public Long DEFAULT_MAX_LOCK_TS = DateUtils.DAY_MILLIS;
    // private String jobId = DEFAULT_JOB_ID;
    // private Long maxLockTs;
    // private boolean getLock = false;
    //
    // protected boolean isAutoCommit() {
    // return true;
    // }
    //
    // public String getDisplayJobName() {
    // return "";
    // }
    //
    // @Override
    // public T doJobWithResult() throws Exception {
    //
    // String machineName = NetworkUtil.getMachineName();
    //
    // boolean canIExecute = canIExecute(machineName);
    //
    // if (!canIExecute) {
    // // log.error("不能执行该JOB ,MachineName={} JOBNAME={}", machineName,
    // // jobId);
    // return null;
    // } else {
    // // log.error("执行该JOB ,MachineName={} JOBNAME={}", machineName,
    // // jobId);
    // }
    //
    // Long jobBeforeTs = System.currentTimeMillis();
    // String jobName = getDisplayJobName();
    // // log.error("JOB: " + this.getClass() + "【{}】开始 ,时间戳={}", jobName,
    // // jobBeforeTs);
    // if (Play.mode.isDev()) {
    // // if(!"短信发送".equals(jobName)){
    // // return null;
    // // }
    // }
    // if (StringUtils.isEmpty(jobName)) {
    // jobName = getJobId();
    // }
    // jobId = getJobId();
    // maxLockTs = getMaxLockTs();
    //
    // getLock = getLock();
    //
    // if (!getLock) {
    // log.error("获得任务锁失败={}", jobId);
    // return null;
    // }
    //
    // doJob();
    // Long jobAfterTs = System.currentTimeMillis();
    // // log.error("JOB: 【{}】结束 ,耗费时间={}毫秒", jobName, jobAfterTs -
    // // jobBeforeTs);
    //
    // return null;
    // }
    //
    // public boolean canIExecute(String machineName) {
    //
    // // 默认为false
    // boolean execute = false;
    //
    // String executeMachine = Play.configuration.getProperty("JobLock." +
    // jobId, "");
    // // log.error(jobId + ":[" + executeMachine + "],this machine name:[" +
    // // machineName + "]");
    //
    // if (StringUtils.isNotEmpty(executeMachine)) {
    // String[] hostNames = executeMachine.split(",");
    // if (hostNames != null && hostNames.length >= 1 &&
    // StringUtils.isNotEmpty(hostNames[0])) {
    // execute = false;
    // for (String can : hostNames) {
    // if (can.equalsIgnoreCase(machineName)) {
    // execute = true;
    // break;
    // }
    // }
    // return execute;
    // }
    // } else {
    // // log.warn("~~no job lock set~~");
    // }
    //
    // return execute;
    // }
    //
    // private void releaseLock() {
    // if (!getLock) {
    // return;
    // }
    // boolean releaseLock = JobLockDao.releaseLock(jobId,
    // NetworkUtil.getMachineName());
    //
    // if (!releaseLock) {
    // // log.error("还原锁失败 = {}", jobId);
    // LogDao.newLog(Log.LOGGER_RIN, 2, "还原锁失败 JOBID = " + jobId, -1L);
    // }
    // }
    //
    // private boolean getLock() {
    //
    // JobLockDao.initJobLock(jobId);
    //
    // boolean lockSuccess = JobLockDao.lockJob(jobId,
    // NetworkUtil.getMachineName());
    //
    // if (lockSuccess) {
    // // log.debug("获得任务锁={}", jobId);
    // return true;
    // }
    // boolean isTimeOut = JobLockDao.checkTimeOut(jobId, maxLockTs);
    //
    // if (isTimeOut) {
    // lockSuccess = JobLockDao.lockJob(jobId, NetworkUtil.getMachineName());
    //
    // if (lockSuccess) {
    // // log.warn("解除任务锁={}", jobId);
    // LogDao.newLog(Log.LOGGER_RIN, 2, "解除任务锁  = " + jobId, -1L);
    // return true;
    // }
    //
    // } else {
    // // log.error("解锁失败={}", jobId);
    //
    // return false;
    // }
    // // log.debug("获得任务锁失败={}", jobId);
    // return false;
    // }
    //
    // public String getJobId() {
    // return DEFAULT_JOB_ID;
    // }
    //
    // public void setJobName(String jobName) {
    // this.jobId = jobName;
    // }
    //
    // public Long getMaxLockTs() {
    // return DEFAULT_MAX_LOCK_TS;
    // }
    //
    // public void setMaxLockTs(Long maxLockTs) {
    // this.maxLockTs = maxLockTs;
    // }
    //
    // @Override
    // public void before() {
    // if (Request.current() == null) {
    // if (isAutoCommit()) {
    // TransactionManager.beginAutoed();
    // } else {
    // TransactionManager.beginTransaction();
    // }
    // }
    //
    // super.before();
    // }
    //
    // @Override
    // public void onException(Throwable e) {
    // if ((Request.current() == null) && TransactionManager.isStarted()) {
    // TransactionManager.rollBack();
    // TransactionManager.beginAutoed();
    // SysLogDao.log(e);
    // }
    //
    // super.onException(e);
    // }
    //
    // @Override
    // public void _finally() {
    //
    // releaseLock();
    //
    // if ((Request.current() == null) && TransactionManager.isStarted()) {
    // TransactionManager.commit();
    // }
    //
    // super._finally();
    // }
    //
    // protected boolean forbid() {
    // return false;
    // }
}
