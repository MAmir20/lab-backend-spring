package com.example.demo;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.entity.EnseignantChercheur;
import com.example.demo.entity.Etudiant;
import com.example.demo.entity.Membre;
import com.example.demo.repository.EnseignantRepository;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.MembreRepository;
import com.example.demo.service.IMembreService;

@SpringBootApplication
@EnableDiscoveryClient
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
		Etudiant etd1=Etudiant.builder()
				.cin("123456")
				.dateInscription(new Date())
				.dateNaissance(new Date())
				.diplome("mastère")
				.email("etd1@gmail.com")
				.password("pass1")
				.encadrant(null)
				.cv("cv1")
				.nom("abid")
				.prenom("youssef")
				.build();
		Etudiant etd2=Etudiant.builder()
				.cin("1239956")
				.dateInscription(new Date())
				.dateNaissance(new Date())
				.diplome("mastère")
				.email("etd2@gmail.com")
				.password("pass2")
				.encadrant(null)
				.cv("cv2")
				.nom("abid")
				.prenom("youssef")
				.build();
		etudiantRepository.save(etd1);
		etudiantRepository.save(etd2);
		
		// Créer et enregistrer deux enseignants chercheurs
		EnseignantChercheur ens1=EnseignantChercheur.builder()
				.cin("1239956")
				.grade("prof")
				.dateNaissance(new Date())
				.etablissement("enis")
				.email("ens2@gmail.com")
				.password("pass11")
				.cv("cv2")
				.nom("abid")
				.prenom("youssef")
				.build();
		EnseignantChercheur ens2=EnseignantChercheur.builder()
				.cin("1279956")
				.grade("ing")
				.dateNaissance(new Date())
				.etablissement("enis")
				.email("ens2@gmail.com")
				.password("pass22")
				.cv("cv2")
				.nom("ammar")
				.prenom("maha")
				.build();
		enseignantRepository.save(ens1);
		enseignantRepository.save(ens2);
		
		
		// Afficher la liste des membres dans le labo
		membreRepository.findAll().forEach(t -> System.out.println(t.getPrenom() + " " + t.getNom()));
		
		// Chercher un membre par ID
		Membre x = membreRepository.findById(3L).get();
		System.out.println(x.getId()+": "+x.getPrenom()+" "+x.getNom());
		
		// Modifier un membre
		x.setNom("Cherif");
		membreRepository.saveAndFlush(x);
		System.out.println(x.getId()+": "+x.getPrenom()+" "+x.getNom());
		
		// Supprimer un membre
		membreRepository.delete(x);
		membreRepository.findAll().forEach(t -> System.out.println(t.getPrenom() + " " + t.getNom()));
		// Update a Membre
		Membre m= membreService.findMembre(1L);
		m.setCv("cv1.pdf");
		membreService.updateMembre(m);
		// Delete a Membre
		membreService.deleteMembre(2L);
		
		// Affecter encadrant a un etudiant
		System.out.println(membreService.affecterEncadrant(1L, 4L));
		
		// Afficher Etudiants encadrés par enseignant
		membreService.afficherEtudiantsEncadres(4L).forEach(t -> System.out.println(t.getPrenom()));
	}
}