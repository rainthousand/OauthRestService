package org.Oauth2RestServices;

import org.Oauth2RestServices.models.Course;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MappedTypes({Course.class})
@MapperScan("org.Oauth2RestServices.mapper")
@SpringBootApplication
@EnableCaching
public class Oauth2RestServicesApplication {

	private static Logger logger = LoggerFactory.getLogger(Oauth2RestServicesApplication.class);

	public static void main(String[] args) {
    	logger.info("Starting the application");
        System.out.println( "RestService Stack" );

		SpringApplication.run(Oauth2RestServicesApplication.class, args);
	}

}
