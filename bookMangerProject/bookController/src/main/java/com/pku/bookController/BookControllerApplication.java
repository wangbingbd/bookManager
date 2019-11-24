package com.pku.bookController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.pku")
public class BookControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookControllerApplication.class, args);
	}

}
