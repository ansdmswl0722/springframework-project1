package com.nhnacademy.edu.springframework.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private final static Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    @Around("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
    public Object targetCsvService(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        try {
            Object retVal = pjp.proceed();
            return retVal;

        } catch (Throwable e) {
            e.printStackTrace();
            throw e;

        }finally {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime -startTime;
            log.info("[{}].[{}] {}ms",className,methodName,elapsedTime);


        }
    }

}
