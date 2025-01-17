package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.PublicationBean;
import com.example.demo.dto.PublicationMembreRequest;
import com.example.demo.dto.PublicationMembreResponse;
import com.example.demo.entity.Membre;
import com.example.demo.service.IMembrePublicationService;
import com.example.demo.service.IMembreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MembrePubController {
	IMembreService membreService;
	IMembrePublicationService membrePublicationService;
	
	@GetMapping(value = "/membres/{m_id}/publications/{p_id}")
	public Membre affecterPublication(@PathVariable(name = "m_id") Long m_id, @PathVariable(name = "p_id") Long p_id) {
		membrePublicationService.affectPublicationToAuteur(m_id, p_id);
		Membre mbr = membreService.findMembre(m_id);
		mbr.setPubs(membrePublicationService.findAllPublicationByAuteur(m_id));
		return mbr;
	}
	
	@GetMapping(value = "/membres/{id}/publications")
	public List<PublicationBean> findPublication(@PathVariable Long id) {
		return membrePublicationService.findAllPublicationByAuteur(id);
	}
	
	@PostMapping(value = "/membres/publications")
	public PublicationMembreResponse createPublication(@RequestBody PublicationMembreRequest pub){
		return membrePublicationService.createPublication(pub);
	}
	
	@PutMapping(value = "/membres/publications/{id}")
	public PublicationMembreResponse updatePublication(@PathVariable Long id, @RequestBody PublicationMembreRequest pub){
		return membrePublicationService.updatePublication(id, pub);
	}
	
	@DeleteMapping(value = "/membres/publications/{p_id}")
	public String deletePublication(@PathVariable Long p_id) {
		return membrePublicationService.deletePublication(p_id);
	}
	
	/* ----------------------------------------- */
	
	@GetMapping(value = "/membres/publications/{id}/full")
	public PublicationMembreResponse findPublicationFullByPubId(@PathVariable Long id) {
		return membrePublicationService.findPublicationFullByPubId(id);
	}
	
	@GetMapping(value = "/membres/{id}/publications/full")
	public List<PublicationMembreResponse> findPublicationsFullByMbrId(@PathVariable Long id) {
		return membrePublicationService.findPublicationsFullByMbrId(id);
	}
	
	@GetMapping(value = "/membres/publications/full")
	public List<PublicationMembreResponse> findPublicationsFull() {
		return membrePublicationService.findAllPublicationsFull();
	}
}
