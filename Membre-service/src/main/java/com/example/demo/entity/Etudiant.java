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

@Entity @DiscriminatorValue("etd")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Etudiant extends Membre {
	/**
	 * 
	 */
	@NonNull @Temporal(TemporalType.DATE)
	private Date dateInscription;
	@NonNull
	private String diplome;
	
	@ManyToOne
	private EnseignantChercheur encadrant;

	@Builder
	public Etudiant(Long id, String cin, String nom, String prenom, Date dateNaissance, byte[] photo, String cv,
			String email, String password, Date dateInscription, String diplome, EnseignantChercheur encadrant) {
		super(id, cin, nom, prenom, dateNaissance, photo, cv, email, password, null);
		this.dateInscription = dateInscription;
		this.diplome = diplome;
		this.encadrant = encadrant;
	}


	
	
}
