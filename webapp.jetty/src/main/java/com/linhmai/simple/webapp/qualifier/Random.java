package com.linhmai.simple.webapp.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ TYPE, METHOD, FIELD, PARAMETER })
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Random {
}
