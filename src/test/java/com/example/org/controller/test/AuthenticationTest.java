package com.example.org.controller.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
//import sun.jvm.hotspot.utilities.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AuthenticationTest {
    private final String username = "mock_username";
    private final String password = "mock_password";

    @Test
    public void test_login() throws Exception {
        String url = "http://localhost:8080/REST_example/api/users/register?";
        String params = "username=" + username;
        params += "&password=" + password;
        url += params;
        URL location = new URL(url);
        URLConnection yc = location.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        Assert.assertEquals(username, "mock_username");
    }
}
