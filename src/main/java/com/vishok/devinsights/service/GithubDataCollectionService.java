package com.vishok.devinsights.service;

import com.vishok.devinsights.annotation.AccessConfig;
import com.vishok.devinsights.annotation.Logged;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.vishok.devinsights.enums.AccessType.github;

@Logged
@Service
@SessionScope
public class GithubDataCollectionService {

    private GitHub userGithub;

    public boolean hasGithubAccess() {
        return userGithub != null;
    }

    public void setPersonalAccessToken(String personalAccessToken) throws IOException {
        this.userGithub = GitHub.connectUsingOAuth(personalAccessToken);
    }

    @AccessConfig(github)
    public Map<String, GHRepository> fetchRepositories() throws IOException {
        return userGithub.getMyself().getAllRepositories();
    }

    @AccessConfig(github)
    public List<GHCommit> fetchCommits(String repoOwner, String repoName) throws IOException {
        GHRepository repo = userGithub.getRepository(repoOwner + "/" + repoName);
        return repo.listCommits().toList();
    }

    @AccessConfig(github)
    public List<GHPullRequest> fetchPullRequests(String repoOwner, String repoName) throws IOException {
        GHRepository repo = userGithub.getRepository(repoOwner + "/" + repoName);
        return repo.getPullRequests(GHIssueState.OPEN);
    }

    @AccessConfig(github)
    public List<GHIssue> fetchIssues(String repoOwner, String repoName) throws IOException {
        GHRepository repo = userGithub.getRepository(repoOwner + "/" + repoName);
        return repo.getIssues(GHIssueState.OPEN);
    }

    @AccessConfig(github)
    public Set<String> fetchContributors(String repoOwner, String repoName) throws IOException {
        GHRepository repo = userGithub.getRepository(repoOwner + "/" + repoName);
        return repo.getCollaboratorNames();
    }
}