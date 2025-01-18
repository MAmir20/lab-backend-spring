package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{
	List<Publication> findByType(String type);
	List<Publication>findByTitleStartingWith(String caractere);
	List<Publication>findByLien(String lien);
	List<Publication>findBySourcepdf(String sourcepdf);
	
	@Query("SELECT p.type, COUNT(p) FROM Publication p GROUP BY p.type")
    List<Object[]> countPublicationsByType();
}
