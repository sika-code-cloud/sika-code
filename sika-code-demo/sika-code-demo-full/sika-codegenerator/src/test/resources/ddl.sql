CREATE TABLE "T_D0_SUSPEND_CONFIG" (
                                       "USERCODE" varchar(64) NOT NULL,
                                       "MCH_ID" varchar(64) NOT NULL,
                                       "MCH_NAME" varchar(255),
                                       "ORG_NO" varchar(64),
                                       "CURRENCY" varchar(3) NOT NULL,
                                       "SUSPEND_AMOUNT" int8,
                                       "HAS_SUSPEND_AMOUNT" int8,
                                       "SUSPEND_ACTUAL_PAID_AMOUNT" int8,
                                       "SETTLE_METHOD" varchar(2) NOT NULL,
                                       "REMARK" varchar(255),
                                       "STATUS" varchar(2) NOT NULL,
                                       "RECOVER_TIME" timestamp,
                                       "CREATE_TIME" timestamp NOT NULL,
                                       "CREATE_ID" varchar(64),
                                       "UPDATE_TIME" timestamp NOT NULL,
                                       "UPDATE_ID" varchar(64) DEFAULT '',
                                       "DEL_FLAG" char(1) NOT NULL DEFAULT N,
                                       PRIMARY KEY ("USERCODE")
);
CREATE UNIQUE INDEX "idx_mch_id" ON "T_D0_SUSPEND_CONFIG" (
                                                           "MCH_ID"
    );
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."MCH_ID" IS '商户号';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."MCH_NAME" IS '商户名称-冗余字段';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."ORG_NO" IS '所属机构';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."CURRENCY" IS '币种-默认CNY';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."SUSPEND_AMOUNT" IS '代付暂缓金额(分)';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."HAS_SUSPEND_AMOUNT" IS '已暂缓交易金额(分)';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."SUSPEND_ACTUAL_PAID_AMOUNT" IS '暂缓实付金额累计(分)-废弃';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."SETTLE_METHOD" IS '清算方式 1 D0付款 ';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."REMARK" IS '暂缓/恢复暂缓原因备注';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."STATUS" IS '商户暂缓状态 10暂缓 11已恢复';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."RECOVER_TIME" IS '恢复时间';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."CREATE_ID" IS '创建人ID';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."UPDATE_ID" IS '更新人ID';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG"."DEL_FLAG" IS '删除标识 Y删除 N正常';

CREATE TABLE "T_D0_SUSPEND_CONFIG_CHECK" (
                                             "ID" numeric(1000,53) NOT NULL,
                                             "CUSTID" varchar(16) NOT NULL,
                                             "CUSTNAME" varchar(64) NOT NULL,
                                             "USERCODE" varchar(20) NOT NULL,
                                             "ORG_NO" varchar(10) NOT NULL,
                                             "CHECK_RESULT" char(2),
                                             "OPTION_TYPE" varchar(2) NOT NULL,
                                             "SUSPEND_AMOUNT" numeric(15,2) NOT NULL,
                                             "HAS_SUSPEND_AMOUNT" numeric(15,2) NOT NULL,
                                             "SETTLE_STYLE" char(1) NOT NULL,
                                             "OPERATOR" varchar(20) NOT NULL,
                                             "AUDITOR" varchar(20),
                                             "CREATE_TIME" timestamp NOT NULL,
                                             "UPDATE_TIME" timestamp,
                                             "AUDIT_TIME" timestamp,
                                             "REMARK" varchar(100) NOT NULL,
                                             "DEL_FLAG" char(1) NOT NULL DEFAULT N,
                                             CONSTRAINT "PK_D0_SUS_CONF_CK" PRIMARY KEY ("ID"),
                                             CONSTRAINT "SYS_C0024138" CHECK ("ID" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024139" CHECK ("CUSTID" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024140" CHECK ("CUSTNAME" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024141" CHECK ("USERCODE" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024142" CHECK ("ORG_NO" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024144" CHECK ("OPTION_TYPE" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024145" CHECK ("SUSPEND_AMOUNT" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024146" CHECK ("HAS_SUSPEND_AMOUNT" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024147" CHECK ("SETTLE_STYLE" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024148" CHECK ("OPERATOR" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024149" CHECK ("CREATE_TIME" IS NOT NULL),
                                             CONSTRAINT "SYS_C0024150" CHECK ("REMARK" IS NOT NULL)
);
ALTER TABLE "T_D0_SUSPEND_CONFIG_CHECK" OWNER TO "UNION_ORDER";
CREATE INDEX "IDX_D0_SUS_CONF_CK_CREATE_TIME" ON "T_D0_SUSPEND_CONFIG_CHECK" (
                                                                              "CREATE_TIME" ASC
    );
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."ID" IS '主键,自增';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."CUSTID" IS '客户编号';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."CUSTNAME" IS '商户名称';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."USERCODE" IS '商户号';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."ORG_NO" IS '商户所属机构';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."CHECK_RESULT" IS '审核结果：00：通过 01：不通过';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."OPTION_TYPE" IS '操作类型10：待审核新增；11：待审核恢复； ';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."SUSPEND_AMOUNT" IS '暂缓金额(单位:元)';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."HAS_SUSPEND_AMOUNT" IS '已暂缓交易金额累计(单位:元)';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."SETTLE_STYLE" IS '清算方式:1：D0付款';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."OPERATOR" IS '操作人';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."AUDITOR" IS '审核人';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."AUDIT_TIME" IS '审核时间';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."REMARK" IS '暂缓备注/申请恢复备注';
COMMENT ON COLUMN "T_D0_SUSPEND_CONFIG_CHECK"."DEL_FLAG" IS '删除标识 Y删除 N正常';

DROP INDEX "idx_mch_id";
CREATE UNIQUE INDEX "idx_mch_id" ON "T_D0_SUSPEND_CONFIG" (
                                                           "MCH_ID"
    );

