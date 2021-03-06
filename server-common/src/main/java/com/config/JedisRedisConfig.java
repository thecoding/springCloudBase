//package com.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.annotation.PostConstruct;
//import java.io.Serializable;
//import java.time.Duration;
//
///**
// * 当前只是单实例连接redis，如果需要做redis读写分离，可以用
// * {@link LettuceClientConfiguration.LettuceClientConfigurationBuilder#readFrom}
// * 对事务的支持:
// * 1. 开启事务管理 {@link org.springframework.transaction.annotation.EnableTransactionManagement}
// * 2. 开启事务支持 {@link RedisTemplate#setEnableTransactionSupport(boolean)}
// * 3. 注册事务管理器 {@link org.springframework.transaction.PlatformTransactionManager} 和jdbc事务管理共用
// * {@see <a>https://docs.spring.io/spring-data/redis/docs/2.1.18.RELEASE/reference/html/#tx.spring</a>}
// * Created by Mirko on 2020/4/12.
// */
//@Configuration
//public class JedisRedisConfig {
//
//    private static final transient Logger log = LoggerFactory.getLogger(JedisRedisConfig.class.getName());
//
//    @PostConstruct
//    public void jedisInit(){
//        log.debug(" jedis init...");
//    }
//
//
//    @Value("${spring.redis.database}")
//    private int database;
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private int port;
//    @Value("${spring.redis.password}")
//    private String password;
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private long maxWaitMillis;
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//
//    /**
//     * 连接池配置信息
//     */
//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        // 最大连接数
//        jedisPoolConfig.setMaxTotal(maxActive);
//        // 当池内没有可用连接时，最大等待时间
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        // 最大空闲连接数
//        jedisPoolConfig.setMinIdle(maxIdle);
//        // 最小空闲连接数
//        jedisPoolConfig.setMinIdle(minIdle);
//        // 其他属性可以自行添加
//        return jedisPoolConfig;
//    }
//
//
//    @Bean
//    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig){
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
//        return jedisPool;
//    }
//
//
//    /**
//     * Jedis 连接
//     * @param jedisPoolConfig
//     * @return
//     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling()
//                .poolConfig(jedisPoolConfig).and().readTimeout(Duration.ofMillis(timeout)).build();
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setDatabase(database);
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
//    }
//
//    /**
//     * 缓存管理器
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public RedisCacheManager cacheManager(@Qualifier("jedisConnectionFactory") RedisConnectionFactory connectionFactory) {
//        return RedisCacheManager.create(connectionFactory);
//    }
//
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(jedisConnectionFactory(jedisPoolConfig()));
//        return redisTemplate;
//    }
//
//}
