#spring-boot项目简单使用
> 1、引入依赖
```xml
<!-- swagger -->
<dependency>
    <groupId>com.pystandard</groupId>
    <artifactId>swagger2-spring-boot-starter</artifactId>
    <version>3.0</version>
</dependency>
```
> 2、application-test.yml配置需要开启的环境
```yaml
springfox:
  documentation:
#    enabled: false
    title: 项目名称
    version: 1.0.0
```
> 3、注意注解了`io.swagger.annotations.Api`才会生成api文档；
访问地址为
```
http://${ip}:${port}/${contextPath}/swagger-ui
```
> 4、zuul集成,同上,再添加整合配置类，代码如下
```java
/**
 * 整合各服务的swagger
 *
 * @author wujing
 * @since 2019/7/31 14:37
 */
@Configuration
public class SwaggerConfig {

    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider(RouteLocator routeLocator) {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            // (path和serviceId都可以访问), 去重处理
            Set<String> routIds = new HashSet<>();
            for (Route route : routeLocator.getRoutes()) {
                if (routIds.add(route.getId())) {
                    resources.add(createResource(route.getLocation(),
                            route.getFullPath().replace("**", "v2/api-docs"), "2.0"));
                }
            }
            return resources;
        };
    }

    private SwaggerResource createResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
```
