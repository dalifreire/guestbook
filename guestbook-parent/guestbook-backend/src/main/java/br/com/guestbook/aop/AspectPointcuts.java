package br.com.guestbook.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Application pointcut definitions.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Aspect
@Component("aspectGuestbookPointcuts")
public class AspectPointcuts {

    @Pointcut("execution(* br.com.guestbook.data.repository.*Repository.*(..))")
    public void repository() {
        // Do nothing, only for pointcut definition
    }

    @Pointcut("execution(* br.com.guestbook.service.impl.*ServiceImpl.*(..))")
    public void service() {
        // Do nothing, only for pointcut definition
    }

    @Pointcut("execution(* br.com.guestbook.controller.*Controller.*(..))")
    public void controller() {
        // Do nothing, only for pointcut definition
    }
}
