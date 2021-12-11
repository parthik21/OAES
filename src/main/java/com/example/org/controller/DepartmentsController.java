package com.example.org.controller;
import com.example.org.bean.Departments;
import com.example.org.service.DepartmentsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("departments")
public class DepartmentsController {
    DepartmentsService service  = new DepartmentsService();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartments(){
        List<Departments> departments = service.getDepartments();
        System.out.println(departments);
        return Response.ok().entity(departments).build();
    }

    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentsById(@QueryParam("id") int id){
        Departments deaparment = service.getDepartmentById(id);
        System.out.println(deaparment);
        return Response.ok().entity(deaparment).build();
    }

    @GET
    @Path("/uploaddata")
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadDepartment(@QueryParam("name") String name){
        boolean result = service.addDepartment(name);
        String n = new String("New Department Created!");
        if(!result)
            n = new String("Error Creating Department!");
        return Response.ok().entity(n).build();
    }
}
