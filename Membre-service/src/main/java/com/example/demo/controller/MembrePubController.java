package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;
import com.example.demo.dto.PublicationMembreRequest;
import com.example.demo.dto.PublicationMembreResponse;
import com.example.demo.entity.Membre;
import com.example.demo.service.IMembreOutilService;
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
	
	@DeleteMapping(value = "/membres/{m_id}/publications/{p_id}")
	public String deletePublication(@PathVariable Long m_id, @PathVariable Long p_id) {
		return membrePublicationService.deletePublication(m_id, p_id);
	}
	
	@GetMapping(value = "/membres/publications/{id}")
	public PublicationMembreResponse findMembresGroupByPublication(@PathVariable Long id) {
		return membrePublicationService.findMembreGroupByPublication(id);
	}
}
