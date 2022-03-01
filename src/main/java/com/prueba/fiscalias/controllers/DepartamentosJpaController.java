/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.fiscalias.controllers;

import com.prueba.fiscalias.controllers.exceptions.NonexistentEntityException;
import com.prueba.fiscalias.entities.Departamentos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.prueba.fiscalias.entities.Fiscalias;
import java.util.ArrayList;
import java.util.List;
import com.prueba.fiscalias.entities.Municipios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author darycastillo
 */
public class DepartamentosJpaController implements Serializable {

    public DepartamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.prueba_Fiscalias_war_1PU");;

    public DepartamentosJpaController() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamentos departamentos) {
        if (departamentos.getFiscaliasList() == null) {
            departamentos.setFiscaliasList(new ArrayList<Fiscalias>());
        }
        if (departamentos.getMunicipiosList() == null) {
            departamentos.setMunicipiosList(new ArrayList<Municipios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Fiscalias> attachedFiscaliasList = new ArrayList<Fiscalias>();
            for (Fiscalias fiscaliasListFiscaliasToAttach : departamentos.getFiscaliasList()) {
                fiscaliasListFiscaliasToAttach = em.getReference(fiscaliasListFiscaliasToAttach.getClass(), fiscaliasListFiscaliasToAttach.getId());
                attachedFiscaliasList.add(fiscaliasListFiscaliasToAttach);
            }
            departamentos.setFiscaliasList(attachedFiscaliasList);
            List<Municipios> attachedMunicipiosList = new ArrayList<Municipios>();
            for (Municipios municipiosListMunicipiosToAttach : departamentos.getMunicipiosList()) {
                municipiosListMunicipiosToAttach = em.getReference(municipiosListMunicipiosToAttach.getClass(), municipiosListMunicipiosToAttach.getId());
                attachedMunicipiosList.add(municipiosListMunicipiosToAttach);
            }
            departamentos.setMunicipiosList(attachedMunicipiosList);
            em.persist(departamentos);
            for (Fiscalias fiscaliasListFiscalias : departamentos.getFiscaliasList()) {
                Departamentos oldDepartamentoidOfFiscaliasListFiscalias = fiscaliasListFiscalias.getDepartamentoid();
                fiscaliasListFiscalias.setDepartamentoid(departamentos);
                fiscaliasListFiscalias = em.merge(fiscaliasListFiscalias);
                if (oldDepartamentoidOfFiscaliasListFiscalias != null) {
                    oldDepartamentoidOfFiscaliasListFiscalias.getFiscaliasList().remove(fiscaliasListFiscalias);
                    oldDepartamentoidOfFiscaliasListFiscalias = em.merge(oldDepartamentoidOfFiscaliasListFiscalias);
                }
            }
            for (Municipios municipiosListMunicipios : departamentos.getMunicipiosList()) {
                Departamentos oldDepartamentoidOfMunicipiosListMunicipios = municipiosListMunicipios.getDepartamentoid();
                municipiosListMunicipios.setDepartamentoid(departamentos);
                municipiosListMunicipios = em.merge(municipiosListMunicipios);
                if (oldDepartamentoidOfMunicipiosListMunicipios != null) {
                    oldDepartamentoidOfMunicipiosListMunicipios.getMunicipiosList().remove(municipiosListMunicipios);
                    oldDepartamentoidOfMunicipiosListMunicipios = em.merge(oldDepartamentoidOfMunicipiosListMunicipios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamentos departamentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos persistentDepartamentos = em.find(Departamentos.class, departamentos.getId());
            List<Fiscalias> fiscaliasListOld = persistentDepartamentos.getFiscaliasList();
            List<Fiscalias> fiscaliasListNew = departamentos.getFiscaliasList();
            List<Municipios> municipiosListOld = persistentDepartamentos.getMunicipiosList();
            List<Municipios> municipiosListNew = departamentos.getMunicipiosList();
            List<Fiscalias> attachedFiscaliasListNew = new ArrayList<Fiscalias>();
            for (Fiscalias fiscaliasListNewFiscaliasToAttach : fiscaliasListNew) {
                fiscaliasListNewFiscaliasToAttach = em.getReference(fiscaliasListNewFiscaliasToAttach.getClass(), fiscaliasListNewFiscaliasToAttach.getId());
                attachedFiscaliasListNew.add(fiscaliasListNewFiscaliasToAttach);
            }
            fiscaliasListNew = attachedFiscaliasListNew;
            departamentos.setFiscaliasList(fiscaliasListNew);
            List<Municipios> attachedMunicipiosListNew = new ArrayList<Municipios>();
            for (Municipios municipiosListNewMunicipiosToAttach : municipiosListNew) {
                municipiosListNewMunicipiosToAttach = em.getReference(municipiosListNewMunicipiosToAttach.getClass(), municipiosListNewMunicipiosToAttach.getId());
                attachedMunicipiosListNew.add(municipiosListNewMunicipiosToAttach);
            }
            municipiosListNew = attachedMunicipiosListNew;
            departamentos.setMunicipiosList(municipiosListNew);
            departamentos = em.merge(departamentos);
            for (Fiscalias fiscaliasListOldFiscalias : fiscaliasListOld) {
                if (!fiscaliasListNew.contains(fiscaliasListOldFiscalias)) {
                    fiscaliasListOldFiscalias.setDepartamentoid(null);
                    fiscaliasListOldFiscalias = em.merge(fiscaliasListOldFiscalias);
                }
            }
            for (Fiscalias fiscaliasListNewFiscalias : fiscaliasListNew) {
                if (!fiscaliasListOld.contains(fiscaliasListNewFiscalias)) {
                    Departamentos oldDepartamentoidOfFiscaliasListNewFiscalias = fiscaliasListNewFiscalias.getDepartamentoid();
                    fiscaliasListNewFiscalias.setDepartamentoid(departamentos);
                    fiscaliasListNewFiscalias = em.merge(fiscaliasListNewFiscalias);
                    if (oldDepartamentoidOfFiscaliasListNewFiscalias != null && !oldDepartamentoidOfFiscaliasListNewFiscalias.equals(departamentos)) {
                        oldDepartamentoidOfFiscaliasListNewFiscalias.getFiscaliasList().remove(fiscaliasListNewFiscalias);
                        oldDepartamentoidOfFiscaliasListNewFiscalias = em.merge(oldDepartamentoidOfFiscaliasListNewFiscalias);
                    }
                }
            }
            for (Municipios municipiosListOldMunicipios : municipiosListOld) {
                if (!municipiosListNew.contains(municipiosListOldMunicipios)) {
                    municipiosListOldMunicipios.setDepartamentoid(null);
                    municipiosListOldMunicipios = em.merge(municipiosListOldMunicipios);
                }
            }
            for (Municipios municipiosListNewMunicipios : municipiosListNew) {
                if (!municipiosListOld.contains(municipiosListNewMunicipios)) {
                    Departamentos oldDepartamentoidOfMunicipiosListNewMunicipios = municipiosListNewMunicipios.getDepartamentoid();
                    municipiosListNewMunicipios.setDepartamentoid(departamentos);
                    municipiosListNewMunicipios = em.merge(municipiosListNewMunicipios);
                    if (oldDepartamentoidOfMunicipiosListNewMunicipios != null && !oldDepartamentoidOfMunicipiosListNewMunicipios.equals(departamentos)) {
                        oldDepartamentoidOfMunicipiosListNewMunicipios.getMunicipiosList().remove(municipiosListNewMunicipios);
                        oldDepartamentoidOfMunicipiosListNewMunicipios = em.merge(oldDepartamentoidOfMunicipiosListNewMunicipios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamentos.getId();
                if (findDepartamentos(id) == null) {
                    throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.");
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
            Departamentos departamentos;
            try {
                departamentos = em.getReference(Departamentos.class, id);
                departamentos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.", enfe);
            }
            List<Fiscalias> fiscaliasList = departamentos.getFiscaliasList();
            for (Fiscalias fiscaliasListFiscalias : fiscaliasList) {
                fiscaliasListFiscalias.setDepartamentoid(null);
                fiscaliasListFiscalias = em.merge(fiscaliasListFiscalias);
            }
            List<Municipios> municipiosList = departamentos.getMunicipiosList();
            for (Municipios municipiosListMunicipios : municipiosList) {
                municipiosListMunicipios.setDepartamentoid(null);
                municipiosListMunicipios = em.merge(municipiosListMunicipios);
            }
            em.remove(departamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamentos> findDepartamentosEntities() {
        return findDepartamentosEntities(true, -1, -1);
    }

    public List<Departamentos> findDepartamentosEntities(int maxResults, int firstResult) {
        return findDepartamentosEntities(false, maxResults, firstResult);
    }

    private List<Departamentos> findDepartamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamentos.class));
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

    public Departamentos findDepartamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamentos> rt = cq.from(Departamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
