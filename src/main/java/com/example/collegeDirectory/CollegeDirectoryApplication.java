package com.example.collegeDirectory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.collegeDirectory"})
public class CollegeDirectoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeDirectoryApplication.class, args);
	}

}
