
package com.nativ.jsonapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nativ.jsonapi.model.ProjectModel;
import com.nativ.jsonapi.repository.ProjectRepository;

import io.crnk.core.resource.list.ResourceList;
import lombok.extern.slf4j.Slf4j;

/**
 * ApiController
 */
 @Slf4j
 @RestController
public class ApiController {

    @Autowired
	ProjectRepository repo;

	@Value("${crnk.enabled}")
	String crnkEnabledStatus;
	
	@GetMapping(path = "/prps")
	public String getProps() {
		log.info("......................{}", crnkEnabledStatus);
		return crnkEnabledStatus;
	}

	@GetMapping(path = "/all")
	public ResourceList<ProjectModel> getJson() {
		return repo.findsAll();
	}
}