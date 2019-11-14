package com.imooc.web.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Aspect
//@Component
public class TimeAspect {

//    @Before()
//    @After()
//    @AfterThrowing
//    @Around()

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        long start = new Date().getTime();
        Object proceed = pjp.proceed();
        System.out.println("time aspect 耗时: " + (new Date().getTime() - start));
        System.out.println("time aspect end");

        return proceed;
    }
}
