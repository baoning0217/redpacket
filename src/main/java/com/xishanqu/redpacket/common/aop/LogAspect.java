package com.xishanqu.redpacket.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * aop切面
 * @Author BaoNing 2019/7/11
 * @Aspect 表明这是一个切面类
 *
 */
@Component
@Aspect
public class LogAspect {

    /**
     * 这是一个切入点的定义
     * 第一个 * 表示方法返回任意值
     * 第二个 * 表示service包下的任意类
     * 第三个 * 表示类中的任意方法, 括号中的两个点表示方法参数任意
     */
    @Pointcut("execution(* com.xishanqu.redpacket.service.*.*(..))")
    public void pointcut(){
    }

    /**
     * 这表示一个前置通知,在目标方法执行前执行
     * @param joinPoint
     */
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.err.println("当前时间戳:"+ System.currentTimeMillis());
        System.out.println(name + "方法开始执行...");
    }

    /**
     * 这表示一个后置通知,在目标方法执行后执行
     * @param joinPoint
     */
    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.err.println("当前时间戳:"+ System.currentTimeMillis());
        System.out.println(name + "方法执行结束...");
    }

    /**
     *  表示一个返回通知,可以获取目标方法的返回值
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法返回值为:" + result);
    }

    /**
     * 表示一个异常通知
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法抛出异常了，异常是:" + ex.getMessage());
    }

    /**
     * 表示一个环绕通知
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        return proceedingJoinPoint.proceed();
    }


}
