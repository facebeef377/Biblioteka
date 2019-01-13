package com.project.library;

import java.util.HashMap;
import java.util.Map;

public final class ApiResponse {

    private static final String STATUS = "Status";

    private ApiResponse() {

    }

    public static Map<String, String> responseOK() {
        Map<String, String> response = new HashMap<>();
        response.put(STATUS, "OK");
        return response;
    }

    public static Map<String, String> responseFailed() {
        Map<String, String> response = new HashMap<>();
        response.put(STATUS, "Failed");
        return response;
    }

    public static Map<String, String> responseTakenEmail() {
        Map<String, String> response = new HashMap<>();
        response.put(STATUS, "FAILED_EMAIL");
        return response;
    }

    public static Map<String, String> responseTakenLogin() {
        Map<String, String> response = new HashMap<>();
        response.put(STATUS, "FAILED_LOGIN");
        return response;
    }

}