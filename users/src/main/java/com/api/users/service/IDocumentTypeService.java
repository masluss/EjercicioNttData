package com.api.users.service;

import com.api.users.entity.DocumentType;

public interface IDocumentTypeService {
    void saveDocumentType(DocumentType documentType);
    DocumentType getDocumentTypeByType(String documentType);
}
