package com.example.demo.service;

import java.util.List;
import java.util.Map;

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

	public List<Publication> findByType(String type) {
		return publicationRepository.findByType(type);
	}

	public List<Publication> findByTitre(String titre) {
		return publicationRepository.findByTitleStartingWith(titre);
	}

	public List<Publication> findByLien(String lien) {
		return publicationRepository.findByLien(lien);
	}

	@Override
	public List<Publication> filterPublications(Map<String, String> filters) {
		if (filters.containsKey("type")) {
            String type = filters.get("type");
            // Use the repository to filter publications by type
            return publicationRepository.findByType(type);
        }
		return null;
	}
	
	public Map<String, Long> countPublicationsByTypes(){
		//Map<String, Long> out = new Map<String, Long>;
		//List<String> types = publicationRepository.findDistinctTypes();
		//for(String type : types){
			
		//}
		return null;
	}
}