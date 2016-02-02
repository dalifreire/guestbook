package br.com.guestbook.data.repository;

import java.util.List;

import br.com.guestbook.data.entity.Uf;
import br.com.jerimum.fw.dao.JpaCrudRepository;

public interface UfRepository extends JpaCrudRepository<Uf, Long> {

    List<Uf> findByAtivoOrderBySiglaAsc(Boolean ativo);

}
