package br.com.guestbook.service;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guestbook.ServiceTestContext;
import br.com.guestbook.data.entity.Uf;
import br.com.guestbook.dto.UfDto;
import br.com.guestbook.i18n.I18nKeys;
import br.com.jerimum.fw.i18n.I18nUtils;

/**
 * JUnit test class for UfService.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 */
@Rollback
@Transactional(propagation = Propagation.REQUIRED)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceTestContext.class })
public class TestUfService extends ServiceTestContext {

    @Autowired
    private UfService ufService;

    @Autowired
    private MessageSource messageSource;

    @Before
    public void setUp() {

    }

    /**
     * Test the CRUD functionality, relevant for all entities.
     */
    @Test
    public void testCrud() {

        UfDto uf = new UfDto();
        uf.setAtivo(true);
        uf.setNome("nome");
        uf.setSigla("SG");

        /* create the entity and check that auto generated fields are null */
        assertNotNull(uf);
        assertNull(uf.getId());

        /* insert the entity and check that auto generated fields are'nt null */
        uf = ufService.insertDto(uf);
        assertNotNull(uf);
        assertNotNull(uf.getId());

        /*
         * check that it's in the record is the same as expected (equals needs to be implemented in
         * entity class)
         */
        UfDto testUf = ufService.getDtoById(uf.getId());
        assertEquals(testUf, uf);

        /* change the entity and see the it's updated */
        uf.setNome("nome do uf alterado");
        uf = ufService.updateDto(uf);
        testUf = ufService.getDtoById(uf.getId());
        assertEquals(testUf, uf);

        try {
            /* delete the entity and check that's its deleted */
            ufService.deleteDtoById(uf.getId());
            uf = ufService.getDtoById(uf.getId());
        } catch (EmptyResultDataAccessException e) {
            String msg = I18nUtils.getMsg(this.messageSource, I18nKeys.EntityNotFound.getKey(),
                Uf.class.getSimpleName(), testUf.getId());
            Assert.assertThat(e.getMessage(), containsString(msg));
        }

    }

    @Test
    public void getUfsAtivas() {

        List<UfDto> ufs = ufService.getActiveUfs();
        Assert.assertNotNull(ufs);
        for (UfDto uf : ufs) {
            Assert.assertTrue(uf.getAtivo());
        }
    }

}
