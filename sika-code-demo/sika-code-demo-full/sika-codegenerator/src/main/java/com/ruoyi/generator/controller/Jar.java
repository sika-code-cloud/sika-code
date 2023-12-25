package com.ruoyi.generator.controller;

public class Jar {

    private String groupId;

    private String artifactId;

    private String version;

    private String desc;

    private String type;

    /**
     * ALTER TABLE IF EXISTS pay.t_pay_transaction
     *     OWNER to postgres;
     */
    private boolean fromParent;

    public boolean isFromParent() {
        return fromParent;
    }

    public void setFromParent(boolean fromParent) {
        this.fromParent = fromParent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
