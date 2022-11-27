package com.example.org.controller.test;

import com.google.gson.JsonElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExamContollerTets {

    private String exam_name;
    private String exam_start_date;
    private String exam_end_date;
    private String token;
    private final String username = "mock_username";
    private final String password = "mock_password";

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
        exam_name = "mock_exam";
        exam_start_date = "1998-06-08";
        exam_end_date = "1998-06-08";
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
    public void examUploadTest() throws Exception {
        String url = "http://localhost:8080/REST_example/api/exam/uploaddata?";
        String params = "exam_name=" + exam_name;
        params += "&exam_start_date=" + exam_start_date;
        params += "&exam_end_date=" + exam_end_date;
        params += "&token=" + token;
        url += params;
        String response = get_response_from(url);
        Assert.assertEquals(response, "New exam created!");

        url = "http://localhost:8080/REST_example/api/exam/get?";
        params += "&token=" + token;
        url += params;
        response = get_response_from(url);

        ArrayList<Map<String, String>> maps = getJsonData(url);
        Map<String, String> map = findInJson("exam_name", exam_name, maps);

        url = "http://localhost:8080/REST_example/api/exam/get_by_id?";
        params = "id=" + map.get("exam_id");
        params += "&token=" + token;
        url += params;
        response = get_response_from(url);
        Assert.assertTrue(response.contains(exam_name));

        url = "http://localhost:8080/REST_example/api/exam/delete?";
        params = "id=" + map.get("exam_id");
        params += "&token=" + token;
        url += params;
        response = get_response_from(url);
        Assert.assertEquals(response, "Exam deleted Successfully.");
    }

    private ArrayList<Map<String, String>> getJsonData(String url) throws Exception {
        String response = get_response_from(url);
        JSONParser parser = new JSONParser();
        Object object = (Object) parser.parse(response);
        System.out.print(response);
        JSONArray array = (JSONArray) object;
        System.out.print(response);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        for (Object o : array) {
            JSONObject jsonObject = (JSONObject) o;
            Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
            Map<String, String> maps = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : entries) {
                maps.put(entry.getKey(), entry.getValue() + "");
            }
            list.add(maps);
        }
        return list;
    }

    private Map<String, String> findInJson(String targetKey, String targetVal, ArrayList<Map<String, String>> maps) {
        for(Map<String, String> map :  maps) {
            if(map.get(targetKey).equals(targetVal)) {
                return map;
            }
        }
        return null;
    }
}
