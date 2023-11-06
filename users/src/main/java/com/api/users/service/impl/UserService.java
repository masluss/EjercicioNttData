package com.api.users.service.impl;

import com.api.users.entity.DocumentType;
import com.api.users.entity.User;
import com.api.users.exceptionHandler.UserNotFoundException;
import com.api.users.repository.IDocumentTypeRepository;
import com.api.users.repository.IUserRepository;
import com.api.users.service.IUserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IDocumentTypeRepository documentTypeRepository;

    public UserService(IUserRepository userRepository, IDocumentTypeRepository documentTypeRepository) {
        this.userRepository = userRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    //@PostConstruct
    @Override
    public void saveUser(User user) {
        DocumentType matchingDocumentType = documentTypeRepository.findByType(user.getDocumentType().getType());

        if (matchingDocumentType != null) {
            user.setDocumentType(matchingDocumentType);
            userRepository.save(user);
        } else {
            System.out.println("error");
        }
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
