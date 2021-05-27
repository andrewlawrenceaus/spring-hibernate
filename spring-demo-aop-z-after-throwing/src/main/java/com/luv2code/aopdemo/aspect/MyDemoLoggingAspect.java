package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetfortune(
            ProceedingJoinPoint joinPoint) throws Throwable {

        // print out method we are advising on
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n===> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = joinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        myLogger.info("\n====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }


    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n===> Executing @After (finally) on method: " + method);

    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n===> Executing @AfterThrowing on method: " + method);

        // log the exception
        myLogger.info("\n===> The exception is " + exc);

    }

    // let's start with an @Before advice
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        myLogger.info("\n=====>>> Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        myLogger.info("Method: " + methodSignature);

        // display method arguments


        // get args
        Object[] args = joinPoint.getArgs();

        // loop through args
        for (Object arg : args) {
            myLogger.info(arg.toString());

            if (arg instanceof Account) {
                Account account = (Account) arg;

                myLogger.info("account name: " + account.getName());
                myLogger.info("account level: " + account.getLevel());
            }
        }

    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(
            JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n===> Executing @AfterReturning on method: " + method);

        // print out result of method call
        myLogger.info("\n====>>> result is: " + result);

        // let's post-process the data
        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        myLogger.info("\n====>>> result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts
        for (Account account : result){
            // get uppercase version of name
            String upperName = account.getName().toUpperCase();

            // update the name on the account
            account.setName(upperName);
        }
    }


}
