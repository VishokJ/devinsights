package com.vishok.devinsights.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@within(com.vishok.devinsights.annotation.Logged) || @annotation(com.vishok.devinsights.annotation.Logged)")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] methodArgs = joinPoint.getArgs();
        String correlationId = MDC.get("correlationId");

        logger.info("Entering method: {} of class: {} with arguments: {} and correlationId: {}", methodName, className, methodArgs, correlationId);

        Object result = joinPoint.proceed();

        logger.info("Exiting method: {} of class: {} with correlationId: {}", methodName, className, correlationId);

        return result;
    }
}