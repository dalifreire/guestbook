package br.com.guestbook.data.repository;

import java.util.List;

import br.com.guestbook.data.entity.Visitante;
import br.com.jerimum.fw.dao.JpaCrudRepository;

public interface VisitanteRepository extends JpaCrudRepository<Visitante, Integer> {

    List<Visitante> findByEventoIdOrderByNomeAsc(Integer idEvento);

}
