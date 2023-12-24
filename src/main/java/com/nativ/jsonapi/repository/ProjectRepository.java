package com.nativ.jsonapi.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nativ.jsonapi.model.ProjectModel;

import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.DefaultResourceList;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.core.resource.meta.DefaultPagedMetaInformation;
import io.crnk.core.resource.meta.PagedMetaInformation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectRepository extends ResourceRepositoryBase<ProjectModel, Long> {

	@Autowired
	ProjectData repo;
	
	@Autowired
	TaskData trepo;
	
	public ProjectRepository() {
		super(ProjectModel.class);
	}
	
	@Override
	public synchronized ResourceList<ProjectModel> findAll(QuerySpec querySpec) {
	
		PagedMetaInformation pinfo = new DefaultPagedMetaInformation();
		pinfo.setTotalResourceCount(10L);
		return querySpec.apply(transformData());
//		return new DefaultResourceList<Project>(transformData(), pinfo, null);
	}

	public synchronized ResourceList<ProjectModel> findsAll() {
		return new DefaultResourceList(transformData(), null, null);
	}
	
	List<ProjectModel> transformData() {
		log.info(".......transformData");
		List<ProjectModel> projs = repo.findAll()
				.stream()
			.map(entity -> {
				ProjectModel model = new ProjectModel();
				model.setId(entity.getId());
				model.setName(entity.getName());
				return model;
			})
			.collect(Collectors.toList());
		log.info(".......transformData -project - {}", projs);		
		return projs;
	}
}
