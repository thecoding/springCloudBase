#### server 公共模块
##### 背景：希望该模块应用于各类api服务的基础，其他模块依赖当前模块就能快速搭建起应用服务。  
##### 设计中需要解决的问题
1. 自动加载问题，springboot在启动的时候检测到只要有指定的jar包就会去加载相应的autoConfig配置类  
    解决方案： 
    * 通过app启动类SpringBootApplication注解来排除对指定的自动配置的依赖
    * 基础包需要做分包，比如一些默认基础配置信息是必须要加载的，就放在一个包下面，选择性加载的配置信息，就需要单独放在另一个包下面。比如UserApplication默认扫描加载com.springcloudbase包下面的信息，特定的配置类放到了com.config包下面，这样就不会扫描到com.config下面的配置信息
    * 需要特定服务模块参考第二点   
2. 指定模块需求的加载。比如现有api服务，需要加载redis，希望通过指定配置类来加载指定的服务
    > 通过自定义enable注解方式来解决  
3. 增加了统一日志返回。yml配置是否开启，并且指定包下有效，注解排除指定类
    * com.springcloudbase.advice.ResponseAdvisor返回结果做了处理，不需要额外再添加messageConverter，使用springBoot自带的就行
    * com.springcloudbase.annotation.IgnoreResponse
    * yml配置 responseBodyAdvice.is-open缺省为false,responseBodyAdvice.basePackages
    