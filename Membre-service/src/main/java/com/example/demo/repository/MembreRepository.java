package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Membre;

public interface MembreRepository extends JpaRepository<Membre, Long>{
	Membre findByCin(String cin);
	List<Membre>findByNomStartingWith(String caractere);
	Membre findByEmail(String email);
	List<Membre> findByNom(String nom);
	@Query(value = "SELECT type_mbr FROM Membre WHERE id = :id", nativeQuery = true)
    String findDiscriminatorValueById(@Param("id") Long id);
}
