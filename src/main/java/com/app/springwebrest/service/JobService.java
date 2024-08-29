package com.app.springwebrest.service;


import com.app.springwebrest.model.JobPost;
import com.app.springwebrest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

	@Autowired
	public JobRepo repo;

	public List<JobPost> returnAllJobPosts() {
		return repo.returnAllJobPosts();
	}

	public void addJobPost(JobPost jobPost) {
		 repo.addJobPost(jobPost);
	}

}
