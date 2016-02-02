package br.com.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.guestbook.service.UfService;
import br.com.guestbook.util.Constants;

/**
 * REST services for UF.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@RestController
@RequestMapping(value = Constants.UF_CONTROLLER)
public class UfController {

    @Autowired
    private UfService ufService;

    @RequestMapping(value = Constants.REST_GET_ACTIVES, method = RequestMethod.GET)
    public @ResponseBody Object getActiveUfs() {

        return ufService.getActiveUfs();
    }

}
