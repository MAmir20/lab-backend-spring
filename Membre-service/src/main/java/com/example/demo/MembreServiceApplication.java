package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.demo.entity.EnseignantChercheur;
import com.example.demo.entity.Etudiant;
import com.example.demo.entity.Membre;
import com.example.demo.repository.EnseignantRepository;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.MembreRepository;
import com.example.demo.service.IMembreService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MembreServiceApplication implements CommandLineRunner {
	@Autowired
	MembreRepository membreRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EnseignantRepository enseignantRepository;

	@Autowired
	IMembreService membreService;

	public static void main(String[] args) {
		SpringApplication.run(MembreServiceApplication.class, args);
	}

	public void run(String... args) throws Exception {

		// Créer et enregistrer deux étudiants
		Etudiant etd1 = Etudiant.builder().cin("123456").dateInscription(new Date()).birthDate(new Date())
				.diploma("mastère").email("amir.mezghani@enis.tn").pic("assets/img/team-1-800x800.jpg").password("pass1")
				.encadrant(null).cv("cv1").name("youssef abid").build();
		Etudiant etd2 = Etudiant.builder().cin("1239956").dateInscription(new Date()).birthDate(new Date())
				.diploma("mastère").email("etd2@gmail.com").password("pass2").encadrant(null).cv("cv2")
				.name("Youssef Abbes").build();
		etudiantRepository.save(etd1);
		etudiantRepository.save(etd2);

		// Créer et enregistrer deux enseignants chercheurs
		EnseignantChercheur ens1 = EnseignantChercheur.builder().cin("1239956").grade("prof").birthDate(new Date())
				.establishment("enis").email("ens2@gmail.com").password("pass11").cv("cv2").name("abida Marwa").build();
		EnseignantChercheur ens2 = EnseignantChercheur.builder().cin("1279956").grade("ing").birthDate(new Date())
				.pic("assets/img/team-1-800x800.jpg").establishment("enis").email("amir.mezghani@gmail.com").password("pass22")
				.cv("cv2").name("ammar maha").build();
		enseignantRepository.save(ens1);
		enseignantRepository.save(ens2);

		// Afficher la liste des membres dans le labo
		membreRepository.findAll().forEach(t -> System.out.println(t.getName()));

		// Chercher un membre par ID
		Membre x = membreRepository.findById(3L).get();
		System.out.println(x.getId() + ": " + x.getName());

		// Modifier un membre
		x.setName("Cherif");
		membreRepository.saveAndFlush(x);
		System.out.println(x.getId() + ": " + x.getName());

		// Supprimer un membre
		// membreRepository.delete(x);
		membreRepository.findAll().forEach(t -> System.out.println(t.getName()));
		// Update a Membre
		Membre m = membreService.findMembre(1L);
		m.setCv("cv1.pdf");
		membreService.updateMembre(m);
		// Delete a Membre
		// membreService.deleteMembre(2L);

		// Affecter encadrant a un etudiant
		System.out.println(membreService.affecterEncadrant(1L, 4L));

		// Afficher Etudiants encadrés par enseignant
		membreService.afficherEtudiantsEncadres(4L).forEach(t -> System.out.println(t.getName()));
	}
}