package com.revature.caseStudy.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.revature.caseStudy.exceptions.*;

@Component
@Aspect
public class ExceptionHandlingAspect {


    @Pointcut("within(com.revature.caseStudy..*)  && execution(* *(..))")
    public void matchAllMyMethods(){}

    @Around(value = "matchAllMyMethods()") //utilizing this to inject logging advice.
    public Object catchAllManager(ProceedingJoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        try{
            return joinPoint.proceed();//attempt to execute the code.
        }
        catch(CardDeclinedException ce)
        {
            LoggingAspect.log.warn("CardDeclinedException caught at "+className+" during method "+methodName+" with arguments " + methodArgs.toString());
            throw new CardDeclinedException();
        }
        catch(CheckoutFailedException cf)
        {
            LoggingAspect.log.warn("CheckoutFailedException caught at "+className+" during method "+methodName+" with arguments " + methodArgs.toString());
            throw new CheckoutFailedException();
        }
        catch(InvalidAddressException ia)
        {
            LoggingAspect.log.warn("InvalidAddressException caught at "+className+" during method "+methodName+" with arguments " + methodArgs.toString());
            throw new InvalidAddressException();
        }
        catch(InvalidUserException iu)
        {
            LoggingAspect.log.warn("InvalidUserException caught at "+className+" during method "+methodName+" with arguments " + methodArgs.toString());
            throw new InvalidUserException();
        }
        catch(ProductRetrievalFailedException pr)
        {
            LoggingAspect.log.warn("ProductRetrievalFailedException caught at "+className+" during method "+methodName+" with arguments " + methodArgs.toString());
            throw new ProductRetrievalFailedException();
        }
        catch(RegistrationFailedException rf)
        {
            LoggingAspect.log.warn("RegistrationFailedException caught at "+className+" during method "+methodName+" with arguments " + methodArgs.toString());
            throw new RegistrationFailedException();
        }
        catch(Exception e)
        {
            LoggingAspect.log.warn("Exception caught at "+className+" during method "+methodName+" with arguments " + methodArgs.toString());
            throw new Exception();
        }

    }


}
