package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.OutilBean;
import com.example.demo.dto.OutilMembreRequest;
import com.example.demo.dto.OutilMembreResponse;

public interface IMembreOutilService {
	public void affectOutilToAuteur(Long idauteur, Long idoutil);
	public List<OutilBean> findAllOutilparauteur (Long idauteur);
	public OutilMembreResponse createOutil(OutilMembreRequest outil);
	public boolean deleteOutil(Long idOutil);
	public OutilMembreResponse findOutilFullByOutilId(Long idOutil);
	public List<OutilMembreResponse> findOutilsFullByMbrId(Long idmbr);
	public List<OutilMembreResponse> findAllOutilsFull();
	public OutilMembreResponse updateOutil(Long id, OutilMembreRequest outil); 
}
