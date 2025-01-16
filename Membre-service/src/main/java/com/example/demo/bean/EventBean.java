package com.example.demo.bean;

import java.util.Date;

import lombok.Data;

@Data
public class EventBean {
	private Long id;
	private String titre;
	private Date date;
	private String lieu;
}
