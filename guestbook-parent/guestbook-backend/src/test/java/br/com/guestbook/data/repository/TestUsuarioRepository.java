package br.com.guestbook.data.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guestbook.RepositoryTestContext;
import br.com.guestbook.data.entity.Usuario;

/**
 * JUnit test class for CidadeRepository.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Rollback
@ContextConfiguration(classes = { RepositoryTestContext.class })
public class TestUsuarioRepository extends RepositoryTestContext {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void findByAtivoAndLoginAndSenha() {

        boolean ativo = true;
        String login = "usuario inexistente";
        String senha = "usuario inexistente";
        Usuario usuarioInexistente = usuarioRepository.findByAtivoAndLoginAndSenha(ativo, login, senha);
        assertNull(usuarioInexistente);
    }

}
