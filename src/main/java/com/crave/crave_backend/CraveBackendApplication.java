package com.crave.crave_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CraveBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraveBackendApplication.class, args);
	}
}
