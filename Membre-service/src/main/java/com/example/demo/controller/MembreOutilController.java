package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.OutilBean;
import com.example.demo.dto.OutilMembreRequest;
import com.example.demo.dto.OutilMembreResponse;
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

	@PostMapping(value = "/membres/outils")
	public OutilMembreResponse createOutil(@RequestBody OutilMembreRequest outil) {
		return membreOutilService.createOutil(outil);
	}

	@PutMapping(value = "/membres/outils/{id}")
	public OutilMembreResponse updateOutil(@PathVariable Long id, @RequestBody OutilMembreRequest outil) {
		return membreOutilService.updateOutil(id, outil);
	}

	@DeleteMapping(value = "/membres/outils/{o_id}")
	public ResponseEntity<Void> deleteOutil(@PathVariable Long o_id) {
		if(membreOutilService.deleteOutil(o_id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/* ----------------------------------------- */

	@GetMapping(value = "/membres/outils/{id}/full")
	public OutilMembreResponse findOutilFullByOutilId(@PathVariable Long id) {
		return membreOutilService.findOutilFullByOutilId(id);
	}

	@GetMapping(value = "/membres/{id}/outils/full")
	public List<OutilMembreResponse> findOutilsFullByMbrId(@PathVariable Long id) {
		return membreOutilService.findOutilsFullByMbrId(id);
	}

	@GetMapping(value = "/membres/outils/full")
	public List<OutilMembreResponse> findOutilsFull() {
		return membreOutilService.findAllOutilsFull();
	}
}
