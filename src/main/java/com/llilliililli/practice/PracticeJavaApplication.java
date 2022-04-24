package com.llilliililli.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@ComponentScan(basePackages = {"com.llilliililli.practice.repository.ArticleRepository"})
public class PracticeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeJavaApplication.class, args);
	}

}
