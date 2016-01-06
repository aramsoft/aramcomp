   /************************************************************************
	작업명 : Browser 객체 생성
 	설  명 : jQuery 1.9.1에서 jQuery.browser() 메서드 제거로 Browser Dectection 함수처리
	사용법 : $.browser.msie -> browser.msie 변경처리(Line 142, 304 확인)
	인  자 :
	작성일 : 2013-10-01
	작성자 : 유지보수 옥찬우
	수정일			수정자		수정내용
	----------      ------		-------------------
	2011.07.14		옥찬우		최초생성
    ************************************************************************/
	var browser = ( function() {
		var s = navigator.userAgent.toLowerCase();
		var match = /(webkit)[ \/](\w.]+)/.exec(s) ||
					/(opera)(?:.*version)?[ \/](\w.]+)/.exec(s) ||
					/(msie) (\w.]+)/.exec(s) ||
					/(mozilla)(?:.*? rv:([\w.]+))?/.exec(s) ||
					[];
		return { name: match[1] || "", version: match[2] || "0" };
	}());

(function($) {

	$.password = {
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .01,                // transparency level of overlay
		overlayColor: '#FFF',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;확인&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;닫기&nbsp;', // text for the Cancel button

		password: function(message, title, theme,  callback) {
			if( title == null ) title = 'Password';
			$.password._show(title, message, theme, null,  'password', function(result) {
				if( callback ) callback(result);
			});
		},

		_show: function(title, msg, value, btmItem, type, callback) { //value= theme
			if(value == null || value ==''){
				value = "a";
			}

			$.password._hide();
			$.password._overlay('show');

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
			// IE6 Fix
			// var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed';
			// jQuery update로 Browser Detection 처리 변경
			var pos = (browser.msie && parseInt(browser.version) <= 6 ) ? 'absolute' : 'fixed';

			var dataTheme = "ui-dialog-theme-"+theme;

			$("#popup_container").css({
				position: "absolute",
				zIndex: 99999
			});

			$("#popup_container").addClass(dataTheme);

			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
		msg = "<div class='ui-dialog-msg'>" + msg + "</div>";
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br/>') );

			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});

			$.password._reposition();
			$.password._maintainPosition(true);

			switch( type ) {
				case 'password':
					$("#popup_message").append('<input type="text" size="30" id="popup_prompt"/> <input type="hidden" size="30" id="popup_password"/>').after('<div id="popup_panel"><input type="button" value="' + $.password.okButton + '" id="popup_ok"/> <input type="button" value="' + $.password.cancelButton + '" id="popup_cancel"/></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						val = $('#popup_password').val() + val.replace(/\*/gi, "");
						$.password._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.password._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});

					$(document).on('keypress', "#popup_prompt", function(e) {

						var password = $('#popup_prompt').val();
						$('#popup_prompt').val(password.replace(/\w/gi, "*"));

						var writngPassword = $('#popup_password').val() + password.replace(/\*/gi, "");
						$('#popup_password').val(writngPassword);
					});

					$("#popup_prompt").focus().select();
				break;
			}

			if( $.password.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},

		_hide: function() {
			$("#popup_container").remove();
			$.password._overlay('hide');
			$.password._maintainPosition(false);
		},

		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.password._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						opacity: $.password.overlayOpacity
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

			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.password.horizontalOffset;
			var top =(scrollPosition + height / 2 - $("#popup_container").outerHeight() / 2);

			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;

			// IE6 fix
			//if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			// jQuery update로 Browser Detection 처리 변경
			if( browser.msie && parseInt(browser.version) <= 6 ) top = top + $(window).scrollTop();

			$("#popup_container").css({
				top: top+"px",
				left: left +"px"
			});
			$("#popup_overlay").height( $(document).height() );
		},

		_maintainPosition: function(status) {
			if( $.password.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.password._reposition);
					break;
					case false:
						$(window).unbind('resize', $.password._reposition);
					break;
				}
			}
		}

	};

	jPassword = function(message, title, theme,  callback) {
		$.password.password(message, title, theme, callback);
	};

})(jQuery);

	/**
    * 모바일 페이지 처리용
    * @param paginationInfo
    * @return
    */
   function pagenationRenderer(paginationInfo, jsFunction) {
	   var pageHtml = "";

   	var firstPageNo = paginationInfo.firstPageNo;
   	var firstPageNoOnPageList = paginationInfo.firstPageNoOnPageList;
   	var totalPageCount = paginationInfo.totalPageCount;
   	var pageSize = paginationInfo.pageSize;
   	var lastPageNoOnPageList = paginationInfo.lastPageNoOnPageList;
   	var currentPageNo = paginationInfo.currentPageNo;
   	var lastPageNo = paginationInfo.lastPageNo;

   	if(totalPageCount > pageSize){
   		if(firstPageNoOnPageList > pageSize){
   			pageHtml += '<a href="javascript:'+jsFunction+'(' + firstPageNo + '); return false;" class=\"first\" onclick="'+jsFunction+'(' + firstPageNo + '); return false;"></a>';
   			pageHtml += '<a href="javascript:'+jsFunction+'(' + (parseInt(firstPageNoOnPageList)-1) + '); return false;" class=\"prev\"onclick="'+jsFunction+'(' + (parseInt(firstPageNoOnPageList)-1) + '); return false;\"></a>';
   		}else{
   			pageHtml += '<a href="javascript:'+jsFunction+'(' + firstPageNo + '); return false;" class=\"first\" onclick="'+jsFunction+'(' + firstPageNo + '); return false;"></a>';
   			pageHtml += '<a href="javascript:'+jsFunction+'(' + firstPageNo + '); return false;" class=\"prev\" onclick="'+jsFunction+'(' + firstPageNo + '); return false;"></a>';
   		}
   	}

   	for(var i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++){
   		if(i == currentPageNo){
   			pageHtml += '<span class="num"><strong>' + i + '</strong></span>';
   		}else{
   			//otherPageLabel = '<a href="#" class=\"num\" onclick="{0}({' + i + '}); return false;">' + i + '</a>';
   			//pageHtml += '<a href="#" class=\"num\" onclick="{0}({' + i + '}); return false;">' + i + '</a>';
   		}
   	}
   	if(totalPageCount > pageSize){
   		if(lastPageNoOnPageList < totalPageCount){
   	        pageHtml += '<a href="javascript:'+jsFunction+'(' + (parseInt(firstPageNoOnPageList)+parseInt(pageSize)) + '); return false;" class=\"next\" onclick="'+jsFunction+'(' + (parseInt(firstPageNoOnPageList)+parseInt(pageSize)) + '); return false;"></a>';
   	        pageHtml += '<a href="javascript:'+jsFunction+'(' + lastPageNo + '); return false;" class=\"last\" onclick="'+jsFunction+'(' + lastPageNo + '); return false;"></a>';
   		}else{
   	        pageHtml += '<a href="javascript:'+jsFunction+'(' + lastPageNo + '); return false;" class=\"next\" onclick="'+jsFunction+'(' + lastPageNo + '); return false;"></a>';
   	        pageHtml += '<a href="javascript:'+jsFunction+'(' + lastPageNo + '); return false;" class=\"last\" onclick="'+jsFunction+'(' + lastPageNo + '); return false;"></a>';
   		}
   	}
   	return pageHtml;

   }
   
	$(document).on( "pageinit", function() {
		  
		  $.mobile.loader.prototype.options.text = "loading";
		  $.mobile.loader.prototype.options.textVisible = false;
		  $.mobile.loader.prototype.options.theme = "a";
		  $.mobile.loader.prototype.options.html = "";
	});
   
	/**
	 * prefix 로부터 숫자 id를 추출
	 * @param paginationInfo
	 * @return
	 */
	function fn_aram_get_idString(original) {
		 var index = original.lastIndexOf("_")+1;
		 if( original.charAt(index) != '0' ) return original;

		 while( index < original.length ) {
			 if( original.charAt(index) != '0' ) break;
			 index++;
		 }
		 return (index == original.length) ? original : original.substring(index);
	}

   
