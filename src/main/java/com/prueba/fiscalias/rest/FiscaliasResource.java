/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.prueba.fiscalias.rest;

import com.prueba.fiscalias.dao.FiscaliasDAO;
import com.prueba.fiscalias.entities.Fiscalias;
import com.prueba.fiscalias.models.FiscaliaModel;
import com.prueba.fiscalias.models.ResponseModel;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.status;

/**
 * REST Web Service
 *
 * @author darycastillo
 */
@Path("fiscalias")
@RequestScoped
public class FiscaliasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FiscaliasResource
     */
    public FiscaliasResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(FiscaliaModel fiscaliaModel) throws SQLException, ClassNotFoundException {

        FiscaliasDAO fiscaliasDAO = new FiscaliasDAO();
        ResponseModel<FiscaliaModel> response = fiscaliasDAO.insert(fiscaliaModel);

        return Response
                .ok(response).status(201)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(FiscaliaModel fiscaliaModel) throws SQLException, ClassNotFoundException {

        FiscaliasDAO fiscaliasDAO = new FiscaliasDAO();
        ResponseModel<FiscaliaModel> response = fiscaliasDAO.update(fiscaliaModel);

        return Response
                .ok(response)
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response delete(@PathParam("id") int fiscaliaId) throws SQLException, ClassNotFoundException {

        FiscaliasDAO fiscaliasDAO = new FiscaliasDAO();
        ResponseModel<FiscaliaModel> response = fiscaliasDAO.Delete(fiscaliaId);

        return Response
                .ok(response)
                .build();
    }

}
