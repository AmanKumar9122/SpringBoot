package com.example.TodoApiSpring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// custom annotation
// @Target: where this annotation can be applied
// uses: can be applied to methods only
@Target(ElementType.METHOD)

// @Retention: how long this annotation will be available
// uses: available at runtime
@Retention(RetentionPolicy.RUNTIME)


// @interface: to declare custom annotation
// uses: to declare custom annotation
public @interface TimeMonitor {

}
