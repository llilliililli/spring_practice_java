package com.llilliililli.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PracticeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeJavaApplication.class, args);
	}

}
