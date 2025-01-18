package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Membre;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class EventMembreResponse {
	private Long id;
	private String title;
	private Date dateFin;
	private Date dateDebut;
	private String lieu;
	private List<Membre> membres;
}
