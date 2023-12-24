package com.nativ.jsonapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiLinksInformation;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.links.DefaultSelfLinksInformation;
import lombok.Data;

@JsonApiResource(type = "project")
public class ProjectModel {

	@JsonApiId
	private Long id;
	
	@JsonProperty
	private String name;
	
	@JsonApiLinksInformation
	private DefaultSelfLinksInformation links = new DefaultSelfLinksInformation();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public DefaultSelfLinksInformation getLinks() {
		return links;
	}

	public void setLinks(DefaultSelfLinksInformation links) {
		this.links = links;
	}



}