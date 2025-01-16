package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.OutilBean;
import com.example.demo.entity.Membre;
import com.example.demo.service.IMembreOutilService;
import com.example.demo.service.IMembreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MembreOutilController {
	IMembreService membreService;
	IMembreOutilService membreOutilService;
	
	@GetMapping(value = "/membres/{m_id}/outils/{o_id}")
	public Membre affecterOutil(@PathVariable(name = "m_id") Long m_id, @PathVariable(name = "o_id") Long o_id) {
		membreOutilService.affectOutilToAuteur(m_id, o_id);
		Membre mbr = membreService.findMembre(m_id);
		mbr.setOutils(membreOutilService.findAllOutilparauteur(m_id));
		return mbr;
	}
	
	@GetMapping(value = "/membres/{id}/outils")
	public List<OutilBean> findOutils(@PathVariable Long id) {
		return membreOutilService.findAllOutilparauteur(id);
	}
	
	@PostMapping(value = "/membres/{id}/outils")
	public List<OutilBean> createOutil(@PathVariable Long id, @RequestBody OutilBean outil){
		return membreOutilService.createOutil(id, outil);
	}
	
	@DeleteMapping(value = "/membres/{m_id}/outils/{o_id}")
	public String deleteOutil(@PathVariable Long m_id, @PathVariable Long o_id) {
		return membreOutilService.deleteOutil(m_id, o_id);
	}
}
