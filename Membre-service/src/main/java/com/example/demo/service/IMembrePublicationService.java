package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.PublicationBean;
import com.example.demo.dto.PublicationMembreRequest;
import com.example.demo.dto.PublicationMembreResponse;

public interface IMembrePublicationService {
	public void affectPublicationToAuteur(Long idauteur, Long idpub);
	public List<PublicationBean> findAllPublicationByAuteur (Long idauteur);
	public PublicationMembreResponse createPublication(PublicationMembreRequest pub);
	public String deletePublication(Long idpub);
	public PublicationMembreResponse findPublicationFullByPubId(Long idpub);
	public List<PublicationMembreResponse> findPublicationsFullByMbrId(Long idmbr);
	public List<PublicationMembreResponse> findAllPublicationsFull();
	public PublicationMembreResponse updatePublication(Long id, PublicationMembreRequest pub); 
}
