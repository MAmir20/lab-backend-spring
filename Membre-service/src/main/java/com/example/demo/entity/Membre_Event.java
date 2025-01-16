package com.example.demo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre_Event {
	@EmbeddedId
	private Membre_Event_Id id;
	@ManyToOne
	@MapsId("membre_id")
	private Membre membre;
}
