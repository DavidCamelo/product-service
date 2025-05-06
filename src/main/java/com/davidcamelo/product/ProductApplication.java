package com.davidcamelo.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(
		servers = { @Server(url = "/product", description = "Product Service URL"), @Server(url = "/", description = "Default Server") },
		info = @Info(title = "OpenAPI definition", version = "v0"))
public class ProductApplication {

	public static void main(String[] args) {
		log.info("Current java.home {}", System.getProperty("java.home"));
		log.info("Current java.vendor {}", System.getProperty("java.vendor"));
		log.info("Current java.vendor.url {}", System.getProperty("java.vendor.url"));
		log.info("Current java.version {}", System.getProperty("java.version"));

		SpringApplication.run(ProductApplication.class, args);
	}
}
