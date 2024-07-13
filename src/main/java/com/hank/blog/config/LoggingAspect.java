package com.hank.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {

  private static final String LOG_ENTRY = "Entry::{}::{}({})";

  private static final String LOG_EXEC = "Exec::{}::{}() - {} ms";

  private static final String LOG_EXIT = "Exit::{}::{}()";

  private static final String LOG_EXCEPTION = "Exception::{}::{}() - {}::{}";

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut("within(@org.springframework.stereotype.Repository *)" +
      " || within(@org.springframework.stereotype.Service *)" +
      " || within(@org.springframework.web.bind.annotation.RestController *)")
  public void springBeanPointcut() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * Pointcut that matches all Spring beans in the application's main packages.
   */
  @Pointcut("within(com.hank.blog.controllers..*) " +
      " || within(com.hank.blog.services..*)" +
      " || within(com.hank.blog.repositories..*)")
  public void applicationPackagePointcut() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  @Around("execution(* com.hank.blog..*.*(..)) && !execution( * com.hank.blog.config.*.*(..)) "
      + "&& !@annotation(org.springframework.web.bind.annotation.ExceptionHandler) "
      + "&& !@annotation(org.aspectj.lang.annotation.Around) "
      + "&& !@annotation(com.hank.blog.config.NoLogging))" +
      " || (applicationPackagePointcut() && springBeanPointcut())")
  public Object logEntryExitAllMethods(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    // get intercepted method details
    final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    final String className = methodSignature.getDeclaringType().getSimpleName();
    final String methodName = methodSignature.getName();
    final String param = Arrays.stream(proceedingJoinPoint.getArgs()).map(o -> o != null ? o.toString() : "").collect(Collectors.joining());

    // log entry
    LOGGER.info(LOG_ENTRY, className, methodName,param);

    // measure method execution time
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    final Object result = proceedingJoinPoint.proceed();
    stopWatch.stop();

    // log method execution time
    LOGGER.info(LOG_EXEC, className, methodName, stopWatch.getTotalTimeMillis());

    // log exit
    LOGGER.info(LOG_EXIT, className, methodName);

    return result;
  }
}
