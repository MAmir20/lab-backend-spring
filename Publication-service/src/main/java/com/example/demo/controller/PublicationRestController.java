package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Publication;
import com.example.demo.service.IPublicationService;

@RestController
public class PublicationRestController {
	@Autowired
	IPublicationService publicationService;

	@RequestMapping(value = "/publications", method = RequestMethod.GET)
	public List<Publication> findPublications() {
		return publicationService.findAll();
	}

	@GetMapping(value = "/publications/{id}")
	public Publication findOnePublicationById(@PathVariable Long id) {
		return publicationService.findPublication(id);
	}

	@PostMapping(value = "/publications")
	public Publication addPublication(@RequestBody Publication p) {
		return publicationService.addPublication(p);
	}

	@DeleteMapping(value = "/publications/{id}")
	public void deletePublication(@PathVariable Long id) {
		publicationService.deletePublication(id);
	}

	@PutMapping(value = "/publications/{id}")
	public Publication updatePublication(@PathVariable Long id, @RequestBody Publication p) {
		p.setId(id);
		return publicationService.updatePublication(p);
	}
	
	@GetMapping(value = "/publications/filter")
	public List<Publication> filterPublications(@RequestParam Map<String, String> filters){
		return publicationService.filterPublications(filters);
	}
	
	@GetMapping(value = "/publications/countByType")
    public ResponseEntity<Map<String, Long>> getPublicationCountByType() {
        Map<String, Long> counts = publicationService.countPublicationsByTypes();
        return ResponseEntity.ok(counts);
    }
}
