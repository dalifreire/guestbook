package br.com.guestbook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaTestHelper {

    @PersistenceContext
    private EntityManager em;

    /**
     * Executes an SQL query
     * 
     * @param sql the sql query
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void executeSQL(String sql) {
        em.createNativeQuery(sql).executeUpdate();
    }

    /**
     * Executes an JPA query
     * 
     * @param sql the sql query
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void executeJPA(String sql) {
        em.createQuery(sql).executeUpdate();
    }

    /**
     * executes a JPQL query
     * 
     * @param qLquery the jqpl query
     * @param clazz the class associated with the entity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> List<T> executeJPAQuery(String qLquery, Class<T> clazz) {
        return em.createQuery(qLquery, clazz).getResultList();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void commitTransaction() {
        em.flush();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void clearPersistenceContext() {
        em.clear();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Set<List<?>> getAllRows(Class<?>... clazzs) {
        Set<List<?>> state = new HashSet<List<?>>();
        for (Class<?> clazz : clazzs) {
            state.add(em.createQuery("FROM " + clazz.getSimpleName() + " A ORDER BY A.id", clazz).getResultList());
        }
        return state;
    }

}
