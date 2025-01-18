package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Membre_Event;
import com.example.demo.entity.Membre_Event_Id;

public interface MembreEventRepository extends JpaRepository<Membre_Event, Membre_Event_Id> {
	@Query("select m from Membre_Event m where m.id.membre_id=:x")
	List<Membre_Event> findEventsByMembreId(@Param("x") Long membreId);
	
	@Query("select m from Membre_Event m where m.id.event_id=:x")
	List<Membre_Event> findEventsByEventId(@Param("x") Long eventId);
	
	@Query("SELECT DISTINCT(m.id.membre_id) FROM Membre_Event m")
	List<Long> findDistinctMembreIds();

	@Query("SELECT DISTINCT(m.id.event_id) FROM Membre_Event m")
	List<Long> findDistinctEventIds();
}