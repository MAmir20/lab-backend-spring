package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.EventBean;

public interface IMembreEventService {
	public void affectEventToAuteur(Long idauteur, Long idEvent);
	public List<EventBean> findAllEventparauteur (Long idauteur);
	public List<EventBean> createEvent(Long idMembre, EventBean event);
	public String deleteEvent(Long idMembre, Long idEvent);

}
