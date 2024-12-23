package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Evenement;
import com.example.demo.repository.EvenementRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EvenementImpl implements IEvenementService {
	EvenementRepository evenementRepository;

	public Evenement addEvenement(Evenement p) {
		evenementRepository.save(p);
		return p;
	}

	public void deleteEvenement(Long id) {
		evenementRepository.deleteById(id);
	}

	public Evenement updateEvenement(Evenement p) {
		return evenementRepository.saveAndFlush(p);
	}

	public Evenement findEvenement(Long id) {
		Evenement p = (Evenement) evenementRepository.findById(id).get();
		return p;
	}

	public List<Evenement> findAll() {
		return evenementRepository.findAll();
	}

	public Evenement findByTitre(String titre) {
		return evenementRepository.findByTitre(titre);
	}

	public List<Evenement> findByDate(Date date) {
		return evenementRepository.findByDate(date);
	}

	public List<Evenement> findByLieu(String lieu) {
		return evenementRepository.findByLieu(lieu);
	}
}