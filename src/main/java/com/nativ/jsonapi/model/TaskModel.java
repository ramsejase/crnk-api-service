package com.nativ.jsonapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiLinksInformation;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiRelationId;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.annotations.LookupIncludeBehavior;
import io.crnk.core.resource.annotations.SerializeType;
import io.crnk.core.resource.links.DefaultSelfLinksInformation;
import lombok.Data;


@JsonApiResource(type = "task")
@JsonInclude(content = Include.NON_NULL)
public class TaskModel {

	@JsonApiId
	private Long id;
	
	@JsonProperty
	private String name;
	
	@JsonApiRelationId
	private Long projectId;
	
	@JsonApiRelation(serialize = SerializeType.ONLY_ID, lookUp = LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL)
	private ProjectModel project;
	
	@JsonApiLinksInformation
	private DefaultSelfLinksInformation links = new DefaultSelfLinksInformation();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public ProjectModel getProject() {
		return project;
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}

	public DefaultSelfLinksInformation getLinks() {
		return links;
	}

	public void setLinks(DefaultSelfLinksInformation links) {
		this.links = links;
	}


}
