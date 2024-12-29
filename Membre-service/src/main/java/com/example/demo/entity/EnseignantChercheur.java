package com.example.demo.entity;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity @DiscriminatorValue("ens")
@NoArgsConstructor @AllArgsConstructor
public class EnseignantChercheur extends Membre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	private String grade;
	@NonNull
	private String etablissement;
	@Builder
	public EnseignantChercheur(Long id, String cin, String nom, String prenom, Date dateNaissance, byte[] photo,
			String cv, String email, String password, String grade, String etablissement) {
		super(id, cin, nom, prenom, dateNaissance, photo, cv, email, password, null);
		this.grade = grade;
		this.etablissement = etablissement;
	}
	
	
}
