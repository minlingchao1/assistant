package controllers;

import org.apache.commons.lang.StringUtils;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http.Cookie;
import assistant.app.base.config.BaseConfigs;

public class AssistantUI extends Controller {

	@Before(unless = { "home", "register", "login" })
	static void check() {

		// 从session中获取
		String merchantId = session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID);

		// 查找cookie中是否存在merchantId
		if (StringUtils.isEmpty(merchantId)) {
			Cookie cookie = request.cookies.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID);
			if (cookie != null) {
				merchantId = cookie.value;
			}
		}
		if (merchantId == null) {
			render("/Application/index.html");
		}
	}
    public static void home(){
      render("/Application/index.html"); 
    }
    public static void index() {
        render("/Application/bind_wechat.html");
    }
    
    public static void register(){
    	render("/Application/register.html");
    }
    
    
    public static void login(){
       render("/Application/login.html");
    }
    
    public static void changePasswd(){
       render("/Application/change_passwd.html");
    }
    
    public static void accountInfo(){
       render("/Application/edit_info.html");
    }
    
    public static void addWechat(){
       render("/Application/add_wechat.html"); 
    }
    
    public static void wxIndex(){
       render("/Application/wx_index.html");
    }
    
    public static void firstFollow(){
       render("Application/first_follow.html");
    }

	public static void keywordReply(){
    	render("/Application/keyword_reply.html");
    }

	public static void keywordAdd() {
		render("/Application/keyword_add.html");
	}

    public static void singleArticle() {
		render("/Application/single_article.html");
	}

    
    public static void multiArticle() {
		render("/Application/multi_article.html");
	}


	public static void qrcodeCreate() {
		render("/Application/qrcode_create.html");
	}

	public static void qrcodeCount() {
		render("/Application/qrcode.html");
	}

	public static void menu() {
		render("/Application/menu.html");
	}
	
	public static void test(){
	    render("/Application/test.html");
	}
	
	public static void source(){
		render("/Application/source_manage.html");
	}
	
	public static void fans() {
		render("/Application/fans.html");
	}

	public static void fansGroup() {
		render("/Application/fans_group.html");
	}

	public static void card() {
		render("/Application/card.html");
	}

	public static void cardAdd() {
		render("/Application/card_add.html");
	}

	public static void scene() {
		render("Application/scene/scene.html");
	}

	public static void sceneAdd() {
		render("/Application/scene/scene_add.html");
	}

	public static void sceneImgAdd() {
		render("/Application/scene/scene_img_add.html");
	}

	public static void sceneImg() {
		render("/Application/scene/scene_img.html");
	}

	public static void overall() {
		render("/Application/360/overall_list.html");
	}

	public static void overallAdd() {
		render("/Application/360/overall_add.html");
	}

	public static void overallTempl() {
		render("/Application/360/overall_templ.html");
	}
	public static void threeView() {
		render("/Application/360/3dview.html");
	}

	public static void lbsAdd() {
		render("/Application/lbs/lbs_add.html");
	}

	public static void lbs() {
		render("/Application/lbs/lbs.html");
	}
} 