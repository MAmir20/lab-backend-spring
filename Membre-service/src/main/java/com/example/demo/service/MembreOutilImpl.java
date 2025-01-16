package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.bean.OutilBean;
import com.example.demo.entity.Membre;
import com.example.demo.entity.Membre_Outil;
import com.example.demo.entity.Membre_Outil_Id;
import com.example.demo.entity.Membre_Pub_Id;
import com.example.demo.entity.Membre_Publication;
import com.example.demo.proxy.OutilProxyService;
import com.example.demo.repository.MembreOutilRepository;
import com.example.demo.repository.MembrePubRepository;
import com.example.demo.repository.MembreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MembreOutilImpl implements IMembreOutilService {
	private MembreRepository membreRepository;
	private MembreOutilRepository membreOutilRepository;
	private OutilProxyService outilProxyService;

	public void affectOutilToAuteur(Long idauteur, Long idoutil) {
		Membre mbr = membreRepository.findById(idauteur).get();
		Membre_Outil mbs = new Membre_Outil();
		mbs.setMembre(mbr);
		mbs.setId(new Membre_Outil_Id(idoutil, idauteur));
		membreOutilRepository.save(mbs);
	}

	public List<OutilBean> findAllOutilparauteur(Long idauteur) {
		List<OutilBean> outils = new ArrayList<OutilBean>();
		List<Membre_Outil> idoutils = membreOutilRepository.findOutilsByMembreId(idauteur);
		idoutils.forEach(s -> {
			System.out.println(s);
			outils.add(outilProxyService.findOneOutilById(s.getId().getOutil_id()));
		});
		return outils;
	}

	@Override
	public List<OutilBean> createOutil(Long idMembre, OutilBean outil) {
		Membre mbr = membreRepository.findById(idMembre).get();
		OutilBean o = outilProxyService.addOutil(outil);
		membreOutilRepository.save(new Membre_Outil(new Membre_Outil_Id(mbr.getId(), o.getId()), mbr));
		return this.findAllOutilparauteur(idMembre);
	}
	
	@Override
	public String deleteOutil(Long idMembre, Long idoutil) {
		Optional<Membre_Outil> mbr_outil = membreOutilRepository.findById(new Membre_Outil_Id(idoutil, idMembre));
		if(!mbr_outil.isEmpty()) {
			outilProxyService.deleteOutil(idoutil);
			membreOutilRepository.delete(mbr_outil.get());
			return "Deleted Successfully";
		}
		return "ERROR: This member does not own this tool";
	}

}
