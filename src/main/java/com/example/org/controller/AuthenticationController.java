package com.example.org.controller;

import com.example.org.service.AuthenticationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("users")
public class AuthenticationController {

    private static final AuthenticationService service = new AuthenticationService();

    @GET
    @Path("/authToken")
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticateUser(@QueryParam("username") String username,
                                     @QueryParam("password") String password) {
        try {
            if(!service.authenticate(username, password)) {
                throw new Exception("auth failed");
            }
            String token = service.issueToken(username, password);
            NewCookie cookie = new NewCookie("token", token, "/", "","",
                    -1,false);
            return Response.ok(token).cookie(cookie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    @GET
    @Path("/auth")
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticateToken(@CookieParam("token") String token) {
        try {
            System.out.println(token);
            if(!service.authenticate(token)) {
                throw new Exception("Exception");
            }
            return Response.ok("200 OK").build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(@CookieParam("token") String token) {
        try {
            System.out.println(token);
            NewCookie cookie = new NewCookie("token", "Invalid");
            return Response.ok(token).cookie(cookie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    @GET
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(@QueryParam("username") String username,
                             @QueryParam("password") String password) {
        try {
            if(!service.signUp(username, password)) {
                throw new Exception("Exception");
            }
            return Response.ok("200 OK").build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
