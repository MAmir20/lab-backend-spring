package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entity.Publication;
import com.example.demo.service.IPublicationService;

@SpringBootApplication
@EnableDiscoveryClient
public class PublicationServiceApplication implements CommandLineRunner {

	@Autowired
	IPublicationService publicationService;
	@Autowired
	RepositoryRestConfiguration configuration;
	
	public static void main(String[] args) {
		SpringApplication.run(PublicationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		configuration.exposeIdsFor(Publication.class);
		Publication pub1 = Publication.builder()
				.type("article")
				.title("test")
				.lien("www.example.com")
				.date(new Date())
				.sourcepdf("file.pdf")
				.build();
		
		pub1 = publicationService.addPublication(pub1);
	}
	

}
