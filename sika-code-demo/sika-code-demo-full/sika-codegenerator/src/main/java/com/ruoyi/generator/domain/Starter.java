package com.ruoyi.generator.domain;

import com.ruoyi.generator.controller.Jar;

import java.util.List;

public class Starter {

    private String projType;

    private String projName;

    private String projDesc;

    private String groupId;

    /**
     * mysql、postgres、oracle.
     */
    private String dbtype;

    /**
     * 作者
     */
    private String author;

    private String artifactId;

    private String ddl;

    private String dml;

    private String version="1.0.0";

    private List<Jar> jars;

    private String apolloId;

    private String ignorePrefix;

    public String getIgnorePrefix() {
        return ignorePrefix;
    }

    public void setIgnorePrefix(String ignorePrefix) {
        this.ignorePrefix = ignorePrefix;
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getApolloId() {
        return apolloId;
    }

    public void setApolloId(String apolloId) {
        this.apolloId = apolloId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Jar> getJars() {
        return jars;
    }

    public void setJars(List<Jar> jars) {
        this.jars = jars;
    }

    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjDesc() {
        return projDesc;
    }

    public void setProjDesc(String projDesc) {
        this.projDesc = projDesc;
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

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public String getDml() {
        return dml;
    }

    public void setDml(String dml) {
        this.dml = dml;
    }
}
