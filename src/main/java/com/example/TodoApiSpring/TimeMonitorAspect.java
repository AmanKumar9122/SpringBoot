package com.example.TodoApiSpring;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// @Aspect: to declare aspect class

// Aspect class: class containing advices
@Aspect
@Component
public class TimeMonitorAspect {

    // @Around: advice type
    // advice: advice is any action taken by an aspect at a particular join point
    // an advice help us to identify where the logic will be applied
    // types: @Before, @After, @AfterReturning, @AfterThrowing
    // @Before: advice will be executed before the join point
    // @After: advice will be executed after the join point
    // @AfterReturning: advice will be executed after the join point if it returns successfully
    // @AfterThrowing: advice will be executed if the join point throws an exception
    // @Around : will surround the join point (before and after)

    // join points: points in the program where we can apply advices
    // pointcut: expression to select join points
    // uses: to log time taken by methods annotated with @TimeMonitor

    @Before("@annotation(TimeMonitor)") // advice
    // @After("@annotation(TimeMonitor)") // advice

    // even after using @After it will print before because the method is very small
    // so it will execute very fast
    // to see the difference we can use Thread.sleep(2000) in doSomething method

    //  println order in java spring boot application is not guaranteed
    // so sometimes it may print after and sometimes before
    // to avoid this we can use synchronized block
    //    public synchronized void logTimeBefore() {
    //        System.out.println("Logging time before method execution: " + System.currentTimeMillis());
    //    }
    // but it will reduce the performance of the application
    // so we can use logger instead of System.out.println

    public void logTime() {
        System.out.println("Logging time: ");
    }
}
