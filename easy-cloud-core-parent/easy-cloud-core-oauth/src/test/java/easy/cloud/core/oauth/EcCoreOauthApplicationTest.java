package easy.cloud.core.oauth;

import org.crazycake.shiro.RedisManager;

/**
 * @author daiqi
 * @create 2018-06-23 16:21
 */
public class EcCoreOauthApplicationTest {
    public static void main(String[] args) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("120.78.74.169");
        redisManager.setPort(6379);
        byte[] b = redisManager.get("d353c7b9-7425-4818-a4b7-5641183074a7".getBytes());
    }
}
