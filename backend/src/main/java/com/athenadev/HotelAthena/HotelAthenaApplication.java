package com.athenadev.HotelAthena;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelAthenaApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(HotelAthenaApplication.class, args);
		System.out.println("hello athena");
	}

}
