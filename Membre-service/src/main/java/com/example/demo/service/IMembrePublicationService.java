package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;
import com.example.demo.dto.PublicationMembreRequest;
import com.example.demo.dto.PublicationMembreResponse;
import com.example.demo.entity.Membre;

public interface IMembrePublicationService {
	public void affectPublicationToAuteur(Long idauteur, Long idpub);
	public List<PublicationBean> findAllPublicationByAuteur (Long idauteur);
	public PublicationMembreResponse createPublication(PublicationMembreRequest pub);
	public String deletePublication(Long idMembre, Long idpub);
	public PublicationMembreResponse findPublicationFull(Long idpub);
	public List<PublicationMembreResponse> findAllPublicationsFull(); 
}
