package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long>{
	Evenement findByTitle(String titre);
	List<Evenement>findByLieu(String lieu);
	List<Evenement>findByDate(Date date);
}
