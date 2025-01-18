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
				.source("file1.pdf")
				.build();
		Outil out2 = Outil.builder()
				.date(new Date())
				.source("file2.pdf")
				.build();
		Outil out3 = Outil.builder()
				.date(new Date())
				.source("file3.pdf")
				.build();
		Outil out4 = Outil.builder()
				.date(new Date())
				.source("file4.pdf")
				.build();
		Outil out5 = Outil.builder()
				.date(new Date())
				.source("file5.pdf")
				.build();
		Outil out6 = Outil.builder()
				.date(new Date())
				.source("file6.pdf")
				.build();
		
		out1 = outilService.addOutil(out1);
		out2 = outilService.addOutil(out2);
		out3 = outilService.addOutil(out3);
		out4 = outilService.addOutil(out4);
		out5 = outilService.addOutil(out5);
		out6 = outilService.addOutil(out6);
	}
	

}
