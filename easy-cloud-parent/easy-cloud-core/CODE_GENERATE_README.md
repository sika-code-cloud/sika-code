
 代码生成器模块使用教程
===================

### 项目说明

​	目的：避免开发人员对基本模版代码的重复编写，方便公司规范代码

### 使用须知

1. #####  建议

   为了避免代码生成组件污染原代码，建议为其专门建议代码生成的子项目进行代码生成

2. #####  各个代码模版可以任意指定项目名称，但该项目需要与执行代码生成的入口在同目录下，方能正确生成

   - target-project1 (生成代码的目标项目)
   - target-project2 (生成代码的目标项目)
   - generate-code-project (执行生成代码的项目)

3. ##### 为了能正确在mappers-config中添加mapper节点的路径，持久化对象与表的映射文件应该低于mappers-config配置文件的层级。如

   - sqlmap-config.xml
   - mybatis(容纳持久化对象与表映射的配置文件目录)

### 使用教程

一、单独创建代码生成项目

二、导入easy-cloud-core.jar包

三、添加添加maven依赖

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>test.generate.code</groupId>
	<artifactId>test-generate-code</artifactId>
	<version>1.0.0</version>
	<packaging>jar</packaging>

	<name>test-generate-code</name>
	<url>http://maven.apache.org</url>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.8.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<!-- alibaba.fastjson依赖 -->
		<fastjson.version>1.2.39</fastjson.version>
		<!-- jom依赖 -->
		<org.jdom.version>2.0.2</org.jdom.version>
		<!-- apache.commons包依赖 -->
		<apache.commons.lang3>3.4</apache.commons.lang3>

		<!-- 版本依赖管理 end -->
		<!-- 设置属性 -->
		<!--设置字符编码及java版本 -->
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<java.version>1.7</java.version>
		<skipTests>true</skipTests>

	</properties>
	<dependencies>
		<!-- spring框架相关依赖 bigen -->
		<!-- spring-boot-start-x依赖 begin -->
		<!-- spring-boot-starter-jdbc 模块 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<!-- spring-boot-starter-data-jpa 依赖 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- spring-boot-starter-web依赖包含springMvc -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- spring-boot-starter-test测试框架依赖 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- spring-boot-start-x依赖 end -->
		<!-- spring框架相关依赖 end -->

		<!-- 非spring项目的第三方依赖 begin -->
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>${apache.commons.lang3}</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/commons-collections/commons-collections -->
		<dependency>
			<groupId>commons-collections</groupId>
			<artifactId>commons-collections</artifactId>
		</dependency>

		<!-- fastjson依赖 -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>${fastjson.version}</version>
		</dependency>
		<!-- jdom依赖 -->
		<dependency>
			<groupId>org.jdom</groupId>
			<artifactId>jdom</artifactId>
			<version>${org.jdom.version}</version>
		</dependency>
		<!-- mysql连接依赖 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.freemarker/freemarker -->
		<dependency>
			<groupId>org.freemarker</groupId>
			<artifactId>freemarker</artifactId>
		</dependency>
		<!-- 非spring项目的第三方依赖 end -->
	</dependencies>
</project>
```
##### 生成mybatis配置文件和java代码的公共数据

1. ##### 设置基础信息

   ```java
   // 数据库配置信息
   private EcDatabaseAbstactConfig databaseAbstactConfig;
   // pojo类所在的项目名称
   private String projectNamePojo = "test-generate-code";
   // dao类所在的项目名称
   private String projectNameDao = "test-generate-code";
   // service类所在的项目名称
   private String projectNameService = "test-generate-code";
   // controller类所在的项目名称
   private String projectNameController = "test-generate-code";
   // mybatis配置文件所在的项目名称
   private String projectNameMybatis = "test-generate-code";
   // 基础包名称
   private String basePackageName = "com.dq.easy";

   // 表名
   private String tableName = "easy_user_info";
   // 模块包名
   private String moduleName = "user";
   // 类主体名称
   private String classBodyName = "User";
   // 类的注释
   private String classComment = "用户";
   ```

2. ##### 创建EcDatabaseAbstactConfig的实例对象databaseAbstactConfig

   ```java
   // 数据库配置信息
   databaseAbstactConfig = new EcDataBaseMysqlConfig();
   // 设置数据库基础url
   databaseAbstactConfig.buildDatabaseBaseUrl("jdbc:mysql://localhost");
   // 设置数据库端口号
   databaseAbstactConfig.buildDatabasePort("3306");
   // 设置数据库名称
   databaseAbstactConfig.buildDatabaseName("dq_easy_cloud");
   // 设置数据库用户名
   databaseAbstactConfig.buildDatabaseUserName("dq_easy_cloud");
   // 设置数据库密码
   databaseAbstactConfig.buildDatabasePassword("dq_easy_cloud123");
   // 设置数据库表名
   databaseAbstactConfig.buildTableName(tableName);
   ```

#### 一、生成mybatis.xml配置文件教程

3. ##### 创建EcGenerateXmlMybatisDTO的实例对象mybatisDTO		

   ```java
   // 获取dao子模块包名
   String daoSubModulePackageName = EcSubModuleDefaultPackageName.DAO_INF;
   // 创建EcGenerateJavaBaseDTO对象
   EcGenerateJavaBaseDTO daoDto = new EcGenerateJavaBaseDTO(projectNameDao, basePackageName, moduleName,daoSubModulePackageName, classBodyName, classComment);
   // 获取DAO层完整的包名
   String fullPackageNameDAO = daoDto.buildFullPackageName();
   // 获取DAO层简称类类型
   String simpleClassTypeDAO = classBodyName + EcClassNameEndWith.DAO_INF;
   // 获取DAO层完整类类型
   String fullClsssTypeDAO = EcCodeGenerateUtils.getFullClassType(fullPackageNameDAO, simpleClassTypeDAO);
   // 创建EcGenerateXmlMybatisDTO实例对象
   EcGenerateXmlMybatisDTO mybatisDTO = new EcGenerateXmlMybatisDTO();
   // 设置命名空间
   mybatisDTO.setNamespace(fullClsssTypeDAO);
   // 设置标明
   mybatisDTO.setTableName(tableName);
   // 设置mybatis配置文件所在的项目名称
   mybatisDTO.setProjectName(projectNameMybatis);
   // 设置子路径名称
   mybatisDTO.setSubPath("mybatis");

   // 持久化对象的子模块包路径 
   String subModulePackageNameDO = EcSubModuleDefaultPackageName.POJO_PO;
   // 创建EcGenerateJavaBaseDTO，持久化对象的数据传输对象
   EcGenerateJavaBaseDTO dtoDO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName, moduleName,subModulePackageNameDO, classBodyName, classComment);
   // 获取持久化对象的完整包名
   String fullPackageNameDO = dtoDO.buildFullPackageName();
   // 获取持久化对象的简称类类型
   String simpleClassTypeDO = classBodyName + EcClassNameEndWith.POJO_PO;
   // 获取持久化对象的完整类类型
   String fullClsssTypeDO = EcCodeGenerateUtils.getFullClassType(fullPackageNameDO, simpleClassTypeDO);

   // 设置持久化对象的简称类类型
   mybatisDTO.setSimpleClassTypeDO(simpleClassTypeDO);
   // 设置持久化对象的完整类类型
   mybatisDTO.setFullClassTypeDO(fullClsssTypeDO);
   // 设置保存mapper配置文件集合的配置文件名称（自动将mapping追加到该文件的mappers节点中）
   mybatisDTO.setMappersConfigName("sqlmap-config");
   ```

4. ##### 创建EcGenerateXmlMybatisBO实例对象mybatisBO传入mybatisDTO对象

   ```java
   // 创建业务逻辑对象
   EcGenerateXmlMybatisBO mybatisBO = new EcGenerateXmlMybatisBO(mybatisDTO);
   // 数据初始化
   mybatisBO.initData();
   // 构建数据库数据源对象
   mybatisBO.buildDatabaseDataSources(new EcMysqlDataSources(databaseAbstactConfig));
   // 构建根节点
   mybatisBO.buildRootContentElementDesc();
   // 构建ResultMap节点
   mybatisBO.buildResultMap();
   // 构建ColumnList节点
   mybatisBO.buildColumnList();
   // 构建根据id获取对象信息的节点
   mybatisBO.buildFindById();
   // 构建获取列表计数的节点
   mybatisBO.buildListCount();
   // 构建列表分页信息的节点
   mybatisBO.buildListPage();
   // 构建设置列名的sql节点
   mybatisBO.buildSetColumnSql();
   // 构建保存对象信息的节点
   mybatisBO.buildSave();
   // 构建更新数据的节点
   mybatisBO.buildUpdate();
   try {
   	// 调用生成代码方法
   	mybatisBO.generateCode();
   } catch (Exception e) {
   e.printStackTrace();
   }
   ```


#### 二、生成java代码教程

**java类结构分析**

  ![类描述分析图](类描述分析图.png)

**创建自定义规则的业务逻辑对象的步骤**

1. 生成普通class类继承EcGenerateJavaClassBO抽象类、生成interface类继承EcGenerateJavaInfBO抽象类、生成enum类需要继承EcGenerateJavaEnumBO抽象类
2. 重写getClassNameEndWith方法（必须重写，若无返回空字符串）
3. 重写getClassCommentEndWith方法（必须重写，若无返回空字符串）
4. 使用EcGenerateJavaDOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule)构造函数  : 必选
5. 重写构建类注解列表:buildAnnotations():方法,（若无注解，可以不重写） : 可选
6. 重写构建类继承的父类列表:buildExtendsParentClass():方法（若无继承的父类 ，可以不重写） : 可选
7. 重写构建类实现接口的列表:buildImplementsInterfaces():方法（若无实现的接口，可以不重写） : 可选
8. 重写构建类构造函数列表:buildConstructors():方法（若只是需要默认构造 ，可以不重写） : 可选
9. 重写构建类属性列表:buildFields():方法（若不需要属性，可以不重写） : 可选
10. 重写构建类方法列表:buildMethods():方法（若不需要方法 ，可以不重写） : 可选
11. 重写构建类泛型列表:buildGenericitys():方法（若不需要泛型 空实现即可） : 可选

##### 示例（User）

*  ##### 生成持久化类：PO类（示例）

   ##### 1. 创建自定义规则业务逻辑对象

   ```java
   package com.easy.cloud.core.generate.bo.javaclass;
   import ***
   /**
   * <p>
   * 生成持久化对象
   * </p>
   * @author daiqi 创建时间 2018年3月27日 上午9:49:06
   */
   public class EcGenerateJavaPOBO extends EcGenerateJavaClassBO {

   /**
    * @param generateJavaBaseDTO : EcGenerateJavaBaseDTO : 生成java的数据传输对象
    * @param generateRule : EcGenerateRule : 生成规则
    */
    public EcGenerateJavaPOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
      super(generateJavaBaseDTO, generateRule);
      }
     
     /** 构建类的注解列表 */
    @Override
    protected void buildAnnotations() {
   	if (EcBaseUtils.isNull(dataBaseConfig)) {
   		return;
   	}
      // 创建注解描述对象（table注解）
   	EcJavaAnnotationDesc tableAnnotationDesc = new EcJavaAnnotationDesc();
      // 设置注解的名称（此处即是类类型简称）
   	tableAnnotationDesc.setName(Table.class.getSimpleName());
      // 设置注解的类类型简称
   	tableAnnotationDesc.setSimpleClassType(Table.class.getSimpleName());
      // 设置注解的完整类类型
   	tableAnnotationDesc.setFullClassType(Table.class.getName());
   	// 设置类注解参数---begin
   	tableAnnotationDesc.addParam("name", dataBaseConfig.getTableName());
   	// 设置类注解参数---end
       // 往javaClassContentDesc对象add注解对象
   	super.javaClassContentDesc.addAnnotation(tableAnnotationDesc);
       // 创建注解描述对象（步骤与创建table注解一样）
   	EcJavaAnnotationDesc entityAnnotationDesc = new EcJavaAnnotationDesc();
   	entityAnnotationDesc.setName(Entity.class.getSimpleName());
   	entityAnnotationDesc.setSimpleClassType(Entity.class.getSimpleName());
   	entityAnnotationDesc.setFullClassType(Entity.class.getName());
   	super.javaClassContentDesc.addAnnotation(entityAnnotationDesc);
   	}
     
     /** 构建继承的类列表 */
     @Override
     protected void buildExtendsParentClass() {
   	  // 创建集成的父类对象
         EcJavaClassContentDesc extendsParentClass = new EcJavaClassContentDesc();
         // 设置名称
         extendsParentClass.setName(EcBaseEntity.class.getSimpleName());
         // 设置类类型简称
         extendsParentClass.setSimpleClassType(EcBaseEntity.class.getSimpleName());
         // 设置完整类类型
         extendsParentClass.setFullClassType(EcBaseEntity.class.getName());
         // 往javaClassContentDesc对象add继承的类对象
         super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
     }
     
    /** 构建属性列表 */
     @Override
     protected void buildFields() {
         // 以数据库的表为数据源构建属性列表
         super.javaClassContentDesc.addFields(getFieldsByDatabaseDataSources());
     }
     
     /** 构建方法列表 */
     @Override
     protected void buildMethods() {
        // 根据属性列表构建对应的方法（如get、set、build方法）
         super.javaClassContentDesc.addMethods(super.getMethodsByFields());
     }

     @Override
     protected String getClassCommentEndWith() {
         // 设置注释的endWith（如若传入的注释主体为User，则完整注释即为 commonBody+commentEndWith）
         return EcClassCommentEndWith.POJO_PO;
     }

     @Override
     protected String getClassNameEndWith() {
         // 类名的endWith（如传入的ClassNameBody为User，则类名即（classNameBody+classNameEndWith）
         // 如classNameBody = User ，则用户持久化实体类为UserEntity或者 UserPO或者UserDO
         return EcClassNameEndWith.POJO_PO;
     }
   }
   ```

2.  **创建测试方法生成持久化对象**

    ```java
    @Test
    public void generateJavaPOByDataBase() {
        // 设置子模块包名---（使用组件自定义的持久化对象的子模块包名，可以自定义）
    	String subModulePackageName = EcSubModuleDefaultPackageName.POJO_PO;
      	// 创建生成java的基础数据传输对象(传入基础包名、模块名称、子模块包名、类主体名称、类注释主体)
    	EcGenerateJavaBaseDTO generateDTO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName,moduleName, subModulePackageName, classBodyName, classComment);
      	// 创建生成规则（是否生成属性、是否生成get方法、是否生成set方法、是否生成build方法）
    	EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, true);
      	// 设置覆盖开关（设置为true则表示每次执行该方法都会覆盖生成的目标类，否则将不覆盖）
    	generateJavaBaseDTO.setCoverSwith(true);
        // 设置需要忽略生成的属性列表的
        Map<String, Boolean> ignoreFields = new HashMap<>();
        ignoreFields.put("id", true);
        generateJavaBaseDTO.setIgnoreFields(ignoreFields);
    	try {
          	// 创建数据源对象（此处使用mysql数据库做为数据源、如果为oracle（则为EcOracleDataSources）
            // 若需要自定义以数据库数据源则可以通过继承EcDatabaseDataSources进行拓展
          	// 若需要自定义非数据库数据源则可以通过继承EcBaseDataSources进行拓展
    		EcDatabaseDataSources dataSources = new EcMysqlDataSources(databaseAbstactConfig);
          	// 创建生成持久化类的业务逻辑对象
    		EcGenerateJavaDOBO generateBO = new EcGenerateJavaDOBO(generateDTO, generateRule);
            // 构建数据库数据源
    		generateBO.buildDtabaseDataSources(dataSources);
            // 调用生成代码的方法
    		generateBO.generateCode();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    ```

3.  **结果示例**

    ```java
    package com.dq.easy.user.pojo.po;
    import javax.persistence.Column;
    import java.util.Date;
    import com.easy.cloud.core.basic.pojo.entity.EcBaseEntity;
    /**
    * 描述：用户持久化类
    * @author THINK
    * @date 2018-04-03 11:29:15
    */
    public class UserPO extends EcBaseEntity {
     /** 创建日期 */
     @Column(name = "create_date")
     public Date createDate;
     /** 用户名 */
     @Column(name = "user_name")
     public String userName;

     /** 获取创建日期 */
     public Date getCreateDate() {
         return this.createDate;
     }
     
     /** 设置创建日期 */
     public void setCreateDate(Date createDate) {
        this.createDate = createDate;
     }

     /** 构建创建日期 */
     public UserPO buildCreateDate(Date createDate) {
         this.createDate = createDate;
         return this;
     }

     /** 获取用户名 */
     public String getUserName() {
         return this.userName;
     }

     /** 设置用户名 */
     public void setUserName(String userName) {
         this.userName = userName;
     }

     /** 构建用户名 */
     public UserPO buildUserName(String userName) {
         this.userName = userName;
         return this;
     }
    }
    ```
   ```

   ​

*  生成数据传输类：DTO类（示例）**

   参考生成持久化类的步骤

*  ##### 生成业务逻辑类：BO类（示例）

   参考生成持久化类的步骤

*  ##### 生成视图类：VO类（示例）

   参考生成持久化类的步骤

*  ##### 生成查询类：Query类（示例）

   参考生成持久化类的步骤

*  ##### 生成数据处理接口类：DAOInf接口（示例）

   ##### 1. 创建自定义规则业务逻辑对象

   ```java
   package testgenerate.bo.javainf;
   import ***
     /** 生成的为DAO接口所以继承EcGenerateJavaInfBO抽象类 */
   public class EcGenerateJavaJpaDAOBO extends EcGenerateJavaInfBO {
     public EcGenerateJavaJpaDAOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
         super(generateJavaBaseDTO, generateRule);
     }

     @Override
     protected String getClassNameEndWith() {
         return EcClassNameEndWith.DAO_INF;
     }

     @Override
     protected String getClassCommentEndWith() {
         return EcClassCommentEndWith.DAO_INF;
     }

     @Override
     protected void buildExtendsParentClass() {
       	// 构建集成的父类
          EcJavaClassContentDesc extendsParentClass = new EcJavaClassContentDesc();
          extendsParentClass.setSimpleClassType(JpaRepository.class.getSimpleName());
          extendsParentClass.setName(JpaRepository.class.getSimpleName());
          extendsParentClass.setFullClassType(JpaRepository.class.getName());
   		// 构建继承父类的泛型数据
          EcJavaGenericityContentDesc entity = new EcJavaGenericityContentDesc();
          entity.setSimpleClassType(EcBaseEntity.class.getSimpleName());
          entity.setName(EcBaseEntity.class.getSimpleName());
          entity.setFullClassType(EcBaseEntity.class.getName());
          extendsParentClass.addGenericity(entity);
   		// 构建父类的第二个泛型对象
          EcJavaGenericityContentDesc stringGen = new EcJavaGenericityContentDesc();
          stringGen.setSimpleClassType(String.class.getSimpleName());
          stringGen.setName(String.class.getSimpleName());
          extendsParentClass.addGenericity(gene);
   		// 将父类add到javaClassContentDesc
          javaClassContentDesc.addExtendsParentClass(extendsParentClass);
     }

     /** 构建自定义的方法列表 */
     @Override
     protected void buildMethods() {
         // PO对象名称后缀
         String nameEndwith = EcClassNameEndWith.POJO_PO;
         // PO对象的子模块包名
         String subModulePackageName = EcSubModuleDefaultPackageName.POJO_PO;
         // 获取自定义的描述对象
         EcJavaContentDesc doClass = super.getCustomJavaContentByEndwith(nameEndwith, subModulePackageName, EcJavaContentDesc.class);
         // 创建save抽象方法
         EcJavaMethodContentDesc methodSave = new EcJavaMethodContentDesc();
         // 设置返回类类型简称
         methodSave.setReturnSimpleClassType(Integer.class.getSimpleName());
         // 设置完整类类型
         methodSave.setReturnFullClassType(Integer.class.getName());
         // 设置方法名称
         methodSave.setName("save");
         // 设置方法的形参
         EcJavaFieldContentDesc methodSaveArg = new EcJavaFieldContentDesc();
         // 设置形参名称
         methodSaveArg.setName(doClass.getName());
         // 设置形参类类型简称
         methodSaveArg.setSimpleClassType(doClass.getSimpleClassType());
         // 设置形参完整类类型
         methodSaveArg.setFullClassType(doClass.getFullClassType());
         // 将该形参add到methodSave中
         methodSave.addArg(methodSaveArg);
         // 设置方法类型（表示为抽象方法）
         methodSave.setType(EcMethodTypeEnum.ABSTRACT.getType());
         // 将该方法add到javaClassContentDesc对象中
         super.javaClassContentDesc.addMethod(methodSave);

         // update 与保存方法步骤一样
         EcJavaMethodContentDesc methodUpdate = new EcJavaMethodContentDesc();
         methodUpdate.setReturnSimpleClassType(Integer.class.getSimpleName());
         methodUpdate.setReturnFullClassType(Integer.class.getName());
         methodUpdate.setName("update");
         EcJavaFieldContentDesc methodUpdateArg = new EcJavaFieldContentDesc();
         methodUpdateArg.setName(doClass.getName());
         methodUpdateArg.setSimpleClassType(doClass.getSimpleClassType());
         methodUpdateArg.setFullClassType(doClass.getFullClassType());
         methodUpdate.addArg(methodSaveArg);
         methodUpdate.setType(EcMethodTypeEnum.ABSTRACT.getType());
         super.javaClassContentDesc.addMethod(methodUpdate);

         // findById 与保存方法步骤一样
         EcJavaMethodContentDesc methodFindById = new EcJavaMethodContentDesc();
         methodFindById.setReturnSimpleClassType(doClass.getSimpleClassType());
         methodFindById.setReturnFullClassType(doClass.getFullClassType());
         methodFindById.setName("findById");
         EcJavaFieldContentDesc methodFindByIdArg = new EcJavaFieldContentDesc();
         methodFindByIdArg.setName("id");
         methodFindByIdArg.setSimpleClassType(Long.class.getSimpleName());
         methodFindById.addArg(methodFindByIdArg);
         methodFindById.setType(EcMethodTypeEnum.ABSTRACT.getType());
         super.javaClassContentDesc.addMethod(methodFindById);

         // listCount 与保存方法步骤一样
         EcJavaMethodContentDesc methodListCount = new EcJavaMethodContentDesc();
         methodListCount.setReturnSimpleClassType(Integer.class.getSimpleName());
         methodListCount.setName("listCount");
         EcJavaFieldContentDesc methodListCountArg = new EcJavaFieldContentDesc();
         // 形参注解 
         EcJavaAnnotationDesc methodListCountArgAnnotation = new EcJavaAnnotationDesc();
         // 设置形参注解名称
         methodListCountArgAnnotation.setName(Param.class.getSimpleName());
         // 设置形参注解类类型简称
         methodListCountArgAnnotation.setSimpleClassType(Param.class.getSimpleName());
         // 设置形参注解的完整类类型
         methodListCountArgAnnotation.setFullClassType(Param.class.getName());
         // 设置形参注解的参数值
         methodListCountArgAnnotation.addParam("value", "maps");
   	  // 将形参注解add到对应的形参对象中
         methodListCountArg.addAnnotation(methodListCountArgAnnotation);
         methodListCountArg.setName("paramsMap");
         methodListCountArg.setSimpleClassType("Map<String, Object>");
         methodListCountArg.setFullClassType(Map.class.getName());
         methodListCount.addArg(methodListCountArg);
         methodListCount.setType(EcMethodTypeEnum.ABSTRACT.getType());
         super.javaClassContentDesc.addMethod(methodListCount);

         // listPage 与保存方法步骤一样
         EcJavaMethodContentDesc methodListPage = new EcJavaMethodContentDesc();
         methodListPage.setReturnSimpleClassType("List<" + doClass.getSimpleClassType() + ">");
         methodListPage.setReturnFullClassType(List.class.getName());
         methodListPage.setName("listPage");
         EcJavaFieldContentDesc methodListPageArg = new EcJavaFieldContentDesc();
         // 形参注解
         EcJavaAnnotationDesc methodListPageArgAnnotation = new EcJavaAnnotationDesc();
         methodListPageArgAnnotation.setName(Param.class.getSimpleName());
         methodListPageArgAnnotation.setSimpleClassType(Param.class.getSimpleName());
         methodListPageArgAnnotation.setFullClassType(Param.class.getName());
         methodListPageArgAnnotation.addParam("value", "maps");

         methodListPageArg.addAnnotation(methodListPageArgAnnotation);
         methodListPageArg.setName("paramsMap");
         methodListPageArg.setSimpleClassType("Map<String, Object>");
         methodListPageArg.setFullClassType(Map.class.getName());
         methodListPage.addArg(methodListPageArg);
         methodListPage.setType(EcMethodTypeEnum.ABSTRACT.getType());
         super.javaClassContentDesc.addMethod(methodListPage);
      }
   }
   ```

   **2.创建测试方法生成DAO接口**

```java
   @Test
   public void generateJavaJpaDAOByDataBase() {
   	String subModulePackageName = EcSubModuleDefaultPackageName.DAO_INF;
   	EcGenerateJavaBaseDTO generateDTO = new EcGenerateJavaBaseDTO(projectNameDao, basePackageName,moduleName, subModulePackageName, classBodyName, classComment);
   	EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
   	try {
   		EcGenerateJavaJpaDAOBO generateBO = new EcGenerateJavaJpaDAOBO(generateDTO, generateRule);
   		generateBO.generateCode();
   	} catch (Exception e) {
   		e.printStackTrace();
   	}
   }
```
   **3. 结果示例**

```java
   package com.dq.easy.user.dao;

   import com.dq.easy.user.pojo.po.UserPO;
   import java.util.Map;
   import org.springframework.data.jpa.repository.JpaRepository;
   import java.util.List;
   import java.lang.Integer;
   import com.easy.cloud.core.basic.pojo.entity.EcBaseEntity;
   import org.springframework.data.repository.query.Param;

   /**
    * 描述：用户数据处理接口
    * 
    * @author THINK
    * @date 2018-04-03 11:29:15
    */
   public interface UserDAO extends JpaRepository<EcBaseEntity, String> {
   	 Integer save(UserPO userPO);
   	
   	 Integer update(UserPO userPO);
   	
   	 UserPO findById(Long id);
   	
   	 Integer listCount(@Param(value = "maps") Map<String, Object> paramsMap);
   	
   	 List<UserPO> listPage(@Param(value = "maps") Map<String, Object> paramsMap);
   	
   }
```

   ​

*  ##### 生成数据处理实现类：DAOImpl类（示例）

   参考生成持久化类的步骤

*  ##### 生成服务接口类：ServiceInf接口（示例）

    参考生成生成数据处理接口类接口

*  ##### 生成服务实现类：ServiceImpl类（示例）

    参考生成持久化类的步骤

*  ##### 生成业务逻辑接口类：LogicInf接口（示例）

    参考生成生成数据处理接口类接口

*  ##### 生成业务逻辑实现类：LogicImpl类（示例）

    参考生成持久化类的步骤

*  ##### 生成控制层类：Controller类（示例）

    参考生成持久化类的步骤

*  ##### 生成错误代码枚举类：ErrorCode枚举（示例）

1.  **创建自定义规则业务逻辑对象**

    ```java
    package testgenerate.bo.javaenum;
    import ***
    /**
    * <p>
    * 生成枚举类的业务逻辑类,默认会根据属性生成对应的私有的构造方法
    * </p>
    * @author daiqi 创建时间 2018年3月27日 上午9:54:03
    */
    public class EcGenerateJavaErrorCodeBO extends EcGenerateJavaEnumBO {
       public EcGenerateJavaErrorCodeBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
           super(generateJavaBaseDTO, generateRule);
       }

       @Override
       /** 获取实现的接口列表 */
       protected void buildImplementsInterfaces() {
         EcJavaImplInterfaceContentDesc implInterfaceContentDesc = new EcJavaImplInterfaceContentDesc();
         implInterfaceContentDesc.setSimpleClassType(EcBaseErrorCodeInf.class.getSimpleName());
         implInterfaceContentDesc.setName(EcBaseErrorCodeInf.class.getSimpleName());
         implInterfaceContentDesc.setFullClassType(EcBaseErrorCodeInf.class.getName());
         javaClassContentDesc.addImplementsInterface(implInterfaceContentDesc);
       }

       @Override
       protected void buildFields() {
           EcJavaFieldContentDesc codeFieldContentDesc = new EcJavaFieldContentDesc();
           codeFieldContentDesc.setComment("错误代码");
           // 设置code属性的modifiers
           codeFieldContentDesc.addModifier(EcModifierMappingEnum.PRIVATE);
           // 设置code属性名称和类型
           codeFieldContentDesc.setName("errorCode");
           codeFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
           javaClassContentDesc.addField(codeFieldContentDesc);

           EcJavaFieldContentDesc msgFieldContentDesc = new EcJavaFieldContentDesc();
           msgFieldContentDesc.setComment("错误信息");
           // 设置msg属性的modifiers
           msgFieldContentDesc.addModifier(EcModifierMappingEnum.PRIVATE);
           // 设置code属性名称和类型
           msgFieldContentDesc.setName("errorMsg");
           msgFieldContentDesc.setSimpleClassType(String.class.getSimpleName());
           javaClassContentDesc.addField(msgFieldContentDesc);
       }

       @Override
       protected void buildMethods() {
           // 通过属性构建方法
           javaClassContentDesc.addMethods(super.getMethodsByFields());
       }

       @Override
       protected String getClassCommentEndWith() {
           return EcClassCommentEndWith.ERROR_CODE;
       }

       @Override
       protected String getClassNameEndWith() {
           return EcClassNameEndWith.ERROR_CODE;
       }
    }
    ```
   ```

2. **创建测试方法生成枚举类**

   ```java
   @Test
   public void generateJavaErrorCodeByDataBase() {
   	String subModulePackageName = EcSubModuleDefaultPackageName.ERROR_CODE;
   	EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameController, basePackageName,
   			moduleName, subModulePackageName, classBodyName, classComment);
   	EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
   	try {
   		new EcGenerateJavaErrorCodeBO(generateJavaBaseDTO, generateRule).generateCode();
   	} catch (Exception e) {
   		e.printStackTrace();
   	}
   }
   ```

3.  **示例结果**

    ```java
    package com.dq.easy.user.constant.error;

    import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;

    /**
    * 描述：用户错误代码枚举
    * 
    * @author THINK
    * @date 2018-04-03 11:29:15
    */
    public enum UserErrorCodeEnum implements EcBaseErrorCodeInf {
    	;
    	/** 错误代码 */
    	private String errorCode;
    	/** 错误信息 */
    	private String errorMsg;
    		
    	private UserErrorCodeEnum(String errorCode, String errorMsg) {
    		this.errorCode = errorCode;
    		this.errorMsg = errorMsg;
    	}
    	
    	/** 获取错误代码 */
    	public String getErrorCode() {
    		return this.errorCode;
    	}

    	/** 设置错误代码 */
    	public void setErrorCode(String errorCode) {
    		this.errorCode = errorCode;
    	}

    	/** 获取错误信息 */
    	public String getErrorMsg() {
    		return this.errorMsg;
    	}

    	/** 设置错误信息 */
    	public void setErrorMsg(String errorMsg) {
    		this.errorMsg = errorMsg;
    	}
    ```

   }
   ```
   ​

   ```