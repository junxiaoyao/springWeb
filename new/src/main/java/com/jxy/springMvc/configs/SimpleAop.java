package com.jxy.springMvc.configs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SimpleAop {
    @Pointcut("execution(* com.jxy.springMvc.controller.*.*(..) )")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        Object o=null;
        try {
            long timeS=System.currentTimeMillis();
             o= joinPoint.proceed();
            long timeE=System.currentTimeMillis();
          String methodName=joinPoint.getSignature().getName();
            long time=timeE-timeS;
            System.out.println(methodName+"方法执行时间："+ time +" ms");
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            return o;
        }

    }

}
