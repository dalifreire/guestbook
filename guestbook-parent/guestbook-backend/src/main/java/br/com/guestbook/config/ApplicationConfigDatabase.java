package br.com.guestbook.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Database properties configuration.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
public class ApplicationConfigDatabase implements Serializable {

    private static final long serialVersionUID = 7896624401620243915L;

    @Value("${database.hostname}")
    private String hostname;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.port}")
    private String port;

    @Value("${database.databaseName}")
    private String databaseName;

    @Value("${database.url}")
    private String url;

    @Value("${database.driverClassName}")
    private String driverClassName;

    @Value("${database.databasePlatform}")
    private String databasePlatform;

}
