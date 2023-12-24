package com.nativ.jsonapi.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nativ.jsonapi.entity.TaskEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TaskData {

	public List<TaskEntity> findAll() {
		
		List<TaskEntity> list = new ArrayList<>();
		
		list.add(new TaskEntity(221L, "Great Task", 121L));
		list.add(new TaskEntity(222L, "Crnk Task", 121L));
		list.add(new TaskEntity(223L, "Some Task", 121L));
		list.add(new TaskEntity(224L, "JSON API Task", 121L));
		log.info("-----------task-data: {}", list);
		return list;
	}
}
