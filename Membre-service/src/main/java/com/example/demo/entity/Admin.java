package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @DiscriminatorValue("Admin")
@NoArgsConstructor
@Getter @Setter
public class Admin extends Membre{
	@Builder
	public Admin(Long id, String cin, String name, Date birthDate,String pic, String cv,
			String email, String password) {
		super(id, cin, name, birthDate, pic, cv, email, password, "Admin", null, null, null);

	}
}
