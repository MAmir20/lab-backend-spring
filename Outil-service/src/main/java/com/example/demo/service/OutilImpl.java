package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Outil;
import com.example.demo.repository.OutilRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OutilImpl implements IOutilService {
	OutilRepository outilRepository;

	public Outil addOutil(Outil p) {
		outilRepository.save(p);
		return p;
	}

	public void deleteOutil(Long id) {
		outilRepository.deleteById(id);
	}

	public Outil updateOutil(Outil p) {
		return outilRepository.saveAndFlush(p);
	}

	public Outil findOutil(Long id) {
		Outil p = (Outil) outilRepository.findById(id).get();
		return p;
	}

	public List<Outil> findAll() {
		return outilRepository.findAll();
	}

	public List<Outil> findByDate(Date date) {
		return outilRepository.findByDate(date);
	}

	public List<Outil> findBySource(String source) {
		return outilRepository.findBySource(source);
	}
}