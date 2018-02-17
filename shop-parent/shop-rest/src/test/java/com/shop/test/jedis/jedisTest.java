package com.shop.test.jedis;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.JedisCluster;

/**
 * @author: chaodong.xi
 * @since: 2018/2/18
 */
public class jedisTest {

    //redis集群
    @Test
    public void testJedisCluster() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-jedis.xml");
        JedisCluster jedisCluster = (JedisCluster) applicationContext
                .getBean("jedisCluster");

        jedisCluster.set("name", "IKari");
        String value = jedisCluster.get("name");
        System.out.println(value);
    }

}
