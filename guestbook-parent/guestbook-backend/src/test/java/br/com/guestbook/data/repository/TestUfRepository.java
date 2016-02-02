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
import br.com.guestbook.data.entity.Uf;

/**
 * JUnit test class for UfRepository.
 * 
 * @author https://github.com/dalifreire/jerimum
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Rollback
@ContextConfiguration(classes = { RepositoryTestContext.class })
public class TestUfRepository extends RepositoryTestContext {

    @Autowired
    private UfRepository ufRepository;

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
        uf.setNome("uf name");
        uf.setSigla("SG_");

        /* create the entity and check that auto generated fields are null */
        assertNotNull(uf);
        assertNull(uf.getPK());

        /* insert the entity and check that auto generated fields are'nt null */
        uf = ufRepository.save(uf);
        assertNotNull(uf);
        assertNotNull(uf.getPK());

        /*
         * check that it's in the record is the same as expected (equals needs to be implemented in
         * entity class)
         */
        Uf testUf = ufRepository.findOne(uf.getPK());
        assertEquals(testUf, uf);

        /* change the entity and see the it's updated */
        uf.setNome("updated name");
        uf = ufRepository.save(uf);
        testUf = ufRepository.findOne(uf.getPK());
        assertEquals(testUf, uf);

        /* delete the entity and check that's its deleted */
        ufRepository.delete(uf);
        uf = ufRepository.findOne(uf.getPK());
        assertNull(uf);

    }

    @Test
    public void fieldsValidation() {

        Uf uf = new Uf();
        uf.setSigla("SG");
        uf.setNome("nome");
        uf.setAtivo(true);

        /*
         * ok
         */
        Set<ConstraintViolation<Uf>> constraintViolations = validator.validate(uf);
        Assert.assertEquals(0, constraintViolations.size());

        /*
         * 'sigla' not null
         */
        uf.setSigla(null);
        constraintViolations = validator.validate(uf);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("sigla", constraintViolations.iterator().next().getPropertyPath().toString());

        /*
         * 'sigla' max size
         */
        uf.setNome(createStringWithLength(5));
        constraintViolations = validator.validate(uf);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("sigla", constraintViolations.iterator().next().getPropertyPath().toString());
        uf.setSigla("SG");

        /*
         * 'nome' not null
         */
        uf.setNome(null);
        constraintViolations = validator.validate(uf);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("nome", constraintViolations.iterator().next().getPropertyPath().toString());

        /*
         * 'nome' max size
         */
        uf.setNome(createStringWithLength(251));
        constraintViolations = validator.validate(uf);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("nome", constraintViolations.iterator().next().getPropertyPath().toString());
        uf.setNome("nome");

        /*
         * 'ativo' not null
         */
        uf.setAtivo(null);
        constraintViolations = validator.validate(uf);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("ativo", constraintViolations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void findByAtivoOrderBySiglaAsc() {

        List<Uf> ufsAtivas = ufRepository.findByAtivoOrderBySiglaAsc(true);
        assertNotNull(ufsAtivas);
        assertFalse(CollectionUtils.isEmpty(ufsAtivas));

        List<Uf> ufsInativas = ufRepository.findByAtivoOrderBySiglaAsc(false);
        assertNotNull(ufsInativas);
        assertTrue(CollectionUtils.isEmpty(ufsInativas));
    }

}
