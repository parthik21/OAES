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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestControllerTest {
    private String test_name;
    private String exam_id;
    private String date;
    private String token;
    private String username;
    private String password;
    private String answer_key;
    private String negative_marking;

    private void createMockUser() throws Exception {
        String url = "http://localhost:8080/REST_example/api/users/register?";
        String params = "username=" + username;
        params += "&password=" + password;
        params += "&role=" + "student";
        url += params;
        get_response_from(url);
    }

    private String getToken() throws Exception {
        createMockUser();
        String url = "http://localhost:8080/REST_example/api/users/authToken?";
        String params = "username=" + username;
        params += "&password=" + password;
        params += "&role=" + "student";
        url += params;
        return get_response_from(url);
    }

    @Before
    public void init() throws Exception{
        username = "mock_username";
        password = "mock_password";
        test_name = "mock_test";
        date = "1998-06-08";
        exam_id = "1";
        answer_key = "answer";
        negative_marking = "0.3";
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
    public void testUploadTest() throws Exception {
        String url = "http://localhost:8080/REST_example/api/test/uploaddata?";
        String params = "name=" + test_name;
        params += "&date=" + date;
        params += "&exam_id=" + exam_id;
        params += "&answer_key=" + answer_key;
        params += "&token=" + token;
        params += "&negative_marking=" + negative_marking;
        url += params;
        String response = get_response_from(url);
        Assert.assertEquals(response, "New Test Created!");

        url = "http://localhost:8080/REST_example/api/test/get?";
        params += "&token=" + token;
        url += params;
        ArrayList<Map<String, String>> maps = getJsonData(url);
        Map<String, String> map = findInJson("name", test_name, maps);

        url = "http://localhost:8080/REST_example/api/test/getbyid?";
        params = "id=" + map.get("test_id");
        params += "&token=" + token;
        url += params;
        response = get_response_from(url);
        Assert.assertTrue(response.contains(test_name));

        url = "http://localhost:8080/REST_example/api/test/delete?";
        params = "id=" + map.get("test_id");
        params += "&token=" + token;
        url += params;
        response = get_response_from(url);
        Assert.assertEquals(response, "Test deleted!");
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
