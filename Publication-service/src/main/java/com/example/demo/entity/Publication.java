package com.example.demo.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Publication implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String type;
	@NonNull
	private String title;
	@NonNull
	private String lien;
	@NonNull @Temporal(TemporalType.DATE)
	private Date date;
	@NonNull
	private String sourcepdf;
}
