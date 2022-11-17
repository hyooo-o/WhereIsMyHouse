package com.ssafy.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
public class WhereIsMyHomeSpringGwangju4ChoejiseongKimjeonghyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyHomeSpringGwangju4ChoejiseongKimjeonghyoApplication.class, args);
	}

}
