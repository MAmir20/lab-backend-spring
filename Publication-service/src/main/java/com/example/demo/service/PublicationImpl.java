package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Publication;
import com.example.demo.repository.PublicationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublicationImpl implements IPublicationService {
	PublicationRepository publicationRepository;

	public Publication addPublication(Publication p) {
		publicationRepository.save(p);
		return p;
	}

	public void deletePublication(Long id) {
		publicationRepository.deleteById(id);
	}

	public Publication updatePublication(Publication p) {
		return publicationRepository.saveAndFlush(p);
	}

	public Publication findPublication(Long id) {
		Publication p = (Publication) publicationRepository.findById(id).get();
		return p;
	}

	public List<Publication> findAll() {
		return publicationRepository.findAll();
	}

	public Publication findByType(String type) {
		return publicationRepository.findByType(type);
	}

	public List<Publication> findByTitre(String titre) {
		return publicationRepository.findByTitleStartingWith(titre);
	}

	public List<Publication> findByLien(String lien) {
		return publicationRepository.findByLien(lien);
	}
}