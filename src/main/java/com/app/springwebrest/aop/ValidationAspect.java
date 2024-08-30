package com.app.springwebrest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.app.springwebrest.service.JobService.getJobById(..)) && args(postId)")
    public Object ValidateAndUpdate(ProceedingJoinPoint joinPoint, int postId)  {

        if (postId < 0) {
            LOGGER.info("Invalid postId: " + postId);
            postId -= postId;
        }

        Object result = null;
        try {
            result = joinPoint.proceed(new Object[]{postId});
        } catch (Throwable throwable) {
            LOGGER.error("Error in ValidationAspect: ", throwable);
        }
        return result;

    }
}
