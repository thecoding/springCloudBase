
#### 业务测试
* 接口实现和定义分包，利于消费者使用  
    - other-provider-spi定义接口，实现这引用该模块，具体实现类实现接口中所有方法
    比如com.springcloud.other.provider.service.impl.OtherServiceImpl实现了com.springcloud.other.provider.service.OtherService中
    定义的方法。  
    - 消费者同样引用该模块，具体调用的地方可以直接通过自动注入的接口实现类来调用方法
        ```java
          @Autowired
          OtherServiceExtends otherServiceExtends;
      
          @GetMapping(value = "/order2")
          public Map getOrder2(){
              String order2 = otherServiceExtends.getHelloTest("order2");
              Map map = Maps.newHashMap();
              map.put("other-return", order2);
              map.put("user-server", "test");
              return map;
          }
        ```
    - 接口定义，指定了url，请求方式，请求的格式，还有参数
        ```java
        @GetMapping(value = "/user/hello")
        String getHelloTest(@RequestParam("name") String name);
        ```
    - 优点：消费者不需要关心调用方式和地址，只需要接口继承提供者的接口，注明FeignClient就行了 