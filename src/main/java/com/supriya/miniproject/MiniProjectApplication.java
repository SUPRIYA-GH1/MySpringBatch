package com.supriya.miniproject;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MiniProjectApplication {

	@GetMapping("/docker")
	public String helloMsg(){
		return "Hello Dooooocker !!!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MiniProjectApplication.class, args);
//		System.exit(SpringApplication.exit(SpringApplication.run(MiniProjectApplication.class, args)));
	}


}
