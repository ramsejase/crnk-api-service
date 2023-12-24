package com.nativ.jsonapi.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nativ.jsonapi.model.TaskModel;

import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.DefaultResourceList;
import io.crnk.core.resource.list.ResourceList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskRepository extends ResourceRepositoryBase<TaskModel, Long> {

	@Autowired
	TaskData repo;
	
	public TaskRepository() {
		super(TaskModel.class);
	}

	@Override
	public ResourceList<TaskModel> findAll(QuerySpec querySpec) {
	
		return new DefaultResourceList<TaskModel>(transformData(), null, null);
	}
	
	List<TaskModel> transformData() {
		
		log.info(".......transformData");
		List<TaskModel> tasks = 
		repo.findAll()
				.stream()
				.map(entity -> { 
					TaskModel model = new TaskModel();
					model.setId(entity.getId());
					model.setName(entity.getName());
					model.setProjectId(entity.getProjectId());
					return model;
				})
				.collect(Collectors.toList());
		log.info(".......transformData - tasks - {}", tasks);		
		return tasks;
	}
}