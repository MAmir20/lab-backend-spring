package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Membre;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class PublicationMembreRequest {
	private Long id;
	private String type;
	private String title;
	private String lien;
	private Date date;
	private String sourcepdf;
	private List<Long> membres;
}
