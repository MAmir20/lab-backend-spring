package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.EnseignantChercheur;
import com.example.demo.entity.Etudiant;
import com.example.demo.entity.Membre;

public interface IMembreService {
	//Crud sur les membres
	public Membre addMembre(Membre m);
	public void deleteMembre(Long id) ;
	public Membre updateMembre(Membre p) ;
	public Membre patchMembre(Long id, Map<String, Object> updates);
	public Membre findMembre(Long id) ;
	public List<Membre> findAll();
	
	//Filtrage par propriété
	public Membre findByCin(String cin);
	public Membre findByEmail(String email);
	public List<Membre> findByName(String nom);
	
	//recherche spécifique des étudiants
	public List<Etudiant> findByDiploma(String diplome);
	
	//recherche spécifique des enseignants
	public List<EnseignantChercheur> findByGrade(String grade);
	public List<EnseignantChercheur> findByEstablishment(String	establishment);
	
	//affecter encadrant
	public String affecterEncadrant(Long idEtd, Long idEns);
	
	//afficher les etudiant encadré par l'encadrant specifie
	public List<Etudiant> afficherEtudiantsEncadres(Long idEns);
}