/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.prueba.fiscalias.controllers;

import com.prueba.fiscalias.models.FiscaliaModel;
import com.prueba.fiscalias.services.FiscaliaService;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author darycastillo
 */
@Path("fiscalias")
@RequestScoped
public class FiscaliasController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FiscaliasController
     */
    public FiscaliasController() {
    }

    /**
     * Retrieves representation of an instance of
     * com.prueba.fiscalias.controllers.FiscaliasController
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<FiscaliaModel> getJson() throws SQLException, ClassNotFoundException {

        ArrayList<FiscaliaModel> Lista = new FiscaliaService().getAll();
        return Lista;
//        return "fiscalias pedorras";
    }

    /**
     * PUT method for updating or creating an instance of FiscaliasController
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
