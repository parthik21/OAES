package com.example.org.controller;

import com.example.org.bean.Student;
import com.example.org.service.StudentService;
import com.example.org.util.authUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
public class StudentController  {
    private final StudentService service = new StudentService();
    private final authUtil authService = new authUtil();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents(@QueryParam("token") String token) {
        if(!authService.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        List<Student> students = service.getStudents();
        return Response.ok().entity(students).build();
    }

    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@QueryParam("id") int id,
                                   @QueryParam("token") String token) {

        if(!authService.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Student student = service.getStudentById(id);
        return Response.ok().entity(student).build();
    }

    @GET
    @Path("/uploaddata")
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadStudent(@QueryParam("first_name") String first_name,
                             @QueryParam("middle_name") String middle_name,
                             @QueryParam("last_name") String last_name,
                             @QueryParam("email") String email,
                             @QueryParam("contact_number") String contact_number,
                             @QueryParam("enrollment_number") String enrollment_number,
                             @QueryParam("address") String address,
                             @QueryParam("username") String username,
                             @QueryParam("password") String password,
                                  @QueryParam("token") String token) {

        if(!authService.authenticate(token)) {
            System.out.print("forbidde");
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        String n = service.uploadStudent(first_name, middle_name, last_name, email,
                contact_number,enrollment_number,address, username, password);
        return Response.ok().entity(n).build();
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStudent(@QueryParam("id") int id,
                                  @QueryParam("token") String token) {

        if(!authService.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        String n = service.deleteStudent(id);
        return Response.ok().entity(n).build();
    }
}
