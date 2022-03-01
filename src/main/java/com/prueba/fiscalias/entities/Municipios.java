/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.fiscalias.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author darycastillo
 */
@Entity
@Table(name = "Municipios", catalog = "Fiscalias_MP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Municipios.findAll", query = "SELECT m FROM Municipios m")})
public class Municipios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;
    @OneToMany(mappedBy = "municipioid", fetch = FetchType.LAZY)
    private List<Fiscalias> fiscaliasList;
    @JoinColumn(name = "Departamento_id", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamentos departamentoid;

    public Municipios() {
    }

    public Municipios(Integer id) {
        this.id = id;
    }

    public Municipios(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Fiscalias> getFiscaliasList() {
        return fiscaliasList;
    }

    public void setFiscaliasList(List<Fiscalias> fiscaliasList) {
        this.fiscaliasList = fiscaliasList;
    }

    public Departamentos getDepartamentoid() {
        return departamentoid;
    }

    public void setDepartamentoid(Departamentos departamentoid) {
        this.departamentoid = departamentoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipios)) {
            return false;
        }
        Municipios other = (Municipios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prueba.fiscalias.entities.Municipios[ id=" + id + " ]";
    }
    
}
