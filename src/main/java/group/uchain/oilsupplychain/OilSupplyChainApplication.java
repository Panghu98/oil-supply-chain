package group.uchain.oilsupplychain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author panghu
 */
@EnableCaching
@EnableScheduling
@MapperScan("group.uchain.oilsupplychain.mapper")
@SpringBootApplication
public class OilSupplyChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(OilSupplyChainApplication.class, args);
    }

}
