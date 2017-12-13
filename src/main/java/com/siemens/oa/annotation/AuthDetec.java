package com.siemens.oa.annotation;

import com.siemens.oa.enums.Auth;

import java.lang.annotation.*;

/**
 * Created by gxurn9 on 12/13/2017.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthDetec {
    Auth[] authorities() default{};
}
