/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.fiscalias.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author darycastillo
 */
@Entity
@Table(name = "Fiscalias", catalog = "Fiscalias_MP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Fiscalias.findAll", query = "SELECT f FROM Fiscalias  f")})
public class Fiscalias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "Nombre", length = 50)
    private String nombre;
    @Size(max = 50)
    @Column(name = "Descripcion", length = 50)
    private String descripcion;
    @Size(max = 50)
    @Column(name = "Telefono", length = 50)
    private String telefono;
    @JoinColumn(name = "Departamento_id", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamentos departamentoid;
    @JoinColumn(name = "Municipio_id", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipios municipioid;

    public Fiscalias() {
    }

    public Fiscalias(Integer id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Departamentos getDepartamentoid() {
        return departamentoid;
    }

    public void setDepartamentoid(Departamentos departamentoid) {
        this.departamentoid = departamentoid;
    }

    public Municipios getMunicipioid() {
        return municipioid;
    }

    public void setMunicipioid(Municipios municipioid) {
        this.municipioid = municipioid;
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
        if (!(object instanceof Fiscalias)) {
            return false;
        }
        Fiscalias other = (Fiscalias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prueba.fiscalias.entities.Fiscalias[ id=" + id + " ]";
    }
    
}
