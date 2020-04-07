package com.textstats.server;

import com.textstats.server.storage.StorageProperties;
import com.textstats.server.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	// CommandLineRunner to delete existing upload-dir and create them during application start/restart
	@Bean
	public CommandLineRunner initialize(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.createDir();
		};
	}
}
