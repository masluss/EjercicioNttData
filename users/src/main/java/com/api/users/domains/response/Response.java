package com.api.users.domains.response;

import com.api.users.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public Response(String systemData, Integer status, String message, T data) {
        this.systemData = systemData;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}