package com.twitter.api.twitterapi.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private Environment environment;

    public LoggingAspect(Environment environment){
      this.environment = environment;
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *) " +
            "|| within(@org.springframework.stereotype.Service *) " +
            "|| within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeansPointcut(){}

    @Pointcut("within(@com.twitter.api.twitterapi.controller..*) " +
            "|| within(@com.twitter.api.twitterapi.service..*) " +
            "|| within(@com.twitter.api.twitterapi.repository..*)")
    public void applicationPackagePointcut(){}

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeansPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable th){
        logger.error("Exception in {}.{}() with cause = {} and exception = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), th.getCause() != null ? th.getCause() : "NULL");
    }

    @Around("applicationPackagePointcut() && springBeansPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        if(logger.isDebugEnabled()){
            logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try{
            Object result = joinPoint.proceed();
            if(logger.isDebugEnabled()){
                logger.debug("Exit: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return  result;
        }catch(IllegalArgumentException ex){
           logger.error("Illegal argument: {}.{}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getDeclaringTypeName(),
                   joinPoint.getSignature().getName());
           throw ex;
        }
    }

}
