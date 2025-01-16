package com.example.demo.bean;

import java.util.Date;

import lombok.Data;

@Data
public class EventBean {
	private Long id;
	private String title;
	private Date dateFin;
	private String lieu;
}
