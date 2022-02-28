/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.fiscalias.models;

/**
 *
 * @author Dary Castillo
 */
public class FiscaliaModel {

    /**
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the Departamento_id
     */
    public int getDepartamento_id() {
        return Departamento_id;
    }

    /**
     * @param Departamento_id the Departamento_id to set
     */
    public void setDepartamento_id(int Departamento_id) {
        this.Departamento_id = Departamento_id;
    }

    /**
     * @return the Municipio_id
     */
    public int getMunicipio_id() {
        return Municipio_id;
    }

    /**
     * @param Municipio_id the Municipio_id to set
     */
    public void setMunicipio_id(int Municipio_id) {
        this.Municipio_id = Municipio_id;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    private int id;
    private int Departamento_id;
    private int Municipio_id;
    private String Nombre = "valor";
    private String Descripcion = "valor";
    private String Telefono = "valor";
}
