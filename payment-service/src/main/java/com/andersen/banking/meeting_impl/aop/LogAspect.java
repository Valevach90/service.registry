package com.andersen.banking.meeting_impl.aop;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("@annotation(logAnnotation)")
    public Object logAspect(ProceedingJoinPoint joinPoint, LogAnnotation logAnnotation)
            throws Throwable {
        String prefixLog;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        if (logAnnotation.prefix().isEmpty()) {
            prefixLog = String.format("method: %s in class: %s",
                    methodSignature.getMethod().getName(),
                    methodSignature.getClass().getSimpleName());
        } else {
            prefixLog = logAnnotation.prefix();
        }
        Object returnObject;
        if (logAnnotation.before()) {
            log.info("Before {} {}.", prefixLog,
                    args == null ? "without args"
                            : String.format("with args %s", Arrays.toString(args)));
        }
        try {
            returnObject = joinPoint.proceed();

            if (logAnnotation.after()) {
                log.info("After {} {}.", prefixLog,
                        returnObject == null ? "without return object"
                                : String.format("with return object %s", returnObject));
            }
            return returnObject;
        } catch (Throwable e) {
            log.error("Error in method {} in class {} with exception {}",
                    methodSignature.getMethod().getName(),
                    methodSignature.getClass().getSimpleName(), e);
            throw e;
        }
    }
}
