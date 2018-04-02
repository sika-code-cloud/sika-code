 代码生成器模块使用教程
===================

### 项目说明

​	目的：避免开发人员对基本模版代码的重复编写，方便公司规范代码

### 使用须知

​	各个代码模版可以任意指定项目名称，但该项目需要与执行代码生成的入口在同目录下，方能正确生成，如

 ![2273dc28da9d88de6a03e09627bcb59](C:\Users\THINK\AppData\Local\Temp\WeChat Files\2273dc28da9d88de6a03e09627bcb59.png)

### 使用教程

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



#### 二、生成java代码教程(示例User模块)

* ##### 生成持久化类：PO类（示例）

* ##### 生成数据传输类：DTO类（示例）

* ##### 生成业务逻辑类：BO类（示例）

* ##### 生成视图类：VO类（示例）

* ##### 生成查询类：Query类（示例）

* ##### 生成数据处理接口类：DAOInf接口（示例）

* ##### 生成数据处理实现类：DAOImpl类（示例）

* ##### 生成服务接口类：ServiceInf接口（示例）

* ##### 生成服务实现类：ServiceImpl类（示例）

* ##### 生成业务逻辑接口类：LogicInf接口（示例）

* ##### 生成业务逻辑实现类：LogicImpl类（示例）

* ##### 生成控制层类：Controller类（示例）

* ##### 生成错误代码枚举类：ErrorCode枚举（示例）