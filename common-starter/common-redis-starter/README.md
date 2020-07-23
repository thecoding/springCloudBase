### redis 自定义启动类
1. 自定义了redis配置文件格式
2. 自定义了redis使用的连接池（GenericObjectPool）
3. 重写了RedisAutoConfiguration --> RedisAutoConfig
4. 使用jedis