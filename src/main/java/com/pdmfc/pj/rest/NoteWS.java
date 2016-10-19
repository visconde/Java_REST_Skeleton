package com.pdmfc.pj.rest;

import com.pdmfc.pj.ejb.NoteEJB;
import com.pdmfc.pj.entities.Note;
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
@Path("notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteWS {
    //protected final Logger logger = LogManager.getLogger(getClass());

    @EJB
    private NoteEJB noteEJB;

    @GET
    @Path("/{id}")
    public Note getNote(@PathParam("id") int id) {
        return noteEJB.getNote(id);
    }
    
    @GET
    @Path("/search/{searchTerm}/")
    public List<Note> getNotes(@PathParam("searchTerm") String searchTerm) {
       
        if (searchTerm.equals(" "))
            searchTerm = "";
        return noteEJB.getNotes(searchTerm);
    }

    @GET
    public List<Note> getNotes() {
        return noteEJB.getNotes();

    }
    
    @POST
    public Note addNew(Note note) {
        return noteEJB.addNew(note);
    }

    @PUT
    @Path("/{id}")
    public Note update(@PathParam("id") int id, Note note) {
        note.setId((long) id);
        return noteEJB.update(note);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        noteEJB.delete(id);
    }
}
