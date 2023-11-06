package com.api.users.utils;

import com.api.users.domains.response.Response;
import org.springframework.http.HttpStatus;

public class Util {
    public static <T> Response<T> callResponse(String msg, Object payload) {
        Response<T> response = new Response<>();
        response.setData((T) payload);
        response.setMessage(msg);
        response.setStatus(HttpStatus.OK.value());
        return response;
    }
}
