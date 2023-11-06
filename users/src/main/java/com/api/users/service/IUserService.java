package com.api.users.service;

import com.api.users.entity.User;
import com.api.users.exceptionHandler.UserNotFoundException;

public interface IUserService {
    void saveUser(User user);
    User getUserByTypeAndDocument(String type, String identification) throws UserNotFoundException;
}
