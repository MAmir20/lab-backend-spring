package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.entity.Evenement;
import com.example.demo.service.IEvenementService;

@SpringBootApplication
@EnableDiscoveryClient
public class EvenementServiceApplication implements CommandLineRunner {

	@Autowired
	IEvenementService evenementService;
	
	public static void main(String[] args) {
		SpringApplication.run(EvenementServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create and save multiple Evenement objects
		Evenement evt1 = Evenement.builder()
		    .title("Event 1")
		    .dateDebut(new Date())
		    .dateFin(new Date())
		    .lieu("Sfax")
		    .build();
		evenementService.addEvenement(evt1);

		Evenement evt2 = Evenement.builder()
		    .title("Event 2")
		    .dateDebut(new Date())
		    .dateFin(new Date())
		    .lieu("Tunis")
		    .build();
		evenementService.addEvenement(evt2);

		Evenement evt3 = Evenement.builder()
		    .title("Event 3")
		    .dateDebut(new Date())
		    .dateFin(new Date())
		    .lieu("Sousse")
		    .build();
		evenementService.addEvenement(evt3);

		Evenement evt4 = Evenement.builder()
		    .title("Event 4")
		    .dateDebut(new Date())
		    .dateFin(new Date())
		    .lieu("Gabes")
		    .build();
		evenementService.addEvenement(evt4);

		Evenement evt5 = Evenement.builder()
		    .title("Event 5")
		    .dateDebut(new Date())
		    .dateFin(new Date())
		    .lieu("Sfax")
		    .build();
		evenementService.addEvenement(evt5);
	}
	

}
