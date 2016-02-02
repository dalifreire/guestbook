package br.com.guestbook;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Base configuration class for service layer tests.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Configuration
@ComponentScan(basePackages = { "${basePackages}.service" })
@Import(RepositoryTestContext.class)
public abstract class ServiceTestContext extends RepositoryTestContext {

}
