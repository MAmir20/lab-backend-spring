package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Membre_Pub_Id;
import com.example.demo.entity.Membre_Publication;

public interface MembrePubRepository extends JpaRepository<Membre_Publication, Membre_Pub_Id> {
	@Query("select m from Membre_Publication m where m.id.auteur_id=:x")
	List<Membre_Publication> findpubId(@Param("x") Long autId);
}