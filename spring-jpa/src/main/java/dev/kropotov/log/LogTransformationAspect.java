package dev.kropotov.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Aspect
@Component
@Slf4j
public class LogTransformationAspect {

    @Around("@annotation(dev.kropotov.log.annotations.LogTransformation)")
    public Object logTransformation(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs().length == 0) {
            return joinPoint.proceed();
        }
        Object inputObject = joinPoint.getArgs()[0];
        String logStr = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + ": " +
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName() +
                ", before = " + inputObject;

        Object proceed = joinPoint.proceed();

        logStr += ", after = " + inputObject;
        log.info(logStr);

        return proceed;
    }

}
