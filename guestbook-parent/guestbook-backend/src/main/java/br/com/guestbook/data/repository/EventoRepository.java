package br.com.guestbook.data.repository;

import java.util.List;

import br.com.guestbook.data.entity.Evento;
import br.com.jerimum.fw.dao.JpaCrudRepository;

public interface EventoRepository extends JpaCrudRepository<Evento, Integer> {

    List<Evento> findByCidadeIdOrderByNomeAsc(Integer idCidade);

}
