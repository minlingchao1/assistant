/**
 * @Project:assistant
 * @Title: SceneCtrl.java
 * @date: 2015-3-3 下午5:55:44
 * @version 1.0
 */
package controllers.scene;

import java.util.List;

import assistant.app.base.dto.ProcessStatus;
import assistant.app.scene.logic.ISceneLogic;
import assistant.app.scene.logic.impl.SceneLogicImpl;
import assistant.app.scene.model.Scene;
import controllers.base.BaseController;

/**
 * @ClassName SceneCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-3 下午5:55:44
 */
public class SceneCtrl extends BaseController {

    private static ISceneLogic sceneLogic = SceneLogicImpl.getInstance();

    public static void addScene(long wechatId, String sceneName, long start, long end, String keyword, String pageImg,
            String descr, String shareTitle, String shareContent, int openSet, String openImg, int outSet,
            String outImg, int slide, String musicSet, String music, String openBtnImg, String outBtnImg,
            String openBtnUrl, String outBtnUrl, String openBtnPos, String outBtnPos) {
        long userId = getMerchantId();
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        if (sceneLogic.addScene(userId, wechatId, sceneName, start, end, keyword, pageImg, descr, shareTitle,
                shareContent, openSet, openImg, outSet, outImg, slide, musicSet, music, openBtnImg, outBtnImg,
                openBtnUrl, outBtnUrl, openBtnPos, outBtnPos)) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    public static void getSceneList(long wechatId) {
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        List<Scene> scenes = sceneLogic.getSceneList(wechatId);
        renderJsonAjaxResult(scenes);
    }

}
