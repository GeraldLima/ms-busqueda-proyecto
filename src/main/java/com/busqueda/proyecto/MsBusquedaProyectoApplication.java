package com.busqueda.proyecto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Project API", 
							version = "1.6", 
							description = "SCIENTIST, ORGANIZATION AND USER INFO"))
public class MsBusquedaProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBusquedaProyectoApplication.class, args);
	}
	
	@Value("{allowed.origin}")
	private String allowedOrigin;
	
	@Bean
	 public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
		        registry.addMapping("/**")
		                .allowedOrigins("http://localhost:4200")
		                .allowedMethods("GET", "POST", "PUT", "DELETE")
		                .maxAge(3600);
			}
		};
		
    }

}
