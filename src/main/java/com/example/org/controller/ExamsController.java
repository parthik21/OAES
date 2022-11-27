package com.example.org.controller;

import com.example.org.service.ExamService;
import com.example.org.util.authUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("exam")
public class ExamsController {
    private final ExamService service = new ExamService();
    private authUtil authUtil = new authUtil();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExams(@QueryParam("token") String token){
        System.out.print(token + " passed!");
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return service.getExams();
    }

    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExamById(@QueryParam("id") int id, @QueryParam("token") String token) {
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return service.getExamById(id);
    }

    @GET
    @Path("/uploaddata")
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadExam(@QueryParam("exam_name") String exam_name,
                             @QueryParam("exam_start_date") String exam_start_date,
                             @QueryParam("exam_end_date") String exam_end_date,
                               @QueryParam("token") String token ) {
        System.out.print("passed " + token);
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return service.uploadExam(exam_name, exam_start_date, exam_end_date);
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteExam(@QueryParam("id") int id,
                               @QueryParam("token") String token){
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
       return service.deleteExam(id);
    }
}