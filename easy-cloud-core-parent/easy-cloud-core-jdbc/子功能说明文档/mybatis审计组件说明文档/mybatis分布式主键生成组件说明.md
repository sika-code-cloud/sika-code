# mybatis分布式主键生成策略使用教程

### 项目说明

​	原因：数据库在分表分库时使用id自增会导致主键冲突

​	目的：生成可用于分表分库生成唯一的主键

​	环境：mybatis、springboot、jdk1.8极其以上版本等等、数据数据类需要放在dao或者repository或者 mapper包或者子包下，主键生成策略属于审计功能的子功能，因此需要与审计功能的支持

### 使用须知

1. ##### 引入组件

   使用该组件需要导入easy-cloud-core和easy-cloud-core-jdbc

   maven引入示例

   ```java
   <dependency>
       <groupId>easy.cloud.core</groupId>
       <artifactId>easy-cloud-core</artifactId>
       <optional>true</optional>
       <version>1.0.0</version>
   </dependency>
   <dependency>
       <groupId>easy.cloud.core</groupId>
       <artifactId>easy-cloud-core-jdbc</artifactId>
       <optional>true</optional>
       <version>1.0.0</version>
   </dependency>
   ```

2. ##### 其他需要引入的依赖见源码pom

### 基础教程

1. 持久化实体关键代码

   ```java
   import javax.persistence.Id;
   import com.easy.cloud.core.jdbc.primarykey.annotation.EcGenericGenerator;
   import com.easy.cloud.core.jdbc.primarykey.snowflake.EcPrimaryKeySnowflakeGenerator;
   @Id
   // 添加主键生成策略注解，指定生成主键的类class（示例为采用了雪花算法）
   @EcGenericGenerator(primaryKeyGeneratorClass = EcPrimaryKeySnowflakeGenerator.class)
   private Long id;
   ```

2. 在保存和更新的接口上添加EcAuditAnnotation注解，只会对EcType.SAVE类型生效

   ```java
   @EcAuditAnnotation(actionType = EcActionType.SAVE, type = EcType.SAVE)
   public int save(final T obj);
   /** 批量保存 */
   @EcAuditAnnotation(actionType = EcActionType.SAVE_BATCH, type = EcType.SAVE)
   public int saveBatch(@Param("entitys") final List<T> entitys);
   ```

3. 创建自己的AuditorAware类实现EcAuditorAware接口重写getCurrentAuditor()方法获取当前审计者

   ```java
   package com.easy.cloud.core.jdbc.user.aware;

   import org.springframework.stereotype.Component;
   import com.easy.cloud.core.jdbc.auditor.aware.EcAuditorAware;

   @Component
   public class EcAuditorAwareImpl implements EcAuditorAware<Long>{
   	@Override
   	public Long getCurrentAuditor() {
   		return 2222l;
   	}
   }
   ```

   注意：1、实现类需要被spring容器管理

   ​	    2、一个容器中只能有一个EcAuditorAware实现实例

通过以上3个步骤就能集成基于mybatis的主键生成功能了、若想自定义规则继续往下看

### 高级教程（定义自己的主键生成策略）

1. 编写自己的主键生成策略实现类实现EcBasePrimaryKeyGenerator接口重写generate()方法

   ```java
   import com.easy.cloud.core.jdbc.primarykey.EcBasePrimaryKeyGenerator;

   public class EcDemoPrimaryKeyGenerator implements EcBasePrimaryKeyGenerator{
   	@Override
   	public Serializable generate(Object entityObj) {
   		return UUID.randomUUID().toString();
   	}
   }

   ```

2. 需要在实体类的主键字段上面指定自己的主键生成策略类

   ```java
   import javax.persistence.Id;
   import com.easy.cloud.core.jdbc.primarykey.annotation.EcGenericGenerator;
   import com.easy.cloud.core.jdbc.primarykey.snowflake.EcPrimaryKeySnowflakeGenerator;
   @Id
   // 添加主键生成策略注解，指定生成主键的类class
   @EcGenericGenerator(primaryKeyGeneratorClass = EcDemoPrimaryKeyGenerator.class)
   private String id;
   ```

   其他步骤与基础教程的步骤一致

   至此就实现了对mybatis的主键生成策略功能

### 快速关闭主键生成策略和审计功能

​	在application.properties或者application.yml等主配置文件中添加**ec.audit.switch=false**即可关闭