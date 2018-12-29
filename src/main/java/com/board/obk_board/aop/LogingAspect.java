package com.board.obk_board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogingAspect {
    @Around("execution(* com.board.obk_board.controller..*(..))")
    public Object setLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] objects = proceedingJoinPoint.getArgs();

        Long start = System.currentTimeMillis();

        StringBuilder stringBuilder = new StringBuilder();
        for(Object object : objects){
            stringBuilder.append("/").append(object);
        }

        Object result = proceedingJoinPoint.proceed();

        Long end = System.currentTimeMillis();

        Float spendTime = (end - start)/1000.0f;

        log.info("method : {}, params : {}, spendTime : {}",proceedingJoinPoint.getSignature().getName(), stringBuilder, spendTime);

        return result;
    }
}
