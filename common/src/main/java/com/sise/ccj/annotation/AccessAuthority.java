package com.sise.ccj.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessAuthority {

    String PUBLIC = "PUBLIC";

    String PRIVATE = "PRIVATE";

    String GENERAL = "GENERAL";

    String value() default PUBLIC;
}
