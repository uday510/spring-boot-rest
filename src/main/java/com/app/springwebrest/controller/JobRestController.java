package com.app.springwebrest.controller;

import com.app.springwebrest.model.JobPost;
import com.app.springwebrest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class JobRestController {

    @Autowired
    private JobService service;

//    @GetMapping("/jobs")
    @GetMapping(path = "jobs", produces = "application/json") // it will return JSON response only
//    @GetMapping(path = "jobs") // it will return JSON response only
    @ResponseBody
    public List<JobPost> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping(path="/jobs/{postId}", produces = "application/json")
    @ResponseBody
    public JobPost getJobById(@PathVariable("postId") int postId) {
        return service.getJobById(postId);
    }

    @PostMapping("jobs")
    public void addJob(@RequestBody JobPost jobPost) {
        service.addJob(jobPost);
    }

    @PutMapping(path="jobs/{postId}", produces = "application/json")
    public JobPost updateJob(@RequestBody JobPost jobPost, @PathVariable("postId") int postId) {
        service.updateJob(jobPost);
        return service.getJobById(postId);
    }

    @DeleteMapping("jobs/{postId}")
    public void deleteJob(@PathVariable("postId") int postId) {
        service.deleteJob(postId);
    }

    @GetMapping(path = "jobs/search/{keyword}", produces = "application/json")
    public List<JobPost> searchJobs(@PathVariable("keyword") String keyword) {
        return service.search(keyword);
    }

    @GetMapping(path = "jobs/load", produces = "application/json")
    public void loadJobs() {
        service.load();
    }
}
