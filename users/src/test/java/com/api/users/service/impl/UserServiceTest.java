package com.api.users.service.impl;

import com.api.users.domains.request.UserRequest;
import com.api.users.domains.response.Response;
import com.api.users.entity.User;
import com.api.users.exceptionhandler.UserNotFoundException;
import com.api.users.repository.IUserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @Test
    public void testGetUserByTypeAndDocument_Success() {
        UserRequest userRequest = new UserRequest("C", "23445322");

        User user = new User();
        user.setDocument("C23445322");
        user.setFirstName("Martha");
        user.setMiddleName("Lucia");
        user.setFirstSurname("Caicedo");
        user.setSecondSurname("Morales");
        user.setAddress("Vittal");
        user.setCity("Popayán");
        user.setPhoneNumber("3002961400");

        when(userRepository.findByDocument("C23445322")).thenReturn(user);

        Response<User> response = userService.getUserByTypeAndDocument(userRequest);

        assertNotNull(response);
        assertEquals("Success", response.getMessage());
        assertEquals(user, response.getData());
    }

    @Test
    public void testGetUserByTypeAndDocument_UserNotFound() {
        UserRequest userRequest = new UserRequest("C", "23445322");
        when(userRepository.findByDocument("C23445322")).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByTypeAndDocument(userRequest);
        });
    }

    @Test
    public void testGetUserByTypeAndDocument_InvalidDocumentType() {
        UserRequest userRequest = new UserRequest("X", "23445322");
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testGetUserByTypeAndDocument_InvalidDocumentFormat() {
        UserRequest userRequest = new UserRequest("C", "||1236");
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty());
    }
}