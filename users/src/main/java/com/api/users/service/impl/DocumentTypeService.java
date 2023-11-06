package com.api.users.service.impl;

import com.api.users.entity.DocumentType;
import com.api.users.repository.IDocumentTypeRepository;
import com.api.users.service.IDocumentTypeService;

public class DocumentTypeService implements IDocumentTypeService {

    private final IDocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(IDocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public void saveDocumentType(DocumentType documentType) {
        documentTypeRepository.save(documentType);
    }

    @Override
    public DocumentType getDocumentTypeByType(String documentType) {
        return null;
    }
}
