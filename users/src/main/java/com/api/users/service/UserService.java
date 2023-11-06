package com.api.users.service;

import com.api.users.domains.request.UserRequest;
import com.api.users.domains.response.Response;
import com.api.users.entity.User;

public interface UserService {
    Response<User> getUserByTypeAndDocument(UserRequest userRequest);
}
