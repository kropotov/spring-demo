package dev.kropotov.log;

import dev.kropotov.log.annotations.LogTransformation;
import lombok.RequiredArgsConstructor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class LogTransformationAspect {
    private final Logger logger;

    @Around("@annotation(dev.kropotov.log.annotations.LogTransformation)")
    public Object logTransformation(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs().length == 0) {
            return joinPoint.proceed();
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object inputObject = joinPoint.getArgs()[0];
        String logStr = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + ": " +
                methodSignature.getDeclaringTypeName() + "." +
                methodSignature.getName() +
                ", before = " + inputObject;

        Object proceed = joinPoint.proceed();

        logStr += ", after = " + inputObject;

        logger.log(logStr, methodSignature.getMethod().getAnnotation(LogTransformation.class).value());

        return proceed;
    }

}
