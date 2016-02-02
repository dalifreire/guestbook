package br.com.guestbook.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.jerimum.fw.aop.JerimumAspectFlushEntityManager;

/**
 * Aspect to intercept and flush entity manager transactions.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Aspect
@Component("aspectGuestbookFlushEntityManager")
@Order(100)
public class AspectFlushEntityManager extends JerimumAspectFlushEntityManager {

    @AfterReturning("br.com.guestbook.aop.AspectPointcuts.service()")
    public void flush(JoinPoint jp) throws Exception {
        super.flush(jp);
    }

}
