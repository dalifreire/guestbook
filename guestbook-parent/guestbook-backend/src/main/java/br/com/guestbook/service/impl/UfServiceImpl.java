package br.com.guestbook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guestbook.data.entity.Uf;
import br.com.guestbook.data.repository.UfRepository;
import br.com.guestbook.dto.UfDto;
import br.com.guestbook.service.UfService;
import br.com.jerimum.fw.dao.JpaCrudRepository;
import br.com.jerimum.fw.service.impl.AbstractCrudService;

@Service
public class UfServiceImpl extends AbstractCrudService<UfDto, Uf> implements UfService {

    @Autowired
    private UfRepository ufRepository;

    @Override
    protected Uf buildEntityFromDto(UfDto dto) {
        return new Uf(dto.getId(), dto.getSigla(), dto.getNome(), dto.getAtivo());
    }

    @Override
    protected UfDto buildDtoFromEntity(Uf entity) {
        return new UfDto(entity.getId(), entity.getSigla(), entity.getNome(), entity.getAtivo());
    }

    @Override
    protected JpaCrudRepository<Uf, Long> getRepository() {
        return ufRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UfDto> getActiveUfs() {

        return buildDtoFromEntity(ufRepository.findByAtivoOrderBySiglaAsc(true));
    }

}
