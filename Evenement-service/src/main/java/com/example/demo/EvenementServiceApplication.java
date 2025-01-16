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
		Evenement evt = Evenement.builder()
				.title("test")
				.dateFin(new Date())
				.lieu("Sfax")
				.build();
		
		evt = evenementService.addEvenement(evt);
	}
	

}
