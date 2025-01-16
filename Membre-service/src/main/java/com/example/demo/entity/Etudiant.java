package com.example.demo.entity;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @DiscriminatorValue("Student")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Etudiant extends Membre {
	/**
	 * 
	 */
	@NonNull @Temporal(TemporalType.DATE)
	private Date dateInscription;
	@NonNull
	private String diploma;
	
	@ManyToOne
	private EnseignantChercheur encadrant;

	@Builder
	public Etudiant(Long id, String cin, String name, Date birthDate,String pic, String cv,
			String email, String password, Date dateInscription, String diploma, EnseignantChercheur encadrant) {
		super(id, cin, name, birthDate, pic, cv, email, password, "Student", null, null, null);
		this.dateInscription = dateInscription;
		this.diploma = diploma;
		this.encadrant = encadrant;
	}


	
	
}
