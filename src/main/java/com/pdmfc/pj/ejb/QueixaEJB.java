package com.pdmfc.pj.ejb;

import com.pdmfc.pj.entities.Queixa;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author pdmfc
 */
@Stateless
public class QueixaEJB {

    @PersistenceContext(unitName = "WebRestNetPU")
    private EntityManager entityManager;
    
    public Queixa getQueixa(int id) {
        return entityManager.find(Queixa.class, id);
    }

    public List<Queixa> getQueixas() {
        TypedQuery<Queixa> query = entityManager.createNamedQuery("Queixa.findAll", Queixa.class);
        return query.getResultList();
    }

    public Queixa addNew(Queixa queixa) {
        entityManager.persist(queixa);
        return queixa;
    }
    
    public List<Queixa> getQueixas(String searchTerm) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Queixa> criteriaQuery = criteriaBuilder.createQuery(Queixa.class);
        Root<Queixa> queixa = criteriaQuery.from(Queixa.class);
        criteriaQuery.select(queixa);
        Predicate idioma = criteriaBuilder.like(queixa.<String>get("idioma"), "%" + searchTerm + "%");
        criteriaQuery.where(idioma);
        
        TypedQuery<Queixa> query = entityManager.createQuery(criteriaQuery);
        List<Queixa> result = query.getResultList();
        return result;
    }

    public Queixa update(Queixa queixa) {
        
        Queixa q = entityManager.find(Queixa.class, queixa.getId());
        if (q != null) {
           entityManager.merge(queixa);
        }
        return queixa;
        /*
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Queixa> criteriaQuery = criteriaBuilder.createQuery(Queixa.class);
        Root<Queixa> searchLang = criteriaQuery.from(Queixa.class);
        criteriaQuery.select(searchLang);
        Predicate idioma = criteriaBuilder.equal(searchLang.<String>get("idioma"), queixa.getIdioma());
        Predicate nivel = criteriaBuilder.equal(searchLang.<Integer>get("nivel"), queixa.getNivel());
        criteriaQuery.where(criteriaBuilder.and(idioma, nivel));
        TypedQuery<Queixa> query = entityManager.createQuery(criteriaQuery);
        if (query.getResultList().isEmpty()) {
            Queixa a = entityManager.find(Queixa.class, queixa.getId());
            if (a != null) {
                entityManager.merge(queixa);
                return queixa;
            }
        }
        */
        
        
    }

    public Queixa delete(int id) {
        Queixa a = entityManager.find(Queixa.class, id);
        if (a != null) {
            entityManager.remove(a);
        }
        return a;
    }
}
