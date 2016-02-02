package br.com.guestbook.data.repository;

import java.util.List;

import br.com.guestbook.data.entity.Especialidade;
import br.com.jerimum.fw.dao.JpaCrudRepository;

public interface EspecialidadeRepository extends JpaCrudRepository<Especialidade, Integer> {

    List<Especialidade> findByAtivoOrderByNomeAsc(Boolean ativo);

}
