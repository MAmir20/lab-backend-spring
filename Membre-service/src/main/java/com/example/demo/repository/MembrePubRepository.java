package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Membre_Pub_Id;
import com.example.demo.entity.Membre_Publication;

public interface MembrePubRepository extends JpaRepository<Membre_Publication, Membre_Pub_Id> {
	@Query("select m from Membre_Publication m where m.id.membre_id=:x")
	List<Membre_Publication> findPubsByMembreId(@Param("x") Long autId);
	
	@Query("select m from Membre_Publication m where m.id.publication_id=:x")
	List<Membre_Publication> findPubsByPubId(@Param("x") Long pubId);
	
	@Query("SELECT DISTINCT(m.id.membre_id) FROM Membre_Publication m")
	List<Long> findDistinctMembreIds();

	@Query("SELECT DISTINCT(m.id.publication_id) FROM Membre_Publication m")
	List<Long> findDistinctPublicationIds();
}