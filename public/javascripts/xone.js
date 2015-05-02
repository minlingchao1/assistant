var bodyWidth = 1024;
var winHeight = 768;
var eHomeHeader = [];
var ePages = [];
var ePageTwo = [];
var ePageThree = [];
var ePageFour = [];
var ePageFive = [];
var ePageSix = [];
var ePageSeven = [];

var trigger = 0;

var scrollTop = 0;
var bodyWidth = 0;
var winHeight = 0;
var scrollRange = 0;
var scrollPos = 0;
var homeHeaderHeight = 600;
var homeHeaderScrollRange = homeHeaderHeight;
var isScroll = false;
var pageOneTop = 0;
var pageTwoTop = 0;
var pageThreeTop = 0;
var pageFourTop = 0;
var pageFiveTop = 0;
var pageSixTop = 0;
var pageSevenTop = 0;
var currentPageIndex = 0;
var scrollCount = 0;
function getPage(index) {
	if (index == 0)
		return eHomeHeader;
	if (index == 1)
		return ePageTwo;
	if (index == 2)
		return ePageThree;
	if (index == 3)
		return ePageFour;
	if (index == 4)
		return ePageFive;
	if (index == 5)
		return ePageSix;
	if (index == 6)
		return ePageSeven;
}

function calcCurrentPageIndex() {
	pageOneTop = getPage(0).offset().top;
	pageTwoTop = getPage(1).offset().top;
	pageThreeTop = getPage(2).offset().top;
	pageFourTop = getPage(3).offset().top;
	pageFiveTop = getPage(4).offset().top;
	pageSixTop = getPage(5).offset().top;
	pageSevenTop = getPage(6).offset().top;

	scrollTop = $(window).scrollTop();
	if (scrollTop >= pageSevenTop)
		currentPageIndex = 6;
	else if (scrollTop >= pageSixTop)
		currentPageIndex = 5;
	else if (scrollTop >= pageFiveTop)
		currentPageIndex = 4;
	else if (scrollTop >= pageFourTop)
		currentPageIndex = 3;
	else if (scrollTop >= pageThreeTop)
		currentPageIndex = 2;
	else if (scrollTop >= pageTwoTop)
		currentPageIndex = 1;
	else
		currentPageIndex = 0;
	$('.slide-control li').each(function() {
		$(this).removeClass('active');
	});
	var currentIndicator = $($('.slide-control li')[currentPageIndex]);
	currentIndicator.addClass('active');

	$('#top-menu li a').each( function() {
		$(this).removeClass('active');
	});
	var currentMenu = $($('#top-menu li a')[currentPageIndex]);
	currentMenu.addClass('active');
}
function initAdvantages() {
	if ( eAdvantages.length && !eAdvantages.hasClass('completed') ) {
		eAdvantages.removeClass('animating');
		eAdvantages.find('.advantage').each(function() {
			jadvantage = $(this);
			jadvantage.data('targetTop',jadvantage.css('top'));
		});
		eAdvantages.addClass('animating');

	}
}

function runAdvantages() {
	if ( eAdvantages.length ) {
		if ( eAdvantages.hasClass('animating') && !eAdvantages.hasClass('completed') ) {
			//eAdvantages.addClass('completed').slideUp(1200);
			//eAdvantages.find('.leaf .animate').animate({width: '100%',height: '100%'}, 1200);
			var eAdvantageFirst = eAdvantages.find('.advantage.first');
			var targetTop = eAdvantageFirst.data('targetTop');
			eAdvantageFirst.delay(100).animate({top: targetTop}, 1000);

			var eAdvantageSecond = eAdvantages.find('.advantage.second');
			targetTop = eAdvantageSecond.data('targetTop');
			eAdvantageSecond.delay(200).animate({top: targetTop}, 1000);

			var eAdvantageThird = eAdvantages.find('.advantage.third');
			targetTop = eAdvantageThird.data('targetTop');
			eAdvantageThird.delay(300).animate({top: targetTop}, 1000);

			eAdvantages.find('.advantage .leaf .animate').each(function() {
				img = $(this);
				img.data('targetTop',jadvantage.css('top'));
			});
		}
		eAdvantages.addClass('completed');
	}
}

function resetAdvantages() {

	if ( eAdvantages.length ) {
		eAdvantages.find('.leaf .animate').each(function() {
			$(this).parents('.advantage').css('top','');
		});
		eAdvantages.removeClass('completed');
	}

}

function runFeatures() {
	if ( eFeatures.length ) {
		//eFeatures.find('.feature.left').animate({left: '10%'}, 1200, function() {
		//	eFeatures.addClass('completed');
		//});
		//eFeatures.find('.feature.right').animate({right: '10%'}, 1200, function() {
		//	eFeatures.addClass('completed');
		//});
		eFeatures.addClass('completed');
		eFeatures.find('.feature.left').addClass('completed');
		eFeatures.find('.feature.right').addClass('completed');
	}
}

function resetFeatures() {
	if ( eFeatures.length ) {
		eFeatures.removeClass('completed');
		eFeatures.find('.feature.left').removeClass('completed');
		eFeatures.find('.feature.right').removeClass('completed');
	}
}

function runAnimation4() {
	$('#page4 .phone').fadeIn(2000);
	$('#page4 .info').addClass('completed');
	$('#page4 .phone').addClass('completed');
}

function resetAnimation4() {
	$('#page4 .phone').css('display', 'none');
	$('#page4 .phone').removeClass('completed');
	$('#page4 .info').removeClass('completed');
}

function runAnimation5() {
	$('#page5 .info').fadeIn(2000);
	$('#page5 .info').addClass('completed');
	$('#page5 .client-app').addClass('completed');
}

function resetAnimation5() {
	$('#page5 .info').css('display', 'none');
	$('#page5 .client-app').removeClass('completed');
	$('#page5 .info').removeClass('completed');
}

function runAnimation6() {
	$('#page6 .phone').fadeIn(2000);
	$('#page6 .info').addClass('completed');
	$('#page6 .phone').addClass('completed');
}

function resetAnimation6() {
	$('#page6 .phone').css('display', 'none');
	$('#page6 .info').removeClass('completed');
	$('#page6 .phone').removeClass('completed');
}

function wheelScrollFunc(e) {
	if (isScroll)
		return;
	/*
	scrollCount = scrollCount+1;
	if (scrollCount == 1)
		return;
	else if (scrollCount == 2)
		scrollCount = 0;
	*/
	var direct = 0;
	e = e || window.event;
	if (e.wheelDelta)
		direct = e.wheelDelta > 0;
	else if (e.detail)
		direct = e.detail < 0;
	if (direct > 0) {
		currentPageIndex = currentPageIndex - 1;
		if (currentPageIndex < 0) {
			currentPageIndex = 0;
			return;
		}

	} else {
		currentPageIndex = currentPageIndex + 1;
		if (currentPageIndex > 6) {
			currentPageIndex = 6;
			return;
		}
	}

	var options = { speed:1000, easing:'easeOutCubic'};
	if (currentPageIndex == 0)
		smoothScroll.animateScroll(null, '#home-header', options);
	if (currentPageIndex == 1)
		smoothScroll.animateScroll(null, '#page2', options);
	if (currentPageIndex == 2)
		smoothScroll.animateScroll(null, '#page3', options);
	if (currentPageIndex == 3)
		smoothScroll.animateScroll(null, '#page4', options);
	if (currentPageIndex == 4)
		smoothScroll.animateScroll(null, '#page5', options);
	if (currentPageIndex == 5)
		smoothScroll.animateScroll(null, '#page6', options);
	if (currentPageIndex == 6)
		smoothScroll.animateScroll(null, '#page7', options);
}

function bodyScroll() {
	scrollTop = $(window).scrollTop();


	if ( eAdvantages.length ) {
		if ( eAdvantages.hasClass('completed') ) {
			trigger = eAdvantages.offset().top - winHeight;
			if ( scrollTop <= trigger ) {
				resetAdvantages();
			}
		} else {
			trigger = eAdvantages.offset().top + eAdvantages.outerHeight() - winHeight;
			if ( scrollTop >= trigger ) {
				runAdvantages();
			}
		}
	}

	if ( eFeatures.length) {
		if ( eFeatures.hasClass('completed') ) {
			trigger = eFeatures.offset().top - winHeight;
			if ( scrollTop <= trigger ) {
				resetFeatures();
			}
		} else {
			trigger = eFeatures.offset().top + eFeatures.outerHeight() - winHeight*2/3;
			if ( scrollTop >= trigger ) {
				runFeatures();
			}
		}
	}

	if ( !$('#page4 .phone').hasClass('completed') ) {
		trigger = $('#page4').offset().top - winHeight*2/3;
		if ( scrollTop >= trigger ) {
			runAnimation4();
		}
	} else {
		trigger = $('#page4').offset().top - winHeight;
		if ( scrollTop <= trigger ) {
			resetAnimation4();
		}
	}

	if ( !$('#page5 .client-app').hasClass('completed') ) {
		trigger = $('#page5').offset().top - winHeight*2/3;
		if ( scrollTop >= trigger ) {
			runAnimation5();
		}
	} else {
		trigger = $('#page5').offset().top - winHeight;
		if ( scrollTop <= trigger ) {
			resetAnimation5();
		}
	}

	if ( !$('#page6 .phone').hasClass('completed') ) {
		trigger = $('#page6').offset().top - winHeight*2/3;
		if ( scrollTop >= trigger ) {
			runAnimation6();
		}
	} else {
		trigger = $('#page6').offset().top - winHeight;
		if ( scrollTop <= trigger ) {
			resetAnimation6();
		}
	}

	calcCurrentPageIndex();

}

function bodyResize() {
	bodyWidth = $(document.body).innerWidth();
	winHeight = $(window).height();
	if ( ePages.length ) {
		ePages.each(function() {
			$(this).css('height',winHeight+'px');
		});
	}
	//ePageSeven.css('height','auto');
	initAdvantages();
	calcCurrentPageIndex();

}

$(document).ready(function() {
	eHomeHeader = $('#home-header');
	ePages = $('.page');
	ePageTwo = $('#page2');
	ePageThree = $('#page3');
	ePageFour = $('#page4');
	ePageFive = $('#page5');
	ePageSix = $('#page6');
	ePageSeven = $('#page7');

	eAdvantages = $('#page2 .advantages');
	eFeatures = $('#page3 .features');
	eFeature = eFeatures.find('.feature');

	$(window).scroll(bodyScroll);
	$(window).resize(bodyResize);

	if (document.addEventListener) {
		document.addEventListener('DOMMouseScroll', wheelScrollFunc, false);
	}
	window.onmousewheel = document.onmousewheel = wheelScrollFunc;
	bodyResize();
});
