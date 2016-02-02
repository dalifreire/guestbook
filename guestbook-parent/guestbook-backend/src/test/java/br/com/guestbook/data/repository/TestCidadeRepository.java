package br.com.guestbook.data.repository;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.guestbook.RepositoryTestContext;
import br.com.guestbook.data.entity.Cidade;
import br.com.guestbook.data.entity.Uf;

/**
 * JUnit test class for CidadeRepository.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Rollback
@ContextConfiguration(classes = { RepositoryTestContext.class })
public class TestCidadeRepository extends RepositoryTestContext {

    @Autowired
    private UfRepository ufRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;
    private static Validator validator;

    @Before
    public void setUp() {
        validator = validatorFactoryBean.getValidator();
    }

    /**
     * Test the CRUD functionality, relevant for all entities.
     */
    @Test
    public void testCrud() {

        Uf uf = new Uf();
        uf.setAtivo(true);
        uf.setNome("nome uf");
        uf.setSigla("SG_");
        uf = ufRepository.save(uf);

        Cidade cidade = new Cidade();
        cidade.setAtivo(true);
        cidade.setDescricao("city description");
        cidade.setNome("city name");
        cidade.setUf(uf);

        /* create the entity and check that auto generated fields are null */
        assertNotNull(cidade);
        assertNull(cidade.getPK());

        /* insert the entity and check that auto generated fields are'nt null */
        cidade = cidadeRepository.save(cidade);
        assertNotNull(cidade);
        assertNotNull(cidade.getPK());

        /*
         * check that it's in the record is the same as expected (equals needs to be implemented in
         * entity class)
         */
        Cidade testCidade = cidadeRepository.findOne(cidade.getPK());
        assertEquals(testCidade, cidade);

        /* change the entity and see the it's updated */
        cidade.setNome("updated name");
        cidade = cidadeRepository.save(cidade);
        testCidade = cidadeRepository.findOne(cidade.getPK());
        assertEquals(testCidade, cidade);

        /* delete the entity and check that's its deleted */
        cidadeRepository.delete(cidade);
        cidade = cidadeRepository.findOne(cidade.getPK());
        assertNull(cidade);

    }

    @Test
    public void fieldsValidation() {

        Uf uf = new Uf();
        uf.setAtivo(true);
        uf.setNome("nome uf");
        uf.setSigla("SG_");

        Cidade cidade = new Cidade();
        cidade.setAtivo(true);
        cidade.setDescricao("descricao cidade");
        cidade.setNome("nome cidade");
        cidade.setUf(uf);

        /*
         * ok
         */
        Set<ConstraintViolation<Cidade>> constraintViolations = validator.validate(cidade);
        Assert.assertEquals(0, constraintViolations.size());

        /*
         * 'nome' not null
         */
        cidade.setNome(null);
        constraintViolations = validator.validate(cidade);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("nome", constraintViolations.iterator().next().getPropertyPath().toString());

        /*
         * 'nome' max size
         */
        cidade.setNome(createStringWithLength(251));
        constraintViolations = validator.validate(cidade);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("nome", constraintViolations.iterator().next().getPropertyPath().toString());
        cidade.setNome("nome cidade");

        /*
         * 'descricao' max size
         */
        cidade.setDescricao(createStringWithLength(451));
        constraintViolations = validator.validate(cidade);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("descricao", constraintViolations.iterator().next().getPropertyPath().toString());
        cidade.setDescricao("descricao cidade");

        /*
         * 'uf' not null
         */
        cidade.setUf(null);;
        constraintViolations = validator.validate(cidade);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("uf", constraintViolations.iterator().next().getPropertyPath().toString());
        cidade.setUf(uf);

        /*
         * 'ativo' not null
         */
        cidade.setAtivo(null);
        constraintViolations = validator.validate(cidade);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("ativo", constraintViolations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void findByAtivoAndUfIdOrderByNomeAsc() {

        List<Cidade> cidadesAtivas = cidadeRepository.findByAtivoAndUfIdOrderByNomeAsc(true, 5L);
        assertNotNull(cidadesAtivas);
        assertFalse(CollectionUtils.isEmpty(cidadesAtivas));
        assertEquals("Abadia", cidadesAtivas.get(0).getNome());

        List<Cidade> cidadesInativas = cidadeRepository.findByAtivoAndUfIdOrderByNomeAsc(false, 5L);
        assertNotNull(cidadesInativas);
        assertTrue(CollectionUtils.isEmpty(cidadesInativas));
    }

}
