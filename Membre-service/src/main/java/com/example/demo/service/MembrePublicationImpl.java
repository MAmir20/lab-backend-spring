package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;
import com.example.demo.dto.PublicationMembreRequest;
import com.example.demo.dto.PublicationMembreResponse;
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
	public PublicationMembreResponse createPublication(PublicationMembreRequest pub) {
		PublicationBean p = PublicationBean.builder().type(pub.getType())
				.title(pub.getTitle())
				.lien(pub.getLien())
				.date(pub.getDate())
				.sourcepdf(pub.getSourcepdf())
				.build();
		p = publicationProxyService.addPublication(p);
		for(Long m_id : pub.getMembres()) {
			if(membreRepository.existsById(m_id)) 
				this.affectPublicationToAuteur(m_id, p.getId());
		}
		return this.findPublicationFull(p.getId());
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

	@Override
	public PublicationMembreResponse findPublicationFull(Long idpub) {
		PublicationBean pub = publicationProxyService.findOnePublicationById(idpub);
		PublicationMembreResponse out = PublicationMembreResponse.builder().id(idpub)
				.type(pub.getType())
				.title(pub.getTitle())
				.lien(pub.getLien())
				.date(pub.getDate())
				.sourcepdf(pub.getSourcepdf())
				.build();
		List<Membre_Publication> pub_mbrs = membrePubRepository.findPubsByPubId(idpub);
		List<Membre> mbrs = new ArrayList<Membre>();
		pub_mbrs.forEach(s -> {
			mbrs.add(membreRepository.findById(s.getId().getMembre_id()).get());
		});
		out.setMembres(mbrs);
		return out;
	}

	@Override
	public List<PublicationMembreResponse> findAllPublicationsFull() {
		List<Long> pub_ids = membrePubRepository.findDistinctPublicationIds();
		List<PublicationMembreResponse> pubsFull = new ArrayList<PublicationMembreResponse>();
		for(Long pub_id : pub_ids) {
			pubsFull.add(this.findPublicationFull(pub_id));
		}
		return pubsFull;
	}
}
