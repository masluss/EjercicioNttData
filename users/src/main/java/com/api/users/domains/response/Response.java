package com.api.users.domains.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Response<T>{

    private String systemData;

    private Integer status;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Response() {
        Date date = new Date();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.setSystemData(fmt.format(date));
    }
}