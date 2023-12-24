package com.nativ.jsonapi.entity;

import javax.annotation.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ProjectEntity {

	private Long id;
	private String name;

	public ProjectEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}
	
}
