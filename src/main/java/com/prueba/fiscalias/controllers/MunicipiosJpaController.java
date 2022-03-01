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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author darycastillo
 */
public class MunicipiosJpaController implements Serializable {

    public MunicipiosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.prueba_Fiscalias_war_1PU");;

    public MunicipiosJpaController() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipios municipios) {
        if (municipios.getFiscaliasList() == null) {
            municipios.setFiscaliasList(new ArrayList<Fiscalias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos departamentoid = municipios.getDepartamentoid();
            if (departamentoid != null) {
                departamentoid = em.getReference(departamentoid.getClass(), departamentoid.getId());
                municipios.setDepartamentoid(departamentoid);
            }
            List<Fiscalias> attachedFiscaliasList = new ArrayList<Fiscalias>();
            for (Fiscalias fiscaliasListFiscaliasToAttach : municipios.getFiscaliasList()) {
                fiscaliasListFiscaliasToAttach = em.getReference(fiscaliasListFiscaliasToAttach.getClass(), fiscaliasListFiscaliasToAttach.getId());
                attachedFiscaliasList.add(fiscaliasListFiscaliasToAttach);
            }
            municipios.setFiscaliasList(attachedFiscaliasList);
            em.persist(municipios);
            if (departamentoid != null) {
                departamentoid.getMunicipiosList().add(municipios);
                departamentoid = em.merge(departamentoid);
            }
            for (Fiscalias fiscaliasListFiscalias : municipios.getFiscaliasList()) {
                Municipios oldMunicipioidOfFiscaliasListFiscalias = fiscaliasListFiscalias.getMunicipioid();
                fiscaliasListFiscalias.setMunicipioid(municipios);
                fiscaliasListFiscalias = em.merge(fiscaliasListFiscalias);
                if (oldMunicipioidOfFiscaliasListFiscalias != null) {
                    oldMunicipioidOfFiscaliasListFiscalias.getFiscaliasList().remove(fiscaliasListFiscalias);
                    oldMunicipioidOfFiscaliasListFiscalias = em.merge(oldMunicipioidOfFiscaliasListFiscalias);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipios municipios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipios persistentMunicipios = em.find(Municipios.class, municipios.getId());
            Departamentos departamentoidOld = persistentMunicipios.getDepartamentoid();
            Departamentos departamentoidNew = municipios.getDepartamentoid();
            List<Fiscalias> fiscaliasListOld = persistentMunicipios.getFiscaliasList();
            List<Fiscalias> fiscaliasListNew = municipios.getFiscaliasList();
            if (departamentoidNew != null) {
                departamentoidNew = em.getReference(departamentoidNew.getClass(), departamentoidNew.getId());
                municipios.setDepartamentoid(departamentoidNew);
            }
            List<Fiscalias> attachedFiscaliasListNew = new ArrayList<Fiscalias>();
            for (Fiscalias fiscaliasListNewFiscaliasToAttach : fiscaliasListNew) {
                fiscaliasListNewFiscaliasToAttach = em.getReference(fiscaliasListNewFiscaliasToAttach.getClass(), fiscaliasListNewFiscaliasToAttach.getId());
                attachedFiscaliasListNew.add(fiscaliasListNewFiscaliasToAttach);
            }
            fiscaliasListNew = attachedFiscaliasListNew;
            municipios.setFiscaliasList(fiscaliasListNew);
            municipios = em.merge(municipios);
            if (departamentoidOld != null && !departamentoidOld.equals(departamentoidNew)) {
                departamentoidOld.getMunicipiosList().remove(municipios);
                departamentoidOld = em.merge(departamentoidOld);
            }
            if (departamentoidNew != null && !departamentoidNew.equals(departamentoidOld)) {
                departamentoidNew.getMunicipiosList().add(municipios);
                departamentoidNew = em.merge(departamentoidNew);
            }
            for (Fiscalias fiscaliasListOldFiscalias : fiscaliasListOld) {
                if (!fiscaliasListNew.contains(fiscaliasListOldFiscalias)) {
                    fiscaliasListOldFiscalias.setMunicipioid(null);
                    fiscaliasListOldFiscalias = em.merge(fiscaliasListOldFiscalias);
                }
            }
            for (Fiscalias fiscaliasListNewFiscalias : fiscaliasListNew) {
                if (!fiscaliasListOld.contains(fiscaliasListNewFiscalias)) {
                    Municipios oldMunicipioidOfFiscaliasListNewFiscalias = fiscaliasListNewFiscalias.getMunicipioid();
                    fiscaliasListNewFiscalias.setMunicipioid(municipios);
                    fiscaliasListNewFiscalias = em.merge(fiscaliasListNewFiscalias);
                    if (oldMunicipioidOfFiscaliasListNewFiscalias != null && !oldMunicipioidOfFiscaliasListNewFiscalias.equals(municipios)) {
                        oldMunicipioidOfFiscaliasListNewFiscalias.getFiscaliasList().remove(fiscaliasListNewFiscalias);
                        oldMunicipioidOfFiscaliasListNewFiscalias = em.merge(oldMunicipioidOfFiscaliasListNewFiscalias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipios.getId();
                if (findMunicipios(id) == null) {
                    throw new NonexistentEntityException("The municipios with id " + id + " no longer exists.");
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
            Municipios municipios;
            try {
                municipios = em.getReference(Municipios.class, id);
                municipios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipios with id " + id + " no longer exists.", enfe);
            }
            Departamentos departamentoid = municipios.getDepartamentoid();
            if (departamentoid != null) {
                departamentoid.getMunicipiosList().remove(municipios);
                departamentoid = em.merge(departamentoid);
            }
            List<Fiscalias> fiscaliasList = municipios.getFiscaliasList();
            for (Fiscalias fiscaliasListFiscalias : fiscaliasList) {
                fiscaliasListFiscalias.setMunicipioid(null);
                fiscaliasListFiscalias = em.merge(fiscaliasListFiscalias);
            }
            em.remove(municipios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipios> findMunicipiosEntities() {
        return findMunicipiosEntities(true, -1, -1);
    }

    public List<Municipios> findMunicipiosEntities(int maxResults, int firstResult) {
        return findMunicipiosEntities(false, maxResults, firstResult);
    }

    private List<Municipios> findMunicipiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipios.class));
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

    public Municipios findMunicipios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipios.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipios> rt = cq.from(Municipios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
