package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EnseignantChercheur;
import com.example.demo.entity.Etudiant;
import com.example.demo.entity.Membre;
import com.example.demo.proxy.OutilProxyService;
import com.example.demo.proxy.PublicationProxyService;
import com.example.demo.repository.EnseignantRepository;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.MembreOutilRepository;
import com.example.demo.repository.MembrePubRepository;
import com.example.demo.repository.MembreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MembreImpl implements IMembreService {
	MembreRepository membreRepository;
	EtudiantRepository etudiantRepository;
	EnseignantRepository enseignantRepository;
	
	MembrePubRepository membrePubRepository;
	PublicationProxyService publicationProxyService;
	
	MembreOutilRepository membreOutilRepository;
	OutilProxyService outilProxyService;
	
	public Membre addMembre(Membre m) {
		membreRepository.save(m);
		return m;
	}

	public void deleteMembre(Long id) {
		membreRepository.deleteById(id);
	}

	public Membre updateMembre(Membre m) {
		return membreRepository.saveAndFlush(m);
	}
	
	@Override
	public Membre patchMembre(Long id, Map<String, Object> updates) {
	    // Find the member by ID
	    Membre membre = membreRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));

	    // Iterate through the updates and apply them
	    updates.forEach((key, value) -> {
	        try {
	            // Use reflection to get the field and make it accessible
	            Field field = Membre.class.getDeclaredField(key);
	            field.setAccessible(true);
	            field.set(membre, value);
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            throw new RuntimeException("Failed to update field: " + key, e);
	        }
	    });

	    // Save the updated member entity
	    return membreRepository.save(membre);
	}

	public Membre findMembre(Long id) {
		Membre m = (Membre) membreRepository.findById(id).get();
		return m;
	}

	public List<Membre> findAll() {
		return membreRepository.findAll();
	}

	public Membre findByCin(String cin) {
		return membreRepository.findByCin(cin);
	}

	public Membre findByEmail(String email) {
		return membreRepository.findByEmail(email);
	}

	public List<Membre> findByName(String name) {
		return membreRepository.findByName(name);
	}

	public List<Etudiant> findByDiploma(String diploma) {
		return etudiantRepository.findByDiploma(diploma);
	}

	public List<EnseignantChercheur> findByGrade(String grade) {
		return enseignantRepository.findByGrade(grade);
	}

	public List<EnseignantChercheur> findByEstablishment(String establishment) {
		return enseignantRepository.findByEstablishment(establishment);
	}

	public String affecterEncadrant(Long idEtd, Long idEns) {
		Etudiant etd = (Etudiant) this.findMembre(idEtd);
		EnseignantChercheur encadrant = (EnseignantChercheur) this.findMembre(idEns);
		etd.setEncadrant(encadrant);
		this.updateMembre(etd);
		return "Encadrant " + encadrant.getName() + " affecté avec succés à l'étudiant "
				+ etd.getName();
	}

	public List<Etudiant> afficherEtudiantsEncadres(Long idEns) {
		EnseignantChercheur ens = enseignantRepository.findById(idEns).get();
		return etudiantRepository.findByEncadrant(ens);
	}
	
	public Membre authenticate(String email) throws Exception {
        Membre membre = membreRepository.findByEmail(email);
        if (membre == null) {
            throw new Exception("Unauthorized: Email not found");
        }
        return membre;
    }

	@Override
	public Map<String, Long> countStudentsByDiplomas() {
        List<Object[]> results = etudiantRepository.countStudentsByDiplomas();
        Map<String, Long> out = new HashMap<String, Long>();
        for (Object[] result : results) {
            String type = (String) result[0]; // Type is at index 0
            Long count = (Long) result[1];    // Count is at index 1
            out.put(type, count);
        }
        return out;
    }

	@Override
	public Map<String, Long> countProfessorsByEstablishments() {
		List<Object[]> results = enseignantRepository.countProfessorsByEstablishments();
        Map<String, Long> out = new HashMap<String, Long>();
        for (Object[] result : results) {
            String type = (String) result[0]; // Type is at index 0
            Long count = (Long) result[1];    // Count is at index 1
            out.put(type, count);
        }
        return out;
	}
	
}
