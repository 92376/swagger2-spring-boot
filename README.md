#spring-boot项目简单使用
> 1、引入依赖
```xml
<!-- swagger -->
<dependency>
    <groupId>com.pystandard</groupId>
    <artifactId>swagger2-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
> 2、application-test.yml配置需要开启的环境
```yaml
swagger2:
  enable: true
```
> 3、注意注解了`io.swagger.annotations.Api`才会生成api文档；
访问地址为
```
http://${ip}:${port}/${contextPath}/swagger-ui.html
```