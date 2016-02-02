package br.com.guestbook.data.repository;

import br.com.guestbook.data.entity.Usuario;
import br.com.jerimum.fw.dao.JpaCrudRepository;

public interface UsuarioRepository extends JpaCrudRepository<Usuario, Integer> {

    Usuario findByAtivoAndLoginAndSenha(Boolean ativo, String login, String senha);

}
