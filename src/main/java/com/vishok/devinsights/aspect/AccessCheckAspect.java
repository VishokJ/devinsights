package com.vishok.devinsights.aspect;

import com.vishok.devinsights.annotation.AccessConfig;
import com.vishok.devinsights.enums.AccessType;
import com.vishok.devinsights.service.GithubDataCollectionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessCheckAspect {

    private final GithubDataCollectionService githubDataCollectionService;

    @Autowired
    public AccessCheckAspect(GithubDataCollectionService githubDataCollectionService) {
        this.githubDataCollectionService = githubDataCollectionService;
    }

    @Around("@annotation(accessConfig)")
    public Object checkAccess(ProceedingJoinPoint joinPoint, AccessConfig accessConfig) throws Throwable {
        if (accessConfig.value() == AccessType.github && !githubDataCollectionService.hasGithubAccess()) {
            throw new IllegalStateException("Github access not provided");
        }
        return joinPoint.proceed();
    }
}
