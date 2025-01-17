package com.example.demo.bean;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class OutilBean {

	private Long id;
	private Date date;
	private String source;
}
