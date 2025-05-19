package com.br.tjrj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.br.tjrj", "com.br.tjrj.mapper"})
public class DesafioTjrjApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTjrjApplication.class, args);
	}

}
