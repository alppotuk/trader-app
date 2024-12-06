package com.alppo.trader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderApplication.class, args);
		System.out.println("**********************************************************************");
		System.out.println("************* Server is started. Listening port 8080 ... *************");
		System.out.println("**********************************************************************");
	}
}
