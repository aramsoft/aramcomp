	/* [1] 오프라인웹  서비스 목록  **************************************************************************/		
	/*********************************************************
	 * 오프라인 서비스 글 목록 조회
	 ******************************************************** */    			
	function fn_offlineList() {
		var uri = contextPath + "/mbl/com/ows/selectMobileOfflineWebList.mdo"; 
		// url        : "/mbl/com/ows/selectMobileOfflineWebList.mdo",
		$.ajax({
	        type       : "POST",
	        cache      : false,                   
	        url        : uri,
	        data       : {fetchRow:fetchRowIdx, deviceType:deviceType},          
	        dataType   : "json",                 
	        success :function(json) {
	            var offlineList = eval(json.offlineList);
	            var html = "";
	
	            for(var k=0; k<offlineList.length; k++) {
	                var offlineWebSj = offlineList[k].offlineWebSj;
	                var offlineWebCn = offlineList[k].offlineWebCn;
	                var creatDt = offlineList[k].creatDt;
	                html += "<li id="+offlineList[k].sn+">";
	                html += "<a href='javascript:fn_goOfflineWebView("+offlineList[k].sn+")'><h3>"+offlineWebSj+"</h3><p>"+creatDt+"</p></a>";
	                html += "<input type='hidden' id='offlineWebCn' value='"+offlineWebCn+"'>";
	                html += "</li>";
	            }
	            localStorage.setItem("localOfflineList", html);
	            $('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
	        }
	    });				
	}
	
	/*********************************************************
	 * 오프라인 서비스 더 보기 조회
	 ******************************************************** */			
	function fn_moreFetch() {
		if(window.navigator.onLine == true) {
			++fetchRowIdx;
			fn_offlineList();
		} else {
			jAlert('오프라인 모드 입니다.', ':: 동기화 서비스 ::', 'a');
		}
	}	
	
	/*********************************************************
	 * 오프라인 서비스 글 상세 조회
	 ******************************************************** */				
	function fn_goOfflineWebView(offlineSn) {		
	
		if(window.navigator.onLine == true) {
			// 1. 오프라인 서비스 상세조회 					
			location.href = contextPath + "/mbl/com/ows/goMobileOfflineWeb.mdo?sn="+offlineSn;
	
		} else {
			// 2. [오프라인 ]오프라인 서비스 상세조회내용 webstorage에 적재 					
	        var changeSj = $("#"+offlineSn).find("h3"); 
	        var changeCreatDt = $("#"+offlineSn).find("p"); 
	        var changeCn = $("#"+offlineSn).find("input"); 
	
	        var changeSjTxt = $(changeSj).text(); 
	        var changeCreatDtTxt = $(changeCreatDt).text(); 
	        var changeCnTxt = $(changeCn).val();    
	
	        localStorage.setItem("localView_SN", offlineSn);                // 오프라인 서비스 일련번호
	        localStorage.setItem("localView_SJ", changeSjTxt);              // 오프라인 서비스 제목
	        localStorage.setItem("localView_CN", changeCnTxt);              // 오프라인 서비스 내용
	        localStorage.setItem("localView_CREAT_DT", changeCreatDtTxt);   // 오프라인 서비스 생성일시                
	        
	        location.href = contextPath + "/mbl/com/ows/goMobileOfflineWeb.mdo";
		}
	};	