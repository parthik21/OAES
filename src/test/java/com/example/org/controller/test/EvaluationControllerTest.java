package com.example.org.controller.test;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class EvaluationControllerTest {
    private String exam_id;
    private String test_id;
    private String responses;
    private String student_id;
    private final String username = "mock_username";
    private final String password = "mock_password";
    private String token;

    private void createMockUser() throws Exception{
        String url = "http://localhost:8080/REST_example/api/users/register?";
        String params = "username=" + username;
        params += "&password=" + password;
        params += "&role=" + "student";
        url += params;
        get_response_from(url);
    }

    private String getToken() throws Exception{
        String url = "http://localhost:8080/REST_example/api/users/authToken?";
        String params = "username=" + username;
        params += "&password=" + password;
        params += "&role=" + "student";
        url += params;
        return get_response_from(url);
    }


    @Before
    public void init() throws Exception{
        exam_id = "1";
        test_id = "1";
        responses = "answer";
        student_id = "1";
        createMockUser();
        token = URLEncoder.encode(getToken());
    }

    @After
    public void endTest() throws Exception {
        String url = "http://localhost:8080/REST_example/api/users/delete?";
        String params = "username=" + username;
        params += "&password=" + password;
        url += params;
        get_response_from(url);
    }


    public String get_response_from(String url) throws Exception {
        URL location = new URL(url);
        URLConnection yc = location.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        String response = "";
        while ((inputLine = in.readLine()) != null)
            response = response + inputLine;
        in.close();
        return response;
    }

    @Test
    public void evaluationUploadTest() throws Exception {
        String url = "http://localhost:8080/REST_example/api/evaluation/uploaddata?";
        String params = "test_id=" + test_id;
        params += "&student_id=" + student_id;
        params += "&responses=" + responses;
        params += "&token=" + token;
        url += params;
        String response = get_response_from(url);
        Assert.assertEquals(response, "Responses were saved successfully!");
    }

    @Test
    public void makeEvaluationTest() throws Exception {
        String url = "http://localhost:8080/REST_example/api/evaluation/evaluate?";
        String params = "";
        params += "&token=" + token;
        url += params;
        String response = get_response_from(url);
        Assert.assertEquals(response, "Made evaluations");
    }

}
