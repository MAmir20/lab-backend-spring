package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{
	Publication findByType(String type);
	List<Publication>findByTitreStartingWith(String caractere);
	List<Publication>findByLien(String lien);
	List<Publication>findBySourcepdf(String sourcepdf);
}
