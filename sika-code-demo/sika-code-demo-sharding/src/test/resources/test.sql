DROP TABLE IF EXISTS t_cbs_fee_config_template_tableidx;
drop sequence if exists t_cbs_fee_config_template_tableidx_id_seq;
CREATE SEQUENCE t_cbs_fee_config_template_tableidx_id_seq START 1;
CREATE TABLE "t_cbs_fee_config_template_tableidx"
(
    "id"                       int8         NOT NULL DEFAULT nextval('t_cbs_fee_config_template_tableidx_id_seq'::regclass),
    "old_primary_key"          varchar(128),
    "fee_config_template_no"   varchar(32)  NOT NULL,
    "fee_config_template_name" varchar(128) NOT NULL,
    "template_ownership_no"    varchar(128) NOT NULL,
    "template_type"            int4         NOT NULL,
    "old_update_time"          timestamp(3),
    "source"                   varchar(8)   NOT NULL,
    "version"                  int4         NOT NULL,
    "create_time"              timestamp(3) NOT NULL,
    "creator_id"               varchar(20)  NOT NULL,
    "creator_name"             varchar(128) NOT NULL,
    "update_time"              timestamp(3),
    "updator_id"               varchar(20),
    "updator_name"             varchar(128),
    "physical_state"           int8         NOT NULL DEFAULT 1,
    "remark"                   varchar(256),
    "remark1"                  varchar(256),
    "remark2"                  int4,
    "remark3"                  varchar(256),
    "remark4"                  int4,
    CONSTRAINT "t_cbs_fee_config_template_tableidx_pkey" PRIMARY KEY ("id")
)
;

CREATE UNIQUE INDEX "uk_t_cbs_fee_config_template_tableidx_old_primary_key" ON "t_cbs_fee_config_template_tableidx" USING btree (
    "old_primary_key","physical_state"
    );
CREATE UNIQUE INDEX "uk_t_cbs_fee_config_template_tableidx_fee_config_template_no" ON "t_cbs_fee_config_template_tableidx" USING btree (
    "fee_config_template_no"
    );
CREATE INDEX "idx_t_cbs_fee_config_template_tableidx_update_time" ON "t_cbs_fee_config_template_tableidx" USING btree (
    "update_time"
    );
COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."id" IS '物理主键ID';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."old_primary_key" IS '源端主键ID（旧系统）';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."fee_config_template_no" IS '计费配置模版编号';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."fee_config_template_name" IS '计费配置模版名称';
COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."template_type" IS '模板类型';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."old_update_time" IS '源端修改时间';
COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."source" IS '系统来源';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."version" IS '版本号';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."create_time" IS '创建时间';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."creator_id" IS '创建人ID';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."creator_name" IS '创建人名称';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."update_time" IS '修改时间';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."updator_id" IS '修改人ID';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."updator_name" IS '修改人名称';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."physical_state" IS '逻辑删除标识 1-正常，逻辑删除时值设置为ID';

COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."remark" IS '备注-可以用来表示数据来源';
COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."remark1" IS '备用字段1';
COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."remark2" IS '备用字段2';
COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."remark3" IS '备用字段3';
COMMENT
ON COLUMN "t_cbs_fee_config_template_tableidx"."remark4" IS '备用字段4';

COMMENT
ON TABLE "t_cbs_fee_config_template_tableidx" IS '计费配置模版定义表';