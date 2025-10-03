package com.example.TodoApiSpring;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// @Aspect: to declare aspect class

// Aspect class: class containing advices
@Aspect
@Component
public class TimeMonitorAspect {

    // @Around: advice type
    // advice: code to be executed at join points

    // join points: points in the program where we can apply advices
    // pointcut: expression to select join points
    // uses: to log time taken by methods annotated with @TimeMonitor
    
    @Around("@annotation(TimeMonitor)") // advice
    public void logTime() {
        System.out.println("Logging time: ");
    }
}
