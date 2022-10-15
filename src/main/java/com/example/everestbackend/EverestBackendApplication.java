package com.example.everestbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@EnableSwagger2
@Configuration
public class EverestBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(EverestBackendApplication.class, args);
	}

//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.everestbackend.controller")).build();
//    }
}
