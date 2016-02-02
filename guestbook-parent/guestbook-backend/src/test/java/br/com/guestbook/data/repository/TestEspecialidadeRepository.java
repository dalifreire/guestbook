package br.com.guestbook.data.repository;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guestbook.RepositoryTestContext;
import br.com.guestbook.data.entity.Especialidade;

/**
 * JUnit test class for EspecialidadeRepository.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Rollback
@ContextConfiguration(classes = { RepositoryTestContext.class })
public class TestEspecialidadeRepository extends RepositoryTestContext {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Test
    public void findByAtivoOrderByNomeAsc() {

        List<Especialidade> especialidadesAtivas = especialidadeRepository.findByAtivoOrderByNomeAsc(true);
        assertNotNull(especialidadesAtivas);
        assertFalse(CollectionUtils.isNotEmpty(especialidadesAtivas));

        List<Especialidade> especialidadesInativas = especialidadeRepository.findByAtivoOrderByNomeAsc(false);
        assertNotNull(especialidadesInativas);
        assertTrue(CollectionUtils.isEmpty(especialidadesInativas));
    }

}
