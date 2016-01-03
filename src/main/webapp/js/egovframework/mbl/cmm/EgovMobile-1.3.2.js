/*
	Copyright 2011, jQuery Project
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
 */

/************************************************************************
   파일명 : EgovMobile.js
   설  명 : 모바일 전자정부 실행환경 공통 JavaScript
   수정일       수정자        Version        Function 명
  -------      ----------      ----------     -----------------
  2011.07.14   윤병욱         1.0              최초 생성
************************************************************************/



/************************************************************************
   함수명 : 기타 Dialog                                  
   설  명 : actionsheet, alert, overlay, prompt, comfirm dialog 사용                
   사용법 : jActionSheet(), jAlert(), jOverlay(), jPrompt, jComfirm Dialog 사용
   인  자 : 제목, 내용, 색상, 리턴값
   작성일 : 2011-07-14   
   작성자 : 모바일 실행환경 개발팀 황민희       
   수정일       수정자             수정내용
   ------      ------     -------------------
   2011.07.14    황민희        최초생성                                       
************************************************************************/
/*
	jQuery Alert Dialogs Plugin
	Version 1.1
	Cory S.N. LaViska
	14 May 2009
 */
(function($) {
	
	$.alerts = {
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .01,                // transparency level of overlay
		overlayColor: '#FFF',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;확인&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;닫기&nbsp;', // text for the Cancel button

		alert: function(message, title, theme, callback) {
			if( title == null ) title = 'Alert';
			$.alerts._show(title, message, theme, null,  'alert', function(result) {
				if( callback ) callback(result);
			});
		},

		ActionSheet: function(message, title, theme, btmItem, callback) {
			if( title == null ) title = 'ActionSheet';
			$.alerts._show(title, message, theme, btmItem, 'ActionSheet', function(result) {
				if( callback ) callback(result);
			});
		},

		Overlay: function(message, title, theme,  callback) {
			if( title == null ) title = 'Overlay';
			$.alerts._show(title, message, theme, null,'Overlay', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, theme, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, theme, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, title, theme,  callback) {
			if( title == null ) title = 'Prompt';
			$.alerts._show(title, message, theme, null,  'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
	
		_show: function(title, msg, value, btmItem, type, callback) { //value= theme
			if(value == null || value ==''){
				value = "a";
			}
				
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');

			if(value =="undefined" || value == null){
				var dialclass = $('.ui-page-active').attr("class");
				var dialindex = $('.ui-page-active').attr("class").indexOf("ui-body-");				
				var theme = $('.ui-page-active').attr("class")[dialindex+8];
			}else{
				theme = value;
			}
			// IE6 fix  $.browser 객체가 jQueryMobile 1.9 버전에서 undefined 되어 주석처리함
			//var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 

			var dataTheme = "ui-dialog-theme-"+theme;

			$("#popup_container").css({
				position: "absolute",
				zIndex: 99999,
			});

			$("#popup_container").addClass(dataTheme);
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
		msg = "<div class='ui-dialog-msg'>" + msg + "</div>";
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'ActionSheet':
					var popup_msg = '<div id="popup_panel" >' ;
					
					$("#popup_message").append('<div id="popup_panel" >');
					
					for(var i =0 ; i < btmItem.length ; i++){	
						popup_msg +='<input type="button" value="' +btmItem[i].value + '" id="'+btmItem[i].id+'"/>';					
					}
					popup_msg+='<input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>';
					
					$("#popup_message").append(popup_msg);
				
					for(var i =0 ; i < btmItem.length ; i++){
						$("#"+btmItem[i].id).click( function() {
							if( callback ) callback(this.value);
							$.alerts._hide();		
						});
					}	
				
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#select1ok").focus();
					$("#select1ok, #select2ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#select1ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				
				case 'Overlay':
					$("#popup_message").after('<div id="popup_panel"></div>');
					$("#popup_container").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel" ><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					$("#popup_message").append('<input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});

					$("#popup_prompt").focus().select();
				break;
			}
			
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99,		// 안드로이드 폰에 들어가면 List 밑으로 간다. 수정 필요 (99998에서 99로 변경 (2012-08-03))
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var height = $(window).height();
			var width = $(window).width();
			var scrollPosition = $(window).scrollTop();
 
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			var top =(scrollPosition + height / 2 - $("#popup_container").outerHeight() / 2);

			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix  $.browser 객체가 jQueryMobile 1.9 버전에서 undefined 되어 주석처리함
			//if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({			
				top: top+"px",
				left: left +"px"
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
	
	jAlert = function(message, title, theme , callback) {
		$.alerts.alert(message, title, theme, callback);
	}
	
	jActionSheet = function(message, title, theme, btmItem, callback) {	
		$.alerts.ActionSheet(message, title, theme, btmItem, callback);
	}
	
	jOverlay = function(message, title, theme, callback) {
		$.alerts.Overlay(message, title, theme, callback);
	}
	
	jConfirm = function(message, title, theme, callback) {
		$.alerts.confirm(message, title, theme, callback);
	};
		
	jPrompt = function(message, title, theme,  callback) {
		$.alerts.prompt(message, title, theme, callback);
	};
	
})(jQuery);


/************************************************************************
   함수명 : Tabs                                  
   설  명 : 문서 내 이동을 Tab으로 구현              
   사용법 :  <data-role="tabs">
   작성일 : 2011-07-14   
   작성자 : 모바일 실행환경 개발팀 윤병욱       
   수정일       수정자             수정내용
   ------      ------     -------------------
   2011.07.14    윤병욱        최초생성                                       
*************************************************************************/
(function($, undefined ) {
$.widget( "mobile.tabs", $.mobile.widget, {
	options: {
		iconpos: 'top',
		grid: null,
		load: function(event, ui) { },
		beforeTabHide: function(event, ui) { },
		beforeTabShow: function(event, ui) { },
		afterTabShow:  function(event, ui) { }
	},
	_create: function(){
		var
			$this = this,
			$tabs = this.element,
			$navbtns = $tabs.find("a"),
			iconpos = $navbtns.filter('[data-icon]').length ? this.options.iconpos : undefined;
		var $content = $tabs.closest('div[data-role="page"]').find('div[data-role="content"]');

		$tabs
			.addClass('ui-navbar')
			.attr("role","navigation")
			.find("ul")
				.grid({grid: this.options.grid });

		if( !iconpos ){ 
			$tabs.addClass("ui-navbar-noicons");
		}

		$navbtns
			.buttonMarkup({
				corners: false,
				shadow:  false,
				iconpos: iconpos
			})
			.removeClass('ui-link');

		$tabs.delegate("a", "click",function(event){
			$navbtns.removeClass( "ui-btn-active" );
			$( this ).addClass( "ui-btn-active" );
			event.preventDefault();
			return false;
		});

		// Set up the direct children of the page as the tab content, hide them
		$content.children().addClass('ui-tabs-content');
		
		// Now show the one that's active
		if( $navbtns.filter('.ui-btn-active').length == 0 )
			$navbtns.first().addClass('ui-btn-active');
		$content.children($navbtns.eq($this.currentTab()).attr('href')).addClass('ui-tabs-content-active');

		$navbtns.bind('click', function(event) {
			$this.changeTab(event, {
				currentTab: $navbtns.eq($this.currentTab()),
				nextTab: $(this),
				currentContent: $this.currentContent(),
				nextContent: $content.children($(this).attr('href'))
			});
		});

		this._trigger('load', null, {
			currentTab: $navbtns.eq($this.currentTab()),
			currentContent: $this.currentContent()
		});
	},
	currentTab: function() {
		var $tabs = this.element,
		$navbtns = $tabs.find("a");
		return this.element.find('.ui-btn-active').parent().prevAll().length;
	},
	currentContent: function() {
		return this.element.closest('div[data-role="page"]').find('div[data-role="content"]').children().filter('.ui-tabs-content-active');
	},
	changeTab: function(event, ui) {
		if( this._trigger('beforeTabHide', event, ui) )
		ui.currentContent.siblings().andSelf().removeClass('ui-tabs-content-active');
		if( this._trigger('beforeTabShow', event, ui) )
			ui.nextContent.addClass('ui-tabs-content-active');
		this._trigger('afterTabShow', event, $.extend({}, ui, { previousContent: ui.currentContent, currentContent: ui.nextContent, nextContent: null }));
	}
});


/***********************************************************************************************/
$(document).bind ("pagecreate create", function (e) 
		  { 
		    $(":jqmData(role=tabs)", e.target).tabs();
		  });
/***********************************************************************************************/

})( jQuery );

/************************************************************************
함수명 : activePageTheme                                  
설  명 : 활성화 된 page 의 테마를 조회한다.              
사용법 : activePageTheme();
작성일 : 2011-07-14   
작성자 : 모바일 실행환경 개발팀 윤병욱       
수정일       수정자             수정내용
------      ------     -------------------
2011.07.14    윤병욱        최초생성                                       
*************************************************************************/
function activePageTheme() {
    var dataTheme;
    
    if ($('.ui-page-active').length > 0) {
        $pageClass = $('.ui-page-active').attr('class');
        
        var startIndex = $pageClass.indexOf('ui-body-') + 8;
        var endIndex = startIndex + 1;

        if (startIndex > 0){
            dataTheme = $pageClass.substring(startIndex, endIndex);
        } else {
            dataTheme = 'a';
        }
        
    }

    return dataTheme;
}		

/************************************************************************
   함수명 : EgovMobile 초기화                                  
   설  명 : tabs 추가, small button 설정, popup_container 제거 기능                    
   사용법 : onload 시 자동 실행
   작성일 : 2011-07-14   
   작성자 : 모바일 실행환경 개발팀 구지연       
   수정일       수정자             수정내용
   ------      ------     -------------------
   2011.07.14    윤병욱        최초생성                                       
************************************************************************/

$(document).on('pageshow', ":jqmData(role='page')", function(){
	
	// 활성화 페이지 조회
	$activePage = $('[class*="ui-page-active"]');
	
	// tabs 초기화
	$activePage.find('[data-role="tabs"]').tabs();

	// small button 초기화
	$activePage.find('a[class*="egov-btn-small"] span').addClass("egov-btn-small-span");
    
    // 페이지 생성시 이전 popup 메시지 제거
	if($('#popup_container').length > 0) {
		$.alerts._hide();
	}
	
});