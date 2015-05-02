/**
 * @Project:assistant
 * @Title: CustomGroupLogicImpl.java
 * @date: 2015-2-13 下午6:10:00
 * @version 1.0
 */
package assistant.app.custom.logic.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.bind.dal.IWechatAccessTokenDao;
import assistant.app.bind.dal.impl.WechatAccessTokenDaoImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.common.util.WebUtil;
import assistant.app.custom.dal.ICustomGroupDao;
import assistant.app.custom.dal.impl.CustomGroupDaoImpl;
import assistant.app.custom.dto.CustomGroupDto;
import assistant.app.custom.logic.ICustomGroupLogic;
import assistant.app.custom.model.CustomGroup;

/**
 * @ClassName CustomGroupLogicImpl
 * @Description 用户分组操作
 * @author minlingchao
 * @date 2015-2-13 下午6:10:00
 */
public class CustomGroupLogicImpl implements ICustomGroupLogic {

	public static final Logger LOG = LogCongfig.CUSTOMLOG;

	private static CustomGroupLogicImpl instance = new CustomGroupLogicImpl();

	private CustomGroupLogicImpl() {

	}

	public static CustomGroupLogicImpl getInstance() {
		return instance;
	}

	private ICustomGroupDao groupDao = CustomGroupDaoImpl.getInstance();

	private IWechatAccessTokenDao wechatAccessTokenDao = WechatAccessTokenDaoImpl.getInstance();
	@Override
	public long insert(CustomGroup group) {
		return groupDao.insert(group);
	}

	@Override
	public long getGroup(long wechatId,String accessToken) {

		List<CustomGroupDto> customGroups = null;
		String url = WechatConfig.CUSTOM_GROUP_GET_URL;
		url = url.replace("ACCESS_TOKEN", accessToken);
		String result = WebUtil.httpRequest(url, "GET", null);
		JSONObject resjson = JSONObject.fromObject(result);
		if (resjson != null) {
			customGroups = JSONArray.toList(resjson.getJSONArray("groups"), CustomGroup.class);

			for (CustomGroupDto customGroupDto : customGroups) {
				LOG.warn("get customGroup for{}:", wechatId);
				CustomGroup customGroup=findById(wechatId, customGroupDto.getId());
				if (customGroup != null) {
					customGroup.setCustomCount(customGroupDto.getCount());
					customGroup.setGroupName(customGroupDto.getName());
					update(customGroup);
				} else {
					customGroup = new CustomGroup();
					customGroup.setCustomCount(customGroupDto.getCount());
					customGroup.setGroupName(customGroupDto.getName());
					customGroup.setWechatId(wechatId);
					customGroup.setGroupId(customGroupDto.getId());
					insert(customGroup);
				}
			}
		}
		return 0;
	}

	@Override
	public long createGroup(long wechatId, String groupName) {
		
		WechatAccessToken wechatAccessToken=wechatAccessTokenDao.findByWechatId(wechatId);
		
		String url=WechatConfig.CUSTOM_GROUP_CREATE_URL;
		url = url.replace("ACCESS_TOKEN", wechatAccessToken.getAccessToken());
		
		String jsonData="{\"group\":{\"name\":\"%s\"}}";
		String result = WebUtil.httpRequest(url, "POST", String.format(jsonData, groupName));
		JSONObject resjson = JSONObject.fromObject(result);
		if (resjson != null) {
			try {
				CustomGroup customGroup = new CustomGroup();
				customGroup.setCustomCount(0);
				customGroup.setGroupId(resjson.getJSONObject("group").getInt("id"));
				customGroup.setGroupName(resjson.getJSONObject("group").getString("name"));
				customGroup.setWechatId(wechatId);
				insert(customGroup);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				int errcode = resjson.getInt("errcode");
				String errmsg = resjson.getString("errmsg");
				LOG.error(errmsg, errcode);
			}
		}
		return 0;
	}

	@Override
	public boolean updateGroup(long wechatId, int groupId, String groupName) {

		boolean isSuccess = false;
		WechatAccessToken wechatAccessToken = wechatAccessTokenDao.findByWechatId(wechatId);

		String url = WechatConfig.CUSTOM_GROUP_UPDATE_URL;
		url = url.replace("ACCESS_TOKEN", wechatAccessToken.getAccessToken());
		String jsonData = "{\"group\":{\"name\":\"%s\"}}";
		String result = WebUtil.httpRequest(url, "POST", String.format(jsonData, groupName));
		JSONObject resjson = JSONObject.fromObject(result);
		if (resjson != null) {
			int errcode = resjson.getInt("errcode");
			String errmsg = resjson.getString("errmsg");
			if (errcode == 0) {
				CustomGroup customGroup = findById(wechatId, groupId);
				customGroup.setGroupName(groupName);
				update(customGroup);
				isSuccess = true;
			} else {
				LOG.error("修改分组失败!,errcode:{},errMsg:{}", errcode, errmsg);
			}
		}
		return isSuccess;
	}

	@Override
	public long update(CustomGroup group) {
		return groupDao.update(group);
	}

	@Override
	public CustomGroup findById(long wechatId, long id) {
		return groupDao.findById(wechatId, id);
	}
}
