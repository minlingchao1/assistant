/**
 * @Project:assistant
 * @Title: ISceneImgLogic.java
 * @date: 2015-3-5 下午12:47:27
 * @version 1.0
 */
package assistant.app.scene.logic;

/**
 * @ClassName ISceneImgLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午12:47:27
 */
public interface ISceneImgLogic {
	
	public long insert(Long sceneId, int reorder, int imgSet, String img, String btnImg, String btnPos, String btnUrl);

}
