package com.nativ.jsonapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


public class TaskEntity {

	private Long id;
	private String name;
	private Long projectId;

	public TaskEntity(Long id, String name, Long projectId) {
		this.id = id;
		this.name = name;
		this.projectId = projectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}
