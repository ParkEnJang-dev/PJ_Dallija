package com.spring.dallija;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class DallijaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DallijaApplication.class, args);
	}


}
