package br.com.guestbook.i18n;

import lombok.Getter;

/**
 * Enumeration containing the keys for internalization.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
public enum I18nKeys {

    EntityNotFound("msg.entity.not.found");

    @Getter
    String key;

    private I18nKeys(String key) {
        this.key = key;
    }
}
