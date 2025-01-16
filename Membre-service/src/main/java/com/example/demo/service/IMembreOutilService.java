package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.OutilBean;
import com.example.demo.entity.Membre;

public interface IMembreOutilService {
	public void affectOutilToAuteur(Long idauteur, Long idoutil);
	public List<OutilBean> findAllOutilparauteur (Long idauteur);
	public List<OutilBean> createOutil(Long idMembre, OutilBean outil);
	public String deleteOutil(Long idMembre, Long idOutil);

}
