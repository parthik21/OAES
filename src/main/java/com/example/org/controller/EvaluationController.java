package com.example.org.controller;

import com.example.org.bean.Evaluation;
import com.example.org.service.EvaluationService;
import com.example.org.util.authUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("evaluation")
public class EvaluationController {

    private final EvaluationService service = new EvaluationService();
    private final authUtil authUtil = new authUtil();
    @GET
    @Path("/evaluate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeEvaluations(@QueryParam("token") String token) {
        /*if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }*/
        service.makeEvaluations();
        return Response.ok().entity("Made evaluations").build();
    }

    @GET
    @Path("/uploaddata")
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadResponses(@QueryParam("student_id") int student_id,
                               @QueryParam("test_id") int test_id,
                               @QueryParam("responses") String responses,
                                    @QueryParam("token") String token){
        if(!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        String response = service.uploadEvaluation(student_id, test_id, responses);
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvaluations(@QueryParam("student_id") int student_id,
                                   @QueryParam("exam_id") int exam_id,
                                   @QueryParam("token") String token) {
        if (!authUtil.authenticate(token)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        List<Evaluation> evaluations = service.getEvaluations(student_id, exam_id);
        return Response.ok().entity(evaluations).build();
    }
}
