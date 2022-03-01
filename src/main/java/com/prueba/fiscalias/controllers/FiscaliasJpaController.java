/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.fiscalias.controllers;

import com.prueba.fiscalias.controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.prueba.fiscalias.entities.Departamentos;
import com.prueba.fiscalias.entities.Fiscalias;
import com.prueba.fiscalias.entities.Municipios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author darycastillo
 */
public class FiscaliasJpaController implements Serializable {

    public FiscaliasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.prueba_Fiscalias_war_1PU");

    public FiscaliasJpaController() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fiscalias fiscalias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos departamentoid = fiscalias.getDepartamentoid();
            if (departamentoid != null) {
                departamentoid = em.getReference(departamentoid.getClass(), departamentoid.getId());
                fiscalias.setDepartamentoid(departamentoid);
            }
            Municipios municipioid = fiscalias.getMunicipioid();
            if (municipioid != null) {
                municipioid = em.getReference(municipioid.getClass(), municipioid.getId());
                fiscalias.setMunicipioid(municipioid);
            }
            em.persist(fiscalias);
            if (departamentoid != null) {
                departamentoid.getFiscaliasList().add(fiscalias);
                departamentoid = em.merge(departamentoid);
            }
            if (municipioid != null) {
                municipioid.getFiscaliasList().add(fiscalias);
                municipioid = em.merge(municipioid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fiscalias fiscalias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fiscalias persistentFiscalias = em.find(Fiscalias.class, fiscalias.getId());
            Departamentos departamentoidOld = persistentFiscalias.getDepartamentoid();
            Departamentos departamentoidNew = fiscalias.getDepartamentoid();
            Municipios municipioidOld = persistentFiscalias.getMunicipioid();
            Municipios municipioidNew = fiscalias.getMunicipioid();
            if (departamentoidNew != null) {
                departamentoidNew = em.getReference(departamentoidNew.getClass(), departamentoidNew.getId());
                fiscalias.setDepartamentoid(departamentoidNew);
            }
            if (municipioidNew != null) {
                municipioidNew = em.getReference(municipioidNew.getClass(), municipioidNew.getId());
                fiscalias.setMunicipioid(municipioidNew);
            }
            fiscalias = em.merge(fiscalias);
            if (departamentoidOld != null && !departamentoidOld.equals(departamentoidNew)) {
                departamentoidOld.getFiscaliasList().remove(fiscalias);
                departamentoidOld = em.merge(departamentoidOld);
            }
            if (departamentoidNew != null && !departamentoidNew.equals(departamentoidOld)) {
                departamentoidNew.getFiscaliasList().add(fiscalias);
                departamentoidNew = em.merge(departamentoidNew);
            }
            if (municipioidOld != null && !municipioidOld.equals(municipioidNew)) {
                municipioidOld.getFiscaliasList().remove(fiscalias);
                municipioidOld = em.merge(municipioidOld);
            }
            if (municipioidNew != null && !municipioidNew.equals(municipioidOld)) {
                municipioidNew.getFiscaliasList().add(fiscalias);
                municipioidNew = em.merge(municipioidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fiscalias.getId();
                if (findFiscalias(id) == null) {
                    throw new NonexistentEntityException("The fiscalias with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fiscalias fiscalias;
            try {
                fiscalias = em.getReference(Fiscalias.class, id);
                fiscalias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fiscalias with id " + id + " no longer exists.", enfe);
            }
            Departamentos departamentoid = fiscalias.getDepartamentoid();
            if (departamentoid != null) {
                departamentoid.getFiscaliasList().remove(fiscalias);
                departamentoid = em.merge(departamentoid);
            }
            Municipios municipioid = fiscalias.getMunicipioid();
            if (municipioid != null) {
                municipioid.getFiscaliasList().remove(fiscalias);
                municipioid = em.merge(municipioid);
            }
            em.remove(fiscalias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fiscalias> findFiscaliasEntities() {
        return findFiscaliasEntities(true, -1, -1);
    }

    public List<Fiscalias> findFiscaliasEntities(int maxResults, int firstResult) {
        return findFiscaliasEntities(false, maxResults, firstResult);
    }

    private List<Fiscalias> findFiscaliasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fiscalias.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fiscalias findFiscalias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fiscalias.class, id);
        } finally {
            em.close();
        }
    }

    public int getFiscaliasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fiscalias> rt = cq.from(Fiscalias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
