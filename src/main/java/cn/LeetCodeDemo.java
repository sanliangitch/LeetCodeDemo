package cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wulang
 * @create 2019/9/12/14:58
 */
@EnableScheduling
@SpringBootApplication
public class LeetCodeDemo {
    public static void main(String[] args) {
        SpringApplication.run(LeetCodeDemo.class,args);
    }
}
