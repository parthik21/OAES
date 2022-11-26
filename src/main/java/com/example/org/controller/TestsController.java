package com.example.org.controller;

import com.example.org.bean.Exam;
import com.example.org.bean.Test;
import com.example.org.service.ExamService;
import com.example.org.service.TestService;
import com.example.org.util.DateUtil;
import com.example.org.util.authUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;


@Path("test")
public class TestsController {
    private TestService service = new TestService();
    private authUtil authUtil = new authUtil();

    @GET
    @Path("/uploaddata")
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadTest(@QueryParam("exam_id") int exam_id,
                               @QueryParam("answer_key") String answer_key,
                               @QueryParam("negative_marking") String negative_marking,
                               @QueryParam("name") String name,
                               @QueryParam("date") String date,
                               @QueryParam("token") String token){
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        String n = service.uploadTest(exam_id, answer_key, negative_marking, name, date);
        return Response.ok().entity(n).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTests(@QueryParam("token") String token){
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        List<Test> tests = service.getTests();
        return Response.ok().entity(tests).build();
    }

    @GET
    @Path("/getbyid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestById(@QueryParam("id") int id,
                                @QueryParam("token") String token ){
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Test test = service.getTestById(id);
        return Response.ok().entity(test).build();
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTest(@QueryParam("id") int id,
                               @QueryParam("token") String token){
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        String n = service.deleteTest(id);
        return Response.ok().entity(n).build();
    }

}



