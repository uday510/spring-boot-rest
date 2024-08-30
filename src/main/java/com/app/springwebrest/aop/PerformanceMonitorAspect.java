package com.app.springwebrest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class PerformanceMonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    @Around("execution(* com.app.springwebrest.service..*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();

        long executionTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        LOGGER.info(
                "[{}][{}] Method '{}' took {} ms to execute.",
                joinPoint.getTarget().getClass().getSimpleName(),
                Thread.currentThread().getName(),
                joinPoint.getSignature().getName(),
                executionTime
        );

        return result;
    }
}