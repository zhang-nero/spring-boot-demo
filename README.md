# Spring Boot Demo
    该项目是一个Spring boot用于web开发的一个示例demo

## 集成的功能

|序号|名称|说明|参考链接|
|:---:|:---------|:--------|:----|
1 | Redisson|使用redis实现分布式锁|[redisson](https://github.com/redisson/redisson)
2 | Mybatis + Mysql多数据源|配置多个数据源，分别进行事务管理，可以指定默认事务管理器 | |
3 | PageHelper|Mybatis通用分页插件|[PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
4 | Swagger|The Best APIs are Built with Swagger Tools|[swagger](https://swagger.io/)
5 | Redis|Jedis客户端，操作Redis|[jedis github](https://github.com/xetorthio/jedis/wiki)

## 打包指令
* dev Env
```text
 mvn clean package -P dev -Dmaven.test.skip=true
```
* prod Env
```text
 mvn clean package -P prod -Dmaven.test.skip=true
```