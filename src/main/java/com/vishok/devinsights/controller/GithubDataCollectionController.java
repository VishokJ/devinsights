package com.vishok.devinsights.controller;

import com.vishok.devinsights.service.GithubDataCollectionService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/collect/github")
public class GithubDataCollectionController {

    private final GithubDataCollectionService githubDataCollectionService;

    @Autowired
    public GithubDataCollectionController(GithubDataCollectionService githubDataCollectionService) {
        this.githubDataCollectionService = githubDataCollectionService;
    }

    @PostMapping("/setToken")
    public ResponseEntity<String> setPersonalAccessToken(@RequestParam String personalAccessToken) throws IOException {
        githubDataCollectionService.setPersonalAccessToken(personalAccessToken);
        return ResponseEntity.ok("Personal access token set successfully");
    }

    @GetMapping("/commits")
    public ResponseEntity<List<GHCommit>> getCommits(@RequestParam String repoOwner, @RequestParam String repoName) throws IOException {
        return ResponseEntity.ok(githubDataCollectionService.fetchCommits(repoOwner, repoName));
    }

    @GetMapping("/pull-requests")
    public ResponseEntity<List<GHPullRequest>> getPullRequests(@RequestParam String repoOwner, @RequestParam String repoName) throws IOException {
        return ResponseEntity.ok(githubDataCollectionService.fetchPullRequests(repoOwner, repoName));
    }

    @GetMapping("/issues")
    public ResponseEntity<List<GHIssue>> getIssues(@RequestParam String repoOwner, @RequestParam String repoName) throws IOException {
        return ResponseEntity.ok(githubDataCollectionService.fetchIssues(repoOwner, repoName));
    }

    @GetMapping("/contributors")
    public ResponseEntity<Set<String>> getContributors(@RequestParam String repoOwner, @RequestParam String repoName) throws IOException {
        return ResponseEntity.ok(githubDataCollectionService.fetchContributors(repoOwner, repoName));
    }
}