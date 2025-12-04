package com.davidcamelo.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@Slf4j
@ImportHttpServices(basePackages = "com.davidcamelo")
@SpringBootApplication
@OpenAPIDefinition(
        servers = { @Server(url = "/products", description = "Product Service URL"), @Server(url = "/", description = "Default Server") },
        info = @Info(title = "OpenAPI definition", version = "v0"))
public class ProductServiceApplication {

    public static void main(String[] args) {
        log.info("Current java.home {}", System.getProperty("java.home"));
        log.info("Current java.vendor {}", System.getProperty("java.vendor"));
        log.info("Current java.vendor.url {}", System.getProperty("java.vendor.url"));
        log.info("Current java.version {}", System.getProperty("java.version"));

        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
