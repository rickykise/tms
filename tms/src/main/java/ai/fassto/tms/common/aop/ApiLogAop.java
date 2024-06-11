package ai.fassto.tms.common.aop;

import ai.fassto.tms.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ApiLogAop {
    @Pointcut("@within(org.springframework.web.bind.annotation.RequestMapping)&&!@target(ai.fassto.tms.common.annotation.NoLogging)")
    public void requestMapping() {
    }

    @Around("requestMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("API request data : {}", JsonUtil.toJson(joinPoint.getArgs()));

        Object result = joinPoint.proceed();
        log.info("API response data : {}", JsonUtil.toJson(result));

        return result;
    }
}
