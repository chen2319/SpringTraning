package com.yangch3.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(UserLogAspect.class);
	
	@Before("execution(* com.yangch3.service.UserService.*(..))")
	public void beforeUserService (JoinPoint jp) {
		logger.info("Aspect Log: Will exeute method:" + jp.getSignature().getName());
	}
	
}
