package com.nativ.jsonapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication(proxyBeanMethods = false)
public class CrnkApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrnkApiServiceApplication.class, args);
	}

}
