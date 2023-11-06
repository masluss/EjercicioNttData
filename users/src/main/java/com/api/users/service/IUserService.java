package com.api.users.service;

import com.api.users.entity.User;
import com.api.users.exceptionhandler.UserNotFoundException;

public interface IUserService {
    User getUserByTypeAndDocument(String type, String identification) throws UserNotFoundException;
}
