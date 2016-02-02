package br.com.guestbook.data.repository;

import java.util.List;

import br.com.guestbook.data.entity.Cidade;
import br.com.jerimum.fw.dao.JpaCrudRepository;

public interface CidadeRepository extends JpaCrudRepository<Cidade, Integer> {

    List<Cidade> findByAtivoAndUfIdOrderByNomeAsc(Boolean ativo, Long idUf);

}
