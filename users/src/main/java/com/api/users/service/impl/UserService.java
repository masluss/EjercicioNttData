package com.api.users.service.impl;

import com.api.users.entity.DocumentType;
import com.api.users.entity.User;
import com.api.users.exceptionhandler.UserNotFoundException;
import com.api.users.repository.IDocumentTypeRepository;
import com.api.users.repository.IUserRepository;
import com.api.users.service.IUserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IDocumentTypeRepository documentTypeRepository;

    public UserService(IUserRepository userRepository, IDocumentTypeRepository documentTypeRepository) {
        this.userRepository = userRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @PostConstruct
    public void saveUserInizialicer() {
        DocumentType documentType1 = new DocumentType(1L, "C", "Cédula");
        DocumentType documentType2 = new DocumentType(2L, "P", "Pasaporte");

        documentTypeRepository.save(documentType1);
        documentTypeRepository.save(documentType2);

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

        userRepository.save(user);
    }


    @Override
    public User getUserByTypeAndDocument(String type, String document) throws UserNotFoundException {
        User user = userRepository.findByDocument(document);
        if (user == null) {
            throw new UserNotFoundException("The user with the entered document is not registered.");
        }
        var idDocument = user.getDocumentType().getType().equals(type);
        if(!idDocument){
            throw new UserNotFoundException("Error in the entered data.");
        }
        return user;
    }
}
