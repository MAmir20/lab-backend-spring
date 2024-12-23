package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Outil;

public interface IOutilService {
	//Crud sur les outils
	public Outil addOutil(Outil p);
	public void deleteOutil(Long id) ;
	public Outil updateOutil(Outil p) ;
	public Outil findOutil(Long id) ;
	public List<Outil> findAll();
	
	//Filtrage par propriété
	public List<Outil> findByDate(Date date);
	public List<Outil> findBySource(String source);
	
}