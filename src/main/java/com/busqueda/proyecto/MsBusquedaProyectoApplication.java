package com.busqueda.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Project API", 
							version = "1.0", 
							description = "SCIENTIST, ORGANIZATION AND USER INFO"))
public class MsBusquedaProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBusquedaProyectoApplication.class, args);
	}

}
