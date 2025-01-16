package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Outil;
import com.example.demo.service.IOutilService;

@RestController
public class OutilRestController {
	@Autowired
	IOutilService outilService;

	@RequestMapping(value = "/outils", method = RequestMethod.GET)
	public List<Outil> findOutils() {
		return outilService.findAll();
	}

	@GetMapping(value = "/outils/{id}")
	public Outil findOneOutilById(@PathVariable Long id) {
		return outilService.findOutil(id);
	}

	@PostMapping(value = "/outils")
	public Outil addOutil(@RequestBody Outil p) {
		return outilService.addOutil(p);
	}

	@DeleteMapping(value = "/outils/{id}")
	public void deleteOutil(@PathVariable Long id) {
		outilService.deleteOutil(id);
	}

	@PatchMapping(value = "/outils/{id}")
	public Outil updateOutil(@PathVariable Long id, @RequestBody Outil p) {
		p.setId(id);
		return outilService.updateOutil(p);
	}
}
