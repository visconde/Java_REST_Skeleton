package com.pdmfc.pj.ejb;

import com.pdmfc.pj.entities.Note;
import java.util.ArrayList;
import java.util.Date;
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
public class NoteEJB {

    @PersistenceContext(unitName = "WebRestNetPU")
    private EntityManager entityManager;
    
    public Note getNote(int id) {
        return entityManager.find(Note.class, id);
    }

    public List<Note> getNotes() {
        TypedQuery<Note> query = entityManager.createNamedQuery("Note.findAll", Note.class);
        return query.getResultList();
    }

    public Note addNew(Note note) {
        Date creationDate = new Date();
        note.setCreationDate( creationDate);
        note.setLastUpdateDate(creationDate);
        entityManager.persist(note);
        return note;
    }
    
    public List<Note> getNotes(String searchTerm) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Note> criteriaQuery = criteriaBuilder.createQuery(Note.class);
        Root<Note> note = criteriaQuery.from(Note.class);
        criteriaQuery.select(note);
       
        Predicate idioma = criteriaBuilder.like(note.<String>get("idioma"), "%" + searchTerm + "%");
        criteriaQuery.where(idioma);
        
        TypedQuery<Note> query = entityManager.createQuery(criteriaQuery);
        List<Note> result = query.getResultList();
        return result;
    }

    public Note update(Note note) {
         Date updateDate = new Date();
      
        Note q = entityManager.find(Note.class, note.getId());
        if (q != null) {
              note.setLastUpdateDate(updateDate);
           entityManager.merge(note);
        }
        return note;
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

    public Note delete(int id) {
        Note a = entityManager.find(Note.class, id);
        if (a != null) {
            entityManager.remove(a);
        }
        return a;
    }
}
