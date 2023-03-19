package cn.evolvefield.kook.onebot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Project: kook
 * Author: cnlimiter
 * Date: 2023/2/6 0:33
 * Description:
 */
@SpringBootApplication
public class OneBotKooK {

    public static Logger logger = LoggerFactory.getLogger("OneBot-Kook");
    public static void main(String[] args) {
        SpringApplication.run(OneBotKooK.class, args);
    }

}

