项目说明:

结构说明:
```text

├─app     
│    ├─com.yspay.demo.app.xxx.controller              // controller,提供对外http接口
│    └─com.yspay.demo.app.xxx.convert                  // BO 与 DTO之间的转换
├─common      // 公共类文件（util,enum,etc...）
│    ├─com.yspay.demo.common.util                 // common util
│    ├─com.yspay.demo.common.enum                 // common enum 
├─auth // 按模块划分的核心业务处理, 依赖manager                                  
│    ├─com.yspay.demo.auth              // 鉴权
│    └─com.yspay.demo.auth.filter         // 统一鉴权过滤
├─service // 按模块划分的核心业务处理, 依赖manager                               
│    ├─com.yspay.demo.service.xxx.service              // 业务逻辑接口
│    └─com.yspay.demo.service.xxx.service.impl         // 业务逻辑实现
├─manager // 对外部服务, 中间件, 数据库依赖的封装, 依赖dao, 外部的api包
│    ├─com.yspay.demo.manager.xxx.bo                    // 业务对象, 其中bo, 
│    ├─com.yspay.demo.manager.facade                    // 对外RPC调用(dubbo/http)封装的接口
│    ├─com.yspay.demo.manager.facade.impl               // 对外RPC调用的实现
│    ├─com.yspay.demo.manager.convert                   // 外部DTO与BO之间的转换, DO(entity) 与BO之间的转换    
│    ├─com.yspay.demo.manager.middlware.message         // 对外消息发送    
│    └─com.yspay.demo.manager.middlware.xxx             // 其他中间件的相关操作  xxx代表不同的中间件        
└─starter    // springboot main函数


```

中间件使用文档：

[系统框架使用说明书](https://doc.weixin.qq.com/doc/w3_ARkALgazAGoVafOoeKXRDGiKduFre?scode=AGgAqwfkAA0UTfifaE)  
[sentinel使用文档](https://doc.weixin.qq.com/doc/w3_AKkAZAY0AHckY0LG2b2TZ0IJeWo5L?scode=AGgAqwfkAA0nEmUSVW)  
[apollo配置和使用](https://doc.weixin.qq.com/doc/w3_AKkAZAY0AHcC14Xcc6hQhOeCTmoGn?scode=AGgAqwfkAA0ANPynjn)  
[错误码规范](https://doc.weixin.qq.com/doc/w3_AOcAlAYQAHQOrMIBR5GQVWnJJSel0?scode=AGgAqwfkAA0rn3hauR)  
[开发环境软件](https://doc.weixin.qq.com/sheet/e3_APUADQZKAHkLX4sdoTFRv6l8dGosy?scode=AGgAqwfkAA0H8C8RjZ)  
