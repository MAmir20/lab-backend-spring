package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.entity.Outil;
import com.example.demo.service.IOutilService;

@SpringBootApplication
@EnableDiscoveryClient
public class OutilServiceApplication implements CommandLineRunner {

	@Autowired
	IOutilService outilService;
	
	public static void main(String[] args) {
		SpringApplication.run(OutilServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Outil out1 = Outil.builder()
				.date(new Date())
				.source("file.pdf")
				.build();
		
		out1 = outilService.addOutil(out1);
	}
	

}
