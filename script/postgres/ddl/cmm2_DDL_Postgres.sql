-----------------------------------------------

CREATE TABLE COMTE_COPSEQ
(
	TABLE_NAME         		varchar(20)  	NOT NULL,
	NEXT_ID            		decimal(30)  	NULL,
	CONSTRAINT COMTE_COPSEQ_PK PRIMARY KEY (TABLE_NAME)
);

-- ========================================================
-- FILE

CREATE TABLE COMTN_FILE
(
	ATCH_FILE_ID          	char(20)  		NOT NULL,
	CREAT_DT              	timestamp		NOT NULL,
	USE_AT                	char(1)  		NULL,
	CONSTRAINT COMTN_FILE_PK PRIMARY KEY (ATCH_FILE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('FILE_ID', 1);

------------------------------------------------------

CREATE TABLE COMTN_FILE_DETAIL
(
	ATCH_FILE_ID          	char(20) 		NOT NULL,
	FILE_SN               	decimal(10)  	NOT NULL,
	FILE_STRE_COURS       	varchar(2000)   NOT NULL,
	STRE_FILE_NM          	varchar(255)  	NOT NULL,
	ORIGNL_FILE_NM        	varchar(255)  	NULL,
	FILE_EXTSN            	varchar(20)  	NULL,
	FILE_TYPE            	varchar(50)  	NULL,
	FILE_CN               	text  			NULL,
	FILE_SIZE            	decimal(10)  	NOT NULL ,
	CONSTRAINT COMTN_FILE_DETAIL_PK PRIMARY KEY (ATCH_FILE_ID, FILE_SN),
	CONSTRAINT COMTN_FILE_DETAIL_FK1 FOREIGN KEY (ATCH_FILE_ID) REFERENCES COMTN_FILE(ATCH_FILE_ID)
);
CREATE INDEX COMTN_FILE_DETAIL_i01 ON COMTN_FILE_DETAIL(ATCH_FILE_ID ASC);

-- ===========================================================
-- POLICY

CREATE TABLE COMTN_LOGIN_POLICY
(
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	IP_INFO               	varchar(23)  	NOT NULL,
	DPLCT_PERM_AT         	char(1)  		NOT NULL,
	LMTT_AT               	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT COMTN_LOGIN_POLICY_PK PRIMARY KEY (EMPLYR_ID)
);

-- ===========================================================
-- ORGNZT

CREATE TABLE COMTN_ORGNZT_INFO
(
	ORGNZT_ID             	char(20)  		NOT NULL,
	ORGNZT_NM             	varchar(20)  	NOT NULL,
	ORGNZT_DC             	varchar(100)  	NULL,
	CONSTRAINT COMTN_ORGNZT_INFO_PK PRIMARY KEY (ORGNZT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('ORGNZT_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_AUTHOR_GROUP_INFO
(
	GROUP_ID              	char(20)  		NOT NULL,
	GROUP_NM              	varchar(60)  	NOT NULL,
	GROUP_CREAT_DE        	timestamp  		NOT NULL,
	GROUP_DC              	varchar(100)  	NULL,
	CONSTRAINT COMTN_AUTHOR_GROUP_INFO_PK PRIMARY KEY (GROUP_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('GROUP_ID', 1);

-----------------------------------------

CREATE TABLE COMTN_EMPLYR_INFO
(
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	ORGNZT_ID             	char(20)  		NULL,
	USER_NM               	varchar(60) 	NOT NULL,
	PASSWORD              	varchar(200)  	NOT NULL,
	EMPL_NO               	varchar(20)  	NULL,
	IHIDNUM               	varchar(200)  	NULL,
	SEXDSTN_CODE          	char(1)  		NULL,
	BRTHDY                	char(20)  		NULL,
	FXNUM                 	varchar(20)  	NULL,
	HOUSE_ADRES           	varchar(100)  	NOT NULL,
	PASSWORD_HINT         	varchar(100)  	NOT NULL,
	PASSWORD_CNSR         	varchar(100)  	NOT NULL,
	HOUSE_END_TELNO       	varchar(4)  	NOT NULL,
	AREA_NO               	varchar(4)  	NOT NULL,
	DETAIL_ADRES          	varchar(100)  	NULL,
	ZIP                   	varchar(6)  	NOT NULL,
	OFFM_TELNO            	varchar(20)  	NULL,
	MBTLNUM               	varchar(20)  	NULL,
	EMAIL_ADRES           	varchar(50)  	NULL,
	OFCPS_NM              	varchar(60)  	NULL,
	HOUSE_MIDDLE_TELNO    	varchar(4)  	NOT NULL,
	GROUP_ID              	char(20)  		NULL,
	PSTINST_CODE          	char(8)  		NULL,
	EMPLYR_STTUS_CODE     	char(1)  		NOT NULL,
	ESNTL_ID              	char(20)  		NOT NULL,
	CRTFC_DN_VALUE        	varchar(100)  	NULL,
	SBSCRB_DE             	timestamp  		NULL,
	GOOGLE_ACCOUNT        	varchar(50)  	NULL,
	CONSTRAINT COMTN_EMPLYR_INFO_PK PRIMARY KEY (EMPLYR_ID),
	CONSTRAINT COMTN_EMPLYR_INFO_FK1 FOREIGN KEY (GROUP_ID) REFERENCES COMTN_AUTHOR_GROUP_INFO(GROUP_ID) ON DELETE CASCADE,
	CONSTRAINT COMTN_EMPLYR_INFO_FK2 FOREIGN KEY (ORGNZT_ID) REFERENCES COMTN_ORGNZT_INFO(ORGNZT_ID) ON DELETE CASCADE
);
CREATE INDEX COMTN_EMPLYR_INFO_i01 ON COMTN_EMPLYR_INFO (ORGNZT_ID ASC);
CREATE INDEX COMTN_EMPLYR_INFO_i02 ON COMTN_EMPLYR_INFO (GROUP_ID ASC);

------------------------------------------------

CREATE TABLE COMTN_EMPLYR_CHANGE_DTLS
(
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	CHANGE_DE             	char(20)  		NOT NULL,
	ORGNZT_ID             	char(20)  		NULL,
	GROUP_ID              	char(20)  		NULL,
	EMPL_NO               	varchar(20)  	NULL,
	SEXDSTN_CODE          	char(1)  		NULL,
	BRTHDY                	char(20)  		NULL,
	FXNUM                 	varchar(20)  	NULL,
	HOUSE_ADRES           	varchar(100)  	NULL,
	HOUSE_END_TELNO       	varchar(4)  	NULL,
	AREA_NO               	varchar(4)  	NULL,
	DETAIL_ADRES          	varchar(100)  	NULL,
	ZIP                   	varchar(6)  	NULL,
	OFFM_TELNO            	varchar(20)  	NULL,
	MBTLNUM               	varchar(20)  	NULL,
	EMAIL_ADRES           	varchar(50)  	NULL,
	HOUSE_MIDDLE_TELNO    	varchar(4)  	NULL,
	PSTINST_CODE          	char(8)  		NULL,
	EMPLYR_STTUS_CODE     	char(1)  		NULL,
	ESNTL_ID              	char(20)  		NULL,
	CONSTRAINT COMTN_EMPLYR_CHANGE_DTLS_PK PRIMARY KEY (EMPLYR_ID, CHANGE_DE),
	CONSTRAINT COMTN_EMPLYR_CHANGE_DTLS_FK1 FOREIGN KEY (EMPLYR_ID) REFERENCES COMTN_EMPLYR_INFO(EMPLYR_ID)
);
CREATE INDEX COMTN_EMPLYR_CHANGE_DTLS_i01 ON COMTN_EMPLYR_CHANGE_DTLS (EMPLYR_ID ASC);

----------------------------------------------

CREATE TABLE COMTN_ENTRPRS_MBER
(
	ENTRPRS_MBER_ID       		varchar(20)  	NOT NULL,
	ENTRPRS_SE_CODE       		char(8)  		NULL,
	BIZRNO                		varchar(10)  	NULL,
	JURIRNO               		varchar(13)  	NULL,
	CMPNY_NM              		varchar(60)  	NOT NULL,
	CXFC                  		varchar(50)  	NULL,
	ZIP                   		varchar(6)  	NOT NULL,
	ADRES                 		varchar(100)  	NOT NULL,
	ENTRPRS_MIDDLE_TELNO  		varchar(4)  	NOT NULL,
	FXNUM                 		varchar(20)  	NULL,
	INDUTY_CODE           		char(1)  		NULL,
	APPLCNT_NM            		varchar(50)  	NOT NULL,
	APPLCNT_IHIDNUM       		varchar(200)  	NULL,
	SBSCRB_DE             		timestamp  		NULL,
	ENTRPRS_MBER_STTUS    		varchar(15)  	NULL,
	ENTRPRS_MBER_PASSWORD  		varchar(200)  	NULL,
	ENTRPRS_MBER_PASSWORD_HINT  varchar(100)  	NOT NULL,
	ENTRPRS_MBER_PASSWORD_CNSR  varchar(100)  	NOT NULL,
	GROUP_ID              		char(20)  		NULL,
	DETAIL_ADRES          		varchar(100)  	NULL,
	ENTRPRS_END_TELNO     		varchar(4)  	NOT NULL,
	AREA_NO               		varchar(4)  	NOT NULL,
	APPLCNT_EMAIL_ADRES   		varchar(50)  	NOT NULL,
	ESNTL_ID              		char(20)  		NOT NULL,
	CONSTRAINT COMTN_ENTRPRS_MBER_PK PRIMARY KEY (ENTRPRS_MBER_ID),
	CONSTRAINT COMTN_ENTRPRS_MBER_FK1 FOREIGN KEY (GROUP_ID) REFERENCES COMTN_AUTHOR_GROUP_INFO(GROUP_ID) ON DELETE CASCADE
);
CREATE INDEX COMTN_ENTRPRS_MBER_i01 ON COMTN_ENTRPRS_MBER (GROUP_ID ASC);

------------------------------------------------

CREATE TABLE COMTN_GNRL_MBER
(
	MBER_ID               	varchar(20)  	NOT NULL,
	PASSWORD              	varchar(200)  	NOT NULL,
	PASSWORD_HINT         	varchar(100)  	NULL,
	PASSWORD_CNSR         	varchar(100)  	NULL,
	IHIDNUM               	varchar(200)  	NULL,
	MBER_NM               	varchar(50)  	NOT NULL,
	ZIP                   	varchar(6)  	NULL,
	ADRES                 	varchar(100)  	NULL,
	DETAIL_ADRES          	varchar(100)  	NULL,
	AREA_NO               	varchar(4)  	NULL,
	MIDDLE_TELNO          	varchar(4)  	NULL,
	END_TELNO             	varchar(4)  	NULL,
	MBTLNUM               	varchar(20)  	NULL,
	MBER_STTUS            	varchar(15)  	NULL,
	GROUP_ID              	char(20)  		NULL,
	MBER_FXNUM            	varchar(20)  	NULL,
	MBER_EMAIL_ADRES      	varchar(50)  	NOT NULL,
	SBSCRB_DE             	timestamp  		NULL,
	SEXDSTN_CODE          	char(1)  		NULL,
	ESNTL_ID              	char(20)  		NOT NULL,
	GOOGLE_ACCOUNT        	varchar(50)  	NULL,
	CONSTRAINT COMTN_GNRL_MBER_PK PRIMARY KEY (MBER_ID),
	CONSTRAINT COMTN_GNRL_MBER_FK1 FOREIGN KEY (GROUP_ID) REFERENCES COMTN_AUTHOR_GROUP_INFO(GROUP_ID) ON DELETE CASCADE
);
CREATE INDEX COMTN_GNRL_MBER_i01 ON COMTN_GNRL_MBER (GROUP_ID ASC);

---------------------------------------------------

CREATE OR REPLACE  
  VIEW COMVN_USER_MASTER ( ESNTL_ID, USER_ID, PASSWORD, USER_NM, USER_ZIP, USER_ADRES, USER_EMAIL, GROUP_ID, USER_SE, ORGNZT_ID, MBTLNUM ) 
AS  
SELECT ESNTL_ID, MBER_ID, PASSWORD, MBER_NM, ZIP,ADRES, MBER_EMAIL_ADRES, ' ', 'GNR' AS USER_SE, ' ' ORGNZT_ID, MBTLNUM
  FROM COMTN_GNRL_MBER
 UNION ALL
SELECT ESNTL_ID, EMPLYR_ID, PASSWORD, USER_NM, ZIP, HOUSE_ADRES, EMAIL_ADRES, GROUP_ID , 'USR' AS USER_SE, ORGNZT_ID, MBTLNUM
  FROM COMTN_EMPLYR_INFO
 UNION ALL
SELECT ESNTL_ID, ENTRPRS_MBER_ID, ENTRPRS_MBER_PASSWORD, CMPNY_NM, ZIP, ADRES, APPLCNT_EMAIL_ADRES, ' ' , 'ENT' AS USER_SE, ' ' ORGNZT_ID, ' ' MBTLNUM
  FROM COMTN_ENTRPRS_MBER ORDER BY ESNTL_ID;

-- ===============================================
-- AUTHOR
  
CREATE TABLE COMTN_AUTHOR_INFO
(
	AUTHOR_CODE           	varchar(30)  	NOT NULL,
	AUTHOR_NM             	varchar(60)  	NOT NULL,
	AUTHOR_DC             	varchar(200)  	NULL,
	AUTHOR_CREAT_DE       	timestamp  		NOT NULL,
	CONSTRAINT COMTN_AUTHOR_INFO_PK PRIMARY KEY (AUTHOR_CODE)
);

---------------------------------------------

CREATE TABLE COMTN_EMPLYR_SCRTY_ESTBS
(
	SCRTY_DTRMN_TRGET_ID  	varchar(20)  	NOT NULL,
	MBER_TY_CODE          	char(5)  		NULL,
	AUTHOR_CODE           	varchar(30)  	NOT NULL,
	CONSTRAINT COMTN_EMPLYR_SCRTY_ESTBS_PK PRIMARY KEY (SCRTY_DTRMN_TRGET_ID)
);
CREATE INDEX COMTN_EMPLYR_SCRTY_ESTBS_i04 ON COMTN_EMPLYR_SCRTY_ESTBS (AUTHOR_CODE  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SCRTY_DTRMN_TRGET_ID', 1);

---------------------------------------------

CREATE TABLE COMTN_ROLES_HIERARCHY
(
	PARNTS_ROLE           	varchar(30)  	NOT NULL,
	CHLDRN_ROLE           	varchar(30)  	NOT NULL,
	CONSTRAINT COMTN_ROLES_HIERARCHY_PK PRIMARY KEY (PARNTS_ROLE, CHLDRN_ROLE),
	CONSTRAINT COMTN_ROLES_HIERARCHY_FK1 FOREIGN KEY (PARNTS_ROLE) REFERENCES COMTN_AUTHOR_INFO(AUTHOR_CODE) ON DELETE CASCADE,
	CONSTRAINT COMTN_ROLES_HIERARCHY_FK2 FOREIGN KEY (CHLDRN_ROLE) REFERENCES COMTN_AUTHOR_INFO(AUTHOR_CODE) ON DELETE CASCADE
);
CREATE UNIQUE INDEX COMTN_ROLES_HIERARCHY_i01 ON COMTN_ROLES_HIERARCHY (PARNTS_ROLE ASC);
CREATE INDEX COMTN_ROLES_HIERARCHY_i02 ON COMTN_ROLES_HIERARCHY (CHLDRN_ROLE ASC);

--------------------------------------

CREATE TABLE COMTN_RESOURCE_INFO
(
	RESOURCE_CODE             	varchar(50)  	NOT NULL,
	RESOURCE_NM               	varchar(60)  	NOT NULL,
	RESOURCE_PTTRN            	varchar(300)  	NULL,
	RESOURCE_DC               	varchar(200)  	NULL,
	RESOURCE_TY               	varchar(80)  	NULL,
	RESOURCE_SORT             	varchar(10)  	NULL,
	RESOURCE_CREAT_DE         	timestamp  		NOT NULL,
	CONSTRAINT  COMTN_RESOURCEE_INFO_PK PRIMARY KEY (RESOURCE_CODE)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('RSRC_ID', 1);

--------------------------------------------------

CREATE TABLE COMTN_AUTHOR_RESOURCE
(
	AUTHOR_CODE           	varchar(30)  	NOT NULL,
	RESOURCE_CODE           varchar(50)  	NOT NULL,
	CREAT_DT              	timestamp  		NULL,
	CONSTRAINT  COMTN_AUTHOR_RESOURCE_PK PRIMARY KEY (AUTHOR_CODE, RESOURCE_CODE),
	CONSTRAINT  COMTN_AUTHOR_RESOURCE_FK1 FOREIGN KEY (AUTHOR_CODE) REFERENCES COMTN_AUTHOR_INFO(AUTHOR_CODE) ON DELETE CASCADE,
	CONSTRAINT  COMTN_AUTHOR_RESOURCE_FK2 FOREIGN KEY (RESOURCE_CODE) REFERENCES COMTN_RESOURCE_INFO(RESOURCE_CODE) ON DELETE CASCADE
);
CREATE INDEX COMTN_AUTHOR_RESOURCE_i01 ON COMTN_AUTHOR_RESOURCE (AUTHOR_CODE ASC);
CREATE INDEX COMTN_AUTHOR_RESOURCE_i02 ON COMTN_AUTHOR_RESOURCE (RESOURCE_CODE ASC);

-- ===================================================
-- BBS

CREATE TABLE COMTN_BBS_MASTER
(
	BBS_ID                	char(20)  		NOT NULL,
	BBS_NM                	varchar(255)  	NOT NULL,
	BBS_INTRCN            	varchar(2400)  	NULL,
	BBS_TY_CODE           	char(6)  		NOT NULL,
	BBS_ATTRB_CODE        	char(6)  		NOT NULL,
	REPLY_POSBL_AT        	char(1)  		NULL,
	FILE_ATCH_POSBL_AT    	char(1)  		NOT NULL,
	ATCH_POSBL_FILE_NUMBER  decimal(2)  	NOT NULL,
	ATCH_POSBL_FILE_SIZE  	decimal(8)  	NULL,
	USE_AT                	char(1)  		NOT NULL,
	TMPLAT_ID             	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BBS_MASTER_PK PRIMARY KEY (BBS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('BBS_ID', 1);

----------------------------------------------

CREATE TABLE COMTN_BBS_MASTER_OPTN
(
	BBS_ID                	char(20)  		NOT NULL,
	ANSWER_AT             	char(1)  		NOT NULL,
	STSFDG_AT             	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BBS_MASTER_OPTN_PK PRIMARY KEY (BBS_ID)
);

---------------------------------------------

CREATE TABLE COMTN_BBS_USE
(
	BBS_ID                	char(20)  		NOT NULL,
	TRGET_ID              	char(20)  		NOT NULL,
	USE_AT                	char(1)  		NOT NULL,
	REGIST_SE_CODE        	char(6)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
  	PUBLIC_AT          		char(1)         NULL,
	CONSTRAINT  COMTN_BBS_USE_PK PRIMARY KEY (BBS_ID, TRGET_ID),
	CONSTRAINT  COMTN_BBS_USE_FK1 FOREIGN KEY (BBS_ID) REFERENCES COMTN_BBS_MASTER(BBS_ID)
);
CREATE INDEX COMTN_BBS_USE_i01 ON COMTN_BBS_USE (BBS_ID ASC);

---------------------------------------------

CREATE TABLE COMTN_BBS
(
	NTT_ID                	decimal(20)  	NOT NULL,
	BBS_ID                	char(20)  		NOT NULL,
	THREAD_NO              	decimal(10)  	NULL,
	NTT_SJ                	varchar(2000)   NULL,
	NTT_CN                	text 		NULL,
	ANSWER_AT             	char(1)  		NULL,
	PARNTS_NTT_ID          	decimal(20)  	NULL,
	THREAD_DEPTH           	decimal(10)		NULL,
	THREAD_GROUP_NO        	decimal(10)		NULL,
	RDCNT                 	decimal(10)  	NULL,
	USE_AT                	char(1)  		NOT NULL,
	NTCE_BGNDE            	char(8)  		NULL,
	NTCE_ENDDE            	char(8)  		NULL,
	NTCR_ID               	varchar(20)  	NULL,
	NTCR_NM               	varchar(20)  	NULL,
	PASSWORD              	varchar(200)  	NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BBS_PK PRIMARY KEY (BBS_ID, NTT_ID),
	CONSTRAINT  COMTN_BBS_FK1 FOREIGN KEY (BBS_ID) REFERENCES COMTN_BBS_MASTER(BBS_ID)
);
CREATE INDEX COMTN_BBS_i01 ON COMTN_BBS (BBS_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NTT_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_COMMENT
(
	COMMENT_NO            	decimal(20)  	NOT NULL,
	BBS_ID                	char(20)  		NOT NULL,
	NTT_ID                	decimal(20)  	NOT NULL,
	WRTER_ID              	varchar(20)  	NULL,
	WRTER_NM              	varchar(20)  	NULL,
	PASSWORD              	varchar(100)  	NULL,
	COMMENT_CN            	varchar(600)  	NULL,
	USE_AT                	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_COMMENT_PK PRIMARY KEY (COMMENT_NO),
	CONSTRAINT  COMTN_COMMENT_FK1 FOREIGN KEY (BBS_ID, NTT_ID) REFERENCES COMTN_BBS(BBS_ID, NTT_ID)
);
CREATE INDEX COMTN_COMMENT_i01 ON COMTN_COMMENT (BBS_ID ASC, NTT_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('COMMENT_NO', 1);

------------------------------------------

CREATE TABLE COMTN_STSFDG
(
	STSFDG_NO             	decimal(20)  	NOT NULL,
	BBS_ID                	char(20)    	NOT NULL,
	NTT_ID                	decimal(20)  	NOT NULL,
	WRTER_ID              	varchar(20)  	NULL,
	WRTER_NM              	varchar(20)  	NULL,
	PASSWORD              	varchar(100)  	NULL,
	STSFDG                	decimal(1)  	NOT NULL,
	STSFDG_CN             	varchar(600)  	NULL,
	USE_AT                	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_STSFDG_PK PRIMARY KEY (STSFDG_NO),
	CONSTRAINT  COMTN_STSFDG_FK1 FOREIGN KEY (BBS_ID, NTT_ID) REFERENCES COMTN_BBS(BBS_ID, NTT_ID)
);
CREATE INDEX COMTN_STSFDG_i01 ON COMTN_STSFDG (BBS_ID ASC, NTT_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('STSFDG_NO', 1);

--------------------------------------------------

CREATE TABLE COMTN_SCRAP
(
	SCRAP_ID              	char(20)  		NOT NULL,
	BBS_ID                	char(20)    	NOT NULL,
	NTT_ID                	decimal(20)  	NOT NULL,
	SCRAP_NM              	varchar(100)  	NOT NULL,
	USE_AT                	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_SCRAP_PK PRIMARY KEY (SCRAP_ID),
	CONSTRAINT  COMTN_SCRAP_FK1 FOREIGN KEY (BBS_ID, NTT_ID) REFERENCES COMTN_BBS(BBS_ID, NTT_ID)
);
CREATE INDEX COMTN_SCRAP_i01 ON COMTN_SCRAP (BBS_ID ASC, NTT_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SCRAP_ID', 1);

-- ==============================================
-- CMMNTY

CREATE TABLE COMTN_CMMNTY
(
	CMMNTY_ID             	char(20) 		NOT NULL,
	CMMNTY_NM             	varchar(255)  	NOT NULL,
	CMMNTY_INTRCN         	varchar(2400)   NULL,
	USE_AT                	char(1)  		NOT NULL,
	REGIST_SE_CODE        	char(6)  		NULL,
	TMPLAT_ID             	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	MEMBER_AT             	char(1)  		NOT NULL,
  	HOME_URL           		varchar(255)    NULL,
  	CMMNTY_LOGO_IMAGE  		bytea            NULL,
  	ADDITIONAL_INFO    		varchar(4000)   NULL,
	CONSTRAINT  COMTN_CMMNTY_PK PRIMARY KEY (CMMNTY_ID)
);
CREATE UNIQUE INDEX COMTN_CMMNTY_IDX_i01 ON COMTN_CMMNTY (HOME_URL);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CMMNTY_ID', 1);

-----------------------------------------------

CREATE TABLE COMTN_PROGRM_LIST
(
	PROGRM_FILE_NM        	varchar(60)  	NOT NULL,
	PROGRM_STRE_PATH      	varchar(100)  	NOT NULL,
	PROGRM_KOREAN_NM      	varchar(60)  	NULL,
	PROGRM_DC             	varchar(200)  	NULL,
	URL                   	varchar(100)  	NOT NULL,
  	CMMNTY_USE_AT     		char(1)         NULL,
	CONSTRAINT  COMTN_PROGRM_LIST_PK PRIMARY KEY (PROGRM_FILE_NM)
);

-----------------------------------------------

CREATE TABLE COMTN_PROGRM_CHANGE_DTLS
(
	PROGRM_FILE_NM        	varchar(60)  	NOT NULL,
	REQUST_NO             	decimal(10)  	NOT NULL,
	REQUSTER_ID            	varchar(20)  	NOT NULL,
	CHANGE_REQUST_CN      	varchar(1000)   NULL,
	REQUST_PROCESS_CN     	text  	NULL,
	OPETR_ID              	varchar(20)  	NULL,
	PROCESS_STTUS_CODE    	varchar(15)  	NOT NULL,
	PROCESS_DE            	char(8)  		NULL,
	REQUST_DE              	char(8)  		NULL,
	REQUST_SJ             	varchar(60)  	NOT NULL,
	CONSTRAINT  COMTN_PROGRM_CHANGE_DTLS_PK PRIMARY KEY (PROGRM_FILE_NM, REQUST_NO),
	CONSTRAINT  COMTN_PROGRM_CHANGE_DTLS_FK1 FOREIGN KEY (PROGRM_FILE_NM) REFERENCES COMTN_PROGRM_LIST(PROGRM_FILE_NM) ON DELETE CASCADE
);
CREATE INDEX COMTN_PROGRM_CHANGE_DTLS_i01 ON COMTN_PROGRM_CHANGE_DTLS (PROGRM_FILE_NM  ASC);

----------------------------------------------------


CREATE TABLE COMTN_CMMNTY_MENU
(
	TRGET_ID              	char(20)  		NOT NULL,
	MENU_NM               	varchar(60)  	NOT NULL,
	MENU_KNM               	varchar(60)  	NOT NULL,
	MENU_POS              	char(6)  		NOT NULL,
	PROGRM_FILE_NM        	varchar(60)  	NULL,
	DIRECT_URL			  	varchar(256)  	NULL, 
	MENU_DC               	varchar(4096)  	NULL,
	TOPMENU_AT			  	char(1)  		NULL,
	MGR_AT                	char(1)  		NULL,
	USE_AT                	char(1)  		NULL,
	CONSTRAINT  COMTN_CMMNTY_MENU_PK PRIMARY KEY (TRGET_ID, MENU_NM),
	CONSTRAINT  COMTN_CMMNTY_MENU_FK1 FOREIGN KEY (TRGET_ID) REFERENCES COMTN_CMMNTY(CMMNTY_ID)
);
CREATE INDEX COMTN_CMMNTY_MENU_i01 ON COMTN_CMMNTY_MENU (TRGET_ID ASC);

-----------------------------------------------------

CREATE TABLE COMTN_CMMNTY_USER
(
	CMMNTY_ID             	char(20)  		NOT NULL,
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	MNGR_AT               	char(1)  		NOT NULL,
	SBSCRB_DE             	timestamp  		NULL,
	SECSN_DE              	char(20)  		NULL,
	USE_AT                	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_CMMNTY_USER_PK PRIMARY KEY (CMMNTY_ID, EMPLYR_ID),
	CONSTRAINT  COMTN_CMMNTY_USER_FK1 FOREIGN KEY (CMMNTY_ID) REFERENCES COMTN_CMMNTY(CMMNTY_ID)
);
CREATE INDEX COMTN_CMMNTY_USER_i01 ON COMTN_CMMNTY_USER (CMMNTY_ID ASC);

----------------------------------------------

CREATE TABLE COMTN_CONFM_HISTORY
(
	CONFM_NO              	decimal(8)  	NOT NULL,
	CONFM_RQESTER_ID      	varchar(20)  	NOT NULL,
	CONFMER_ID            	varchar(20)  	NULL,
	CONFM_DE              	char(20)  		NULL,
	CONFM_TY_CODE         	char(4)  		NOT NULL,
	CONFM_STTUS_CODE      	char(4)  		NOT NULL,
	OPERT_TY_CODE         	char(4)  		NULL,
	OPERT_ID              	varchar(20)  	NULL,
	TRGET_JOB_TY_CODE    	char(3)  		NULL,
	TRGET_JOB_ID          	char(20)  		NULL,
	CONSTRAINT  COMTN_CONFM_HISTORY_PK PRIMARY KEY (CONFM_NO)
);

-- ===============================================
-- Email

CREATE TABLE COMTN_EMAIL_DSPTCH_MANAGE
(
	MSSAGE_ID             	varchar(20)  	NOT NULL,
	EMAIL_CN              	text  	NULL,
	SNDR                  	varchar(50)  	NOT NULL,
	RCVER                 	varchar(50)  	NOT NULL,
	SJ                    	varchar(60)  	NOT NULL,
	SNDNG_RESULT_CODE     	char(1)  		NULL,
	DSPTCH_DT             	char(20)  		NOT NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	CONSTRAINT  COMTN_EMAIL_DSPTCH_MANAGE_PK PRIMARY KEY (MSSAGE_ID),
	CONSTRAINT  COMTN_EMAIL_DSPTCH_MANAGE_FK1 FOREIGN KEY (SNDR) REFERENCES COMTN_EMPLYR_INFO(EMPLYR_ID),
	CONSTRAINT  COMTN_EMAIL_DSPTCH_MANAGE_FK2 FOREIGN KEY (ATCH_FILE_ID) REFERENCES COMTN_FILE(ATCH_FILE_ID) ON DELETE SET NULL
);
CREATE INDEX COMTN_EMAIL_DSPTCH_MANAGE_i01 ON COMTN_EMAIL_DSPTCH_MANAGE (SNDR  ASC);
CREATE INDEX COMTN_EMAIL_DSPTCH_MANAGE_i02 ON COMTN_EMAIL_DSPTCH_MANAGE (ATCH_FILE_ID  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('MAILMSG_ID', 1);

-- ==============================================
-- Address Book

CREATE TABLE COMTN_ADBK_MANAGE
(
	ADBK_ID               	char(20)  		NOT NULL,
	ADBK_NM               	varchar(50)  	NOT NULL,
	OTHBC_SCOPE           	varchar(20)  	NOT NULL,
	USE_AT                	char(1)  		NOT NULL,
	WRTER_ID              	varchar(20)  	NULL,
  	TRGET_ORGNZT_ID    		varchar(20)     NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_ADBK_MANAGE_PK PRIMARY KEY (ADBK_ID)
);
INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('ADBK_ID', 1);

-------------------------------------------------

CREATE TABLE COMTN_ADBK
(
	ADBK_USER_ID		  	char(20)  		NOT NULL,
	ADBK_ID               	char(20)  		NOT NULL,
	EMPLYR_ID             	varchar(20) 	NULL,
	NCRD_ID               	char(20)  		NULL,
	NM                    	varchar(50)  	NULL,
	EMAIL_ADRES           	varchar(50)  	NULL,
	MBTLNUM               	varchar(20)  	NULL,
	FXNUM                 	varchar(20)  	NULL,
	OFFM_TELNO            	varchar(20) 	NULL,
	HOUSE_TELNO           	varchar(20)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_ADBK_PK PRIMARY KEY (ADBK_ID, ADBK_USER_ID),
	CONSTRAINT  COMTN_ADBK_FK1 FOREIGN KEY (ADBK_ID) REFERENCES COMTN_ADBK_MANAGE(ADBK_ID) ON DELETE CASCADE
);
CREATE INDEX COMTN_ADBK_i01 ON COMTN_ADBK (ADBK_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('ADBKUSER_ID', 1);

-------------------------------------------------

CREATE TABLE COMTN_NCRD
(
	NCRD_ID               	char(20)  		NOT NULL,
	TRGET_ID        	  	varchar(20)  	NULL,
	NM                    	varchar(50)  	NOT NULL,
	TELNO                 	varchar(20)  	NULL,
	NATION_NO             	varchar(10)  	NULL,
	AREA_NO               	varchar(4)  	NULL,
	MIDDLE_TELNO          	varchar(4)  	NULL,
	END_TELNO             	varchar(4)  	NULL,
	MBTLNUM               	varchar(20)  	NULL,
	IDNTFC_NO             	varchar(10)  	NULL,
	MIDDLE_MBTLNUM        	varchar(4)  	NULL,
	END_MBTLNUM           	varchar(4) 		NULL,
	EMAIL_ADRES           	varchar(50)  	NULL,
	CMPNY_NM              	varchar(60)  	NULL,
	DEPT_NM               	varchar(60)  	NULL,
	ZIP                   	varchar(6)  	NULL,
	ADRES                 	varchar(100)  	NULL,
	DETAIL_ADRES          	varchar(100)  	NULL,
	OFCPS_NM              	varchar(60)  	NULL,
	CLSF_NM               	varchar(60)  	NULL,
  	OTHBC_SCOPE        		char(1)         NULL,
	RM                    	varchar(2500)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_NCRD_PK PRIMARY KEY (NCRD_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NCRD_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_NCRD_USER
(
	NCRD_ID               	char(20)  		NOT NULL,
	REGIST_SE_CODE        	char(6)  		NOT NULL,
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	USE_AT                	char(1)  		NOT NULL,
	CREAT_DT              	timestamp  		NOT NULL,
	CONSTRAINT  COMTN_NCRD_USER_PK PRIMARY KEY (NCRD_ID, EMPLYR_ID),
	CONSTRAINT  COMTN_NCRD_USER_FK1 FOREIGN KEY (NCRD_ID) REFERENCES COMTN_NCRD(NCRD_ID)
);
CREATE INDEX COMTN_NCRD_USER_i01 ON COMTN_NCRD_USER (NCRD_ID  ASC);

-- ========================================
-- SMS

CREATE TABLE COMTN_SMS
(
	SMS_ID                	char(20)  		NOT NULL,
	TRNSMIS_TELNO         	varchar(12)  	NOT NULL,
	TRNSMIS_CN            	varchar(80)  	NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	CONSTRAINT  COMTN_SMS_PK PRIMARY KEY (SMS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SMS_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_SMS_RECPTN
(
	SMS_ID                	char(20)  		NOT NULL,
	RECPTN_TELNO          	varchar(12)  	NOT NULL,
	RESULT_CODE           	varchar(4)  	NULL,
	RESULT_MSSAGE         	varchar(4000)   NULL,
	CONSTRAINT  COMTN_SMS_RECPTN_PK PRIMARY KEY (SMS_ID, RECPTN_TELNO),
	CONSTRAINT  COMTN_SMS_RECPTN_FK1 FOREIGN KEY (SMS_ID) REFERENCES COMTN_SMS(SMS_ID)
);
CREATE INDEX COMTN_SMS_RECPTN_i01 ON COMTN_SMS_RECPTN (SMS_ID ASC);

-- =========================================
-- DEPT_JOB

CREATE TABLE COMTN_DEPT_JOB_BX
(
	DEPT_JOBBX_ID         	char(20)  		NOT NULL,
	DEPT_JOBBX_NM         	varchar(255)  	NOT NULL,
	DEPT_ID               	varchar(20)  	NOT NULL,
	INDICT_ORDR           	decimal(6)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DEPT_JOB_BX_PK PRIMARY KEY (DEPT_JOBBX_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('DEPT_JOBBX_ID', 1);

---------------------------------------

CREATE TABLE COMTN_DEPT_JOB
(
	DEPT_JOB_ID           	char(20)  		NOT NULL,
	DEPT_JOBBX_ID         	char(20)  		NOT NULL,
	DEPT_JOB_NM           	varchar(255)  	NOT NULL,
	DEPT_JOB_CN           	varchar(2500)   NOT NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	CHARGER_ID            	varchar(20)  	NOT NULL,
	PRIORT                	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DEPT_JOB_PK PRIMARY KEY (DEPT_JOB_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('DEPT_JOB_ID', 1);

-- ========================================
-- SCHDUL

CREATE TABLE COMTN_SCHDUL_INFO
(
	SCHDUL_ID             	char(20)  		NOT NULL,
	SCHDUL_SE             	char(1)  		NULL,
	OTHBC_SCOPE		      	char(1)  		NULL,
	SCHDUL_BGNDE          	char(20)  		NULL,
	SCHDUL_ENDDE          	char(20)  		NULL,
	SCHDUL_NM             	varchar(255)  	NULL,
	SCHDUL_CN             	varchar(2500)   NULL,
	SCHDUL_PLACE          	varchar(255)  	NULL,
	SCHDUL_IPCR_CODE      	char(1)  		NULL,
	SCHDUL_CHARGER_ID     	varchar(20) 	NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	REPTIT_SE_CODE        	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	TRGET_ID        	  	varchar(20)  	NULL,
	CONSTRAINT  COMTN_SCHDUL_INFO_PK PRIMARY KEY (SCHDUL_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SCHDUL_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_DIARY_INFO
(
	SCHDUL_ID             	char(20)  		NOT NULL,
	DIARY_ID              	char(20)  		NOT NULL,
	DIARY_PROGRSRT        	decimal(3)  	NULL,
	DIARY_NM              	varchar(255)  	NULL,
	DRCT_MATTER           	varchar(2500)   NULL,
	PARTCLR_MATTER        	varchar(2500)   NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DIARY_INFO_PK PRIMARY KEY (SCHDUL_ID, DIARY_ID),
	CONSTRAINT  COMTN_DIARY_INFO_FK1 FOREIGN KEY (SCHDUL_ID) REFERENCES COMTN_SCHDUL_INFO(SCHDUL_ID)
);
CREATE INDEX COMTN_DIARY_INFO_i01 ON COMTN_DIARY_INFO (SCHDUL_ID  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('DIARY_ID', 1);

---------------------------------------------

CREATE TABLE COMTN_LEADER_SCHDUL
(
	SCHDUL_ID             	char(20)  		NOT NULL,
	SCHDUL_SE             	char(1)  		NULL,
	SCHDUL_NM             	varchar(255)  	NOT NULL,
	SCHDUL_CN             	varchar(2500)   NOT NULL,
	SCHDUL_PLACE          	varchar(255)  	NULL,
	LEADER_ID             	varchar(20)  	NOT NULL,
	REPTIT_SE_CODE        	char(1)  		NULL,
	SCHDUL_BGNDE          	char(20)  		NULL,
	SCHDUL_ENDDE          	char(20)  		NULL,
	SCHDUL_CHARGER_ID     	varchar(20)  	NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_LEADER_SCHDUL_PK PRIMARY KEY (SCHDUL_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('LEADER_SCHDUL_ID', 1);

-- ============================================
-- MEMO

CREATE TABLE COMTN_MEMO_TODO
(
	TODO_ID               	char(20)  		NOT NULL,
	TODO_SJ               	varchar(255)  	NOT NULL,
	TODO_BEGIN_TIME       	varchar(14)  	NOT NULL,
	TODO_END_TIME         	varchar(14)  	NOT NULL,
	WRTER_ID              	varchar(20)  	NOT NULL,
	TODO_CN               	varchar(2500)   NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_MEMO_TODO_PK PRIMARY KEY (TODO_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('MEMO_TODO_ID', 1);

--------------------------------------------------

CREATE TABLE COMTN_MEMO_REPRT
(
	REPRT_ID              	char(20)  		NOT NULL,
	REPRT_SJ              	varchar(255)  	NOT NULL,
	REPORT_DE             	char(20)  		NOT NULL,
	WRTER_ID              	varchar(20)  	NOT NULL,
	REPORTR_ID            	varchar(20)  	NOT NULL,
	REPORT_CN             	varchar(2500)   NOT NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	DRCT_MATTER           	varchar(2500)   NULL,
	DRCT_MATTER_REGIST_DT  	varchar(14)  	NULL,
	REPORTR_INQIRE_DT     	varchar(14)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_MEMO_REPRT_PK PRIMARY KEY (REPRT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('MEMO_REPRT_ID', 1);

---------------------------------------------

CREATE TABLE COMTN_WIKMNTHNG_REPRT
(
	REPRT_ID              	char(20)  		NOT NULL,
	REPRT_SE              	char(1)  		NOT NULL,
	REPRT_SJ              	varchar(255)  	NOT NULL,
	REPORT_DE             	char(20)  		NOT NULL,
	WRTER_ID              	varchar(20)  	NOT NULL,
	REPORTR_ID            	varchar(20)  	NOT NULL,
	REPORT_BGNDE          	char(8)  		NOT NULL,
	REPORT_ENDDE          	char(8)  		NOT NULL,
	THSWIK_REPORT_CN      	text  	NOT NULL,
	NEXTWIK_REPORT_CN     	text  	NOT NULL,
	PARTCLR_MATTER        	varchar(2500)   NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	CONFM_DT              	varchar(14)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_WIKMNTHNG_REPRT_PK PRIMARY KEY (REPRT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('WIKMNTHNG_REPRT_ID', 1);

-- =============================================
-- TMPLAT

CREATE TABLE COMTN_TMPLAT_INFO
(
	TMPLAT_ID             	char(20)  		NOT NULL,
	TMPLAT_NM             	varchar(255)  	NULL,
	TMPLAT_COURS          	varchar(2000)   NULL,
	USE_AT                	char(1)  		NULL,
	TMPLAT_SE_CODE        	char(6)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_TMPLAT_INFO_PK PRIMARY KEY (TMPLAT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('TMPLAT_ID', 1);

-- ===========================================
-- CNTC

CREATE TABLE COMTN_SYSTEM_CNTC
(
	CNTC_ID               	varchar(20)  	NOT NULL,
	CNTC_NM               	varchar(100)  	NULL,
	PROVD_INSTT_ID        	varchar(20)  	NULL,
	PROVD_SYS_ID          	varchar(20)  	NULL,
	PROVD_SVC_ID          	varchar(20)  	NULL,
	REQUST_INSTT_ID       	varchar(20)  	NULL,
	REQUST_SYS_ID         	varchar(20)  	NULL,
	CONFM_AT              	char(1)  		NULL,
	USE_AT                	char(1)  		NULL,
	VALID_BGNDE           	char(20)  		NULL,
	VALID_ENDDE           	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CNTC_TY               	varchar(60)  	NULL,
	CONSTRAINT  COMTN_SYSTEM_CNTC_PK PRIMARY KEY (CNTC_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNTC_ID', 1);

-----------------------------------------

CREATE TABLE COMTN_CNTC_INSTT
(
	INSTT_ID              	varchar(20)  	NOT NULL,
	INSTT_NM              	varchar(100)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	USE_AT                	char(1)  		NULL,
	CONSTRAINT  COMTN_CNTC_INSTT_PK PRIMARY KEY (INSTT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNTC_INSTT_ID', 1);

------------------------------------------

CREATE TABLE COMTN_CNTC_SYSTEM
(
	INSTT_ID              	varchar(20)  	NOT NULL,
	SYS_ID                	varchar(20)  	NOT NULL,
	SYS_NM                	varchar(255)  	NULL,
	SYS_IP                	varchar(23)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	USE_AT                	char(1)  		NULL,
	CONSTRAINT  COMTN_CNTC_SYSTEM_PK PRIMARY KEY (INSTT_ID, SYS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNTC_SYS_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_CNTC_SERVICE
(
	INSTT_ID              	varchar(20)  	NOT NULL,
	SYS_ID                	varchar(20)  	NOT NULL,
	SVC_ID                	varchar(20)  	NOT NULL,
	SVC_NM                	varchar(255)  	NULL,
	REQUST_MSSAGE_ID      	varchar(20)  	NULL,
	RSPNS_MSSAGE_ID       	varchar(20) 	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	USE_AT                	char(1)  		NULL,
	CONSTRAINT  COMTN_CNTC_SERVICE_PK PRIMARY KEY (INSTT_ID, SYS_ID, SVC_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNTC_SVC_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_CNTC_MESSAGE
(
	CNTC_MSSAGE_ID        	varchar(20)  	NOT NULL,
	CNTC_MSSAGE_NM        	varchar(100)  	NULL,
	UPPER_CNTC_MSSAGE_ID  	varchar(20)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	USE_AT                	char(1)  		NULL,
	CONSTRAINT  COMTN_CNTC_MESSAGE_PK PRIMARY KEY (CNTC_MSSAGE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNTC_MESSAGE_ID', 1);

-----------------------------------------------

CREATE TABLE COMTN_CNTC_MESSAGE_ITEM
(
	CNTC_MSSAGE_ID        	varchar(20)  	NOT NULL,
	IEM_ID                	varchar(20)  	NOT NULL,
	IEM_NM                	varchar(100) 	NULL,
	IEM_TY                	varchar(50)  	NULL,
	IEM_LT                	decimal(8)  	NULL,
	USE_AT                	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_CNTC_MESSAGE_ITEM_PK PRIMARY KEY (CNTC_MSSAGE_ID, IEM_ID),
	CONSTRAINT  COMTN_CNTC_MESSAGE_ITEM_FK1 FOREIGN KEY (CNTC_MSSAGE_ID) REFERENCES COMTN_CNTC_MESSAGE(CNTC_MSSAGE_ID)
);
CREATE INDEX COMTN_CNTC_MESSAGE_ITEM_i01 ON COMTN_CNTC_MESSAGE_ITEM (CNTC_MSSAGE_ID  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNTC_ITEM_ID', 1);

-- =========================================
-- SUMMARY

CREATE TABLE COMTS_BBS_SUMMARY
(
	OCCRRNC_DE            	char(20)  		NOT NULL,
	STATS_SE              	varchar(10)  	NOT NULL,
	DETAIL_STATS_SE       	varchar(10)  	NOT NULL,
	CREAT_CO              	decimal(10)  	NULL,
	TOT_RDCNT             	decimal(10)  	NULL,
	AVRG_RDCNT            	decimal(10)  	NULL,
	TOP_INQIRE_BBSCTT_ID  	varchar(20)  	NULL,
	MUMM_INQIRE_BBSCTT_ID  	varchar(20)  	NULL,
	TOP_NTCR_ID           	varchar(20)  	NULL,
	CONSTRAINT  COMTS_BBS_SUMMARY_PK PRIMARY KEY (OCCRRNC_DE, STATS_SE, DETAIL_STATS_SE)
);

------------------------------------

CREATE TABLE COMTS_NTT_STATS
(
	STATS_ID              	char(18)  		NOT NULL,
	NTCE_CO               	decimal(10)  	NULL,
	AVRG_RDCNT            	decimal(10)  	NULL,
	TOP_RDCNT             	decimal(10)  	NULL,
	MUMM_RDCNT            	decimal(10)  	NULL,
	TOP_NTCR_ID           	varchar(20)  	NULL,
	CONSTRAINT  COMTS_NTT_STATS_PK PRIMARY KEY (STATS_ID)
);

----------------------------------------

CREATE TABLE COMTS_SYS_LOG_SUMMARY
(
	OCCRRNC_DE            	char(20)  		NOT NULL,
	SVC_NM                	varchar(255)  	NOT NULL,
	METHOD_NM             	varchar(60)  	NOT NULL,
	CREAT_CO              	decimal(10)  	NULL,
	UPDT_CO               	decimal(10)  	NULL,
	RDCNT                 	decimal(10)  	NULL,
	DELETE_CO             	decimal(10)  	NULL,
	OUTPT_CO              	decimal(10)  	NULL,
	ERROR_CO              	decimal(10)  	NULL,
	CONSTRAINT  COMTS_SYS_LOG_SUMMARY_PK PRIMARY KEY (OCCRRNC_DE, SVC_NM, METHOD_NM)
);

---------------------------------------------
CREATE TABLE COMTN_DTA_USE_STATS
(
	DTA_USE_STATS_ID      	char(20)  		NOT NULL,
	BBS_ID                	char(20)  		NOT NULL,
	NTT_ID                	decimal(20)  	NOT NULL,
	ATCH_FILE_ID          	char(20)  		NOT NULL,
	FILE_SN               	decimal(10)  	NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DTA_USE_STATS_PK PRIMARY KEY (DTA_USE_STATS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('DTA_USE_STATS_ID', 1);

---------------------------------------------------------

CREATE TABLE COMTN_REPRT_STATS
(
	REPRT_ID              	char(20)  		NOT NULL,
	REPRT_NM              	varchar(20)  	NOT NULL,
	REPRT_TY              	char(2)  		NULL,
	REPRT_STTUS           	char(2)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_REPRT_STATS_PK PRIMARY KEY (REPRT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('REPRT_STATS_ID', 1);

----------------------------------------------

CREATE TABLE COMTS_WEB_LOG_SUMMARY
(
	OCCRRNC_DE            	char(20)  		NOT NULL,
	URL                   	varchar(100)  	NOT NULL,
	RDCNT                 	decimal(10)  	NULL,
	CONSTRAINT  COMTS_WEB_LOG_SUMMARY_PK PRIMARY KEY (OCCRRNC_DE, URL)
);

-----------------------------------------------

CREATE TABLE COMTS_USER_SUMMARY
(
	OCCRRNC_DE            	char(20)  		NOT NULL,
	STATS_SE              	varchar(10)  	NOT NULL,
	DETAIL_STATS_SE       	varchar(10)  	NOT NULL,
	USER_CO               	decimal(10)  	NULL,
	CONSTRAINT  COMTS_USER_SUMMARY_PK PRIMARY KEY (OCCRRNC_DE, STATS_SE, DETAIL_STATS_SE)
);

-------------------------------------------

CREATE TABLE COMTS_TRSMRCV_LOG_SUMMARY
(
	OCCRRNC_DE            	char(20)  		NOT NULL,
	TRSMRCV_SE_CODE       	char(3)  		NOT NULL,
	PROVD_INSTT_ID        	char(8)  		NOT NULL,
	PROVD_SYS_ID          	char(8)  		NOT NULL,
	PROVD_SVC_ID          	char(8)  		NOT NULL,
	REQUST_INSTT_ID       	char(8)  		NOT NULL,
	REQUST_SYS_ID         	char(8)  		NOT NULL,
	RDCNT                 	decimal(10)  	NULL,
	ERROR_CO              	decimal(10)  	NULL,
	CONSTRAINT  COMTS_TRSMRCV_LOG_SUMMARY_PK PRIMARY KEY (OCCRRNC_DE, TRSMRCV_SE_CODE, PROVD_INSTT_ID, PROVD_SYS_ID, PROVD_SVC_ID, REQUST_INSTT_ID, REQUST_SYS_ID)
);

-- =======================================
-- BATCH

CREATE TABLE COMTN_BATCH_OPERT
(
	BATCH_OPERT_ID        	varchar(20)  	NOT NULL,
	BATCH_OPERT_NM        	varchar(60)  	NULL,
	BATCH_PROGRM          	varchar(255)  	NULL,
	PARAMTR               	varchar(250)  	NULL,
	BATCH_OBJECT          	varchar(255)  	NULL,
	BATCH_METHOD          	varchar(255)  	NULL,
	BATCH_BEAN          	varchar(255)  	NULL,
	USE_AT                	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BATCH_OPERT_PK PRIMARY KEY (BATCH_OPERT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('BATCH_OPERT_ID', 1);

-----------------------------------------

CREATE TABLE COMTN_BATCH_SCHDUL
(
	BATCH_SCHDUL_ID       	varchar(20)  	NOT NULL,
	BATCH_OPERT_ID        	varchar(20)  	NOT NULL,
	START_DELAY          	decimal(20)  	NULL,
	REPEAT_INTERVAL       	decimal(20)  	NULL,
	EXECUT_CYCLE          	varchar(2)  	NULL,
	EXECUT_SCHDUL_DE      	char(20)  		NULL,
	EXECUT_SCHDUL_HOUR    	char(2)  		NULL,
	EXECUT_SCHDUL_MNT     	char(2)  		NULL,
	EXECUT_SCHDUL_SECND   	char(2)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BATCH_SCHDUL_PK PRIMARY KEY (BATCH_SCHDUL_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('BATCH_SCHDUL_ID', 1);

------------------------------------------------------

CREATE TABLE COMTN_BATCH_SCHDUL_DFK
(
	BATCH_SCHDUL_ID       	varchar(20)  	NOT NULL,
	EXECUT_SCHDUL_DFK_SE  	char(1)  		NOT NULL,
	CONSTRAINT  COMTN_BATCH_SCHDUL_DFK_PK PRIMARY KEY (BATCH_SCHDUL_ID, EXECUT_SCHDUL_DFK_SE),
	CONSTRAINT  COMTN_BATCH_SCHDUL_DFK_FK1 FOREIGN KEY (BATCH_SCHDUL_ID) REFERENCES COMTN_BATCH_SCHDUL(BATCH_SCHDUL_ID)
);

-------------------------------------------------------

CREATE TABLE COMTN_BATCH_RESULT
(
	BATCH_RESULT_ID       	varchar(20)  	NOT NULL,
	BATCH_SCHDUL_ID       	varchar(20)  	NOT NULL,
	BATCH_OPERT_ID       	varchar(20)  	NOT NULL,
	PARAMTR               	varchar(250)  	NULL,
	STTUS                 	varchar(2)  	NULL,
	ERROR_INFO            	varchar(2000)   NULL,
	EXECUT_BEGIN_TM       	varchar(14)  	NULL,
	EXECUT_END_TM         	varchar(14)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BATCH_RESULT_PK PRIMARY KEY (BATCH_RESULT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('BATCH_RESULT_ID', 1);

-- =========================================
-- RESTDE

CREATE TABLE COMTN_RESTDE
(
	RESTDE_NO             	decimal(6)  	NOT NULL,
	RESTDE                	char(8)  		NULL,
	RESTDE_NM             	varchar(60)  	NULL,
	RESTDE_DC             	varchar(200)  	NULL,
	RESTDE_SE_CODE        	varchar(2)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_RESTDE_PK PRIMARY KEY (RESTDE_NO)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('RESTDE_NO', 1);

-- =========================================
-- CODE

CREATE TABLE COMTC_ADMINIST_CODE
(
	ADMINIST_ZONE_SE      		char(1)  		NOT NULL,
	ADMINIST_ZONE_CODE    		varchar(10)  	NOT NULL,
	USE_AT                		char(1)  		NOT NULL,
	ADMINIST_ZONE_NM      		varchar(60)  	NULL,
	UPPER_ADMINIST_ZONE_CODE  	varchar(10)  	NULL,
	CREAT_DE              		char(20)  		NULL,
	ABL_DE                		char(20)  		NULL,
	FRST_REGISTER_ID      		varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     		timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        		varchar(20)  	NULL,
	LAST_UPDT_PNTTM       		timestamp  		NULL,
	CONSTRAINT  COMTC_ADMINIST_CODE_PK PRIMARY KEY (ADMINIST_ZONE_SE, ADMINIST_ZONE_CODE)
);

---------------------------------------------------

CREATE TABLE COMTH_ADMINIST_CODE_LOG
(
	OCCRRNC_DE            		char(20)  		NOT NULL,
	ADMINIST_ZONE_SE      		char(1)  		NOT NULL,
	ADMINIST_ZONE_CODE    		varchar(10)  	NOT NULL,
	OPERT_SN              		decimal(10)  	NOT NULL,
	CHANGE_SE_CODE        		varchar(2)  	NULL,
	PROCESS_SE            		varchar(2)  	NULL,
	ADMINIST_ZONE_NM      		varchar(60)  	NULL,
	LOWEST_ADMINIST_ZONE_NM  	varchar(60)  	NULL,
	CTPRVN_CODE           		varchar(2)  	NULL,
	SIGNGU_CODE           		varchar(3)  	NULL,
	EMD_CODE              		varchar(3)  	NULL,
	LI_CODE               		varchar(2)  	NULL,
	CREAT_DE              		char(20)  		NULL,
	ABL_DE                		char(20)  		NULL,
	ABL_ENNC              		char(1)  		NULL,
	FRST_REGISTER_ID      		varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     		timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        		varchar(20)  	NULL,
	LAST_UPDT_PNTTM       		timestamp  		NULL,
	CONSTRAINT  COMTH_ADMINIST_CODE_LOG_PK PRIMARY KEY (OCCRRNC_DE, ADMINIST_ZONE_SE, ADMINIST_ZONE_CODE, OPERT_SN)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('ADMIN_CODE_OPERT_SN', 1);

------------------------------------------

CREATE TABLE COMTC_CMMN_CL_CODE
(
	CL_CODE               	char(3)  		NOT NULL,
	CL_CODE_NM            	varchar(60)  	NULL,
	CL_CODE_DC            	varchar(200)  	NULL,
	USE_AT                	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTC_CMMN_CL_CODE_PK PRIMARY KEY (CL_CODE)
);

------------------------------------------------

CREATE TABLE COMTC_CMMN_CODE
(
	CODE_ID               	varchar(6)  	NOT NULL,
	CODE_ID_NM            	varchar(60)  	NULL,
	CODE_ID_DC            	varchar(200)  	NULL,
	USE_AT                	char(1)  		NULL,
	CL_CODE               	char(3)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTC_CMMN_CODE_PK PRIMARY KEY (CODE_ID),
	CONSTRAINT  COMTC_CMMN_CODE_FK1 FOREIGN KEY (CL_CODE) REFERENCES COMTC_CMMN_CL_CODE(CL_CODE) ON DELETE SET NULL
);

CREATE INDEX COMTC_CMMN_CODE_i01 ON COMTC_CMMN_CODE (CL_CODE ASC);

----------------------------------------------------

CREATE TABLE COMTC_CMMN_DETAIL_CODE
(
	CODE_ID               	varchar(6)  	NOT NULL,
	CODE                  	varchar(15)  	NOT NULL,
	CODE_NM               	varchar(60)  	NULL,
	CODE_DC               	varchar(200)  	NULL,
	USE_AT                	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTC_CMMN_DETAIL_CODE_PK PRIMARY KEY (CODE_ID, CODE),
	CONSTRAINT  COMTC_CMMN_DETAIL_CODE_FK1 FOREIGN KEY (CODE_ID) REFERENCES COMTC_CMMN_CODE(CODE_ID)
);
CREATE INDEX COMTC_CMMN_DETAIL_CODE_i01 ON COMTC_CMMN_DETAIL_CODE (CODE_ID ASC);

-------------------------------------------------

CREATE TABLE COMTC_INSTT_CODE
(
	INSTT_CODE            	char(7)  		NOT NULL,
	ALL_INSTT_NM          	varchar(255)  	NULL,
	LOWEST_INSTT_NM       	varchar(100)  	NULL,
	INSTT_ABRV_NM         	varchar(50)  	NULL,
	ODR                   	char(1)  		NULL,
	ORD                   	char(3)  		NULL,
	INSTT_ODR             	char(2)  		NULL,
	UPPER_INSTT_CODE      	char(7)  		NULL,
	BEST_INSTT_CODE       	char(7)  		NULL,
	REPRSNT_INSTT_CODE    	char(7)  		NULL,
	INSTT_TY_LCLAS        	char(2)  		NULL,
	INSTT_TY_MLSFC        	char(2)  		NULL,
	INSTT_TY_SCLAS        	char(2)  		NULL,
	TELNO                 	varchar(20)  	NULL,
	FXNUM                 	varchar(20)  	NULL,
	CREAT_DE              	char(20)  		NULL,
	ABL_DE                	char(20)  		NULL,
	ABL_ENNC              	char(1)  		NULL,
	CHANGE_DE             	char(20)  		NULL,
	CHANGE_TIME           	varchar(6)  	NULL,
	BSIS_DE               	char(20) 	 	NULL,
	SORT_ORDR             	decimal(8)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTC_INSTT_CODE_PK PRIMARY KEY (INSTT_CODE)
);

----------------------------------------------

CREATE TABLE COMTH_INSTT_CODE_LOG
(
	OCCRRNC_DE            	char(20)  		NOT NULL,
	INSTT_CODE            	char(7)  		NOT NULL,
	OPERT_SN              	decimal(10)  	NOT NULL,
	CHANGE_SE_CODE        	varchar(2)  	NULL,
	PROCESS_SE            	varchar(2) 		NULL,
	ETC_CODE              	char(2)  		NULL,
	ALL_INSTT_NM          	varchar(255)  	NULL,
	LOWEST_INSTT_NM       	varchar(100)  	NULL,
	INSTT_ABRV_NM         	varchar(50)  	NULL,
	ODR                   	char(1)  		NULL,
	ORD                   	char(3)  		NULL,
	INSTT_ODR             	char(2)  		NULL,
	UPPER_INSTT_CODE      	char(7)  		NULL,
	BEST_INSTT_CODE       	char(7)  		NULL,
	REPRSNT_INSTT_CODE    	char(7)  		NULL,
	INSTT_TY_LCLAS        	char(2)  		NULL,
	INSTT_TY_MLSFC        	char(2)  		NULL,
	INSTT_TY_SCLAS        	char(2)  		NULL,
	TELNO                 	varchar(20)  	NULL,
	FXNUM                 	varchar(20)  	NULL,
	CREAT_DE              	char(20)  		NULL,
	ABL_DE                	char(20)  		NULL,
	ABL_ENNC              	char(1)  		NULL,
	CHANGE_DE             	char(20)  		NULL,
	CHANGE_TIME           	varchar(6)  	NULL,
	BSIS_DE               	char(20)  		NULL,
	SORT_ORDR             	decimal(8)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTH_INSTT_CODE_LOG_PK PRIMARY KEY (OCCRRNC_DE, INSTT_CODE, OPERT_SN)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('INSTT_CODE_OPERT_SN', 1);

------------------------------------------

CREATE TABLE COMTC_ZIP
(
	ZIP                   	varchar(6)  	NOT NULL,
	SN                    	decimal(10)  	NOT NULL,
	CTPRVN_NM             	varchar(60)  	NULL,
	SIGNGU_NM             	varchar(60)  	NULL,
	EMD_NM                	varchar(60)  	NULL,
	LI_BULD_NM            	varchar(128)  	NULL,
	LNBR_DONG_HO          	varchar(60)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTC_ZIP_PK PRIMARY KEY (ZIP, SN)
);

-- ==========================================
-- LOG

CREATE TABLE COMTH_LOGIN_LOG
(
	LOG_ID                	char(20)  		NOT NULL,
	CONECT_ID             	varchar(20)  	NULL,
	CONECT_IP            	varchar(23)  	NULL,
	CONECT_MTHD           	char(4)  		NULL,
	ERROR_OCCRRNC_AT      	char(1)  		NULL,
	ERROR_CODE            	char(3)  		NULL,
	CREAT_DT              	timestamp  		NULL,
	CONSTRAINT  COMTH_LOGIN_LOG_PK PRIMARY KEY (LOG_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('LOGIN_LOG_ID', 1);

--------------------------------------------

CREATE TABLE COMTH_SYS_LOG
(
	REQUST_ID             	varchar(20)  	NOT NULL,
	JOB_SE_CODE           	char(3)  		NULL,
	INSTT_CODE            	char(7)  		NULL,
	OCCRRNC_DE            	char(20)  		NULL,
	RQESTER_IP            	varchar(23)  	NULL,
	RQESTER_ID            	varchar(20)  	NULL,
	TRGET_MENU_NM         	varchar(255)  	NULL,
	SVC_NM                	varchar(255)  	NULL,
	METHOD_NM             	varchar(60)  	NULL,
	PROCESS_SE_CODE       	char(3)  		NULL,
	PROCESS_CO           	decimal(10)  	NULL,
	PROCESS_TIME          	varchar(14)  	NULL,
	RSPNS_CODE            	char(3)  		NULL,
	ERROR_SE              	char(1)  		NULL,
	ERROR_CO              	decimal(10)  	NULL,
	ERROR_CODE            	char(3)  		NULL,
	CONSTRAINT  COMTH_SYS_LOG_PK PRIMARY KEY (REQUST_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SYS_LOG_ID', 1);

----------------------------------------

CREATE TABLE COMTN_SYS_HIST
(
	HIST_ID               	char(20)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	HIST_SE_CODE          	char(6)  		NOT NULL,
	HIST_CN               	varchar(2500)   NOT NULL,
	SYS_NM                	varchar(255)  	NOT NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	CONSTRAINT  COMTN_SYS_HIST_PK PRIMARY KEY (HIST_ID)
);

------------------------------------------------

CREATE TABLE COMTH_TRSMRCV_LOG
(
	REQUST_ID             	varchar(20)  	NOT NULL,
	OCCRRNC_DE            	char(20)  		NULL,
	TRSMRCV_SE_CODE       	char(3)  		NULL,
	CNTC_ID               	char(8)  		NULL,
	PROVD_INSTT_ID        	char(8)  		NULL,
	PROVD_SYS_ID          	char(8)  		NULL,
	PROVD_SVC_ID          	char(8)  		NULL,
	REQUST_INSTT_ID       	char(8)  		NULL,
	REQUST_SYS_ID         	char(8)  		NULL,
	REQUST_TRNSMIT_TM     	varchar(14)  	NULL,
	REQUST_RECPTN_TM      	varchar(14)  	NULL,
	RSPNS_TRNSMIT_TM      	varchar(14)  	NULL,
	RSPNS_RECPTN_TM       	varchar(14)  	NULL,
	RESULT_CODE           	varchar(4)  	NULL,
	RESULT_MSSAGE         	varchar(4000)   NULL,
	FRST_REGIST_PNTTM     	timestamp  		NULL,
	RQESTER_ID            	varchar(20)  	NULL,
	CONSTRAINT  COMTH_TRSMRCV_LOG_PK PRIMARY KEY (REQUST_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('TRSMRCV_LOG_ID', 1);

----------------------------------------

CREATE TABLE COMTH_USER_LOG
(
	OCCRRNC_DE            	char(20)  		NOT NULL,
	RQESTER_ID            	varchar(20)  	NOT NULL,
	SVC_NM                	varchar(255)  	NOT NULL,
	METHOD_NM             	varchar(60)  	NOT NULL,
	CREAT_CO              	decimal(10)  	NULL,
	UPDT_CO               	decimal(10)  	NULL,
	RDCNT                 	decimal(10)  	NULL,
	DELETE_CO             	decimal(10)  	NULL,
	OUTPT_CO              	decimal(10) 	NULL,
	ERROR_CO              	decimal(10)  	NULL,
	CONSTRAINT  COMTH_USER_LOG_PK PRIMARY KEY (OCCRRNC_DE, RQESTER_ID, SVC_NM, METHOD_NM)
);

----------------------------------------

CREATE TABLE COMTH_WEB_LOG
(
	REQUST_ID             	varchar(20)  	NOT NULL,
	OCCRRNC_DE            	timestamp  		NULL,
	URL                   	varchar(100) 	NULL,
	RQESTER_ID            	varchar(20)  	NULL,
	RQESTER_IP            	varchar(23)  	NULL,
	CONSTRAINT  COMTH_WEB_LOG_PK PRIMARY KEY (REQUST_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('WEB_LOG_ID', 1);

-- ===============================================
-- MENU

CREATE TABLE COMTN_MENU_INFO
(
	MENU_NO               	decimal(20)  	NOT NULL,
	UPPER_MENU_NO         	decimal(20)  	NULL,
	MENU_NM               	varchar(60)  	NOT NULL,
	PROGRM_FILE_NM        	varchar(60)  	NOT NULL,
	MENU_ORDR             	decimal(5)  	NOT NULL,
	MENU_DC               	varchar(250)  	NULL,
	RELATE_IMAGE_PATH     	varchar(100)  	NULL,
	RELATE_IMAGE_NM       	varchar(60)  	NULL,
	CONSTRAINT  COMTN_MENU_INFO_PK PRIMARY KEY (MENU_NO),
	CONSTRAINT  COMTN_MENU_INFO_FK2 FOREIGN KEY (PROGRM_FILE_NM) REFERENCES COMTN_PROGRM_LIST(PROGRM_FILE_NM) ON DELETE CASCADE,
	CONSTRAINT  COMTN_MENU_INFO_FK1 FOREIGN KEY (UPPER_MENU_NO) REFERENCES COMTN_MENU_INFO(MENU_NO) ON DELETE CASCADE
);
CREATE INDEX COMTN_MENU_INFO_i02 ON COMTN_MENU_INFO (UPPER_MENU_NO  ASC);

-----------------------------------

CREATE TABLE COMTN_SITE_MAP
(
	MAPNG_CREAT_ID        	varchar(30)  	NOT NULL,
	CREATR_ID             	varchar(20)  	NOT NULL,
	MAPNG_FILE_NM         	varchar(60)  	NOT NULL,
	MAPNG_FILE_PATH       	varchar(100)  	NOT NULL,
	CONSTRAINT  COMTN_SITE_MAP_PK PRIMARY KEY (MAPNG_CREAT_ID)
);

-------------------------------------------

CREATE TABLE COMTN_MENU_CREAT_DTLS
(
	MENU_NO               	decimal(20) 	NOT NULL,
	AUTHOR_CODE           	varchar(30)  	NOT NULL,
	MAPNG_CREAT_ID        	varchar(30)  	NULL,
	CONSTRAINT  COMTN_MENU_CREAT_DTLS_PK PRIMARY KEY (MENU_NO, AUTHOR_CODE),
	CONSTRAINT  COMTN_MENU_CREAT_DTLS_FK2 FOREIGN KEY (MENU_NO) REFERENCES COMTN_MENU_INFO(MENU_NO) ON DELETE CASCADE,
	CONSTRAINT  COMTN_MENU_CREAT_DTLS_FK3 FOREIGN KEY (MAPNG_CREAT_ID) REFERENCES COMTN_SITE_MAP(MAPNG_CREAT_ID) ON DELETE CASCADE,
	CONSTRAINT  COMTN_MENU_CREAT_DTLS_FK1 FOREIGN KEY (AUTHOR_CODE) REFERENCES COMTN_AUTHOR_INFO(AUTHOR_CODE)
);
CREATE INDEX COMTN_MENU_CREAT_DTLS_i02 ON COMTN_MENU_CREAT_DTLS (MENU_NO ASC);
CREATE INDEX COMTN_MENU_CREAT_DTLS_i03 ON COMTN_MENU_CREAT_DTLS (MAPNG_CREAT_ID ASC);
CREATE INDEX COMTN_MENU_CREAT_DTLS_i04 ON COMTN_MENU_CREAT_DTLS (AUTHOR_CODE ASC);

--------------------------------------

CREATE TABLE COMTN_BKMK_MENU_MANAGE
(
	MENU_ID               	decimal(20)  	NOT NULL,
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	MENU_NM               	varchar(60)  	NOT NULL,
	PROGRM_STRE_PATH      	varchar(100)  	NOT NULL,
	CONSTRAINT  COMTN_BKMK_MENU_MANAGE_PK PRIMARY KEY (MENU_ID, EMPLYR_ID)
);

-- ===========================================
-- BACKUP

CREATE TABLE COMTN_BACKUP_OPERT
(
	BACKUP_OPERT_ID       	varchar(20)  	NOT NULL,
	BACKUP_OPERT_NM       	varchar(60)  	NULL,
	BACKUP_ORGINL_DRCTRY  	varchar(255)  	NULL,
	BACKUP_STRE_DRCTRY    	varchar(255)  	NULL,
	CMPRS_SE              	varchar(2)  	NULL,
	EXECUT_CYCLE          	varchar(2)  	NULL,
	EXECUT_SCHDUL_DE      	char(20)  		NULL,
	EXECUT_SCHDUL_HOUR    	char(2)  		NULL,
	EXECUT_SCHDUL_MNT     	char(2)  		NULL,
	EXECUT_SCHDUL_SECND   	char(2)  		NULL,
	USE_AT                	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BACKUP_OPERT_PK PRIMARY KEY (BACKUP_OPERT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('BACKUP_OPERT_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_BACKUP_SCHDUL_DFK
(
	BACKUP_OPERT_ID       	varchar(20)  	NOT NULL,
	EXECUT_SCHDUL_DFK_SE  	char(1)  		NOT NULL,
	CONSTRAINT  COMTN_BACKUP_SCHDUL_DFK_PK PRIMARY KEY (BACKUP_OPERT_ID, EXECUT_SCHDUL_DFK_SE),
	CONSTRAINT  COMTN_BACKUP_SCHDUL_DFK_FK1 FOREIGN KEY (BACKUP_OPERT_ID) REFERENCES COMTN_BACKUP_OPERT(BACKUP_OPERT_ID)
);

---------------------------------------------

CREATE TABLE COMTN_BACKUP_RESULT
(
	BACKUP_RESULT_ID      	varchar(20)  	NOT NULL,
	BACKUP_OPERT_ID       	varchar(20)  	NOT NULL,
	BACKUP_FILE           	varchar(255)  	NULL,
	STTUS                 	varchar(2)  	NULL,
	ERROR_INFO            	varchar(2000)   NULL,
	EXECUT_BEGIN_TM       	varchar(14)  	NULL,
	EXECUT_END_TM         	varchar(14)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BACKUP_RESULT_PK PRIMARY KEY (BACKUP_RESULT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('BACKUP_RESULT_ID', 1);

-- ================================================
-- NTWRK

CREATE TABLE COMTN_NTWRK_INFO
(
	NTWRK_ID              	char(20)  		NOT NULL,
	NTWRK_IP              	varchar(23)  	NULL,
	GTWY                  	varchar(23)  	NULL,
	SUBNET                	varchar(23)  	NULL,
	DOMN_NM_SERVER        	varchar(23) 	NULL,
	MANAGE_IEM            	char(2)  		NULL,
	USER_NM               	varchar(60)  	NULL,
	USE_AT                	char(1)  		NULL,
	RGSDE                 	char(8)			NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_NTWRK_INFO_PK PRIMARY KEY (NTWRK_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NTWRK_ID', 1);

-- ================================================
-- TROBL

CREATE TABLE COMTN_TROBL_INFO
(
	TROBL_ID              	char(20)  		NOT NULL,
	TROBL_NM              	varchar(60)  	NULL,
	TROBL_KND             	char(2)  		NULL,
	TROBL_DC              	varchar(2000)   NULL,
	TROBL_OCCRRNC_TIME    	varchar(14)  	NULL,
	TROBL_RQESTER_NM      	varchar(60)  	NULL,
	TROBL_REQUST_TIME     	varchar(14)  	NULL,
	TROBL_PROCESS_RESULT  	varchar(2000)   NULL,
	TROBL_OPETR_NM        	varchar(60)  	NULL,
	TROBL_PROCESS_TIME    	varchar(14)  	NULL,
	PROCESS_STTUS         	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_TROBL_INFO_PK PRIMARY KEY (TROBL_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('TROBL_ID', 1);

-- =============================================
-- Application

CREATE TABLE COMTN_ANNVRSRY_MANAGE
(
	ANNVRSRY_ID           	varchar(20)  	NOT NULL,
	USER_ID               	varchar(20)  	NOT NULL,
	ANNVRSRY_SE           	varchar(2)  	NOT NULL,
	ANNVRSRY_NM           	varchar(255)  	NOT NULL,
	ANNVRSRY              	char(8)  		NOT NULL,
	CLDR_SE               	char(1)  		NOT NULL,
	ANNVRSRY_NTCN_SETUP   	char(1)  		NULL,
	ANNVRSRY_NTCN_BGNDE   	char(20)  		NULL,
	MEMO                  	varchar(1000)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	REPTIT_AT             	char(1)  		NULL,
	CONSTRAINT  COMTN_ANNVRSRY_MANAGE_PK PRIMARY KEY (ANNVRSRY_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('ANNVRSRY_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_BANNER
(
	BANNER_ID             	char(20)  		NOT NULL,
	BANNER_NM             	varchar(60)  	NOT NULL,
	LINK_URL              	varchar(255)  	NOT NULL,
	BANNER_IMAGE          	varchar(60)  	NOT NULL,
	BANNER_DC             	varchar(200)  	NULL,
	REFLCT_AT             	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	BANNER_IMAGE_FILE     	varchar(60)  	NULL,
	SORT_ORDR             	decimal(8)  	NULL,
	CONSTRAINT  COMTN_BANNER_PK PRIMARY KEY (BANNER_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('BANNER_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_BNDT_MANAGE
(
	BNDT_ID               	varchar(20)  	NOT NULL,
	BNDT_DE               	char(8)  		NOT NULL,
	RM                    	varchar(2500)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BNDT_MANAGE_PK PRIMARY KEY (BNDT_ID, BNDT_DE)
);

-------------------------------------------

CREATE TABLE COMTN_BNDT_CECK_MANAGE
(
	BNDT_CECK_CODE        	varchar(10)  	NOT NULL,
	BNDT_CECK_CODE_NM     	varchar(255)  	NOT NULL,
	BNDT_CECK_SE          	char(2)  		NOT NULL,
	USE_AT                	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BNDT_CECK_MANAGE_PK PRIMARY KEY (BNDT_CECK_CODE, BNDT_CECK_SE)
);

------------------------------------------

CREATE TABLE COMTN_BNDT_DIARY
(
	BNDT_ID               	varchar(20)  	NOT NULL,
	BNDT_DE               	char(8)  		NOT NULL,
	BNDT_CECK_SE          	char(2)  		NOT NULL,
	BNDT_CECK_CODE        	varchar(10)  	NOT NULL,
	CHCK_STTUS            	varchar(1000)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_BNDT_DIARY_PK PRIMARY KEY (BNDT_ID, BNDT_DE, BNDT_CECK_SE, BNDT_CECK_CODE),
	CONSTRAINT  COMTN_BNDT_DIARY_FK2 FOREIGN KEY (BNDT_ID, BNDT_DE) REFERENCES COMTN_BNDT_MANAGE(BNDT_ID, BNDT_DE),
	CONSTRAINT  COMTN_BNDT_DIARY_FK1 FOREIGN KEY (BNDT_CECK_CODE, BNDT_CECK_SE) REFERENCES COMTN_BNDT_CECK_MANAGE(BNDT_CECK_CODE, BNDT_CECK_SE)
);

-------------------------------------

CREATE TABLE COMTN_CTSNN_MANAGE
(
	CTSNN_ID              	varchar(20)  	NOT NULL,
	USER_ID               	varchar(20)  	NOT NULL,
	CTSNN_CODE            	char(2)  		NOT NULL,
	REQST_DE              	char(8)  		NOT NULL,
	CTSNN_NM              	varchar(255)  	NOT NULL,
	TRGTER_NM             	varchar(20)  	NOT NULL,
	BRTHDY                	char(8)  		NOT NULL,
	OCCRRNC_DE            	char(8)  		NOT NULL,
	RELATE                	char(2)  		NOT NULL,
	RM                    	varchar(2500)   NULL,
	SANCTNER_ID           	varchar(20)  	NOT NULL,
	CONFM_AT              	char(1)  		NULL,
	SANCTN_DT             	timestamp  		NULL,
	RETURN_RESN           	varchar(1000)   NULL,
	INFRML_SANCTN_ID      	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_CTSNN_MANAGE_PK PRIMARY KEY (CTSNN_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CTSNN_ID', 1);

---------------------------------------------

CREATE TABLE COMTN_EVENT_INFO
(
	EVENT_ID              	char(20)  		NOT NULL,
	BSNS_YEAR             	char(4)  		NULL,
	BSNS_CODE             	varchar(2)  	NULL,
	EVENT_CN              	varchar(1000)   NULL,
	EVENT_SVC_BGNDE       	char(8)  		NULL,
	SVC_USE_NMPR_CO       	decimal(10)  	NULL,
	CHARGER_NM            	varchar(50)  	NULL,
	PRPARETG_CN           	varchar(2500)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NOT NULL,
	LAST_UPDT_PNTTM       	timestamp  		NOT	NULL,
	EVENT_SVC_ENDDE       	char(8)  		NULL,
	EVENT_TY_CODE         	char(1)  		NULL,
	EVENT_CONFM_AT        	char(1)  		NULL,
	EVENT_CONFM_DE        	char(8)  		NULL,
	CONSTRAINT  COMTN_EVENT_INFO_PK PRIMARY KEY (EVENT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('EVENT_INFO_ID', 1);

--------------------------------------------------

CREATE TABLE COMTN_EXTRL_HR_INFO
(
	EVENT_ID              	char(20)  		NOT NULL,
	EXTRL_HR_ID           	char(20)  		NOT NULL,
	SEXDSTN_CODE          	char(1)  		NULL,
	EXTRL_HR_NM           	varchar(60)  	NULL,
	OCCP_TY_CODE          	char(1)  		NULL,
	PSITN_INSTT_NM        	varchar(100)  	NULL,
	BRTHDY                	char(8)  		NULL,
	AREA_NO               	varchar(4)  	NULL,
	MIDDLE_TELNO          	varchar(4)  	NULL,
	END_TELNO             	varchar(4)  	NULL,
	EMAIL_ADRES           	varchar(50)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_EXTRL_HR_INFO_PK PRIMARY KEY (EVENT_ID, EXTRL_HR_ID),
	CONSTRAINT  COMTN_EXTRL_HR_INFO_FK1 FOREIGN KEY (EVENT_ID) REFERENCES COMTN_EVENT_INFO(EVENT_ID)
);
CREATE INDEX COMTN_EXTRL_HR_INFO_i01 ON COMTN_EXTRL_HR_INFO (EVENT_ID  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('EXTRL_HR_INFO_ID', 1);

-------------------------------------------------

CREATE TABLE COMTN_EVENT_MANAGE
(
	EVENT_ID              	char(20)  		NOT NULL,
	EVENT_SE              	varchar(2)  	NOT NULL,
	EVENT_NM              	varchar(60)  	NOT NULL,
	EVENT_PURPS           	varchar(200)  	NOT NULL,
	EVENT_BGNDE           	char(8)  		NOT NULL,
	EVENT_ENDDE           	char(8)  		NOT NULL,
	EVENT_AUSPC_INSTT_NM  	varchar(60)  	NULL,
	EVENT_MNGT_INSTT_NM   	varchar(60)  	NULL,
	EVENT_PLACE           	varchar(200)  	NOT NULL,
	EVENT_CN              	varchar(1000)   NULL,
	CT_OCCRRNC_AT         	char(1)  		NULL,
	PARTCPT_CT            	decimal(16)  	NULL,
	GARDEN                	decimal(10)  	NOT NULL,
	REFRN_URL             	varchar(1024)   NULL,
	RCEPT_BGNDE           	char(8)  		NOT NULL,
	RCEPT_ENDDE           	char(8)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_EVENT_MANAGE_PK PRIMARY KEY (EVENT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('EVENT_ID', 1);

----------------------------------------------------

CREATE TABLE COMTN_EVENT_ATDRN
(
	APPLCNT_ID            	varchar(20)  	NOT NULL,
	EVENT_ID              	char(20)  		NOT NULL,
	REQST_DE              	char(20)  		NOT NULL,
	SANCTNER_ID           	varchar(20)  	NOT NULL,
	CONFM_AT              	char(1)  		NULL,
	SANCTN_DT             	timestamp  		NULL,
	RETURN_RESN           	varchar(1000)   NULL,
	INFRML_SANCTN_ID      	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_EVENT_ATDRN_PK PRIMARY KEY (APPLCNT_ID, EVENT_ID),
	CONSTRAINT  COMTN_EVENT_ATDRN_FK1 FOREIGN KEY (EVENT_ID) REFERENCES COMTN_EVENT_MANAGE(EVENT_ID)
);
CREATE INDEX COMTN_EVENT_ATDRN_i01 ON COMTN_EVENT_ATDRN (EVENT_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('APPLCNT_ID', 1);

-----------------------------------------------------------

CREATE TABLE COMTN_INTNET_SVC
(
	INTNET_SVC_ID         	char(20)  		NOT NULL,
	INTNET_SVC_NM         	varchar(20)  	NOT NULL,
	INTNET_SVC_DC         	varchar(200)  	NULL,
	REFLCT_AT             	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_INTNET_SVC_PK PRIMARY KEY (INTNET_SVC_ID)
);
INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('INTNET_SVC_ID', 1);

----------------------------------------------------

CREATE TABLE COMTN_INFRML_SANCTN
(
	INFRML_SANCTN_ID      	char(20)  		NOT NULL,
	JOB_SE_CODE           	char(3)  		NOT NULL,
	APPLCNT_ID            	varchar(20)  	NOT NULL,
	REQST_DE              	char(20)  		NOT NULL,
	SANCTNER_ID           	varchar(20)  	NOT NULL,
	CONFM_AT              	char(1)  		NOT NULL,
	SANCTN_DT             	timestamp  		NULL,
	RETURN_RESN           	varchar(1000)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_INFRML_SANCTN_PK PRIMARY KEY (INFRML_SANCTN_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('INFRML_SANCTN_ID', 1);

-----------------------------------------------

CREATE TABLE COMTN_LOGIN_SCRIN_IMAGE
(
	IMAGE_ID              	char(20)  		NOT NULL,
	IMAGE_NM              	varchar(20)  	NOT NULL,
	REFLCT_AT             	char(1)  		NOT NULL,
	IMAGE                 	varchar(60)  	NOT NULL,
	IMAGE_DC              	varchar(200)  	NULL,
	IMAGE_FILE            	varchar(60)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_LOGIN_SCRIN_IMAGE_PK PRIMARY KEY (IMAGE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('LOGIN_SCRIN_IMAGE_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_MAIN_IMAGE
(
	IMAGE_ID              	char(20)  		NOT NULL,
	IMAGE_NM              	varchar(20)  	NOT NULL,
	REFLCT_AT             	char(1)  		NOT NULL,
	IMAGE                 	varchar(60)  	NOT NULL,
	IMAGE_DC              	varchar(200)  	NULL,
	IMAGE_FILE            	varchar(60)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_MAIN_IMAGE_PK PRIMARY KEY (IMAGE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('MAIN_SCRIN_IMAGE_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_MTG_PLACE_MANAGE
(
	MTGRUM_ID             	char(20)  		NOT NULL,
	MTGRUM_NM             	varchar(255)  	NOT NULL,
	OPN_BEGIN_TM          	varchar(6)  	NOT NULL,
	OPN_END_TM            	varchar(6)  	NOT NULL,
	ACEPTNC_POSBL_NMPR    	decimal(10)  	NOT NULL,
	LC_SE                 	varchar(5)		NULL,
	LC_DETAIL             	varchar(200)  	NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
  	TRGET_ID            	char(20)        NULL,
	CONSTRAINT  COMTN_MTG_PLACE_MANAGE_PK PRIMARY KEY (MTGRUM_ID)
);
CREATE INDEX COMTN_MTG_PLACE_MANAGE_I02 ON COMTN_MTG_PLACE_MANAGE (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('MTG_PLACE_ID', 1);

----------------------------------------------------

CREATE TABLE COMTN_MTG_PLACE_RESVE
(
	RESVE_ID              	char(20)  		NOT NULL,
	MTGRUM_ID             	char(20)  		NOT NULL,
	MTG_SJ                	varchar(100)  	NOT NULL,
	RSVCTM_ID             	varchar(20)  	NOT NULL,
	RESVE_DE              	char(8)  		NOT NULL,
	RESVE_BEGIN_TM        	varchar(14)  	NOT NULL,
	RESVE_END_TM          	varchar(14)  	NOT NULL,
	ATNDNC_NMPR           	decimal(10)  	NULL,
	MTG_CN                	varchar(2500)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_MTG_PLACE_RESVE_PK PRIMARY KEY (RESVE_ID),
	CONSTRAINT  COMTN_MTG_PLACE_RESVE_FK1 FOREIGN KEY (MTGRUM_ID) REFERENCES COMTN_MTG_PLACE_MANAGE(MTGRUM_ID) 
);
CREATE INDEX COMTN_MTG_PLACE_RESVE_i01 ON COMTN_MTG_PLACE_RESVE (MTGRUM_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('MTG_RESVE_ID', 1);

-----------------------------------------------------

CREATE TABLE COMTN_FXTRS_MANAGE
(
	FXTRS_CODE            	char(14)  		NOT NULL,
	FXTRS_NM              	varchar(100)  	NOT NULL,
	MAKR_NM               	varchar(100)  	NULL,
	PRICE                 	decimal(16)  	NULL,
	CONSTRAINT  COMTN_FXTRS_MANAGE_PK PRIMARY KEY (FXTRS_CODE)
);

-----------------------------------------------------

CREATE TABLE COMTN_MTG_PLACE_FXTRS
(
	MTGRUM_ID             	char(20)  		NOT NULL,
	FXTRS_CODE            	char(14)  		NOT NULL,
	QY                    	decimal(20)  	NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_MTG_PLACE_FXTRS_PK PRIMARY KEY (MTGRUM_ID, FXTRS_CODE),
	CONSTRAINT  COMTN_MTG_PLACE_FXTRS_FK2 FOREIGN KEY (MTGRUM_ID) REFERENCES COMTN_MTG_PLACE_MANAGE(MTGRUM_ID),
	CONSTRAINT  COMTN_MTG_PLACE_FXTRS_FK1 FOREIGN KEY (FXTRS_CODE) REFERENCES COMTN_FXTRS_MANAGE(FXTRS_CODE)
);
CREATE INDEX COMTN_MTG_PLACE_FXTRS_i01 ON COMTN_MTG_PLACE_FXTRS (MTGRUM_ID ASC);

-------------------------------------------

CREATE TABLE COMTN_NTFC_INFO
(
	NTCN_NO               	decimal(20)  	NOT NULL,
	NTCN_SJ               	varchar(60)  	NOT NULL,
	NTCN_CN               	varchar(100)  	NOT NULL,
	NTCN_TM               	varchar(14)  	NOT NULL,
	BH_NTCN_INTRVL        	varchar(20)  	NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_NTFC_INFO_PK PRIMARY KEY (NTCN_NO)
);

-------------------------------------------

CREATE TABLE COMTN_NOTE
(
	NOTE_ID               	char(20)  		NOT NULL,
	NOTE_SJ               	varchar(255)  	NULL,
	NOTE_CN               	varchar(4000)   NULL,
	ATCH_FILE_ID          	char(20)  		NULL, 
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_NOTE_PK PRIMARY KEY (NOTE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NOTE_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_NOTE_TRNSMIT
(
	NOTE_ID               	char(20)  		NOT NULL,
	NOTE_TRNSMIT_ID       	char(20)  		NOT NULL,
	TRNSMITER_ID          	char(20)  		NULL,
	DELETE_AT             	char(8)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_NOTE_TRNSMIT_PK PRIMARY KEY (NOTE_ID, NOTE_TRNSMIT_ID),
	CONSTRAINT  COMTN_NOTE_TRNSMIT_FK1 FOREIGN KEY (NOTE_ID) REFERENCES COMTN_NOTE(NOTE_ID)
);
CREATE INDEX COMTN_NOTE_TRNSMIT_i01 ON COMTN_NOTE_TRNSMIT (NOTE_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NOTE_TRNSMIT_ID', 1);

-----------------------------------------------

CREATE TABLE COMTN_NOTE_RECPTN
(
	NOTE_ID               	char(20)  		NOT NULL,
	NOTE_TRNSMIT_ID       	char(20)  		NOT NULL,
	NOTE_RECPTN_ID        	char(20)  		NOT NULL,
	RCVER_ID              	char(20)  		NULL,
	OPEN_YN               	char(1)  		NULL,
	RECPTN_SE             	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_NOTE_RECPTN_PK PRIMARY KEY (NOTE_ID, NOTE_TRNSMIT_ID, NOTE_RECPTN_ID),
	CONSTRAINT  COMTN_NOTE_RECPTN_FK1 FOREIGN KEY (NOTE_ID, NOTE_TRNSMIT_ID) REFERENCES COMTN_NOTE_TRNSMIT(NOTE_ID, NOTE_TRNSMIT_ID)
);
CREATE INDEX COMTN_NOTE_RECPTN_i01 ON COMTN_NOTE_RECPTN (NOTE_ID ASC, NOTE_TRNSMIT_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NOTE_RECPTN_ID', 1);

--------------------------------------------------

CREATE TABLE COMTN_NEWS_INFO
(
	NEWS_ID               	char(20)  		NOT NULL,
	NEWS_SJ               	varchar(100)  	NULL,
	NEWS_CN               	varchar(2500)   NULL,
	NEWS_ORIGIN           	varchar(250)  	NULL,
	NTCE_AT               	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	NTCE_DE               	char(8)  		NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
  	TRGET_ID           		char(20)        NULL,
	CONSTRAINT  COMTN_NEWS_INFO_PK PRIMARY KEY (NEWS_ID),
	CONSTRAINT  COMTN_NEWS_INFO_FK1 FOREIGN KEY (ATCH_FILE_ID) REFERENCES COMTN_FILE(ATCH_FILE_ID) ON DELETE CASCADE
);
CREATE INDEX COMTN_NEWS_INFO_i01 ON COMTN_NEWS_INFO (ATCH_FILE_ID  ASC);
CREATE INDEX COMTN_NEWS_INFO_i02 ON COMTN_NEWS_INFO (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NEWS_ID', 1);

---------------------------------------------------

CREATE TABLE COMTN_POPUP_MANAGE
(
	POPUP_ID              	varchar(20)  	NOT NULL,
	POPUP_SJ_NM           	varchar(1024)   NULL,
	FILE_URL              	varchar(1024)   NULL,
	POPUP_WIDTH_LC        	varchar(20)  	NULL,
	POPUP_WIDTH_SIZE      	decimal  		NULL,
	POPUP_VRTICL_LC       	varchar(20)  	NULL,
	POPUP_VRTICL_SIZE     	decimal  		NULL,
	NTCE_BGNDE            	char(20)  		NULL,
	NTCE_ENDDE            	char(20)  		NULL,
	STOPVEW_SETUP_AT      	char(1)  		NULL,
	NTCE_AT               	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_POPUP_MANAGE_PK PRIMARY KEY (POPUP_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('POPUP_ID', 1);

--------------------------------------------------

CREATE TABLE COMTN_RECOMEND_SITE_INFO
(
	RECOMEND_SITE_ID      	char(20)  		NOT NULL,
	RECOMEND_SITE_NM      	varchar(100)  	NULL,
	RECOMEND_SITE_URL     	varchar(255)  	NULL,
	RECOMEND_SITE_DC      	varchar(1000)   NULL,
	RECOMEND_RESN_CN      	varchar(1000)   NULL,
	RECOMEND_CONFM_AT     	char(1)  		NULL,
	CONFM_DE              	char(8)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
  	TRGET_ID           		char(20)        NULL,
	CONSTRAINT  COMTN_RECOMEND_SITE_INFO_PK PRIMARY KEY (RECOMEND_SITE_ID)
);
CREATE INDEX COMTN_RECOMEND_SITE_INFO_I02 ON COMTN_RECOMEND_SITE_INFO (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('RECOMEND_SITE_ID', 1);

-----------------------------------------------------

CREATE TABLE COMTN_RECENT_SRCHWRD_MNG
(
	SRCHWRD_MANAGE_ID     	char(20)  		NOT NULL,
	SRCHWRD_MANAGE_NM     	varchar(255)  	NULL,
	SRCHWRD_CONECT_URL    	varchar(255)  	NULL,
	USER_SEARCH_AT        	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_RECENT_SRCHWRD_MNG_PK PRIMARY KEY (SRCHWRD_MANAGE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SRCHWRD_MANAGE_ID', 1);

--------------------------------------------------

CREATE TABLE COMTN_RECENT_SRCHWRD
(
	SRCHWRD_MANAGE_ID     	char(20)  		NOT NULL,
	RECENT_SRCHWRD_ID     	char(20)  		NOT NULL,
	RECENT_SRCHWRD_NM     	varchar(255)  	NULL,
	RECENT_SRCHWRD_CO     	decimal(20)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_RECENT_SRCHWRD_PK PRIMARY KEY (SRCHWRD_MANAGE_ID, RECENT_SRCHWRD_ID),
	CONSTRAINT  COMTN_RECENT_SRCHWRD_FK1 FOREIGN KEY (SRCHWRD_MANAGE_ID) REFERENCES COMTN_RECENT_SRCHWRD_MNG(SRCHWRD_MANAGE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SRCHWRD_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_RSS
(
	RSS_ID                	char(20)  		NOT NULL,
	TRGET_SVC_NM          	varchar(255)  	NOT NULL,
	TRGET_SVC_TABLE       	varchar(255)  	NOT NULL,
	TRGET_SVC_LIST_CO     	decimal(5)  	NOT NULL,
	HDER_TITLE            	varchar(255)  	NOT NULL,
	HDER_LINK             	varchar(255)  	NOT NULL,
	HDER_DC               	varchar(4000)   NOT NULL,
	HDER_TAG              	varchar(255)  	NULL,
	HDER_ETC              	varchar(250)  	NULL,
	BDT_TITLE             	varchar(255)  	NULL,
	BDT_LINK              	varchar(255)  	NULL,
	BDT_DC                	varchar(4000)   NULL,
	BDT_TAG               	varchar(255)  	NULL,
	BDT_ETC_TAG           	varchar(255)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_RSS_PK PRIMARY KEY (RSS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('RSS_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_RWARD_MANAGE
(
	RWARD_ID              	char(20)  		NOT NULL,
	RWARDWNR_ID           	varchar(20)  	NOT NULL,
	RWARD_CODE            	char(2)  		NOT NULL,
	RWARD_DE              	char(8)  		NOT NULL,
	RWARD_NM              	varchar(255)  	NOT NULL,
	PBLEN_CN              	varchar(1000)   NULL,
	SANCTNER_ID           	varchar(20)  	NOT NULL,
	CONFM_AT              	char(1)  		NULL,
	SANCTN_DT             	timestamp  		NULL,
	RETURN_RESN           	varchar(1000)   NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	INFRML_SANCTN_ID      	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_RWARD_MANAGE_PK PRIMARY KEY (RWARD_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('RWARD_ID', 1);

-----------------------------------------------------

CREATE TABLE COMTN_SITE_LIST
(
	SITE_ID               	char(20)  		NOT NULL,
	SITE_NM               	varchar(100)  	NULL,
	SITE_URL              	varchar(100)  	NULL,
	SITE_DC               	varchar(1000)   NULL,
	SITE_THEMA_CL_CODE    	varchar(2)  	NULL,
	ACTVTY_AT             	char(1)  		NULL,
	USE_AT                	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_SITE_LIST_PK PRIMARY KEY (SITE_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SITE_ID', 1);

------------------------------------------------------

CREATE TABLE COMTN_USER_ABSNCE
(
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	USER_ABSNCE_AT        	char(1)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_USER_ABSNCE_PK PRIMARY KEY (EMPLYR_ID)
);

--------------------------------------------

CREATE TABLE COMTN_UNITY_LINK
(
	UNITY_LINK_ID         	char(20)  		NOT NULL,
	UNITY_LINK_GROUP      	varchar(255)  	NULL,
	UNITY_LINK_SE_CODE    	char(3)  		NULL,
	UNITY_LINK_NM         	varchar(255)  	NULL,
	UNITY_LINK_URL        	varchar(255)  	NULL,
	UNITY_LINK_DC         	varchar(2500)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_UNITY_LINK_PK PRIMARY KEY (UNITY_LINK_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('UNITY_LINK_ID', 1);

----------------------------------------------------

CREATE TABLE COMTN_INDVDL_YRYC_MANAGE
(
	USER_ID               	varchar(20)  	NOT NULL,
	OCCRRNC_YEAR          	char(4)  		NOT NULL,
	YRYC_OCCRRNC_CO       	decimal(5,1)  	NULL,
	USE_YRYC_CO           	decimal(5,1)  	NULL,
	REMNDR_YRYC_CO        	decimal(5,1)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_INDVDL_YRYC_MANAGE_PK PRIMARY KEY (OCCRRNC_YEAR, USER_ID)
);

------------------------------------------------

CREATE TABLE COMTN_VCATN_MANAGE
(
	APPLCNT_ID            	varchar(20)  	NOT NULL,
	VCATN_SE              	char(2)  		NOT NULL,
	NOON_SE               	char(1)  		NULL,
	BGNDE                 	char(8) 	 	NOT NULL,
	ENDDE                 	char(8)  		NOT NULL,
	VCATN_RESN            	varchar(200)  	NOT NULL,
	REQST_DE              	char(8)  		NOT NULL,
	OCCRRNC_YEAR          	char(4)  		NULL,
	SANCTNER_ID           	varchar(20)  	NULL,
	CONFM_AT              	char(1)  		NULL,
	SANCTN_DT             	timestamp  		NULL,
	RETURN_RESN           	varchar(1000)   NULL,
	INFRML_SANCTN_ID      	char(20) 		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_VCATN_MANAGE_PK PRIMARY KEY (APPLCNT_ID, VCATN_SE, BGNDE, ENDDE)
);

-- ==================================================
-- INDVDL

CREATE TABLE COMTN_INDVDL_PGE_CNTNTS
(
	CNTNTS_ID             	varchar(20)  	NOT NULL,
	CNTNTS_NM             	varchar(100)  	NOT NULL,
	CNTC_URL              	varchar(255)  	NOT NULL,
	CNTNTS_USE_AT         	char(1)  		NOT NULL,
	CNTNTS_LINK_URL       	varchar(1000)   NULL,
	CNTNTS_DC             	varchar(250)  	NULL,
	CONSTRAINT  COMTN_INDVDL_PGE_CNTNTS_PK PRIMARY KEY (CNTNTS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNTNTS_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_INDVDL_PGE_ESTBS
(
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	UPEND_IMAGE           	varchar(1024)   NULL,
	TITLEBAR_COLOR        	char(7)  		NULL,
	ALGN_MTHD             	char(1)  		NULL,
	ALGN_CO               	decimal(10)  	NULL,
	CONSTRAINT  COMTN_INDVDL_PGE_ESTBS_PK PRIMARY KEY (EMPLYR_ID)
);

---------------------------------------------

CREATE TABLE COMTN_CNTNTS_LIST
(
	CNTNTS_ID             	varchar(20)  	NOT NULL,
	EMPLYR_ID             	varchar(20)  	NOT NULL,
	CONSTRAINT  COMTN_CNTNTS_LIST_PK PRIMARY KEY (CNTNTS_ID, EMPLYR_ID),
	CONSTRAINT  COMTN_CNTNTS_LIST_FK1 FOREIGN KEY (CNTNTS_ID) REFERENCES COMTN_INDVDL_PGE_CNTNTS(CNTNTS_ID),
	CONSTRAINT  COMTN_CNTNTS_LIST_FK2 FOREIGN KEY (EMPLYR_ID) REFERENCES COMTN_INDVDL_PGE_ESTBS(EMPLYR_ID)
);
CREATE INDEX COMTN_CNTNTS_LIST_i01 ON COMTN_CNTNTS_LIST (CNTNTS_ID  ASC);
CREATE INDEX COMTN_CNTNTS_LIST_i02 ON COMTN_CNTNTS_LIST (EMPLYR_ID  ASC);

-- =======================================
-- HELP

CREATE TABLE COMTN_ADMINISTRATION_WORD
(
	ADMINIST_WORD_ID      	char(20)  		NOT NULL,
	ADMINIST_WORD_NM      	varchar(255)  	NULL,
	ADMINIST_WORD_ENG_NM  	varchar(255)  	NULL,
	ADMINIST_WORD_ABRV_NM  	varchar(255)  	NULL,
	THEMA_RELM            	varchar(255)  	NULL,
	WORD_SE               	varchar(255)  	NULL,
	RELATE_STD_WORD       	varchar(255)  	NULL,
	ADMINIST_WORD_DFN     	varchar(2500)   NULL,
	ADMINIST_WORD_DC      	varchar(4000)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_ADMINISTRATION_WORD_PK PRIMARY KEY (ADMINIST_WORD_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('ADMINIST_WORD_ID', 1);

-----------------------------------------------------

CREATE TABLE COMTN_FAQ_INFO
(
	FAQ_ID                	char(20)  		NOT NULL,
	QESTN_SJ              	varchar(255)  	NULL,
	QESTN_CN              	varchar(2500)   NULL,
	ANSWER_CN             	varchar(2500)   NULL,
	RDCNT                 	decimal(10)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
  	TRGET_ID                char(20)        NULL,
	CONSTRAINT  COMTN_FAQ_INFO_PK PRIMARY KEY (FAQ_ID),
	CONSTRAINT  COMTN_FAQ_INFO_FK1 FOREIGN KEY (ATCH_FILE_ID) REFERENCES COMTN_FILE(ATCH_FILE_ID) ON DELETE SET NULL
);
CREATE INDEX COMTN_FAQ_INFO_i01 ON COMTN_FAQ_INFO (ATCH_FILE_ID  ASC);
CREATE INDEX COMTN_FAQ_INFO_I02 ON COMTN_FAQ_INFO (TRGET_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('FAQ_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_HPCM_INFO
(
	HPCM_ID               	char(20)  		NOT NULL,
	HPCM_SE_CODE          	char(1)  		NULL,
	HPCM_DFN              	varchar(1000)   NULL,
	HPCM_DC               	varchar(2500)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_HPCM_INFO_PK PRIMARY KEY (HPCM_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('HPCM_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_ONLINE_MANUAL
(
	ONLINE_MNL_ID         	char(20)  		NOT NULL,
	ONLINE_MNL_SE_CODE    	char(3)  		NULL,
	ONLINE_MNL_DFN        	text  	NULL,
	ONLINE_MNL_DC         	text  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	ONLINE_MNL_NM         	varchar(255)  	NULL,
	CONSTRAINT  COMTN_ONLINE_MANUAL_PK PRIMARY KEY (ONLINE_MNL_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('ONLINE_MNL_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_QA_INFO
(
	QA_ID                 	char(20)  		NOT NULL,
	QESTN_SJ              	varchar(255)  	NULL,
	QESTN_CN              	varchar(2500)   NULL,
	WRITNG_DE             	char(20)  		NULL,
	RDCNT                 	decimal(10)  	NULL,
	EMAIL_ADRES           	varchar(50)  	NULL,
	QNA_PROCESS_STTUS_CODE  char(1)  		NULL,
	WRTER_NM              	varchar(20)  	NULL,
	ANSWER_CN             	varchar(2500)   NULL,
	WRITNG_PASSWORD       	varchar(20)  	NULL,
	ANSWER_DE             	char(20)  		NULL,
	EMAIL_ANSWER_AT       	char(1)  		NULL,
	AREA_NO               	varchar(4)  	NULL,
	MIDDLE_TELNO          	varchar(4)  	NULL,
	END_TELNO             	varchar(4)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
  	TRGET_ID                char(20)        NULL,
	CONSTRAINT  COMTN_QA_INFO_PK PRIMARY KEY (QA_ID)
);
CREATE INDEX COMTN_QA_INFO_I02 ON COMTN_QA_INFO (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('QNA_ID', 1);

-------------------------------------------------

CREATE TABLE COMTN_WORD_DICARY_INFO
(
	WORD_ID               	char(20)  		NOT NULL,
	WORD_NM               	varchar(255)  	NULL,
	ENG_NM                	varchar(60)  	NULL,
	WORD_DC               	varchar(4000)   NULL,
	SYNONM                	varchar(100)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_WORD_DICARY_INFO_PK PRIMARY KEY (WORD_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('WORD_ID', 1);

---------------------------------------------

CREATE TABLE COMTN_CNSLT_INFO
(
	CNSLT_ID              	char(20)  		NOT NULL,
	CNSLT_SJ              	varchar(255)  	NULL,
	OTHBC_AT              	char(1)  		NULL,
	EMAIL_ADRES           	varchar(50)  	NULL,
	CNSLT_CN              	varchar(2500)   NULL,
	MANAGT_CN             	varchar(2500)   NULL,
	MANAGT_DE             	char(20)  		NULL,
	RDCNT                 	decimal(10)  	NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	AREA_NO               	varchar(4)  	NULL,
	MIDDLE_TELNO          	varchar(4)  	NULL,
	END_TELNO             	varchar(4)  	NULL,
	FRST_MBTLNUM          	varchar(4)  	NULL,
	MIDDLE_MBTLNUM        	varchar(4)  	NULL,
	END_MBTLNUM           	varchar(4)  	NULL,
	WRITNG_DE             	char(20)  		NULL,
	WRTER_NM              	varchar(20)  	NULL,
	EMAIL_ANSWER_AT       	char(1)  		NULL,
	QNA_PROCESS_STTUS_CODE  char(1)  		NULL,
	WRITNG_PASSWORD       	varchar(20)  	NULL,
  	TRGET_ID                char(20)        NULL,
	CONSTRAINT  COMTN_CNSLT_INFO_PK PRIMARY KEY (CNSLT_ID)
);
CREATE INDEX COMTN_CNSLT_INFO_I02 ON COMTN_CNSLT_INFO (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CNSLT_ID', 1);

----------------------------------------------

CREATE TABLE COMTN_MTG_INFO
(
	MTG_ID                	char(20)  		NOT NULL,
	MTG_NM                	varchar(255)  	NULL,
	MTG_MTR_CN            	varchar(1000)   NULL,
	MTG_SN                	decimal(10)  	NULL,
	MTG_CO                	decimal(5)  	NULL,
	MTG_DE                	char(8)  		NULL,
	MTG_PLACE             	varchar(255)  	NULL,
	MTG_BEGIN_TM          	varchar(14)  	NULL,
	MTG_END_TM            	varchar(14)  	NULL,
	CLSDR_MTG_AT          	char(1)  		NULL,
	READNG_BGNDE          	char(8)  		NULL,
	READNG_AT             	char(1)  		NULL,
	MTG_RESULT_CN         	varchar(1000)   NULL,
	MTG_RESULT_ENNC       	char(1)  		NULL,
	ETC_MATTER            	varchar(1000)   NULL,
	MNGT_DEPT_ID          	varchar(20)  	NULL,
	MNAER_ID              	varchar(20)  	NULL,
	MNAER_DEPT_ID         	varchar(20)  	NULL,
	MTG_AT                	char(1)  		NULL,
	NONATDRN_CO           	decimal(10)  	NULL,
	ATDRN_CO              	decimal(10)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
  	TRGET_ID           		char(20)        NULL,
	CONSTRAINT  COMTN_MTG_INFO_PK PRIMARY KEY (MTG_ID)
);
CREATE INDEX COMTN_MTG_INFO_I02 ON COMTN_MTG_INFO (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('MTG_ID', 1);

---------------------------------------------------

CREATE TABLE COMTN_ONLINE_POLL_MANAGE
(
	POLL_ID               	char(20)  		NOT NULL,
	POLL_NM               	varchar(255)  	NULL,
	POLL_BGNDE            	char(8)  		NULL,
	POLL_ENDDE            	char(8)  		NULL,
	POLL_KND              	char(3)  		NULL,
	POLL_DSUSE_ENNC       	char(1)  		NULL,
	POLL_ATMC_DSUSE_ENNC  	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
  	TRGET_ID              	char(20)        NULL,
	CONSTRAINT  COMTN_ONLINE_POLL_MANAGE_PK PRIMARY KEY (POLL_ID)
);
CREATE INDEX COMTN_ONLINE_POLL_MANAGE_I01 ON COMTN_ONLINE_POLL_MANAGE (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('POLL_MGR_ID', 1);

------------------------------------------------------

CREATE TABLE COMTN_ONLINE_POLL_IEM
(
	POLL_IEM_NM           	varchar(255)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	POLL_IEM_ID           	char(20)  		NOT NULL,
	POLL_ID               	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_ONLINE_POLL_IEM_PK PRIMARY KEY (POLL_ID, POLL_IEM_ID),
	CONSTRAINT  COMTN_ONLINE_POLL_IEM_FK1 FOREIGN KEY (POLL_ID) REFERENCES COMTN_ONLINE_POLL_MANAGE(POLL_ID)
);
CREATE INDEX COMTN_ONLINE_POLL_IEM_i01 ON COMTN_ONLINE_POLL_IEM (POLL_ID  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('POLL_IEM_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_ONLINE_POLL_RESULT
(
	POLL_RESULT_ID        	char(20)  		NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	POLL_IEM_ID           	char(20)  		NOT NULL,
	POLL_ID               	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_ONLINE_POLL_RESULT_PK PRIMARY KEY (POLL_RESULT_ID, POLL_IEM_ID, POLL_ID),
	CONSTRAINT  COMTN_ONLINE_POLL_RESULT_FK1 FOREIGN KEY (POLL_ID, POLL_IEM_ID) REFERENCES COMTN_ONLINE_POLL_IEM(POLL_ID, POLL_IEM_ID)
);
CREATE INDEX COMTN_ONLINE_POLL_RESULT_i01 ON COMTN_ONLINE_POLL_RESULT (POLL_IEM_ID ASC, POLL_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('POLL_RESULT_ID', 1);

----------------------------------------

CREATE TABLE COMTN_QUSTNR_TMPLAT
(
	QUSTNR_TMPLAT_ID      		char(20)  		NOT NULL,
	QUSTNR_TMPLAT_TY      		varchar(100)  	NULL,
	QUSTNR_TMPLAT_DC      		varchar(2000)   NULL,
	QUSTNR_TMPLAT_PATH_NM  		varchar(100)  	NULL,
	QUSTNR_TMPLAT_IMAGE_INFO  	bytea  			NULL,
	FRST_REGISTER_ID      		varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     		timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        		varchar(20)  	NULL,
	LAST_UPDT_PNTTM       		timestamp  		NULL,
	CONSTRAINT  COMTN_QUSTNR_TMPLAT_PK PRIMARY KEY (QUSTNR_TMPLAT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('QUSTNR_TMPLAT_ID', 1);

-----------------------------------------

CREATE TABLE COMTN_QESTNR_INFO
(
	QESTNR_ID             		char(20)  		NOT NULL,
	QUSTNR_TMPLAT_ID      		char(20)  		NOT NULL,
	QUSTNR_SJ             		varchar(255)  	NULL,
	QUSTNR_PURPS          		varchar(1000)   NULL,
	QUSTNR_WRITNG_GUIDANCE_CN  	varchar(2000)   NULL,
	QUSTNR_TRGET          		varchar(1000)   NULL,
	QUSTNR_BGNDE          		char(8)  		NULL,
	QUSTNR_ENDDE          		char(8)  		NULL,
	FRST_REGISTER_ID      		varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     		timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        		varchar(20)  	NULL,
	LAST_UPDT_PNTTM       		timestamp  		NULL,
  	TRGET_ID                   	char(20)        NULL,
	CONSTRAINT  COMTN_QESTNR_INFO_PK PRIMARY KEY (QESTNR_ID),
	CONSTRAINT  COMTN_QESTNR_INFO_FK1 FOREIGN KEY (QUSTNR_TMPLAT_ID) REFERENCES COMTN_QUSTNR_TMPLAT(QUSTNR_TMPLAT_ID)
);
CREATE INDEX COMTN_QESTNR_INFO_i01 ON COMTN_QESTNR_INFO (QUSTNR_TMPLAT_ID ASC);
CREATE INDEX COMTN_QESTNR_INFO_I02 ON COMTN_QESTNR_INFO (TRGET_ID);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('QESMANAGE_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_QUSTNR_QESITM
(
	QESTNR_ID             	char(20)  		NOT NULL,
	QUSTNR_QESITM_ID      	char(20)  		NOT NULL,
	QESTN_SN              	decimal(10)  	NULL,
	QESTN_TY_CODE         	char(1)  		NULL,
	QESTN_CN              	varchar(2500)   NULL,
	MXMM_CHOISE_CO        	decimal(5)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_QUSTNR_QESITM_PK PRIMARY KEY (QESTNR_ID, QUSTNR_QESITM_ID),
	CONSTRAINT  COMTN_QUSTNR_QESITM_FK1 FOREIGN KEY (QESTNR_ID) REFERENCES COMTN_QESTNR_INFO(QESTNR_ID)
);
CREATE INDEX COMTN_QUSTNR_QESITM_i02 ON COMTN_QUSTNR_QESITM (QESTNR_ID  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('QESQESTN_ID', 1);

---------------------------------------------

CREATE TABLE COMTN_QUSTNR_IEM
(
	QESTNR_ID             	char(20)  		NOT NULL,
	QUSTNR_QESITM_ID      	char(20)  		NOT NULL,
	QUSTNR_IEM_ID         	varchar(20)  	NOT NULL,
	IEM_SN                	decimal(5)  	NULL,
	IEM_CN                	varchar(1000)   NULL,
	ETC_ANSWER_AT         	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_QUSTNR_IEM_PK PRIMARY KEY (QESTNR_ID, QUSTNR_QESITM_ID, QUSTNR_IEM_ID),
	CONSTRAINT  COMTN_QUSTNR_IEM_FK1 FOREIGN KEY (QESTNR_ID, QUSTNR_QESITM_ID) REFERENCES COMTN_QUSTNR_QESITM(QESTNR_ID, QUSTNR_QESITM_ID)
);
CREATE INDEX COMTN_QUSTNR_IEM_i01 ON COMTN_QUSTNR_IEM (QUSTNR_QESITM_ID ASC, QESTNR_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('QESITM_ID', 1);

-----------------------------------------

CREATE TABLE COMTN_QUSTNR_RSPNS_RESULT
(
	QUSTNR_RSPNS_RESULT_ID  char(20)  		NOT NULL,
	QESTNR_ID             	char(20)  		NOT NULL,
	QUSTNR_QESITM_ID      	char(20)  		NOT NULL,
	QUSTNR_IEM_ID         	varchar(20)  	NULL,
	RESPOND_ANSWER_CN     	varchar(1000)   NULL,
	ETC_ANSWER_CN         	varchar(1000)   NULL,
	RESPOND_NM            	varchar(50)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_QUSTNR_RSPNS_RESULT_PK PRIMARY KEY (QUSTNR_RSPNS_RESULT_ID, QESTNR_ID, QUSTNR_QESITM_ID),
	CONSTRAINT  COMTN_QUSTNR_RSPNS_RESULT_FK1 FOREIGN KEY (QESTNR_ID, QUSTNR_QESITM_ID) REFERENCES COMTN_QUSTNR_QESITM(QESTNR_ID, QUSTNR_QESITM_ID)
);
CREATE INDEX COMTN_QUSTNR_RSPNS_RESULT_i01 ON COMTN_QUSTNR_RSPNS_RESULT (QESTNR_ID ASC, QUSTNR_QESITM_ID ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('QESRPD_ID', 1);

-------------------------------------------------

CREATE TABLE COMTN_QUSTNR_RESPOND_INFO
(
	QESTNR_ID             	char(20)  		NOT NULL,
	QUSTNR_RESPOND_ID     	char(20)  		NOT NULL,
	SEXDSTN_CODE          	char(1)  		NULL,
	OCCP_TY_CODE          	varchar(250)  	NULL,
	RESPOND_NM            	varchar(50)  	NULL,
	BRTHDY                	char(20)  		NULL,
	AREA_NO               	varchar(4)  	NULL,
	MIDDLE_TELNO          	varchar(4)  	NULL,
	END_TELNO             	varchar(4)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_QUSTNR_RESPOND_INFO_PK PRIMARY KEY (QESTNR_ID, QUSTNR_RESPOND_ID),
	CONSTRAINT  COMTN_QUSTNR_RESPOND_INFO_FK1 FOREIGN KEY (QESTNR_ID) REFERENCES COMTN_QESTNR_INFO(QESTNR_ID)
);
CREATE INDEX COMTN_QUSTNR_RESPOND_INFO_i01 ON COMTN_QUSTNR_RESPOND_INFO (QESTNR_ID  ASC);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('QESRSPNS_ID', 1);

-- =================================================
-- CPYRHT

CREATE TABLE COMTN_CPYRHT_INFO
(
	CPYRHT_ID             	char(20)  		NOT NULL,
	CPYRHT_PRTC_POLICY_CN  	varchar(2500)   NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_CPYRHT_INFO_PK PRIMARY KEY (CPYRHT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('CPYRHT_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_INDVDL_INFO_POLICY
(
	INDVDL_INFO_POLICY_ID  		char(20)  		NOT NULL,
	INDVDL_INFO_POLICY_CN  		varchar(2500)  	NULL,
	INDVDL_INFO_POLICY_AGRE_AT  char(1)  		NULL,
	FRST_REGISTER_ID      		varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     		timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        		varchar(20)  	NULL,
	LAST_UPDT_PNTTM       		timestamp  		NULL,
	INDVDL_INFO_POLICY_NM  		varchar(255)  	NULL,
	CONSTRAINT  COMTN_INDVDL_INFO_POLICY_PK PRIMARY KEY (INDVDL_INFO_POLICY_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('INDVDL_INFO_ID', 1);

-------------------------------------------------

CREATE TABLE COMTN_STPLAT_INFO
(
	USE_STPLAT_ID         	char(20)  		NOT NULL,
	USE_STPLAT_NM         	varchar(100)  	NULL,
	USE_STPLAT_CN         	text  	NULL,
	INFO_PROVD_AGRE_CN    	text  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_STPLAT_INFO_PK PRIMARY KEY (USE_STPLAT_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('STPLAT_ID', 1);

-- =============================================
-- DAM

CREATE TABLE COMTN_DAM_MAP_KNO
(
	KNWLDG_TY_CODE        	varchar(3)  	NOT NULL,
	ORGNZT_ID             	varchar(20)  	NULL,
	EXPERT_ID             	varchar(20)  	NULL,
	KNWLDG_TY_NM          	varchar(60)  	NULL,
	CL_DE                 	char(8)  		NULL,
	KNWLDG_URL            	varchar(255)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DAM_MAP_KNO_PK PRIMARY KEY (KNWLDG_TY_CODE)
);

------------------------------------------

CREATE TABLE COMTN_DAM_MAP_TEAM
(
	ORGNZT_ID             	varchar(20)  	NOT NULL,
	ORGNZT_NM             	varchar(20)  	NULL,
	CL_DE                 	char(20)  		NULL,
	KNWLDG_URL            	varchar(255)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DAM_MAP_TEAM_PK PRIMARY KEY (ORGNZT_ID)
);

--------------------------------------------

CREATE TABLE COMTN_DAM_CALRES
(
	KNWLDG_ID             	char(20)  		NOT NULL,
	ORGNZT_ID             	varchar(20)  	NULL,
	EXPERT_ID             	varchar(20)  	NULL,
	KNWLDG_TY_CODE        	varchar(3)  	NULL,
	EMPLYR_ID             	varchar(20)  	NULL,
	KNWLDG_NM             	varchar(60)  	NULL,
	KNWLDG_CN             	varchar(2500)  	NULL,
	ATCH_FILE_ID          	varchar(20)  	NULL,
	PARNTS_KNWLDG_ID      	varchar(20)  	NULL,
	THREAD_DEPTH           	decimal(20)  	NULL,
	THREAD_NO           	decimal(20)  	NULL,
	THREAD_GROUP_NO       	decimal(20)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DAM_CALRES_PK PRIMARY KEY (KNWLDG_ID)
);

---------------------------------------------

CREATE TABLE COMTN_DAM_PRO
(
	EXPERT_ID             	varchar(20)  	NOT NULL,
	KNWLDG_TY_CODE        	varchar(3)  	NOT NULL,
	EXPERT_GRAD           	char(1)  		NOT NULL,
	EXPERT_CONFM_DE       	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	EXPERT_DC             	varchar(2000)   NULL,
	CONSTRAINT  COMTN_DAM_PRO_PK PRIMARY KEY (EXPERT_ID, KNWLDG_TY_CODE, EXPERT_GRAD),
	CONSTRAINT  COMTN_DAM_PRO_FK1 FOREIGN KEY (KNWLDG_TY_CODE) REFERENCES COMTN_DAM_MAP_KNO(KNWLDG_TY_CODE)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('DAM_ID', 1);

------------------------------------------

CREATE TABLE COMTN_DAM_KNOIFM
(
	KNWLDG_ID             	varchar(20)  	NOT NULL,
	KNWLDG_TY_CODE        	varchar(3)  	NULL,
	ORGNZT_ID             	varchar(20)  	NULL,
	EXPERT_ID             	varchar(20)  	NULL,
	EMPLYR_ID             	varchar(20)  	NULL,
	KNWLDG_NM             	varchar(60)  	NULL,
	KNWLDG_CN             	varchar(2500)   NULL,
	KWRD                  	varchar(100)  	NULL,
	OTHBC_AT              	char(1)  		NULL,
	KNWLDG_EVL            	char(1)  		NULL,
	COLCT_DE              	char(8)  		NULL,
	EVL_DE                	char(8)  		NULL,
	ATCH_FILE_ID          	char(20)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	DSUSE_DE              	char(8)  		NULL,
	CONSTRAINT  COMTN_DAM_KNOIFM_PK PRIMARY KEY (KNWLDG_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('KNO_ID', 1);

-- ======================================
-- MNTRNG

CREATE TABLE COMTN_DB_MNTRNG
(
	DATA_SOURC_NM         	varchar(60)  	NOT NULL,
	SERVER_NM             	varchar(60)  	NULL,
	DBMS_KND              	varchar(2)  	NULL,
	CECK_SQL              	varchar(250)  	NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_DB_MNTRNG_PK PRIMARY KEY (DATA_SOURC_NM)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('DB_MNTRNG_LOG_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_DB_MNTRNG_LOG
(
	DATA_SOURC_NM         	varchar(60)  	NOT NULL,
	SERVER_NM             	varchar(60)  	NULL,
	DBMS_KND              	varchar(2)  	NULL,
	CECK_SQL              	varchar(250)  	NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	LOG_INFO              	varchar(2000)   NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	LOG_ID                	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_DB_MNTRNG_LOG_PK PRIMARY KEY (LOG_ID)
);

-------------------------------------------

CREATE TABLE COMTN_FILESYS_MNTRNG
(
	FILE_SYS_ID           	char(20)  		NOT NULL,
	FILE_SYS_NM           	varchar(60)  	NOT NULL,
	FILE_SYS_MANAGE_NM    	varchar(255)  	NOT NULL,
	FILE_SYS_SIZE         	decimal(8)  	NOT NULL,
	FILE_SYS_THRHLD       	decimal(8)  	NOT NULL,
	FILE_SYS_USGQTY       	decimal(8)  	NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_FILESYS_MNTRNG_PK PRIMARY KEY (FILE_SYS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('FILESYS_MNTRNG', 1);

-----------------------------------------

CREATE TABLE COMTN_FILESYS_MNTRNG_LOG
(
	FILE_SYS_ID          	char(20)  		NOT NULL,
	FILE_SYS_NM           	varchar(60)  	NOT NULL,
	FILE_SYS_MANAGE_NM    	varchar(255)  	NOT NULL,
	FILE_SYS_SIZE         	decimal(8)  	NOT NULL,
	FILE_SYS_THRHLD       	decimal(8)  	NOT NULL,
	FILE_SYS_USGQTY       	decimal(8)  	NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	LOG_INFO              	varchar(2000)   NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	LOG_ID                	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_FILESYS_MNTRNG_LOG_PK PRIMARY KEY (FILE_SYS_ID, LOG_ID),
	CONSTRAINT  COMTN_FILESYS_MNTRNG_LOG_FK2 FOREIGN KEY (FILE_SYS_ID) REFERENCES COMTN_FILESYS_MNTRNG(FILE_SYS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('FILESYS_LOG_ID', 1);

----------------------------------------------

CREATE TABLE COMTN_HTTP_MON
(
	SYS_ID                	varchar(20)  	NOT NULL,
	SITE_URL              	varchar(100)  	NULL,
	WEBSVC_KND            	varchar(10)  	NULL,
	HTTP_STTUS_CODE       	varchar(3)  	NULL,
	CREAT_DT              	timestamp  		NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_HTTP_MON_PK PRIMARY KEY (SYS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('HTTP_ID', 1);

-------------------------------------------

CREATE TABLE COMTN_HTTP_MON_LOG
(
	SYS_ID                	varchar(20)  	NOT NULL,
	SITE_URL              	varchar(100)  	NULL,
	WEBSVC_KND            	varchar(10)  	NULL,
	HTTP_STTUS_CODE       	varchar(3)  	NULL,
	CREAT_DT              	timestamp  		NULL,
	LOG_INFO              	varchar(2000)   NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	LOG_ID                	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_HTTP_MON_LOG_PK PRIMARY KEY (SYS_ID, LOG_ID),
	CONSTRAINT  COMTN_HTTP_MON_LOG_FK2 FOREIGN KEY (SYS_ID) REFERENCES COMTN_HTTP_MON(SYS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('HTTP_LOG_ID', 1);

--------------------------------------------

CREATE TABLE COMTN_NTWRK_SVC_MNTRNG
(
	SYS_IP                	varchar(23)  	NOT NULL,
	SYS_PORT              	decimal(5)  	NOT NULL,
	SYS_NM                	varchar(255)  	NOT NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_NTWRK_SVC_MNTRNG_PK PRIMARY KEY (SYS_IP, SYS_PORT)
);

---------------------------------------------

CREATE TABLE COMTN_NTWRK_SVC_MNTRNG_LOG
(
	SYS_IP                	varchar(23)  	NOT NULL,
	SYS_PORT              	decimal(5)  	NOT NULL,
	SYS_NM                	varchar(255)  	NOT NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	LOG_INFO              	varchar(2000)   NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	LOG_ID                	char(20)  		NOT NULL ,
	CONSTRAINT  COMTN_NTWRK_SVC_MNTRNG_LOG_PK PRIMARY KEY (SYS_IP, SYS_PORT, LOG_ID),
	CONSTRAINT  COMTN_NTWRK_SVC_MNTRNG_LOG_FK2 FOREIGN KEY (SYS_IP, SYS_PORT) REFERENCES COMTN_NTWRK_SVC_MNTRNG(SYS_IP, SYS_PORT)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('NTWRK_LOG_ID', 1);

----------------------------------------------

CREATE TABLE COMTN_PROCESS_MON
(
	PROCS_ID              	char(20)  		NOT NULL,
	PROCS_NM              	varchar(60)  	NULL,
	PROCS_STTUS           	varchar(3)  	NULL,
	CREAT_DT              	timestamp  		NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_PROCESS_MON_PK PRIMARY KEY (PROCS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('PROC_ID', 1);

-------------------------------------------------

CREATE TABLE COMTN_PROCESS_MON_LOG
(
	PROCS_ID              	char(20)  		NOT NULL,
	PROCS_NM              	varchar(60)  	NULL,
	PROCS_STTUS           	varchar(3)  	NULL,
	CREAT_DT              	timestamp  		NULL,
	LOG_INFO              	varchar(2000)   NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	LOG_ID                	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_PROCESS_MON_LOG_PK PRIMARY KEY (PROCS_ID, LOG_ID),
	CONSTRAINT  COMTN_PROCESS_MON_LOG_FK2 FOREIGN KEY (PROCS_ID) REFERENCES COMTN_PROCESS_MON(PROCS_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('PROC_LOG_ID', 1);

------------------------------------------------------

CREATE TABLE COMTN_PROXY_INFO
(
	PROXY_ID              	char(20)  		NOT NULL,
	PROXY_NM              	varchar(60)  	NULL,
	PROXY_IP              	varchar(23)  	NULL,
	PROXY_PORT            	varchar(10)  	NULL,
	TRGET_SVC_NM          	varchar(255)  	NULL,
	SVC_DC                	varchar(2000)   NULL,
	SVC_IP                	varchar(23)  	NULL,
	SVC_PORT              	varchar(10)  	NULL,
	SVC_STTUS             	char(2)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_PROXY_INFO_PK PRIMARY KEY (PROXY_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('PROXY_ID', 1);

-------------------------------------------------------

CREATE TABLE COMTN_PROXY_LOG
(
	PROXY_ID              	char(20)  		NOT NULL,
	CLNT_IP               	varchar(23)  	NULL,
	CLNT_PORT             	varchar(10)  	NULL,
	CONECT_TIME           	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	LOG_ID                	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_PROXY_LOG_PK PRIMARY KEY (PROXY_ID, LOG_ID),
	CONSTRAINT  COMTN_PROXY_LOG_FK1 FOREIGN KEY (PROXY_ID) REFERENCES COMTN_PROXY_INFO(PROXY_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('PROXY_LOG_ID', 1);

--------------------------------------------------

CREATE TABLE COMTN_SERVER_INFO
(
	SERVER_ID             	char(20)  		NOT NULL,
	SERVER_NM             	varchar(60)  	NULL,
	SERVER_KND            	char(2)  		NULL,
	RGSDE                 	char(8)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_SERVER_INFO_PK PRIMARY KEY (SERVER_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SERVER_ID', 1);

---------------------------------------------------

CREATE TABLE COMTN_SERVER_EQPMN_INFO
(
	SERVER_EQPMN_ID       	varchar(20)  	NOT NULL,
	SERVER_EQPMN_NM       	varchar(60)  	NULL,
	SERVER_EQPMN_IP       	varchar(23) 	NULL,
	SERVER_EQPMN_MNGR     	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	OPERSYSM_INFO         	varchar(2000)   NULL,
	CPU_INFO              	varchar(2000)   NULL,
	MORY_INFO             	varchar(2000)   NULL,
	HDDISK                	char(18)  		NULL,
	ETC_INFO              	varchar(250)  	NULL,
	RGSDE                 	char(8)			NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_SERVER_EQPMN_INFO_PK PRIMARY KEY (SERVER_EQPMN_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SEVEQ_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_SERVER_EQPMN_RELATE
(
	SERVER_EQPMN_ID       	varchar(20)  	NOT NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	SERVER_ID             	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_SERVER_EQPMN_RELATE_PK PRIMARY KEY (SERVER_EQPMN_ID, SERVER_ID),
	CONSTRAINT  COMTN_SERVER_EQPMN_RELATE_FK2 FOREIGN KEY (SERVER_EQPMN_ID) REFERENCES COMTN_SERVER_EQPMN_INFO(SERVER_EQPMN_ID),
	CONSTRAINT  COMTN_SERVER_EQPMN_RELATE_FK1 FOREIGN KEY (SERVER_ID) REFERENCES COMTN_SERVER_INFO(SERVER_ID)
);

----------------------------------------------

CREATE TABLE COMTN_SERVER_RESRCE_LOG
(
	SERVER_EQPMN_ID       	varchar(20)  	NOT NULL,
	CPU_USE_RT            	decimal(3)  	NULL,
	MORY_USE_RT           	decimal(3)  	NULL,
	SVC_STTUS             	char(2)  		NULL,
	LOG_INFO              	varchar(2000)   NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	SERVER_ID             	char(20)  		NOT NULL,
	LOG_ID                	char(20)  		NOT NULL,
	CONSTRAINT  COMTN_SERVER_RESRCE_LOG_PK PRIMARY KEY (SERVER_EQPMN_ID, SERVER_ID, LOG_ID),
	CONSTRAINT  COMTN_SERVER_RESRCE_LOG_FK1 FOREIGN KEY (SERVER_EQPMN_ID, SERVER_ID) REFERENCES COMTN_SERVER_EQPMN_RELATE(SERVER_EQPMN_ID, SERVER_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SVR_MNTRNG_LOG_ID', 1);

------------------------------------------------

CREATE TABLE COMTN_SYNCHRN_SERVER_INFO
(
	SERVER_ID             	char(20)  		NOT NULL,
	SERVER_NM             	varchar(60)  	NULL,
	SERVER_IP             	varchar(23)  	NULL,
	SERVER_PORT           	varchar(10)  	NULL,
	FTP_ID                	varchar(20)  	NULL,
	FTP_PASSWORD          	varchar(20)  	NULL,
	SYNCHRN_LC            	varchar(255)  	NULL,
	REFLCT_AT             	char(1)  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_SYNCHRN_SERVER_INFO_PK PRIMARY KEY (SERVER_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('SYNCHRN_SERVER_ID', 1);

-----------------------------------------------

CREATE TABLE COMTN_TRSMRCV_MNTRNG
(
	CNTC_ID               	char(8)  		NOT NULL,
	TEST_CLASS_NM         	varchar(255)  	NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50) 	NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_TRSMRCV_MNTRNG_PK PRIMARY KEY (CNTC_ID)
);

----------------------------------------------

CREATE TABLE COMTN_TRSMRCV_MNTRNG_LOG
(
	LOG_ID                	char(20)  		NOT NULL,
	CNTC_ID               	char(8)  		NOT NULL,
	TEST_CLASS_NM         	varchar(255)  	NULL,
	MNGR_NM               	varchar(60)  	NULL,
	MNGR_EMAIL_ADRES      	varchar(50)  	NULL,
	MNTRNG_STTUS          	char(2)  		NULL,
	LOG_INFO              	varchar(2000)   NULL,
	CREAT_DT              	timestamp  		NULL,
	FRST_REGISTER_ID      	varchar(20)  	NOT NULL,
	FRST_REGIST_PNTTM     	timestamp  		NOT	NULL,
	LAST_UPDUSR_ID        	varchar(20)  	NULL,
	LAST_UPDT_PNTTM       	timestamp  		NULL,
	CONSTRAINT  COMTN_TRSMRCV_MNTRNG_LOG_PK PRIMARY KEY (LOG_ID)
);

INSERT INTO COMTE_COPSEQ ( TABLE_NAME, NEXT_ID ) VALUES ('TR_MNTRNG_LOG_ID', 1);

