# SpringBoot using Redis

## Steps to get started with redis
1. Add the following into pom.xml 
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

2. Add the Redis config to application.yml
```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

3. Create Redis Configuration. \
The currect Redis config uses StringRedisSerializer to serialize and deserialize.
```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer() );

        return redisTemplate;
    }
}
```

4. Create RedisService.java file with set and get methods
    1. Get Method
    ```java
    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception: ", e);
            return null;
        }
    }
   ```
   
   2. Set Method
   ```java
    public void set(String key, Object o, Long ttl) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
   ```
   
5. Use this Service in regular service files
```java
    public User getUserById(Long userId) {
        User redisResponse = redisService.get(userId.toString(), User.class);
        if (Objects.isNull(redisResponse)) {
            User u = repository.findById(userId).orElseThrow();
            redisService.set(userId.toString(), u, 60L);
            return u;
        } else {
            return redisResponse;
        }
    }
```
