/**
 * @Project:assistant
 * @Title: MenuCtrl.java
 * @date: 2015-2-21 下午1:46:03
 * @version 1.0
 */
package controllers.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assistant.app.base.dto.ProcessStatus;
import assistant.app.common.util.RandomCode;
import assistant.app.menu.logic.IMenuLogic;
import assistant.app.menu.logic.impl.MenuLogicImpl;
import assistant.app.menu.model.Menu;
import controllers.base.BaseController;

/**
 * @ClassName MenuCtrl
 * @Description 自定义菜单操作
 * @author minlingchao
 * @date 2015-2-21 下午1:46:03
 */
public class MenuCtrl extends BaseController {

    public static final Logger LOG = LoggerFactory.getLogger(MenuCtrl.class);

    private static IMenuLogic menuLogic = MenuLogicImpl.getInstance();

    // 添加自定义菜单
    public static void add(Long wechatId, String menuJson) {
        Long merchatId = getMerchantId();
        validWechatInfo(merchatId, wechatId);
        Long userId = getMerchantId();
        Menu menu = menuLogic.findByWechatId(wechatId);
        if (menu != null) {
            menu.setMenuJson(menuJson);
            if (menuLogic.update(menu) > 0) {
                renderJsonSuccess();
            } else {
                renderJsonFail();
            }
        } else {
            if (menuLogic.insert(wechatId, userId, menuJson) > 0) {
                renderJsonSuccess();
            } else {
                renderJsonFail();
            }
        }

    }

    // 查找菜单

    public static void getMenu(Long wechatId) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        Menu menu = menuLogic.findByWechatId(wechatId);
        renderJsonAjaxResult(menu);
    }

    // 发布菜单
    public static void createMenu(Long wechatId, String menuJson) {

        Long userId = getMerchantId();
        Menu menu = menuLogic.findByWechatId(wechatId);

        ProcessStatus processStatus = validWechatInfo(userId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }

        String eventKey = RandomCode.getRandStr(6);
        if (menu != null) {
            menu.setMenuJson(menuJson);
            if (menuLogic.update(menu) > 0) {
                if (menuLogic.createMenuDto(wechatId, menuJson, eventKey)) {
                    renderJsonSuccess();
                } else {
                    renderJsonFail();
                }
            }
        } else {
            if (menuLogic.insert(wechatId, userId, menuJson) > 0) {
                if (menuLogic.createMenuDto(wechatId, menuJson, eventKey)) {
                    renderJsonSuccess();
                } else {
                    renderJsonFail();
                }
            }
        }
    }
}
