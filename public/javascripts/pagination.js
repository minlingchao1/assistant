define(function (require, exports, module) {
	var $ = require('jquery');
    $.fn.extend({
        pagination: function(param) {
            init(param, $(this));
            return $(this);
        }
    });

    function init(param, obj) {
        if (param && param instanceof Object) {
            var options;
            var currPage;
            var pageCount;
			var count;
            var pageItemNum;
            var tempPage;
            var defaults = new Object({
                currPage:1,
                pageCount:10,
				count:10,
                pageItemNum:5,
                ajax:{
                    on:false,
                    pageCountId:'pageCount',
					curPageId:'curPage',
                    param:{
                        //curPage:1
                    },
                    ajaxStart:function() {
                        return false;
                    }},
                info:{
                    next:'下一页',
                    prev:'上一页',
                    next_on:true,
                    prev_on:true,
                    msg_on:true,
                    link:'javascript:;',
                    msg:'<span>&nbsp;&nbsp;共&nbsp;{sumPage}&nbsp;页&nbsp;,&nbsp;{sumCount}&nbsp;条记录&nbsp;&nbsp;到&nbsp;{currText}&nbsp;页&nbsp;<input  class="gopage-submit hand" title="跳转页面" type="button" value="确定"/></span>'
                }
            });

            function getCurrPage() {
                if (typeof options.currPage != 'undefined') {
                    return options.currPage;
                } else {
                    return defaults.currPage;
                }
            }

            function getPageCount() {
                if (typeof options.pageCount != 'undefined') {
                    return options.pageCount;
                } else {
                    return defaults.pageCount;
                }
            }
			
            function getCount() {
                if (typeof options.count != 'undefined') {
                    return options.count;
                } else {
                    return defaults.count;
                }
            }

            function getPageItemNum() {
                if (typeof options.pageItemNum != 'undefined') {
                    return options.pageItemNum;
                } else {
                    return defaults.pageItemNum;
                }
            }

            function getPrev() {
                if (options.info && options.info.prev_on == false) {
                    return "";
                }
                if (options.info && options.info.prev) {
                    return options.info.prev;
                } else {
                    return defaults.info.prev;
                }
            }

            function getNext() {
                if (options.info && options.info.next_on == false) {
                    return "";
                }
                if (options.info && options.info.next) {
                    return options.info.next;
                } else {
                    return defaults.info.next;
                }
            }

            function getLink() {
                if (options.info && options.info.link) {
                    return options.info.link;
                } else {
                    return defaults.info.link;
                }
            }

            function getAjax() {
                if (options.ajax && options.ajax.on) {
                    return options.ajax;
                } else {
                    return defaults.ajax;
                }
            }

            function getParam() {
				var curpageId=getCurPageId();
                if (options.ajax.param) {
                    options.ajax.param[curpageId] = currPage;
                    return options.ajax.param;
                } else {
                    defaults.ajax.param[curpageId] = currPage;
                    return defaults.ajax.param;
                }
            }

            function getPageCountId() {
                if (options.ajax && options.ajax.pageCountId) {
                    return options.ajax.pageCountId;
                } else {
                    return defaults.ajax.pageCountId;
                }
            }

            function getCurPageId() {
                if (options.ajax && options.ajax.curPageId) {
                    return options.ajax.curPageId;
                } else {
                    return defaults.ajax.curPageId;
                }
            }

            function getAjaxStart() {
                if (options.ajax && options.ajax.ajaxStart) {
                    options.ajax.ajaxStart();
                } else {
                    defaults.ajax.ajaxStart;
                }
            }

            function getMsg() {
                var input = "<input type='text' class='jumpTo' value='" + currPage + "' >";
                var str;
                if (options.info && options.info.msg_on == false) {
                    return false;
                }
                str = (options.info && options.info.msg) ? options.info.msg : defaults.info.msg;
                str = str.replace("{currText}", input);
                str = str.replace("{currPage}", currPage);
                str = str.replace("{sumPage}", pageCount);
                str = str.replace("{sumCount}", count);

                return str;
            }
        }

        function getText() {
            var msg = getMsg();
            if (msg) {
                msg = $(msg);
            } else {
                return "";
            }
            return msg.html();
        }

        function isCode(val) {
            if (val < 1) {
	
                alert("输入值不能小于1");
                return false;
            }
            var patrn = /^[0-9]{1,8}$/;
            if (!patrn.exec(val)) {
	
                alert("请输入正确的数字");
                return false;
            }
            if (val > pageCount) {
	
                alert("输入值不能大于总页数");
                return false;
            }
            return true;
        }

        function updateView() {
            currPage = parseInt(currPage);
            pageCount = parseInt(pageCount);
            count = parseInt(count);
			
            var link = getLink();
            var lastPage;
            var firstPage = lastPage = 1;
			
            if (currPage - tempPage > 0) {
                firstPage = currPage - tempPage;
            } else {
                firstPage = 1;
            }
            if (firstPage + pageItemNum > pageCount) {
                lastPage = pageCount + 1;
                firstPage = lastPage - pageItemNum;
            } else {
                lastPage = firstPage + pageItemNum;
            }
            var content = "";

            if (currPage == 1) {
                content += "<span class=\"page-prev page-prev-disabled\" title=\"" + getPrev() + "\">上一页</span>&nbsp;";
            } else {
                content += "<a class='page-prev page-prev-abled' href='" + link + "' title='" + (currPage - 1) + "'>上一页</a>&nbsp;";
            }
            if (firstPage <= 0) {
                firstPage = 1;
            }
            for (firstPage; firstPage < lastPage; firstPage++) {
                if (firstPage == currPage) {
                    content += "<span class=\"current\" title=\"" + firstPage + "\">" + firstPage + "</span>&nbsp;";
                } else {
                    content += "<a href='" + link + "' title='" + firstPage + "'>" + firstPage + "</a>&nbsp;";
                }
            }
            if (currPage == pageCount) {
                content += "<span class=\"page-next page-next-disabled\" title=\"" + getNext() + "\">下一页</span>&nbsp;";
            } else {
                content += "<a class='page-next page-next-abled' href='" + link + "' title='" + (currPage + 1) + "'>下一页 </a>&nbsp;";
            }

            content += getText();
            obj.html(content);
            obj.children(":text").keypress(function(event) {
                var keycode = event.which;
                if (keycode == 13) {
                    var page = $(this).val();
                    if (isCode(page)) {

                        createView(page);
                    }
                }
            });

            obj.children(":button").click(function() {
                var page = obj.children(":text").val();
                if (isCode(page)) {
                    createView(page);
                }
            });

            obj.children("a").click(function(i) {
                var page = this.title;
                createView(page);
            });
			
		}


        function createView(page) {
			obj.hide();
			
            currPage = page;
            var ajax = getAjax();
            if (ajax.on) {
                getAjaxStart();
                var varUrl = ajax.url;
                var param = getParam();
                $.ajax({
                    url:varUrl,
                    type:'get',
                    data:param,
                    contentType:"application/x-www-form-urlencoded;utf-8",
                    //async:false,
                    dataType:'json',
                    error:function(jqXHR, textStatus) {
                        updateView();

                    },
                    success:function(data) {
                        loadPageCount({
                            dataType:ajax.dataType,
                            callback:ajax.callback,
                            data:data
                        });
                        updateView();
                        

                    }
				});
            } else {
                updateView();
            }
        }



        function checkParam() {
            if (currPage < 1) {
	
                //alert("配置参数错误\n错误代码:-1");
                return false;
            }
            if (currPage > pageCount) {
	
                //alert("配置参数错误\n错误代码:-2");
                return false;
            }
            if (pageItemNum < 2) {
	
                //alert("配置参数错误\n错误代码:-3");
                return false;
            }
            return true;
        }

        function loadPageCount(options) {
            if (options.dataType) {
                var formData;
                var data = options.data;
                var resultPageCount = false;
                var isB = true;
                var pageCountId = getPageCountId();
                var callback;
				if(data.code==200||data.success){
					switch (options.dataType) {
	                    case"json":
	                        formData = options.data;
	                        resultPageCount = formData.pageCount;
							count=formData.count;
							
	                        break;
	                    case"xml":
	                        resultPageCount = $(data).find(pageCountId).text();
	                        break;
	                    default:
	                        isB = false;
	                        options.callback && options.callback(data);
	                        resultPageCount = $("#" + pageCountId).val();
	                        break;
	                }
	                pageCount = resultPageCount;
					if(pageCount<=1){
						obj.hide();
					}else{
						obj.show();
					}
				}else{
					obj.hide();
					
				}
                

                if (isB) {
                    options.callback && options.callback(data);
                }
            }
        }

        options = param;
        currPage = getCurrPage();
        pageCount = getPageCount();
        pageItemNum = getPageItemNum();
        tempPage = parseInt(pageItemNum / 2);
        if (checkParam() && createView(currPage)) {
            updateView();
        }
		
    }
	exports.pagination;
});