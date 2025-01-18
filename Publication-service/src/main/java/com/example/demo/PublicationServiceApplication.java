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
				.type("Article")
				.title("test1")
				.lien("www.example.com")
				.date(new Date())
				.sourcepdf("file.pdf")
				.build();
		Publication pub2 = Publication.builder()
				.type("Research")
				.title("test2")
				.lien("www.example.com")
				.date(new Date())
				.sourcepdf("file.pdf")
				.build();
		Publication pub3 = Publication.builder()
				.type("Conference Paper")
				.title("test3")
				.lien("www.example.com")
				.date(new Date())
				.sourcepdf("file.pdf")
				.build();
		Publication pub4 = Publication.builder()
				.type("Article")
				.title("test4")
				.lien("www.example.com")
				.date(new Date())
				.sourcepdf("file.pdf")
				.build();
		Publication pub5 = Publication.builder()
				.type("Research")
				.title("test5")
				.lien("www.example.com")
				.date(new Date())
				.sourcepdf("file.pdf")
				.build();
		Publication pub6 = Publication.builder()
				.type("Conference Paper")
				.title("test6")
				.lien("www.example.com")
				.date(new Date())
				.sourcepdf("file.pdf")
				.build();
		
		pub1 = publicationService.addPublication(pub1);
		pub2 = publicationService.addPublication(pub2);
		pub3 = publicationService.addPublication(pub3);
		pub4 = publicationService.addPublication(pub4);
		pub5 = publicationService.addPublication(pub5);
		pub6 = publicationService.addPublication(pub6);
		
	}
	

}
