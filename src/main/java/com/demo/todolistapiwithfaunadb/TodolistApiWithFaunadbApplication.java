package com.demo.todolistapiwithfaunadb;

import com.faunadb.client.FaunaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class TodolistApiWithFaunadbApplication {

	@Value("${fauna-db.secret}")
	private String serverKey;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApiWithFaunadbApplication.class, args);
	}



	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public FaunaClient faunaConfiguration() {
		FaunaClient faunaClient = FaunaClient.builder()
				.withSecret(serverKey)
				.build();

		return faunaClient;
	}
}
