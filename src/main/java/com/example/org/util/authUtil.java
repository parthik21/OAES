package com.example.org.util;

import com.example.org.service.AuthenticationService;

import java.net.URLDecoder;

public class authUtil {
    private AuthenticationService service = new AuthenticationService();
    public boolean authenticate(String token) {
        token = URLDecoder.decode(token);
        try {
            service.authenticate(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
