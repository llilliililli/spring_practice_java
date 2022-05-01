package com.llilliililli.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication //(exclude = DataSourceAutoConfiguration.class) // exclude로 인해 @Autowired 적용 불가
public class PracticeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeJavaApplication.class, args);
	}

}
