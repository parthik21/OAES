package com.example.org.controller;

import com.example.org.bean.Users;
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
            Users users = service.authenticate(username, password);

            String token = service.issueToken(username, password);
            token += "ROLE" + users.getRole();
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
    public Response authenticateToken(@QueryParam("token") String token) {
        try {
            System.out.println(token);
            token = token.split("ROLE")[0];
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
            token = token.split("ROLE")[0];
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
                             @QueryParam("password") String password,
                             @QueryParam("role") String role) {
        try {
            if(!service.signUp(username, password, role)) {
                throw new Exception("Exception");
            }
            return Response.ok("200 OK").build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(@QueryParam("username") String username,
                             @QueryParam("password") String password) {
        try {
            if(!service.deleteUser(username, password))
                throw new Exception("user cannot be deleted!");
            return Response.ok("200 OK").build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


}
