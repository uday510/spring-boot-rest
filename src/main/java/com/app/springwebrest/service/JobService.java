package com.app.springwebrest.service;


import com.app.springwebrest.model.JobPost;
import com.app.springwebrest.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

	@Autowired
	public JobRepository repository;

	public List<JobPost> getAllJobs() {
		return repository.findAll();
	}

	public JobPost getJobById(int id) {
		return repository.findById(id).orElse(null);
	}

	public void addJob(JobPost job) {
		repository.save(job);
	}

	public void updateJob(JobPost job) {
		repository.save(job);
	}

	public void deleteJob(int id) {
		repository.deleteById(id);
	}

	public void load() {
		List<JobPost> jobs =
				new ArrayList<>(List.of(
						new JobPost(1, "Software Engineer", "Exciting opportunity for a skilled software engineer.", 3, List.of("Java", "Spring", "SQL")),
						new JobPost(2, "Data Scientist", "Join our data science team and work on cutting-edge projects.", 5, List.of("Python", "Machine Learning", "TensorFlow")),
						new JobPost(3, "Frontend Developer", "Create amazing user interfaces with our talented frontend team.", 2, List.of("JavaScript", "React", "CSS")),
						new JobPost(4, "Network Engineer", "Design and maintain our robust network infrastructure.", 4, List.of("Cisco", "Routing", "Firewalls")),
						new JobPost(5, "UX Designer", "Shape the user experience with your creative design skills.", 3, List.of("UI/UX Design", "Adobe XD", "Prototyping"))

				));

		repository.saveAll(jobs);
	}
	public List<JobPost> search(String keyword) {
		return repository.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
	}

}
