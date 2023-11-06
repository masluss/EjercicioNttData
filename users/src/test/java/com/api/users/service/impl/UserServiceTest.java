package com.api.users.service.impl;

import com.api.users.entity.DocumentType;
import com.api.users.entity.User;
import com.api.users.exceptionhandler.UserNotFoundException;
import com.api.users.repository.IDocumentTypeRepository;
import com.api.users.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IDocumentTypeRepository documentTypeRepository;

   @Test
    public void testGetUserByTypeAndDocument_UserOk() throws UserNotFoundException {
        String type = "C";
        String document = "23445322";

        DocumentType documentType1 = new DocumentType(1L, "C", "Cédula");
        documentTypeRepository.save(documentType1);

        User user = new User();
        user.setDocument("23445322");
        user.setFirstName("Martha");
        user.setMiddleName("Lucia");
        user.setFirstSurname("Caicedo");
        user.setSecondSurname("Morales");
        user.setAddress("Vittal");
        user.setCity("Popayán");
        user.setPhoneNumber("3002961400");
        user.setDocumentType(documentType1);

        Mockito.when(userRepository.findByDocument(document)).thenReturn(user);

        User result = userService.getUserByTypeAndDocument(type, document);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserByDocument_UserNotFound() {
        String type = "C";
        String document = "12345";

        Mockito.when(userRepository.findByDocument(document)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userService.getUserByTypeAndDocument(type, document));
    }

    @Test
    public void testGetUserByTypeAndDocument_UserNotFound() {
        String type = "CC";
        String document = "23445322";

        DocumentType documentType1 = new DocumentType(1L, "C", "Cédula");
        documentTypeRepository.save(documentType1);

        User user = new User();
        user.setDocument("23445322");
        user.setFirstName("Martha");
        user.setMiddleName("Lucia");
        user.setFirstSurname("Caicedo");
        user.setSecondSurname("Morales");
        user.setAddress("Vittal");
        user.setCity("Popayán");
        user.setPhoneNumber("3002961400");
        user.setDocumentType(documentType1);
        Mockito.when(userRepository.findByDocument(document)).thenReturn(user);

        assertThrows(UserNotFoundException.class, () -> userService.getUserByTypeAndDocument(type, document));
    }

}