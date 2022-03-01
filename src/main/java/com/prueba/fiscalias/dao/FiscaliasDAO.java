/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.fiscalias.dao;

import com.prueba.fiscalias.controllers.DepartamentosJpaController;
import com.prueba.fiscalias.controllers.FiscaliasJpaController;
import com.prueba.fiscalias.controllers.MunicipiosJpaController;
import com.prueba.fiscalias.entities.Departamentos;
import com.prueba.fiscalias.entities.Fiscalias;
import com.prueba.fiscalias.entities.Municipios;
import com.prueba.fiscalias.models.FiscaliaModel;
import com.prueba.fiscalias.models.ResponseModel;

/**
 *
 * @author darycastillo
 */
public class FiscaliasDAO {

    private FiscaliasJpaController fiscaliaJpaController = new FiscaliasJpaController();
    private Departamentos departamentoEntity = new Departamentos();
    private Municipios municipioEntity = new Municipios();
    private Fiscalias fiscaliaEntity = new Fiscalias();
    private ResponseModel<FiscaliaModel> response = new ResponseModel<FiscaliaModel>();

    public ResponseModel<FiscaliaModel> insert(FiscaliaModel fiscaliaModel) {
        try {
            departamentoEntity.setId(fiscaliaModel.getDepartamento_id());
            municipioEntity.setId(fiscaliaModel.getMunicipio_id());

            fiscaliaEntity.setId(Integer.SIZE);
            fiscaliaEntity.setNombre(fiscaliaModel.getNombre());
            fiscaliaEntity.setDepartamentoid(departamentoEntity);
            fiscaliaEntity.setMunicipioid(municipioEntity);
            fiscaliaEntity.setTelefono(fiscaliaModel.getTelefono());
            fiscaliaEntity.setDescripcion(fiscaliaModel.getDescripcion());
            fiscaliaJpaController.create(fiscaliaEntity);

            //preparar respuesta
            response.setData(fiscaliaModel);
            response.setMessage("Registro creado exitosamente");
            response.setValid(true);

        } catch (Exception e) {
            response.setData(null);
            response.setMessage("Error al insertar: " + e.getMessage());
            response.setValid(false);
        }

        return response;
    }

    public ResponseModel<FiscaliaModel> update(FiscaliaModel fiscaliaModel) {
        try {
            departamentoEntity.setId(fiscaliaModel.getDepartamento_id());
            municipioEntity.setId(fiscaliaModel.getMunicipio_id());

            fiscaliaEntity.setId(fiscaliaModel.getId());
            fiscaliaEntity.setNombre(fiscaliaModel.getNombre());
            fiscaliaEntity.setDepartamentoid(departamentoEntity);
            fiscaliaEntity.setMunicipioid(municipioEntity);
            fiscaliaEntity.setTelefono(fiscaliaModel.getTelefono());
            fiscaliaEntity.setDescripcion(fiscaliaModel.getDescripcion());
            fiscaliaJpaController.edit(fiscaliaEntity);

            //preparar respuesta
            response.setData(fiscaliaModel);
            response.setMessage("Registro actualizado exitosamente");
            response.setValid(true);

        } catch (Exception e) {
            response.setMessage("Error al actualizar: " + e.getMessage());
            response.setValid(false);
        }

        return response;
    }

    public ResponseModel Delete(int fiscaliaId) {
        try {

            fiscaliaJpaController.destroy(fiscaliaId);

            response.setMessage("Registro eliminado exitosamente");
            response.setValid(true);

        } catch (Exception e) {
            response.setMessage("Error al eliminar: " + e.getMessage());
            response.setValid(true);
        }

        return response;
    }
}
