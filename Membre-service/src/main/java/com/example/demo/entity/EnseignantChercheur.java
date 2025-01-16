package com.example.demo.entity;

import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity @DiscriminatorValue("Professor")
@NoArgsConstructor @AllArgsConstructor
public class EnseignantChercheur extends Membre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	private String grade;
	@NonNull
	private String establishment;
	@Builder
	public EnseignantChercheur(Long id, String cin, String name, Date birthDate, byte[] pic,
			String cv, String email, String password, String grade, String establishment) {
		super(id, cin, name, birthDate, pic, cv, email, password, "Professor", null, null, null);
		this.grade = grade;
		this.establishment = establishment;
	}
	
	
}
