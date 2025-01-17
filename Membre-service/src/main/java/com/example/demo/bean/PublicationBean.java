package com.example.demo.bean;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class PublicationBean {
	private Long id;
	private String type;
	private String title;
	private String lien;
	private Date date;
	private String sourcepdf;
}
