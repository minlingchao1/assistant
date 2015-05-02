/**
 * @Project:assistant
 * @Title: UploadMediaJob.java
 * @date: 2015-2-10 下午4:23:45
 * @version 1.0
 */
package assistant.app.base.job;

import java.util.List;

import org.slf4j.Logger;

import play.jobs.Every;
import assistant.app.base.config.LogCongfig;
import assistant.app.base.job.base.OnceJob;
import assistant.app.bind.dal.IWechatInfoDao;
import assistant.app.bind.dal.impl.WechatInfoDaoImpl;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.DateUtils;
import assistant.app.common.util.wxutil.UploadMediaUtil;
import assistant.app.reply.dal.IReplyMsgTempleteDao;
import assistant.app.reply.dal.impl.ReplyMsgTempleteDaoImpl;
import assistant.app.reply.model.ReplyMsgTemplete;
import assistant.app.subscribe.dal.ISubscribeDao;
import assistant.app.subscribe.dal.impl.SubscribeDaoImpl;
import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName UploadMediaJob
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 下午4:23:45
 */
@Every("10s")
public class UploadMediaJob extends OnceJob<ReplyMsgTemplete> {

	public static final Logger LOG = LogCongfig.MEDIAUPDOWNLOG;

	private IReplyMsgTempleteDao replyMsgTempleteDao = ReplyMsgTempleteDaoImpl.getInstance();

	private IWechatInfoDao wechatInfoDao = WechatInfoDaoImpl.getInstance();

	private ISubscribeDao subscribeDao = SubscribeDaoImpl.getInstance();

	@Override
	public List<ReplyMsgTemplete> getAllData() {
		return replyMsgTempleteDao.findAll();
	}

	@Override
	public boolean dealOne(ReplyMsgTemplete replyMsgTemplete) {
		
		long now=System.currentTimeMillis();
		if(now-replyMsgTemplete.getCreateTime()>DateUtils.THREE_DAY_MILLS){
			Subscribe subscribe=subscribeDao.findByMsgId(replyMsgTemplete.getId());
			if(subscribe.getType()!=0){
				String type=null;
				if(subscribe.getType()==1){
					type="image";
				}else if(subscribe.getType()==2){
					type="voice";
				}else if(subscribe.getType()==3){
					type="video";
				}
				WechatAccountInfo wechatAccountInfo=wechatInfoDao.findById(subscribe.getWechatId());

				String mediaId = UploadMediaUtil.uploadMedia(wechatAccountInfo.getAccessToken(), type,
						replyMsgTemplete.getReplyMsg());

				replyMsgTemplete.setMediaId(mediaId);
				replyMsgTemplete.setCreateTime(System.currentTimeMillis());
				replyMsgTempleteDao.update(replyMsgTemplete);
			}
			
		}
		return false;
	}

	@Override
	public Logger getLog() {
		return LOG;
	}

}
