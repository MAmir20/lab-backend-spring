package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.EventBean;
import com.example.demo.dto.EventMembreResponse;

public interface IMembreEventService {
	public void affectEventToAuteur(Long idauteur, Long idEvent);
	public List<EventBean> findAllEventparauteur (Long idauteur);
	public List<EventBean> createEvent(Long idMembre, EventBean event);
	public boolean deleteEvent(Long idEvent);

	public EventMembreResponse findEventFullByEventId(Long idEvent);
	public List<EventMembreResponse> findEventsFullByMbrId(Long idmbr);
	public List<EventMembreResponse> findAllEventsFull();
}
