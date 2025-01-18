package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bean.EventBean;
import com.example.demo.bean.EventBean;
import com.example.demo.dto.EventMembreResponse;
import com.example.demo.dto.EventMembreResponse;
import com.example.demo.entity.Membre;
import com.example.demo.entity.Membre_Event;
import com.example.demo.entity.Membre_Event_Id;
import com.example.demo.entity.Membre_Event;
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
	public boolean deleteEvent(Long idevent) {
		List<Membre_Event> mbr_events = membreEventRepository.findEventsByEventId(idevent);
		if (!mbr_events.isEmpty()) {
			membreEventRepository.deleteAll(mbr_events);
			eventProxyService.deleteEvent(idevent);
			return true;
		}
		return false;
	}

	@Override
	public EventMembreResponse findEventFullByEventId(Long idEvent) {
		EventBean event = eventProxyService.findOneEventById(idEvent);
		EventMembreResponse out = EventMembreResponse.builder().id(idEvent)
				.title(event.getTitle())
				.dateFin(event.getDateFin())
				.lieu(event.getLieu())
				.build();
		List<Membre_Event> event_mbrs = membreEventRepository.findEventsByEventId(idEvent);
		List<Membre> mbrs = new ArrayList<Membre>();
		event_mbrs.forEach(s -> {
			mbrs.add(membreRepository.findById(s.getId().getMembre_id()).get());
		});
		out.setMembres(mbrs);
		return out;
	}

	@Override
	public List<EventMembreResponse> findEventsFullByMbrId(Long idmbr) {
		List<Membre_Event> mbr_events = membreEventRepository.findEventsByMembreId(idmbr);
		List<EventMembreResponse> eventsFull = new ArrayList<EventMembreResponse>();
		for(Membre_Event mbr_event : mbr_events) {
			eventsFull.add(this.findEventFullByEventId(mbr_event.getId().getEvent_id()));
		}
		return eventsFull;
	}

	@Override
	public List<EventMembreResponse> findAllEventsFull() {
		List<Long> event_ids = membreEventRepository.findDistinctEventIds();
		List<EventMembreResponse> eventsFull = new ArrayList<EventMembreResponse>();
		for(Long event_id : event_ids) {
			eventsFull.add(this.findEventFullByEventId(event_id));
		}
		return eventsFull;
	}

}
