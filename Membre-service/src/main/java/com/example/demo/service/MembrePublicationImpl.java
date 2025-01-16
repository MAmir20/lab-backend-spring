package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;
import com.example.demo.entity.Membre;
import com.example.demo.entity.Membre_Outil;
import com.example.demo.entity.Membre_Outil_Id;
import com.example.demo.entity.Membre_Pub_Id;
import com.example.demo.entity.Membre_Publication;
import com.example.demo.proxy.OutilProxyService;
import com.example.demo.proxy.PublicationProxyService;
import com.example.demo.repository.MembreOutilRepository;
import com.example.demo.repository.MembrePubRepository;
import com.example.demo.repository.MembreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MembrePublicationImpl implements IMembrePublicationService {
	private MembreRepository membreRepository;
	private MembrePubRepository membrePubRepository;
	private PublicationProxyService publicationProxyService;
	
	@Override
	public void affectPublicationToAuteur(Long idauteur, Long idpub) {
		Membre mbr = membreRepository.findById(idauteur).get();
		Membre_Publication mbs = new Membre_Publication();
		mbs.setMembre(mbr);
		mbs.setId(new Membre_Pub_Id(idpub, idauteur));
		membrePubRepository.save(mbs);
	}
	
	@Override
	public List<PublicationBean> findAllPublicationByAuteur(Long idauteur) {
		List<PublicationBean> pubs = new ArrayList<PublicationBean>();
		List<Membre_Publication> idpubs = membrePubRepository.findPubsByMembreId(idauteur);
		idpubs.forEach(s -> {
			System.out.println(s);
			pubs.add(publicationProxyService.findOnePublicationById(s.getId().getPublication_id()));
		});
		return pubs;
	}
	
	@Override
	public List<PublicationBean> createPublication(Long idMembre, PublicationBean pub) {
		Membre mbr = membreRepository.findById(idMembre).get();
		PublicationBean p = publicationProxyService.addPublication(pub);
		membrePubRepository.save(new Membre_Publication(new Membre_Pub_Id(mbr.getId(), p.getId()), mbr));
		return this.findAllPublicationByAuteur(idMembre);
	}

	@Override
	public String deletePublication(Long idMembre, Long idpub) {
		Optional<Membre_Publication> mbr_pub = membrePubRepository.findById(new Membre_Pub_Id(idpub, idMembre));
		if(!mbr_pub.isEmpty()) {
			publicationProxyService.deletePublication(idpub);
			membrePubRepository.delete(mbr_pub.get());
			return "Deleted Successfully";
		}
		return "ERROR: This member does not own this publication";
	}
	
	
}
