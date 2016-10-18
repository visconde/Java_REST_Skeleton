package com.pdmfc.pj.rest;

import com.pdmfc.pj.ejb.QueixaEJB;
import com.pdmfc.pj.entities.Queixa;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

@Stateless
@Path("queixas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QueixaWS {
    //protected final Logger logger = LogManager.getLogger(getClass());

    @EJB
    private QueixaEJB queixaEJB;

    @GET
    @Path("/{id}")
    public Queixa getQueixa(@PathParam("id") int id) {
        return queixaEJB.getQueixa(id);
    }
    
    @GET
    @Path("/search/{searchTerm}/")
    public List<Queixa> getQueixas(@PathParam("searchTerm") String searchTerm) {
        //logger.debug("Queixa term: " + searchTerm,true);
        if (searchTerm.equals(" "))
            searchTerm = "";
        return queixaEJB.getQueixas(searchTerm);
    }

    @GET
    public List<Queixa> getQueixas() {
        return queixaEJB.getQueixas();

    }
    
    @POST
    public Queixa addNew(Queixa queixa) {
        return queixaEJB.addNew(queixa);
    }

    @PUT
    @Path("/{id}")
    public Queixa update(@PathParam("id") int id, Queixa queixa) {
        queixa.setId((long) id);
        return queixaEJB.update(queixa);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        queixaEJB.delete(id);
    }
}
