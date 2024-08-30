package com.app.springwebrest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method calls and exceptions.
 */
@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    private static final String LOGGING_CORRELATION_ID = "correlationId";

    /**
     * Logs the method call with the method name, input arguments, and correlation ID.
     *
     * @param joinPoint the join point of the method call
     */
    @Before("execution(* com.app.springwebrest.service.JobService.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String correlationId = MDC.get(LOGGING_CORRELATION_ID);

        LOGGER.info(
                "[{}][{}] Method called: {} with arguments: {}",
                correlationId,
                Thread.currentThread().getName(),
                methodName,
                args
        );
    }


    /**
     * Logs any exception thrown during method execution.
     *
     * @param joinPoint the join point of the method call
     * @param exception the thrown exception
     */
    @AfterThrowing(pointcut = "execution(* com.app.springwebrest.service.JobService.*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String correlationId = MDC.get(LOGGING_CORRELATION_ID);

        LOGGER.error(
                "[{}][{}] Method {} threw an exception: {}",
                correlationId,
                Thread.currentThread().getName(),
                methodName,
                exception.getMessage(),
                exception
        );
    }
    @After("execution(* com.app.springwebrest.service.JobService.*(..))")
    public void logMethodCallAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String correlationId = MDC.get(LOGGING_CORRELATION_ID);

        LOGGER.info(
                correlationId,
                Thread.currentThread().getName(),
                methodName,
                args
        );
    }

    @AfterReturning("execution(* com.app.springwebrest.service.JobService.*(..))")
    public void logMethodCallAfterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String correlationId = MDC.get(LOGGING_CORRELATION_ID);

        LOGGER.info(
                correlationId,
                Thread.currentThread().getName(),
                methodName,
                args
        );
    }
}
