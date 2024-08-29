package com.app.springwebrest.controller;

import com.app.springwebrest.model.JobPost;
import com.app.springwebrest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {

    @Autowired
    private JobService jobService;

//    @GetMapping("/jobs")
    @GetMapping(path = "/jobs", produces = "application/json") // it will return JSON response only
    @ResponseBody
    public List<JobPost> getAllJobs() {
        return jobService.returnAllJobPosts();
    }

    @GetMapping("/jobs/{postId}")
    @ResponseBody
    public JobPost getJobById(@PathVariable("postId") int postId) {
        return jobService.repo.getJobById(postId);
    }

    @PostMapping("jobs")
    public void addJob(@RequestBody JobPost jobPost) {
        jobService.repo.addJobPost(jobPost);
    }

    @PutMapping("jobs/{postId}")
    public JobPost updateJob(@RequestBody JobPost jobPost, @PathVariable("postId") int postId) {
        return jobService.repo.updateJobPost(jobPost, postId);
    }

    @DeleteMapping("jobs/{postId}")
    public void deleteJob(@PathVariable("postId") int postId) {
        jobService.repo.deleteJobPost(postId);
    }
}
