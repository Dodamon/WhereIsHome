package com.ssafy.happyhouse.member.dto;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080"})
@Retention(RUNTIME)
@Target(METHOD)
public @interface MySecured {
	Role role() default Role.USER;
}
