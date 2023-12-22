项目说明:

结构说明:
```text

com.yspay.demo
├─api   // 对外提供服务的依赖, 无外部依赖, 可以单独打包
│    ├─com.yspay.demo.api.xxx.dto                   // 服务对外的DTO,  xxx代表业务子模块, 下同
│    ├─com.yspay.demo.api.xxx.api                   // 服务对外透出的API
│    └─README.md                                       // 说明使用此依赖对应的注册中心, 应用名等信息, 方便依赖方快速接入
├─app      // 对service进行编排,对外服务的实际提供者, 依赖service和api
│    ├─com.yspay.demo.app.xxx.executor                 // 处理dubbo请求
│    ├─com.yspay.demo.app.xxx.consumer                 // 处理消息消费
│    ├─com.yspay.demo.app.xxx.scheduler                // 处理任务调度
│    └─com.yspay.demo.app.xxx.convert                  // DTO 与 BO之间的转换
├─common      // 公共类文件（util,enum,etc...）
│    ├─com.yspay.demo.common.util                 // common util
│    ├─com.yspay.demo.common.enum                 // common enum 
├─service // 按模块划分的核心业务处理, 依赖manager, 可以根据需要是否依赖dao                                  
│    ├─com.yspay.demo.service.xxx.service              // 业务逻辑接口
│    └─com.yspay.demo.service.xxx.service.impl         // 业务逻辑实现
├─manager // 对外部服务, 中间件, 数据库依赖的封装, 依赖dao, 外部的client
│    ├─com.yspay.demo.manager.xxx.bo                    // 业务对象, 其中bo, 
│    ├─com.yspay.demo.manager.facade                    // 对外RPC调用(dubbo/http)封装的接口
│    ├─com.yspay.demo.manager.facade.impl               // 对外RPC调用的实现
│    ├─com.yspay.demo.manager.xxx.repository            // 对数据库调用封装的接口
│    ├─com.yspay.demo.manager.xxx.repository.impl       // 对数据库调用封装的实现, 多dao复合组装, 事务管理
│    ├─com.yspay.demo.manager.convert                   // 外部DTO与BO之间的转换, DO(entity) 与BO之间的转换    
│    ├─com.yspay.demo.manager.middlware.message         // 对外消息发送    
│    └─com.yspay.demo.manager.middlware.xxx             // 其他中间件的相关操作  xxx代表不同的中间件        
└─dao    // 数据库操作, 依赖数据库
|    ├─com.yspay.demo.dao.entity                        // 数据库表映射对象
|    └─com.yspay.demo.dao.mapper
|                  // ORM数据表操作对象
└─starter    // springboot main函数

```

中间件使用文档：

[系统框架使用说明书](https://doc.weixin.qq.com/doc/w3_ARkALgazAGoVafOoeKXRDGiKduFre?scode=AGgAqwfkAA0UTfifaE)  
[sentinel使用文档](https://doc.weixin.qq.com/doc/w3_AKkAZAY0AHckY0LG2b2TZ0IJeWo5L?scode=AGgAqwfkAA0nEmUSVW)  
[apollo配置和使用](https://doc.weixin.qq.com/doc/w3_AKkAZAY0AHcC14Xcc6hQhOeCTmoGn?scode=AGgAqwfkAA0ANPynjn)  
[错误码规范](https://doc.weixin.qq.com/doc/w3_AOcAlAYQAHQOrMIBR5GQVWnJJSel0?scode=AGgAqwfkAA0rn3hauR)  
[开发环境软件](https://doc.weixin.qq.com/sheet/e3_APUADQZKAHkLX4sdoTFRv6l8dGosy?scode=AGgAqwfkAA0H8C8RjZ)  
