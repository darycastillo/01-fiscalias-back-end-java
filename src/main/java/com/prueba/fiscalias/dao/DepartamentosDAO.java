/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.fiscalias.dao;

import com.prueba.fiscalias.controllers.DepartamentosJpaController;
import com.prueba.fiscalias.controllers.FiscaliasJpaController;
import com.prueba.fiscalias.entities.Departamentos;
import com.prueba.fiscalias.entities.Fiscalias;

/**
 *
 * @author darycastillo
 */
public class DepartamentosDAO {

    private DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController();
    private Departamentos departamentoEntity = new Departamentos();
    private String responseMessage = "";

    public String Insert() {
        try {
            departamentoEntity.setId(Integer.SIZE);
            departamentoEntity.setNombre("Primera");
            departamentosJpaController.create(departamentoEntity);

            responseMessage = "Registro creado exitosamente";
        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());

            responseMessage = "Error al insertar: " + e.getMessage();
        }

        return responseMessage;
    }

    public String Update() {
        return null;
    }

    public String Delete() {
        return null;
    }
}
