package com.api.users.service.impl;

import com.api.users.domains.request.UserRequest;
import com.api.users.domains.response.Response;
import com.api.users.entity.User;
import com.api.users.exceptionhandler.UserNotFoundException;
import com.api.users.repository.IUserRepository;
import com.api.users.service.UserService;
import com.api.users.utils.Util;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void saveUserInizialicer() {
        User user = new User();

        user.setDocument("C23445322");
        user.setFirstName("Martha");
        user.setMiddleName("Lucia");
        user.setFirstSurname("Caicedo");
        user.setSecondSurname("Morales");
        user.setAddress("Vittal");
        user.setCity("Popayán");
        user.setPhoneNumber("3002961400");
        userRepository.save(user);
    }

    @Override
    public Response<User> getUserByTypeAndDocument(UserRequest userRequest) {
        User user = userRepository.findByDocument(userRequest.getDocumentType().concat(userRequest.getDocument()));
        if(user == null){
            throw new UserNotFoundException("Error in the entered data.");
        }
        return Util.callResponse("Success", user);
    }
}
