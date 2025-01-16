package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.bean.EventBean;
import com.example.demo.entity.Membre;
import com.example.demo.entity.Membre_Event;
import com.example.demo.entity.Membre_Event_Id;
import com.example.demo.proxy.EventProxyService;
import com.example.demo.repository.MembreEventRepository;
import com.example.demo.repository.MembreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MembreEventImpl implements IMembreEventService {
	private MembreRepository membreRepository;
	private MembreEventRepository membreEventRepository;
	private EventProxyService eventProxyService;

	public void affectEventToAuteur(Long idauteur, Long idevent) {
		Membre mbr = membreRepository.findById(idauteur).get();
		Membre_Event mbs = new Membre_Event();
		mbs.setMembre(mbr);
		mbs.setId(new Membre_Event_Id(idevent, idauteur));
		membreEventRepository.save(mbs);
	}

	public List<EventBean> findAllEventparauteur(Long idauteur) {
		List<EventBean> events = new ArrayList<EventBean>();
		List<Membre_Event> idevents = membreEventRepository.findEventsByMembreId(idauteur);
		idevents.forEach(s -> {
			System.out.println(s);
			events.add(eventProxyService.findOneEventById(s.getId().getEvent_id()));
		});
		return events;
	}

	@Override
	public List<EventBean> createEvent(Long idMembre, EventBean event) {
		Membre mbr = membreRepository.findById(idMembre).get();
		EventBean o = eventProxyService.addEvent(event);
		membreEventRepository.save(new Membre_Event(new Membre_Event_Id(mbr.getId(), o.getId()), mbr));
		return this.findAllEventparauteur(idMembre);
	}

	@Override
	public String deleteEvent(Long idMembre, Long idevent) {
		Optional<Membre_Event> mbr_event = membreEventRepository.findById(new Membre_Event_Id(idevent, idMembre));
		if (!mbr_event.isEmpty()) {
			eventProxyService.deleteEvent(idevent);
			membreEventRepository.delete(mbr_event.get());
			return "Deleted Successfully";
		}
		return "ERROR: This member is not a member of this event";
	}

}
