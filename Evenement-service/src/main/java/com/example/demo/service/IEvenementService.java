package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Evenement;

public interface IEvenementService {
	//Crud sur les evenements
	public Evenement addEvenement(Evenement p);
	public void deleteEvenement(Long id) ;
	public Evenement updateEvenement(Evenement p) ;
	public Evenement findEvenement(Long id) ;
	public List<Evenement> findAll();
	
	//Filtrage par propriété
	public Evenement findByTitre(String titre);
	public List<Evenement> findByDateFin(Date date);
	public List<Evenement> findByDateDebut(Date date);

	public List<Evenement> findByLieu(String lieu);
	
}