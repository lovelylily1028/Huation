-- 서버 시작시 스크립트 바로 실행
-- 테이블 스키마

--------------------------------------------------------
--  DDL for Table YJ_USER_BOARD
--------------------------------------------------------
  CREATE TABLE IF NOT EXISTS YJ_USER_BOARD (
  "USER_ID" VARCHAR2(30 BYTE),
  "PW" 	  VARCHAR2(50 BYTE),
  "USER_NAME" VARCHAR2(100 BYTE),
  "EMAIL"		VARCHAR2(100 BYTE),
  "EMAIL_ADR" VARCHAR2(100 BYTE)
  );

--------------------------------------------------------
--  DDL for Table YJ_NOTICE_BOARD
--------------------------------------------------------
  CREATE TABLE IF NOT EXISTS YJ_NOTICE_BOARD
   ("USER_ID" VARCHAR2(30 BYTE), 
	"BID" VARCHAR2(30 BYTE), 
	"TITLE" VARCHAR2(100 BYTE), 
	"BCONTENTS" VARCHAR2(1000 BYTE), 
	"FILENAME" VARCHAR2(100 BYTE), 
	"DAY" DATE DEFAULT NULL, 
	"HIT" NUMBER, 
	"BGROUP" NUMBER, 
	"BSTEP" NUMBER, 
	"BINDENT" NUMBER
   );
   
--------------------------------------------------------
--  DDL for Sequence TESTBOARD
--------------------------------------------------------
   CREATE SEQUENCE IF NOT EXISTS TESTBOARD
   START WITH 1
   INCREMENT BY 1
   MINVALUE 1 
   NOMAXVALUE
   CACHE 20;  
   
   
--------------------------------------------------------
--  DDL for Table YJ_AJAX_BOARD
--------------------------------------------------------

  CREATE TABLE IF NOT EXISTS YJ_AJAX_BOARD
   ("CODE" NUMBER, 
	"ORIGINNO" NUMBER, 
	"TITLE" VARCHAR2(250 BYTE), 
	"ACONTENT" CLOB, 
	"WRITER" VARCHAR2(50 BYTE), 
	"REG_DATETIME" DATE, 
	"HIT" VARCHAR2(50 BYTE), 
	"GROUPORD" NUMBER
   );
   
--------------------------------------------------------
--  DDL for Sequence A_SEQ
--------------------------------------------------------

   CREATE SEQUENCE IF NOT EXISTS A_SEQ
   START WITH 1
   INCREMENT BY 1
   MINVALUE 1
   NOMAXVALUE
   NOCACHE;

--------------------------------------------------------
--  DDL for Table YJ_BCOMMENT
--------------------------------------------------------

  CREATE TABLE IF NOT EXISTS YJ_BCOMMENT
   ("BID" VARCHAR2(30 BYTE), 
	"CID" VARCHAR2(30 BYTE), 
	"USER_ID" VARCHAR2(100 BYTE), 
	"RECONTENT" VARCHAR2(100 BYTE), 
	"CDATE" DATE, 
	"CGROUP" NUMBER, 
	"STEP" NUMBER DEFAULT 1, 
	"INDENT" NUMBER
   );
   
--------------------------------------------------------
--  DDL for Sequence CBOARD
--------------------------------------------------------

   CREATE SEQUENCE IF NOT EXISTS CBOARD
   START WITH 1
   INCREMENT BY 1
   MINVALUE 1
   NOMAXVALUE
   CACHE 20;
   
--------------------------------------------------------
--  DDL for Table YJ_AJAX_COMMENT
--------------------------------------------------------

  CREATE TABLE IF NOT EXISTS YJ_AJAX_COMMENT
   ("CODE" NUMBER, 
	"CCODE" NUMBER DEFAULT 0, 
	"WRITER" VARCHAR2(50 BYTE) DEFAULT NULL, 
	"RECONTENT" CLOB, 
	"REG_DATE" DATE DEFAULT NULL
   ); 
   
--------------------------------------------------------
--  DDL for Sequence RE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE IF NOT EXISTS RE_SEQ
   START WITH 1
   INCREMENT BY 1
   MINVALUE 1
   NOMAXVALUE
   NOCACHE;
   