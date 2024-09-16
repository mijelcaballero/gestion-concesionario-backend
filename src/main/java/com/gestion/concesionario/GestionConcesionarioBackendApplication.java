package com.gestion.concesionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gestion.inventario", "net.javaguides.springboot"})
public class GestionConcesionarioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionConcesionarioBackendApplication.class, args);
	}

}
