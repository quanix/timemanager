package com.domac.app.system.repository;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author : lihaoquan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
    String value() default "";
}
