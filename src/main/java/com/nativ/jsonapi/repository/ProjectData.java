package com.nativ.jsonapi.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.nativ.jsonapi.entity.ProjectEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProjectData {

	@PostConstruct
	public List<ProjectEntity> findAll() {
		
		List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		
		list.add(new ProjectEntity(121L, "Great Project"));
		list.add(new ProjectEntity(122L, "Crnk Project"));
		list.add(new ProjectEntity(123L, "Some Project"));
		list.add(new ProjectEntity(124L, "JSON API Project"));
		
		log.info("-----------project-data: {}", list);
		return list;
	}	
}
