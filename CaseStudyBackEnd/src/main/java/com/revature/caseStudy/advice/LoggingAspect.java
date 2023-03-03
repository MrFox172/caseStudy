package com.revature.caseStudy.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect()
    {
        log.info("Logging Initiated.");
    }

    //Very simple logging. With time, will make this a lot better.
    @Before("within(com.revature.caseStudy.controllers.*.*)")
    public void logControllerMethods(JoinPoint jp)
    {
        log.info("-=CONTROLLER=-");
        log.info(jp.getTarget()+ "invoked "+jp.getSignature());
    }

    @Before("within(com.revature.caseStudy.services.*.*)")
    public void logServiceMethods(JoinPoint jp)
    {
        log.info("-=SERVICE=-");
        log.info(jp.getTarget()+ "invoked "+jp.getSignature());
    }

}
