#  mybatis审计模块使用教程

### 项目说明

​	原因：开发人员手动设置实体的创建者、更新者、创建时间、更新时间产生了大量的重复代码

​	目的：减少重复代码、减少开发者设置公共字段的时间，从而优化代码结构、提高开发效率

​	环境：mybatis、springboot、jdk1.8极其以上版本等等、数据数据类需要放在dao或者repository或者 mapper包或者子包下

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

###   基础教程

1. 持久化实体关键代码（详情建EcBaseEntity）

   ```java
   import org.springframework.data.annotation.CreatedBy;
   import org.springframework.data.annotation.CreatedDate;
   import org.springframework.data.annotation.LastModifiedBy;
   import org.springframework.data.annotation.LastModifiedDate;
   @CreatedDate
   private Date createDate;
   @LastModifiedDate
   private Date updateDate;
   @CreatedBy
   private Long createBy;
   @LastModifiedBy
   private Long updateBy;
   ```

2. 在保存和更新的接口上添加EcAuditAnnotation注解，如：详情见源码说明

   ```java
   @EcAuditAnnotation(actionType = EcActionType.SAVE, type = EcType.SAVE)
   public int save(final T obj);

   @EcAuditAnnotation(actionType = EcActionType.UPDATE, type = EcType.UPDATE)
   public int update(final T obj);
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

   注意：1、’实现实现类需要被spring容器管理

   ​	    2、一个容器中只能有一个EcAuditorAware实现实例

通过以上3个步骤就能集成基于mybatis的审计功能了、若想自定义规则继续往下看

### 高级教程（定义自己的处理规则）

1. 编写自己的审计处理类继承EcBaseAuditProcced抽象类重写EcBaseAuditProcced类中的相关方法

   ```java
   public class EcMyselfAuditProcced extends EcBaseAuditProcced{

   }
   ```

2. 需要在方法的添加一个新的注解参数proccedClass值为自己定义审计处理类的class

   ```java
   @EcAuditAnnotation(actionType = EcActionType.SAVE, type = EcType.SAVE, proccedClass = EcMyselfAuditProcced.class)
   public int save(final T obj);

   @EcAuditAnnotation(actionType = EcActionType.UPDATE, type = EcType.UPDATE, proccedClass = EcMyselfAuditProcced.class)
   public int update(final T obj);
   ```

   其他步骤与基础教程的步骤一致

   至此就实现了对mybatis的审计功能