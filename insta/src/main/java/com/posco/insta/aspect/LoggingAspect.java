package com.posco.insta.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
//메모리 상에 올려놓고 필요할때 가져다 쓴다
public class LoggingAspect {
//    @Before("execution(* com.posco.insta.user.service.*.find*(..))")
//    public void loggerBefore(){
//      //  System.out.println("----- before --------");
//        log.info("----- before --------");
//    }
//    @After("execution(* com.posco.insta.user.service.*.find*(..))")
//    public void loggerAfter(){
//        //System.out.println("----- After --------");
//        log.info("----- After --------");
//    }

    @Around("execution(* com.posco.insta.user.service.*.find*(..))")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long beforeTimeMillis = System.currentTimeMillis();
        log.info("start: "+ beforeTimeMillis);
        Object result = proceedingJoinPoint.proceed();
        long afterTimeMillis = System.currentTimeMillis();
        log.info("before : "+ afterTimeMillis + "시간차 : "+ (afterTimeMillis - beforeTimeMillis));
        return result;
    }
}
//실제 서비스에서 로그를 남기는 것은 선택이 아닌 필수입니다. 로그를 남겨서 에러가 발생하였을 때,
// 즉각적으로 대처할 때 주요할 것이며, 실제 내가 작성한 코드의 흐름이 정상적으로 동작하고 있는지에 대한 판단 여부에도 많은 도움이 될 수 있습니다.
