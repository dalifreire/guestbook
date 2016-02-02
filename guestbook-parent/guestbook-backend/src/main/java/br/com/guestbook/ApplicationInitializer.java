package br.com.guestbook;

import org.springframework.core.annotation.Order;

import br.com.jerimum.fw.config.JerimumWebApplicationInitializer;

/**
 * Startup spring application class.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Order(1)
public class ApplicationInitializer extends JerimumWebApplicationInitializer {

    @Override
    public Class<?> getConfigurationClass() {
        return Application.class;
    }

}
