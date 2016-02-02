package br.com.guestbook.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.jerimum.fw.aop.JerimumAspectMonitor;

/**
 * Aspect for application monitoring.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Aspect
@Component("aspectGuestbookMonitor")
@Order(200)
public class AspectMonitor extends JerimumAspectMonitor {

    @Around("br.com.guestbook.aop.AspectPointcuts.service()")
    @Override
    protected Object monitor(ProceedingJoinPoint jp) throws Throwable {
        return super.monitor(jp);
    }

}

