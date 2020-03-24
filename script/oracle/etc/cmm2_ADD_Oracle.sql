
CREATE TABLE COMTN_CMMNTY_MENU
(
	TRGET_ID              	CHAR(20)  		NOT NULL,
	MENU_NM               	VARCHAR2(60)  	NOT NULL,
	MENU_KNM               	VARCHAR2(60)  	NOT NULL,
	MENU_POS			  	CHAR(6) 		NOT NULL,
	PROGRM_FILE_NM        	VARCHAR2(60)  	NULL,
	DIRECT_URL			  	VARCHAR2(256)  	NULL,
	MENU_DC               	VARCHAR2(256)  	NULL,
	TOPMENU_AT			  	CHAR(1)  		NULL,
	MGR_AT                	CHAR(1)  		NULL,
	USE_AT                	CHAR(1)  		NULL,
	CONSTRAINT  COMTN_CMMNTY_MENU_PK PRIMARY KEY (TRGET_ID, MENU_NM),
	CONSTRAINT  COMTN_CMMNTY_MENU_FK1 FOREIGN KEY (TRGET_ID) REFERENCES COMTN_CMMNTY(CMMNTY_ID)
);
CREATE INDEX COMTN_CMMNTY_MENU_i01 ON COMTN_CMMNTY_MENU (TRGET_ID ASC);

COMMENT ON TABLE COMTN_CMMNTY_MENU IS '메뉴정보';

COMMENT ON COLUMN COMTN_CMMNTY_MENU.TRGET_ID IS 'TRGET_ID';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.MENU_NM IS '메뉴명';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.MENU_KNM IS '메뉴한글명';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.MENU_POS IS '메뉴위치';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.PROGRM_FILE_NM IS '프로그램파일명';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.DIRECT_URL IS '바로가기URL';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.MENU_DC IS '메뉴설명';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.TOPMENU_AT IS '상위메뉴여부';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.MGR_AT IS '관리자용여부';
COMMENT ON COLUMN COMTN_CMMNTY_MENU.USE_AT IS '사용여부';


INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'board','게시판', '010000', NULL, '/cop/cmy/CmmntyMainContents.do', NULL, 'Y', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'notice', '공지게시판', '010100', NULL, '/board/102/list', '공지게시판', 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'gallery', '갤러리', '010200', NULL, '/board/103/list', '갤러리 게시판', 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'freeboard', '자유게시판', '010300', NULL, '/board/104/list', '자유게시판', 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'dataroom', '자료실', '010400', NULL, '/board/105/list', '자료실 게시판', 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'anonymous', '익명게시판', '010500', NULL, '/board/106/list', '익명 게시판', 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'bugissue', '버그게시판', '010600', NULL, '/board/51/list', '버그 및 이슈 게시판', 'N', 'N', 'Y'); 

INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'infoshare','정보공유', '020000', 'FaqManage', NULL, NULL, 'Y', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'faq','FAQ', '020100', 'FaqManage', NULL, NULL, 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'qna','QnA', '020200', 'QnaManage', NULL, NULL, 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'codedetail','공통코드', '020300', 'CmmnDetailCodeManage', NULL, '공통코드관리', 'N', 'N', 'Y'); 

INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'workplace','협업', '030000', 'SchdulManage', NULL, NULL, 'Y', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'schedule','일정관리', '030100', 'SchdulManage', NULL, NULL, 'N', 'N', 'Y'); 

INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'myplace','개인', '040000', 'DiaryManage', NULL, NULL, 'Y', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'diary','일지관리', '040100', 'DiaryManage', NULL, NULL, 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'namecard','명함관리', '040200', 'MyNcrdManage', NULL, NULL, 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'publicname','공개명함목록', '040300', 'NcrdManage', NULL, NULL, 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'addressbook','주소록관리', '040400', 'AdbManage', NULL, NULL, 'N', 'N', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'scrap','스크랩관리', '040500', 'ScrapManage', NULL, NULL, 'N', 'N', 'Y'); 

INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'service','업무', '080000', 'SchdulManage', NULL, NULL, 'Y', 'Y', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'qnanswer','QnA답변', '080100', 'QnaAnswerManage', NULL, NULL, 'N', 'Y', 'Y'); 

INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'manage','관리자메뉴', '090000', NULL, '/cop/cmy/detailCommunity.do', NULL, 'Y', 'Y', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'boardman','게시판관리', '090100', 'BdMstrListByTrget', NULL, NULL, 'N', 'Y', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'userman','사용자관리', '090200', 'CmmntyUserManage', NULL, NULL, 'N', 'Y', 'Y'); 
INSERT INTO COMTN_CMMNTY_MENU ( TRGET_ID, MENU_NM, MENU_KNM, MENU_POS, PROGRM_FILE_NM, DIRECT_URL, MENU_DC, TOPMENU_AT, MGR_AT, USE_AT ) 
VALUES ('CMMNTY_0000000000001', 'confirm','승인관리', '090300', 'ConfirmManage', NULL, NULL, 'N', 'Y', 'Y'); 

COMMIT;