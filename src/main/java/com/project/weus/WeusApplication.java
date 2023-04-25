package com.project.weus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import com.project.weus.DBConnection;

@SpringBootApplication
@Slf4j
public class WeusApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeusApplication.class, args);
        DBConnection.getInstance().Initailize();
    }
    
     @Bean
    public PropertiesFactoryBean configProperties(){ //config.properties를 읽어 필요한 속성들을 읽어들인다.
        log.debug("configProperties() called...");
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("/config.properties"));
        return bean;
    }

}
