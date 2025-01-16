package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;
import com.example.demo.entity.Membre;

public interface IMembrePublicationService {
	public void affectPublicationToAuteur(Long idauteur, Long idpub);
	public List<PublicationBean> findAllPublicationByAuteur (Long idauteur);
	public List<PublicationBean> createPublication(Long idMembre, PublicationBean pub);
	public String deletePublication(Long idMembre, Long idpub);
}
