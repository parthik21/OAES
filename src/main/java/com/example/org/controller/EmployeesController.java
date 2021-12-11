package com.example.org.controller;

import com.example.org.bean.Employees;
import com.example.org.service.EmployeesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("employees")
public class EmployeesController {
    EmployeesService service = new EmployeesService();
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(){
        List<Employees> employees = service.getEmployees();
        System.out.println(employees);
        return Response.ok().entity(employees).build();
    }

    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesById(@QueryParam("id") int id){
        Employees employees = service.getEmployeesById(id);
        System.out.println(employees);
        return Response.ok().entity(employees).build();
    }
}
