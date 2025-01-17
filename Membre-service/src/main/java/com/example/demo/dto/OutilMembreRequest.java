package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.Membre;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OutilMembreRequest {
	private Long id;
	private Date date;
	private String source;
	private List<Long> membres;
}
