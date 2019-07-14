<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="ResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName ? uncap_first}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName ? uncap_first}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName ? uncap_first}" />
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="columnList">
        <#list table.commonFields as field>${field.name}, </#list>${table.fieldNames}
    </sql>

</#if>
    <!-- 根据查询条件获取列表信息 -->
    <select id="listByQuery" resultMap="ResultMap" parameterType="${package.Query}.${table.classBodyName}Query" >
        SELECT <include refid="columnList" />
        FROM ${table.name}
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
    </select>

    <!-- 根据查询条件获取Id列表信息 -->
    <select id="listIdByQuery" resultType="java.lang.Long" parameterType="${package.Query}.${table.classBodyName}Query" >
        SELECT id
        FROM ${table.name}
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
    </select>

    <!-- 根据查询条件获取实体信息 -->
    <select id="findByQuery" resultMap="ResultMap" parameterType="${package.Query}.${table.classBodyName}Query" >
        SELECT <include refid="columnList" />
        FROM ${table.name}
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
        LIMIT 1
    </select>

    <!-- 根据查询条件获取表id -->
    <select id="findIdByQuery" resultType="java.lang.Long" parameterType="${package.Query}.${table.classBodyName}Query" >
        SELECT id
        FROM ${table.name}
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
        LIMIT 1
    </select>

    <!-- 根据查询条件获取分页信息 -->
    <select id="pageByQuery" resultMap="ResultMap" parameterType="${package.Query}.${table.classBodyName}Query" >
        SELECT <include refid="columnList" />
        FROM ${table.name}
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
        <include refid="order_by_sql"/>
    </select>

    <!-- 根据查询条件获取总数量信息 -->
    <select id="totalCountByQuery" resultType="Integer" parameterType="${package.Query}.${table.classBodyName}Query" >
        SELECT count(*)
        FROM ${table.name}
        <where>
            is_deleted = 0
            <include refid="query_sql" />
        </where>
    </select>

    <!-- 根据查询条件SQL -->
    <sql id="query_sql" >
        <if test="query.${table.classBodyName?uncap_first}Id != null">AND id = ${r"#{"}query.${table.classBodyName?uncap_first}Id${r"}"}</if>
        <#list table.fields as field>
        <#if !field.keyFlag>
        <if test="query.${field.propertyName ? uncap_first} != null">AND ${field.name} = ${r"#{"}query.${field.propertyName ? uncap_first}${r"}"}</if>
        </#if>
        </#list>
    </sql>

    <!-- 排序的sql -->
    <sql id="order_by_sql">
        <if test="query.sortColumn != null and query.sortType != null" >
            ORDER BY
            <include refid="order_by_column_sql"/>
            <include refid="order_by_type_sql"/>
        </if>
    </sql>

    <!-- 排序列名的sql -->
    <sql id="order_by_column_sql">
        <choose>
            <when test="query.sortColumn == '${table.classBodyName?uncap_first}Id'">
                id
            </when>
            <otherwise>
                id
            </otherwise>
        </choose>
    </sql>

    <!-- 排序类型的sql -->
    <sql id="order_by_type_sql">
        <choose>
            <when test="query.sortType == 'DESC'">
                DESC
            </when>
            <otherwise>
                ASC
            </otherwise>
        </choose>
    </sql>
</mapper>
