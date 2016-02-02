package br.com.guestbook.service;

import java.util.List;

import br.com.guestbook.data.entity.Uf;
import br.com.guestbook.dto.UfDto;
import br.com.jerimum.fw.service.CrudService;

public interface UfService extends CrudService<UfDto, Uf> {

    List<UfDto> getActiveUfs();

}
