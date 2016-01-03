	/* [1] 실시간 공지  서비스 목록  **************************************************************************/		
	/*********************************************************
	 * 실시간 공지 서비스 관리자 더 보기 조회
	******************************************************** */			
	function fn_moreFetch() {
		++fetchRowIdx;
		setTimeout("fn_rnsList()",300);
	}
	
	/*********************************************************
	 * 실시간 공지 서비스 목록 조회
	 ******************************************************** */			
	function fn_rnsList() {
		 var uri = contextPath + "/mbl/com/rns/selectRealtimeNoticeList.mdo"; 
		 $.ajax({
			 type       : "POST",
			 cache      : false,				      
			 url        : uri,
			 data       : {fetchRow:fetchRowIdx},			 
			 dataType   : "json",			     
			 success :function(json) {
				 var rnsList = eval(json.rtnList);
				 var html = "";
				
				for(var k=0; k<rnsList.length; k++) {
					var noticeSj = rnsList[k].noticeSj;
					var creatDt = rnsList[k].creatDt;
					html += "<li><a href='javascript:fn_goRnsView("+rnsList[k].sn+")'><h3>"+noticeSj+"</h3><p>"+creatDt+"</p></a></li>";
				}
				$('div[id="list"] ul[data-role="listview"]').html(html).listview('refresh');
			 } 
		 });
	 };
	
	/*********************************************************
	 *  실시간 공지 서비스 등록 페이지 이동 
	 ******************************************************** */			
	function fn_goRnsInsert() {
		  form = document.frmRns;
	      form.action = contextPath + "/mbl/com/rns/goRealtimeNoticeInsert.mdo";
	      form.submit();  
	};
	
	/*********************************************************
	 *  실시간 공지 서비스 상세 페이지 이동 
	 ******************************************************** */			
	function fn_goRnsView(noticeSn) {
		form = document.frmRns;
		form.searchSn.value = noticeSn;
	    form.action = contextPath + "/mbl/com/rns/goRealtimeNotice.mdo";
	    form.submit();  
	};
	
	/* [2] 실시간 공지  서비스 상세  **************************************************************************/		
	/*********************************************************
	 * 실시간 공지 서비스 상세조회 
	 ******************************************************** */
	function fn_view(sn) {
		var uri = contextPath + "/mbl/com/rns/selectRealtimeNotice.mdo"; 
		$.ajax({
			type       : "POST",
			cache      : false,				      
			url        : uri,
			data       : {sn:sn},			 
			dataType   : "json",			     
			success :function(json) {

				var resultView = eval(json.resultView);
				var noticeDt = "";
				$("#noticeSj").text(resultView.noticeSj);
				$("#noticeCn").text(resultView.noticeCn);
				noticeDt = resultView.noticeBgnDt + " ~ " +resultView.noticeEndDt;
				$("#noticeDt").text(noticeDt);
				$("#creatDt").text(resultView.creatDt);

			} 
		});
	}		
	
	/*********************************************************
	 * 실시간 공지 서비스 삭제 
	 ******************************************************** */
	function fn_delete() {
		jConfirm('', '삭제 하시겠습니까?', 'b', function(r) {
			if(r == true) {
				form = document.searchVO;
			  	form.sn.value = sn;
		      	form.action = contextPath + "/mbl/com/rns/deleteRealtimeNotice.mdo";
		      	form.submit();  
			}
		});
	};
	
	/* [3] 실시간 공지  서비스 등록  **************************************************************************/
	/*********************************************************
	 * 실시간공지 등록 Validation 체크
	 ******************************************************** */
	function fn_validation() {
		
		var pattern = /[\+\@\#\$\%\^\&\*\(\)\_\-\=\,\<\>\?\/\|]/gi;
		var regx = new RegExp(pattern);
		
		var noticeSj = $("#noticeSj").val();
		var noticeCn = $("#noticeCn").val();
		var noticeBgnDt = $("#noticeBgnDt").val();
		var noticeEndDt = $("#noticeEndDt").val();

		if(regx.test(noticeSj) == true || regx.test(noticeCn) == true) {
			jAlert('특수문자 입력을 금지합니다.', ':: 실시간 공지 등록 ::', 'a');
			return false;
		
		} else {
			if(noticeSj.length < 1) {
				jAlert('제목을 입력하여 주십시오.', ':: 실시간 공지 등록 ::', 'a');
				$("#noticeSj").focus();
				return false;

			} else {
				if(noticeSj.length > 30) {
					jAlert('제목의 길이를 확인하여 주십시오.', ':: 실시간 공지 등록 ::', 'a');
					$("#noticeSj").focus();
					return false;
				}
			}

			if(noticeCn.length < 1) {
				jAlert('내용을 입력하여 주십시오.', ':: 실시간 공지 등록 ::', 'a');
				$("#noticeCn").focus();
				return false;

			} else {
				if(noticeCn.length > 100) {
					$("#noticeCn").focus();
					jAlert('내용의 길이를 확인하여 주십시오.', ':: 실시간 공지 등록 ::', 'a');
					return false;		
				}
			}

			if(noticeBgnDt.length < 1) {
				$("#noticeBgnDt").focus();
				jAlert('게시기간 시작일을 확인하여주십시오.', ':: 실시간 공지 등록 ::', 'a');
				return false;
			} 
							
			if(noticeEndDt.length < 1) {
				$("#noticeEndDt").focus();
				jAlert('게시기간 종료일을 확인하여주십시오.', ':: 실시간 공지 등록 ::', 'a');
				return false;
			} 

			var bgnDt = noticeBgnDt;
			var endDt = noticeEndDt;
			bgnDt = bgnDt.replace(/-/gi,'');
			endDt = endDt.replace(/-/gi,'');

			if(bgnDt > endDt) {
				$("#noticeBgnDt").focus();
				jAlert('시작일은 종료일보다 클 수 없습니다.', ':: 실시간 공지 등록 ::', 'a');
				return false;
			}

			return true;
		}
		
		

	}
	
	/*********************************************************
	 * 실시간공지 서비스 등록
	 ******************************************************** */	        
	function fn_insert() {
		if(fn_validation()) {
			var uri = contextPath + "/mbl/com/rns/insertRealtimeNotice.mdo";
			var noticeSj = $("#noticeSj").val();
			var noticeCn = $("#noticeCn").val();
			var noticeBgnDt = $("#noticeBgnDt").val();
			var noticeEndDt = $("#noticeEndDt").val();

			// -- 최신구분 코드 최신..
			var recentCodeId = "COM078";
			var recentCode = "WRT01";
			
			$.ajax({
				type       : "POST",
				cache      : false,				      
				url        : uri,
				data       : {noticeSj:noticeSj, noticeCn:noticeCn, noticeBgnDt:noticeBgnDt, noticeEndDt:noticeEndDt, recentCodeId:recentCodeId, recentCode:recentCode},			 
				dataType   : "json",			     
				success :function(json) {
					// fn_goList(); 
					location.href = contextPath + "/mbl/com/rns/goRealtimeNoticeList.mdo";
				} 
			});
		}
	};

	/*********************************************************
	 * 실시간 공지 서비스 등록 취소 이벤트 
	 ******************************************************** */	        
	function fn_cancel() {
		$("#noticeSj").val("");
		$("#noticeCn").val("");
		$("#noticeBgnDt").val("");
		$("#noticeEndDt").val("");
	}
	
	/* [4] 실시간 공지  서비스 메시지  **************************************************************************/

	/*********************************************************
	 * 실시간 공지 서비스 메시지 화면 표출
	 ******************************************************** */
	function fn_showMsg(srcData) {
		
		var data = srcData.split("|");						 
		data[1] = data[1].replace(/\+/g, ' ');
		data[2] = data[2].replace(/\+/g, ' ');
		
		if (data.length == 3 && notiStatus == "Y" && data[0] > maxSn) {
			var alertMessage = "<dl class='mcom-msg'><dt><span class='left'>제목</span><span class='right'>" + data[1] + "</span></dt>" + "<dd><span class='left'>내용</span><span class='right'>" + data[2] + "</span></dd><dd></dd></dl>";
			var btmItem = [ {id : 'button1', value: "메시지 확인"}];

			jActionSheet(alertMessage, '메시지 확인', 'b', btmItem , function(r) {
				if(r == "메시지 확인") {
					maxSn = data[0];
				} 
			});
		}
	}
	
	/*********************************************************
	 * server sent event 호출 
	 ******************************************************** */	
	function fn_serverSentEvent() {
		
		eventURI = contextPath + "/mbl/com/rns/goEvent.mdo";	
		// 1. Server-sent event 지원
		if (!!window.EventSource) {

			
			var eventSrc = new EventSource(eventURI);
			eventSrc.addEventListener('message', function(event) {

				fn_showMsg(decodeURI(event.data));			
			});		
			
			eventSrc.addEventListener('open',  function(e) {

				 } );

			eventSrc.addEventListener('error',  function(e) {
				 if (e.readyState == EventSource.CLOSED) {

				 } else {
					 console.log(e.readyState); 
				 }
				 });

		// 2. Server-sent event 미지원
		} else {
			alert("EventSource is not able");
			
			fn_msgContent(eventURI);					
		}
	}
	
	/*********************************************************
	 * server sent event 메시지    
	 ******************************************************** */				
	 function fn_msgContent(eventURI) {
		 
		 $.ajax({
		 	type       : "POST",
		 	cache      : false,				      
		 	url        : eventURI,
		 	dataType   : "text",			     
		 	success :function(textData) {
				var srcData = decodeURI(textData).split("\n");
				var retryTime = 5000;
				if (srcData.length == 2) {
					retryTime = srcData[0].split(":")[1];
					var data = srcData[1].split(":")[1];
					fn_showMsg(data);
				}
				setTimeout("fn_serverSentEvent()", retryTime);
		 	}
		 });
	 }			

	
     /*********************************************************
     * server sent event 알림상태    
     ******************************************************** */
    $(document).on("vclick", "#btn_noti_yes", function (event) {
        notiStatus = "Y";
    });

    $(document).on("vclick", "#btn_noti_no", function (event) {
        notiStatus = "N";
    });