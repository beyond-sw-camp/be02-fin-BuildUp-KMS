package com.example.bootshelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
public class BootshelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootshelfApplication.class, args);
	}

}
