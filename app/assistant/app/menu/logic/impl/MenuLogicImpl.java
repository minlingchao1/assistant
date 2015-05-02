/**
 * @Project:assistant
 * @Title: MenuLogicImpl.java
 * @date: 2015-2-21 下午2:08:39
 * @version 1.0
 */
package assistant.app.menu.logic.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.bind.dal.IWechatAccessTokenDao;
import assistant.app.bind.dal.impl.WechatAccessTokenDaoImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.common.util.RandomCode;
import assistant.app.common.util.WebUtil;
import assistant.app.common.util.wxutil.ConvertXml;
import assistant.app.menu.config.MenuType;
import assistant.app.menu.dal.IMenuDao;
import assistant.app.menu.dal.impl.MenuDaoImpl;
import assistant.app.menu.dto.Button;
import assistant.app.menu.dto.ClickButton;
import assistant.app.menu.dto.ComplexButton;
import assistant.app.menu.dto.MenuDto;
import assistant.app.menu.dto.ViewButton;
import assistant.app.menu.logic.IMenuLogic;
import assistant.app.menu.model.Menu;
import assistant.app.reply.dal.IMenuReplyRuleDao;
import assistant.app.reply.dal.IReplyMsgTempleteDao;
import assistant.app.reply.dal.impl.MenuReplyRuleDaoImpl;
import assistant.app.reply.dal.impl.ReplyMsgTempleteDaoImpl;
import assistant.app.reply.dto.TextMessageDto;
import assistant.app.reply.logic.INewsMessageRefLogic;
import assistant.app.reply.logic.IReplyMessageLogic;
import assistant.app.reply.logic.impl.NewsMessageRefLogicImpl;
import assistant.app.reply.logic.impl.ReplyMessageLogicImpl;
import assistant.app.reply.model.MenuReplyRule;
import assistant.app.reply.model.ReplyMsgTemplete;

/**
 * @ClassName MenuLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-21 下午2:08:39
 */
public class MenuLogicImpl implements IMenuLogic {

	public static final Logger LOG = LogCongfig.MENULOG;

	private static MenuLogicImpl instance = new MenuLogicImpl();

	private MenuLogicImpl() {

	}

	public static MenuLogicImpl getInstance() {
		return instance;
	}

	private IMenuDao menuDao = MenuDaoImpl.getInstance();

	private IMenuReplyRuleDao menuReplyRuleDao = MenuReplyRuleDaoImpl.getInstance();

	private IWechatAccessTokenDao accessTokenDao = WechatAccessTokenDaoImpl.getInstance();

	private IReplyMsgTempleteDao templeteDao = ReplyMsgTempleteDaoImpl.getInstance();

	private IReplyMessageLogic replyMessageLogic = ReplyMessageLogicImpl.getInstance();

	private INewsMessageRefLogic newsMessageRefLogic = NewsMessageRefLogicImpl.getInstance();

	@Override
	public long insert(Long wechatId, Long userId, String menuJson) {
		return menuDao.insert(wechatId, userId, menuJson);
	}

	@Override
	public Menu findByWechatId(Long wechatId) {
		return menuDao.findByWechatId(wechatId);
	}

	@Override
	public long update(Menu menu) {
		return menuDao.update(menu);
	}

	@Override
	public boolean createMenuDto(Long wechatId, String menuJson,String eventKey) {

		LOG.warn("启用发布菜单!");
		JSONArray jsonArray = JSONArray.fromObject(menuJson);
		MenuDto menuDto = new MenuDto();
		List<Button> buttons = new ArrayList<Button>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			String menuType = obj.getString("typ");
			if (menuType.equals(MenuType.SHOW_SEC_MENU)) {
				// 创建复合按钮
				ComplexButton complexButton = new ComplexButton();
				List<Button> subButton = new ArrayList<Button>();
				complexButton.setName(obj.getString("tit"));
				String subdata = obj.getString("subdata");
				// 子菜单个数
				String[] data = obj.getString("data").split("@");
				for (int j = 0; j < data.length; j++) {
					JSONObject subjson = JSONObject.fromObject(subdata);
					String zicd = "zizicd" + (j + 1);
					JSONObject zizicd = subjson.getJSONObject(zicd);
					String subType = zizicd.getString("typ");
					if (subType.equals(MenuType.REPLY_TEXT)) {
						String replyMsg = zizicd.getString("data");
						String menuKey = RandomCode.getRandomString(6);
						if (createMenuReplyRule(wechatId, menuKey, subType, replyMsg)) {
							ClickButton clickButton = createClickButton(zizicd, menuKey);
							subButton.add(clickButton);
						}
					} else if (subType.equals(MenuType.URL)) {
						ViewButton viewButton = createViewButton(zizicd);
						subButton.add(viewButton);
					} else if (subType.equals(MenuType.NEWS)) {
						String menuKey = RandomCode.getRandomString(6);
						ClickButton clickButton = createClickButton(zizicd, menuKey);
						subButton.add(clickButton);
					}
				}
				Button[] subBtn=subButton.toArray(new Button[subButton.size()]);
				complexButton.setSub_button(subBtn);
				buttons.add(complexButton);

			} else if (menuType.equals(MenuType.REPLY_TEXT)) {
				String replyMsg = obj.getString("data");
				if (createMenuReplyRule(wechatId, eventKey, menuType, replyMsg)) {
					ClickButton clickButton = createClickButton(obj, eventKey);
					buttons.add(clickButton);
				}
			} else if (menuType.equals(MenuType.URL)) {
				ViewButton viewButton = createViewButton(obj);
				buttons.add(viewButton);
			} else if (menuType.equals(MenuType.NEWS)) {
				// String menuKey = RandomCode.getRandomString(6);
				ClickButton clickButton = createClickButton(obj, eventKey);
				buttons.add(clickButton);
			}
		}
		Button[] buttonArray = buttons.toArray(new Button[buttons.size()]);
		for (Button b : buttonArray) {
			LOG.info("按钮:{}", b.getName());
		}
		menuDto.setButton(buttonArray);
		menuJson = JSONObject.fromObject(menuDto).toString();
		LOG.warn("菜单:{}", menuJson);
		return createMenu(wechatId, menuJson);
	}
	@Override
	public boolean createMenu(Long wechatId, String menu) {
		LOG.warn("menu:{}", menu);
		boolean isSuccess = false;
		String url = WechatConfig.MENU_CREATE_URL;
		WechatAccessToken accessToken=accessTokenDao.findByWechatId(wechatId);
		LOG.warn("accessToken:{}", accessToken.getAccessToken());
		url = url.replace("ACCESS_TOKEN", accessToken.getAccessToken());
		String result = WebUtil.httpRequest(url, "POST", menu);
		JSONObject jsonObj = JSONObject.fromObject(result);
		if (jsonObj != null) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				isSuccess = true;
			} else {
				LOG.error("创建菜单失败  errcode:{},errmsg:{}", errorCode, errorMsg);
			}
		}
		return isSuccess;
	}

	@Override
	public ClickButton createClickButton(JSONObject obj, String eventKey) {
		ClickButton clickButton = new ClickButton();
		clickButton.setKey(eventKey);
		clickButton.setName(obj.getString("tit"));
		clickButton.setType("click");
		return clickButton;
	}

	@Override
	public ViewButton createViewButton(JSONObject obj) {
		ViewButton viewButton = new ViewButton();
		viewButton.setName(obj.getString("tit"));
		viewButton.setUrl(obj.getString("data"));
		viewButton.setType("view");
		return viewButton;
	}

	@Override
	public boolean createComplexButton(JSONObject obj) {
		
		return false;
	}

	@Override
	public boolean createMenuReplyRule(Long wechatId, String menuKey, String menuType, String replyMsg) {
		return menuReplyRuleDao.add(wechatId, menuType, menuKey, replyMsg);
	}

	@Override
	public String dealMenuClick(String eventKey, String fromUserName, String toUserName, Long wechatId) {

		String respXml = null;
		MenuReplyRule menuReplyRule = menuReplyRuleDao.findByKey(eventKey, wechatId);
		LOG.warn("eventKey:{},menuReplyRule:{}", eventKey, menuReplyRule.getId());

		if (menuReplyRule != null) {
			String msgType = menuReplyRule.getMsgType();
			if (msgType.equals(MenuType.REPLY_TEXT)) {
				ReplyMsgTemplete templete = templeteDao.findById(menuReplyRule.getMsgId());
				TextMessageDto textMessageDto = replyMessageLogic.createTextMessage(fromUserName, toUserName,
						templete.getReplyMsg());
				respXml = ConvertXml.messageToXml(textMessageDto);
				replyMessageLogic.saveTextMessage(fromUserName, toUserName, templete.getReplyMsg());
			} else if (msgType.equals(MenuType.NEWS)) {

				respXml = replyMessageLogic.createNewsMessage(menuReplyRule.getMsgId(), fromUserName, toUserName);
			}
		}

		return respXml;
	}

	public static void main(String[] args) {
		String jsonStr = "[{\"typ\":\"res_ejcd\",\"data\":\"1@2\",\"tit\":\"菜单一\",\"subdata\":{\"zizicd1\":{\"typ\":\"res_wb\",\"data\":\"/酷\",\"tit\":\"9090\"},\"zizicd2\":{\"typ\":\"res_wb\",\"data\":\"/微笑\",\"tit\":\"9090000\"}}},{\"typ\":\"res_ejcd\",\"data\":\"1\",\"tit\":\"菜单二\",\"subdata\":{\"zizicd1\":{\"typ\":\"res_wb\",\"data\":\"/右哼哼\",\"tit\":\"90909090\"}}}]";
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			String menuType = obj.getString("typ");
			if (menuType.equals(MenuType.SHOW_SEC_MENU)) {
				// int menuNum = obj.getInt("data");
				String[] data = obj.getString("data").split("@");
				for (int j = 0; j < data.length; j++) {
					String subdata = obj.getString("subdata");
					// System.out.println(subdata);
					JSONObject subjson = JSONObject.fromObject(subdata);
					JSONArray arr = JSONArray.fromObject(subjson);
					String zicd = "zizicd" + (j + 1);
					JSONObject zizicd = subjson.getJSONObject(zicd);
					// System.out.println(arr);
					System.out.println(zizicd);
					// System.out.println(subjson);
				}

			}
		}

	}
}
