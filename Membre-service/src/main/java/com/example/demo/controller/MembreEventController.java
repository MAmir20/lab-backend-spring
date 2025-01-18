package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.EventBean;
import com.example.demo.dto.EventMembreResponse;
import com.example.demo.entity.Membre;
import com.example.demo.service.IMembreEventService;
import com.example.demo.service.IMembreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MembreEventController {
	IMembreService membreService;
	IMembreEventService membreEventService;
	
	@GetMapping(value = "/membres/{m_id}/events/{e_id}")
	public Membre affecterEvent(@PathVariable(name = "m_id") Long m_id, @PathVariable(name = "e_id") Long e_id) {
		membreEventService.affectEventToAuteur(m_id, e_id);
		Membre mbr = membreService.findMembre(m_id);
		mbr.setEvents(membreEventService.findAllEventparauteur(m_id));
		return mbr;
	}
	
	@GetMapping(value = "/membres/{id}/events")
	public List<EventBean> findEvents(@PathVariable Long id) {
		return membreEventService.findAllEventparauteur(id);
	}
	
	@PostMapping(value = "/membres/{id}/events")
	public List<EventBean> createEvent(@PathVariable Long id, @RequestBody EventBean event){
		return membreEventService.createEvent(id, event);
	}
	
	@DeleteMapping(value = "/membres/events/{o_id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable Long o_id) {
		if(membreEventService.deleteEvent(o_id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/* ----------------------------------------- */

	@GetMapping(value = "/membres/events/{id}/full")
	public EventMembreResponse findEventFullByEventId(@PathVariable Long id) {
		return membreEventService.findEventFullByEventId(id);
	}

	@GetMapping(value = "/membres/{id}/events/full")
	public List<EventMembreResponse> findEventsFullByMbrId(@PathVariable Long id) {
		return membreEventService.findEventsFullByMbrId(id);
	}

	@GetMapping(value = "/membres/events/full")
	public List<EventMembreResponse> findEventsFull() {
		return membreEventService.findAllEventsFull();
	}
}
