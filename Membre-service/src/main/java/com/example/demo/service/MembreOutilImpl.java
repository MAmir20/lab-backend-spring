package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bean.OutilBean;
import com.example.demo.dto.OutilMembreRequest;
import com.example.demo.dto.OutilMembreResponse;
import com.example.demo.entity.Membre;
import com.example.demo.entity.Membre_Outil;
import com.example.demo.entity.Membre_Outil_Id;
import com.example.demo.proxy.OutilProxyService;
import com.example.demo.repository.MembreOutilRepository;
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
	public OutilMembreResponse createOutil(OutilMembreRequest outil) {
		OutilBean o = OutilBean.builder()
				.date(outil.getDate())
				.source(outil.getSource())
				.build();
		o = outilProxyService.addOutil(o);
		for(Long m_id : outil.getMembres()) {
			if(membreRepository.existsById(m_id)) 
				this.affectOutilToAuteur(m_id, o.getId());
		}
		return this.findOutilFullByOutilId(o.getId());
	}
	
	@Override
	public String deleteOutil(Long idoutil) {
		List<Membre_Outil> mbr_outils = membreOutilRepository.findOutilsByOutilId(idoutil);
		if(!mbr_outils.isEmpty()) {
			membreOutilRepository.deleteAll(mbr_outils);
			outilProxyService.deleteOutil(idoutil);
			return "Deleted Successfully";
		}
		return "ERROR: This member does not own this tool";
	}

	@Override
	public OutilMembreResponse findOutilFullByOutilId(Long idOutil) {
		OutilBean outil = outilProxyService.findOneOutilById(idOutil);
		OutilMembreResponse out = OutilMembreResponse.builder().id(idOutil)
				.date(outil.getDate())
				.source(outil.getSource())
				.build();
		List<Membre_Outil> outil_mbrs = membreOutilRepository.findOutilsByOutilId(idOutil);
		List<Membre> mbrs = new ArrayList<Membre>();
		outil_mbrs.forEach(s -> {
			mbrs.add(membreRepository.findById(s.getId().getMembre_id()).get());
		});
		out.setMembres(mbrs);
		return out;
	}

	@Override
	public List<OutilMembreResponse> findOutilsFullByMbrId(Long idmbr) {
		List<Membre_Outil> mbr_outils = membreOutilRepository.findOutilsByMembreId(idmbr);
		List<OutilMembreResponse> outilsFull = new ArrayList<OutilMembreResponse>();
		for(Membre_Outil mbr_outil : mbr_outils) {
			outilsFull.add(this.findOutilFullByOutilId(mbr_outil.getId().getOutil_id()));
		}
		return outilsFull;
	}

	@Override
	public List<OutilMembreResponse> findAllOutilsFull() {
		List<Long> outil_ids = membreOutilRepository.findDistinctOutilIds();
		List<OutilMembreResponse> outilsFull = new ArrayList<OutilMembreResponse>();
		for(Long outil_id : outil_ids) {
			outilsFull.add(this.findOutilFullByOutilId(outil_id));
		}
		return outilsFull;
	}

	@Override
	public OutilMembreResponse updateOutil(Long id, OutilMembreRequest outil) {
		OutilBean o = outilProxyService.findOneOutilById(id);
		o.setDate(outil.getDate());
		o.setSource(outil.getSource());
		outilProxyService.updateOutil(id, o);
		List<Membre_Outil> mbr_outils = membreOutilRepository.findOutilsByOutilId(id);
		membreOutilRepository.deleteAll(mbr_outils);
		for(Long m_id : outil.getMembres()) {
			this.affectOutilToAuteur(m_id, id);
		}
		return this.findOutilFullByOutilId(id);
	}

}
