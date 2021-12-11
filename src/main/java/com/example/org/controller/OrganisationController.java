package com.example.org.controller;

import com.example.org.bean.Organisation;
import com.example.org.service.OrganisationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("organisations")
public class OrganisationController {
    OrganisationService service = new OrganisationService();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganisations(){
        List<Organisation> organisations = service.getOrganisations();
        System.out.println(organisations);
        return Response.ok().entity(organisations).build();
    }

    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganisationById(@QueryParam("id") int id){
        Organisation organisation = service.getOrganisationById(id);
        System.out.println(organisation);
        return Response.ok().entity(organisation).build();
    }

}
